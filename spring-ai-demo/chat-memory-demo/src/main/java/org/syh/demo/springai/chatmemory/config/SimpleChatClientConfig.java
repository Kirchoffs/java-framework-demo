package org.syh.demo.springai.chatmemory.config;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.syh.demo.springai.chatmemory.advisors.TokenUsageAuditAdvisor;

@Configuration
public class SimpleChatClientConfig {
    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        ChatOptions chatOptions = ChatOptions.builder()
            .temperature(0.75)
            .build();

        return chatClientBuilder
            .defaultOptions(chatOptions)
            .defaultAdvisors(List.of(
                new SimpleLoggerAdvisor(),
                new TokenUsageAuditAdvisor()
            ))
            .defaultSystem(
                """
                    You are a happy chatbot.
                    Every time you answer a question, you should start with "Haha!".
                """
            )
            .build();
    }
}
