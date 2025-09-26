package com.StarHotel.StarHotel.dto;

import com.StarHotel.StarHotel.entity.Booking;
import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Long id;
    private String email;
    private String phoneNumber;
    private String name;
    private String role;
    private List<BookingDTO> bookings = new ArrayList<>();

}
