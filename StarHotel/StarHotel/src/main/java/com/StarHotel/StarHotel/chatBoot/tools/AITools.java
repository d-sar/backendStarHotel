package com.StarHotel.StarHotel.chatBoot.tools;

import com.StarHotel.StarHotel.entity.Room;
import com.StarHotel.StarHotel.repo.RoomRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class AITools {

    @Autowired
    private RoomRepository roomRepository;

    // DTO pour éviter les problèmes de lazy initialization
    public record RoomInfo(
            Long id, 
            String roomType, 
            BigDecimal roomPrice, 
            String roomDescription, 
            boolean isAvailable
    ) {}

    // Request DTOs for function parameters
    public record RoomTypeRequest(
            @JsonProperty(required = true) String roomType
    ) {}

    public record EmptyRequest() {}

    public Function<EmptyRequest, List<RoomInfo>> getAllRooms() {
        return input -> roomRepository.findAll().stream()
                .map(this::toRoomInfo)
                .collect(Collectors.toList());
    }

    public Function<RoomTypeRequest, List<RoomInfo>> getRoomsByType() {
        return request -> roomRepository.findByRoomType(request.roomType()).stream()
                .map(this::toRoomInfo)
                .collect(Collectors.toList());
    }

    public Function<EmptyRequest, List<RoomInfo>> getAvailableRooms() {
        return input -> roomRepository.findAll().stream()
                .filter(room -> room.getBookings() == null || room.getBookings().isEmpty())
                .map(this::toRoomInfo)
                .collect(Collectors.toList());
    }

    private RoomInfo toRoomInfo(Room room) {
        boolean isAvailable = room.getBookings() == null || room.getBookings().isEmpty();
        return new RoomInfo(
                room.getId(),
                room.getRoomType(),
                room.getRoomPrice(),
                room.getRoomDescription(),
                isAvailable
        );
    }
}