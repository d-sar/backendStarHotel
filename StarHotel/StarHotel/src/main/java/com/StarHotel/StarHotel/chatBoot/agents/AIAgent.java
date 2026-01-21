package com.StarHotel.StarHotel.chatBoot.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

@Component
public class AIAgent {

    private ChatClient chatClient;

    public AIAgent(ChatClient.Builder builder, ChatMemory memory) {
        this.chatClient = builder
                .defaultSystem("""
                        You are a helpful hotel assistant for StarHotel. 
                        
                        You have access to real-time database functions to check:
                        - All rooms in the hotel
                        - Rooms filtered by type (SINGLE, DOUBLE, DELUXE, SUITE)
                        - Currently available rooms
                        
                        IMPORTANT: When users ask about rooms, availability, or pricing, 
                        you MUST call the appropriate function to get current data from the database.
                        Never make up room information.
                        
                        Be friendly, professional, and helpful.
                        """)
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(memory).build())
                .defaultFunctions("getAllRooms", "getRoomsByType", "getAvailableRooms")
                .build();
    }

    public Flux<String> askAgent(String query) {
        // Utiliser call() au lieu de stream() pour éviter le bug de NullPointerException
        // dans Spring AI 1.0.0-M5 avec Ollama
        String response = chatClient.prompt()
                .user(query)
                .call()
                .content();
        
        // Convertir la réponse en Flux pour compatibilité
        return Flux.just(response);
    }
}