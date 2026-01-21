package com.StarHotel.StarHotel.controller;

import com.StarHotel.StarHotel.chatBoot.agents.AIAgent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {
    private AIAgent aiAgent;
    public ChatController(AIAgent aiAgent) {
        this.aiAgent = aiAgent;
    }
    @GetMapping(value = "/chat")
    public Flux<String> chat(@RequestParam(name = "query") String query) {
        return aiAgent.askAgent(query);
    }
}
