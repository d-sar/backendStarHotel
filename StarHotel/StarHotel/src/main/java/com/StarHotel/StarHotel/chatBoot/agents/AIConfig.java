package com.StarHotel.StarHotel.chatBoot.agents;

import com.StarHotel.StarHotel.chatBoot.tools.AITools;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.List;
import java.util.function.Function;

@Configuration
public class AIConfig {

    @Bean
    public ChatMemory chatMemory() {
        return new InMemoryChatMemory();
    }

    @Bean
    @Description("Get all available rooms in the hotel with their details including room type, price, and description")
    public Function<AITools.EmptyRequest, List<AITools.RoomInfo>> getAllRooms(AITools aiTools) {
        return aiTools.getAllRooms();
    }

    @Bean
    @Description("Get rooms by type. Valid types include: SINGLE, DOUBLE, DELUXE, SUITE")
    public Function<AITools.RoomTypeRequest, List<AITools.RoomInfo>> getRoomsByType(AITools aiTools) {
        return aiTools.getRoomsByType();
    }

    @Bean
    @Description("Get all currently available rooms that are not booked")
    public Function<AITools.EmptyRequest, List<AITools.RoomInfo>> getAvailableRooms(AITools aiTools) {
        return aiTools.getAvailableRooms();
    }
}