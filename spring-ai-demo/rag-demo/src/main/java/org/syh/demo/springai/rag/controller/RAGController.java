package org.syh.demo.springai.rag.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
@RequestMapping("/api/rag")
public class RAGController {
    private final ChatClient chatClient;
    private final ChatClient chatClientWithRAGAdvisor;
    private final ChatClient chatClientWithWebSearchAdvisor;
    private final VectorStore vectorStore;

    @Value("classpath:/promptTemplates/funFactsSystemPromptTemplate.st")
    private Resource funFactsSystemPromptTemplate;

    @Value("classpath:/promptTemplates/ragPaperDocumentSystemPromptTemplate.st")
    private Resource ragPaperDocumentSystemPromptTemplate;

    public RAGController(
        @Qualifier("chatMemoryChatClient") ChatClient chatClient, 
        @Qualifier("chatMemoryChatClientWithRAGAdvisor") ChatClient chatClientWithRAGAdvisor,
        @Qualifier("chatMemoryChatClientWithWebSearchAdvisor") ChatClient chatClientWithWebSearchAdvisor,
        VectorStore vectorStore) {
        this.chatClient = chatClient;
        this.chatClientWithRAGAdvisor = chatClientWithRAGAdvisor;
        this.chatClientWithWebSearchAdvisor = chatClientWithWebSearchAdvisor;
        this.vectorStore = vectorStore;
    }

    @RequestMapping("/fun-facts")
    public ResponseEntity<String> funFactsChat(
        @RequestHeader("username") String username,
        @RequestParam("message") String message) {
        SearchRequest searchRequest = SearchRequest.builder().query(message).topK(3).similarityThreshold(0.36).build();
        List<Document> similarDocuments = vectorStore.similaritySearch(searchRequest);
        String similarContext = similarDocuments.stream()
            .map(Document::getText)
            .collect(Collectors.joining(System.lineSeparator()));
        
        String answer = chatClient.prompt()
            .system(
                promptSystemSpec -> promptSystemSpec.text(funFactsSystemPromptTemplate).param("documents", similarContext)
            )
            .advisors(
                advisorSpec -> advisorSpec.param(CONVERSATION_ID, username)
            )
            .user(message)
            .call()
            .content();

        return ResponseEntity.ok(answer);
    }

    @RequestMapping("/rag-paper")
    public ResponseEntity<String> ragPaperChat(
        @RequestHeader("username") String username,
        @RequestParam("message") String message) {
        SearchRequest searchRequest = SearchRequest.builder().query(message).topK(3).similarityThreshold(0.36).build();
        List<Document> similarDocuments = vectorStore.similaritySearch(searchRequest);
        String similarContext = similarDocuments.stream()
            .map(Document::getText)
            .collect(Collectors.joining(System.lineSeparator()));
        
        String answer = chatClient.prompt()
            .system(
                promptSystemSpec -> promptSystemSpec.text(ragPaperDocumentSystemPromptTemplate).param("documents", similarContext)
            )
            .advisors(
                advisorSpec -> advisorSpec.param(CONVERSATION_ID, username)
            )
            .user(message)
            .call()
            .content();

        return ResponseEntity.ok(answer);
    }

    @RequestMapping("/rag-paper-with-advisor")
    public ResponseEntity<String> ragPaperChatWithAdvisor(
        @RequestHeader("username") String username,
        @RequestParam("message") String message) {        
        String answer = chatClientWithRAGAdvisor.prompt()
            .advisors(
                advisorSpec -> advisorSpec.param(CONVERSATION_ID, username)
            )
            .user(message)
            .call()
            .content();

        return ResponseEntity.ok(answer);
    }

    @RequestMapping("/web-search")
    public ResponseEntity<String> webSearchChat(
        @RequestHeader("username") String username,
        @RequestParam("message") String message) {        
        String answer = chatClientWithWebSearchAdvisor.prompt()
            .advisors(
                advisorSpec -> advisorSpec.param(CONVERSATION_ID, username)
            )
            .user(message)
            .call()
            .content();

        return ResponseEntity.ok(answer);
    }
}
