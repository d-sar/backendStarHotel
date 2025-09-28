package com.StarHotel.StarHotel.dto;

import com.StarHotel.StarHotel.entity.Booking;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDTO {
   private Long id;
    private String roomType;
    private BigDecimal roomPrice;
    private String RoomPhotoUrl;
    private String roomDescription;
    private List<BookingDTO> bookings;


}
