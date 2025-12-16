package com.example.agentia.controller;


import com.example.agentia.tools.ProductTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private ChatClient chatClient;
    public ChatController(ChatClient.Builder builder, ChatMemory memory, ProductTools productTools)
    {
        this.chatClient = builder
                .defaultTools(productTools)
             //pour toute l'application   .defaultSystem("")
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(memory).build())
                .build();
    }

    @GetMapping("/ask")
    public reactor.core.publisher.Flux<String> ask(@RequestParam String message) {
        return chatClient
                .prompt()
                .user(message)
                .stream()
                .content();

    }
}
