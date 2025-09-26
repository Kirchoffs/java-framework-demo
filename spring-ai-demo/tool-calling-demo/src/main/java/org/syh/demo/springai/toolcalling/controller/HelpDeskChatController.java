package org.syh.demo.springai.toolcalling.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.syh.demo.springai.toolcalling.tools.HelpDeskTools;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

import java.util.Map;

@RestController
@RequestMapping("/api/tools")
public class HelpDeskChatController {
    private final ChatClient chatClient;
    private final HelpDeskTools helpDeskTools;

    public HelpDeskChatController(@Qualifier("helpDeskChatClient") ChatClient chatClient, HelpDeskTools helpDeskTools) {
        this.chatClient = chatClient;
        this.helpDeskTools = helpDeskTools;
    }

    @GetMapping("/help-desk")
    public ResponseEntity<String> helpDesk(
        @RequestHeader("username") String username,
        @RequestParam("message") String message
    ) {
        String answer = chatClient.prompt()
            .advisors(advisorSpec -> advisorSpec.param(CONVERSATION_ID, username))
            .user(message)
            .tools(helpDeskTools)
            .toolContext(Map.of("username", username))
            .call().content();
        return ResponseEntity.ok(answer);
    }
}
