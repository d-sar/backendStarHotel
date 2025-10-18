package com.StarHotel.StarHotel.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Base64;
import java.util.function.Function;

@Service
public class JWTUtils {
    private static final long EXPIRATION_TIME = 1000 * 60 * 24 * 7; //FOR 7 DAYs
    private  final SecretKey key;
    public JWTUtils() {
        String secreteString ="N2Q4YjY2NzI1NzYzNjcyNzkyNmI4N2U3ZDY1NmM3NjE2NDZhNzg4NjM2Nzc=";
        byte[] KeyBytes = Base64.getDecoder().decode(secreteString);
        this.key = new SecretKeySpec(KeyBytes, "HmacSHA256");
    }
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }
    public String extractUsername(String token) {
        return  extractClaims(token, Claims::getSubject);
    }
    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction ) {
        return  claimsTFunction.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
    }
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    private boolean isTokenExpired(String token) {
        return extractClaims(token,Claims::getExpiration).before(new Date(System.currentTimeMillis()));
    }


}
