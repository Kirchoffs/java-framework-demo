package org.syh.demo.springai.toolcalling.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.syh.demo.springai.toolcalling.tools.TimeTools;

@Configuration
public class TimeChatClientConfig {
    @Bean("timeChatClient")
    public ChatClient timeChatClient(
        ChatClient.Builder chatClientBuilder,
        ChatMemory chatMemory,
        TimeTools timeTools) {
        Advisor loggerAdvisor = new SimpleLoggerAdvisor();
        Advisor memoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();

        ChatOptions chatOptions = ChatOptions.builder()
            .temperature(1.0)
            .build();

        return chatClientBuilder
            .defaultOptions(chatOptions)
            .defaultTools(timeTools)
            .defaultAdvisors(loggerAdvisor, memoryAdvisor)
            .build();
    }
}
