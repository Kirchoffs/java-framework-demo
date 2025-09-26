package org.syh.demo.springai.rag.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    private final ChatClient chatClient;
    private final EmbeddingModel embeddingModel;

    public TestController(OpenAiChatModel openAiChatModel, EmbeddingModel embeddingModel) {
        this.chatClient = ChatClient.create(openAiChatModel);
        this.embeddingModel = embeddingModel;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam("message") String message) {
        return chatClient.prompt(message).call().content();
    }

    @GetMapping("/embedding")
    public float[] embedding(@RequestParam("message") String message) {
        return embeddingModel.embed(message);
    }
}
