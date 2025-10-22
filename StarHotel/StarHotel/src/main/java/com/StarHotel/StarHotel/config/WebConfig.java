package com.StarHotel.StarHotel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Ici, "file:uploads/rooms/" correspond au dossier physique où tu stockes les images
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}
