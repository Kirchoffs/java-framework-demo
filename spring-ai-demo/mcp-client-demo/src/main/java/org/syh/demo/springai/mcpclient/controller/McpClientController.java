package org.syh.demo.springai.mcpclient.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mcp-client")
public class McpClientController {
    private static final Logger LOGGER = LoggerFactory.getLogger(McpClientController.class);

    private final ChatClient chatClient;

    public McpClientController(ChatClient.Builder chatClientBuilder, ToolCallbackProvider toolCallbackProvider) {
        ChatOptions options = ChatOptions.builder()
            .temperature(1.0)
            .build();

        LOGGER.info("===== Inspecting ToolCallbackProvider Tools =====");
        
        Arrays.stream(toolCallbackProvider.getToolCallbacks()).forEach(callback -> {
            LOGGER.info("Tool Name: {}", callback.getToolDefinition().name());
            LOGGER.debug("Description: {}", callback.getToolDefinition().description()); 
        });
        
        LOGGER.info("===== Inspection Finished. Total tools found: {} =====", toolCallbackProvider.getToolCallbacks().length);

        this.chatClient = chatClientBuilder
            .defaultOptions(options)
            .defaultToolCallbacks(toolCallbackProvider)
            .defaultAdvisors(new SimpleLoggerAdvisor())
            .build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam("message") String message) {
        return chatClient.prompt().user(message).call().content();
    }
}
