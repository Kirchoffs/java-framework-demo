package org.syh.demo.springai.rag.clients.search.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.syh.demo.springai.rag.clients.search.SearchClientInterface;
import org.syh.demo.springai.rag.clients.search.SearchResponsePayload;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@Service
public class TavilyClient implements SearchClientInterface {
    private static final String ADVANCED_SEARCH_MODE = "advanced";

    private final RestClient restClient;
    private final String searchEndpoint;
    private final String apiKey;

    public TavilyClient(
        RestClient.Builder clientBuilder,
        @Value("${tavily.endpoint}") String searchEndpoint,
        @Value("${tavily.api-key}") String apiKey) {
        this.restClient = clientBuilder
            .baseUrl(searchEndpoint)
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
            .build();
        this.searchEndpoint = searchEndpoint;
        this.apiKey = apiKey;
    }

    public SearchResponsePayload search(String query, int resultLimit) {
        return restClient
            .post()
            .body(new TavilyRequestPayload(query, ADVANCED_SEARCH_MODE, resultLimit))
            .retrieve()
            .body(SearchResponsePayload.class);
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    private record TavilyRequestPayload(String query, String searchDepth, int resultLimit) {}
}
