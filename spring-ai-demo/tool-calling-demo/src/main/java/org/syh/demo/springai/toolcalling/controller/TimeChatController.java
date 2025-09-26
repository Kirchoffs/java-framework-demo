package org.syh.demo.springai.toolcalling.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
@RequestMapping("/api/tools")
public class TimeChatController {
    private final ChatClient timeChatClient;

    public TimeChatController(@Qualifier("timeChatClient") ChatClient timeChatClient) {
        this.timeChatClient = timeChatClient;
    }

    @GetMapping("/local-time")
    public ResponseEntity<String> localTime(@RequestHeader("username") String username, @RequestParam("message") String message) {
        String answer = timeChatClient.prompt()
            .advisors(advisorSpec -> advisorSpec.param(CONVERSATION_ID, username))
            .user(message)
            .call()
            .content();
        
        return ResponseEntity.ok(answer);
    }
}
