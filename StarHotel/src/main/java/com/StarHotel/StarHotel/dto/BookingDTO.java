package com.StarHotel.StarHotel.dto;

import com.StarHotel.StarHotel.entity.Room;
import com.StarHotel.StarHotel.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDate;
@Data
@JsonInclude (JsonInclude.Include.NON_NULL)
public class BookingDTO {
    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numOfAdults;
    private int numOfChildren;
    private int totalNumberOfGuests;
    private String bookingConfirmationCode ;
    private UserDTO user;
     private RoomDTO room;
}
