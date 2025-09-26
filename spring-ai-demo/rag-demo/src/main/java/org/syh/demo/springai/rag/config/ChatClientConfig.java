package org.syh.demo.springai.rag.config;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.syh.demo.springai.rag.advisors.TokenUsageAuditAdvisor;
import org.syh.demo.springai.rag.clients.search.SearchClientInterface;
import org.syh.demo.springai.rag.retrievers.WebSearchDocumentRetriever;

@Configuration
public class ChatClientConfig {
    @Bean("chatMemoryChatClient")
    public ChatClient chatClient(@Qualifier("openAiChatModel") ChatModel chatModel, ChatMemory chatMemory) {
        Advisor loggerAdvisor = new SimpleLoggerAdvisor();
        Advisor memoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        Advisor tokenUsageAdvisor = new TokenUsageAuditAdvisor();

        ChatOptions chatOptions = ChatOptions.builder()
            .temperature(1.0)
            .build();

        return ChatClient.builder(chatModel)
            .defaultOptions(chatOptions)
            .defaultAdvisors(List.of(loggerAdvisor, memoryAdvisor, tokenUsageAdvisor))
            .build();
    }

    @Bean("chatMemoryChatClientWithRAGAdvisor")
    public ChatClient chatClientWithRAGAdvisor(@Qualifier("openAiChatModel") ChatModel chatModel, ChatMemory chatMemory, RetrievalAugmentationAdvisor ragAdvisor) {
        Advisor loggerAdvisor = new SimpleLoggerAdvisor();
        Advisor memoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        Advisor tokenUsageAdvisor = new TokenUsageAuditAdvisor();

        ChatOptions chatOptions = ChatOptions.builder()
            .temperature(1.0)
            .build();

        return ChatClient.builder(chatModel)
            .defaultOptions(chatOptions)
            .defaultAdvisors(List.of(loggerAdvisor, memoryAdvisor, tokenUsageAdvisor, ragAdvisor))
            .build();
    }

    @Bean("chatMemoryChatClientWithWebSearchAdvisor")
    public ChatClient chatClientWithWebSearch(@Qualifier("openAiChatModel") ChatModel chatModel, ChatMemory chatMemory, SearchClientInterface searchClient) {
        Advisor loggerAdvisor = new SimpleLoggerAdvisor();
        Advisor memoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        Advisor tokenUsageAdvisor = new TokenUsageAuditAdvisor();
        Advisor webSearchAdvisor = RetrievalAugmentationAdvisor.builder()
            .documentRetriever(
                WebSearchDocumentRetriever.builder()
                    .searchClient(searchClient)
                    .maxResults(3)
                    .build()
            )
            .build();

        return ChatClient.builder(chatModel)
            .defaultOptions(ChatOptions.builder().temperature(1.0).build())
            .defaultAdvisors(List.of(loggerAdvisor, memoryAdvisor, tokenUsageAdvisor, webSearchAdvisor))
            .build();
    }
}
