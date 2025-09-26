package org.syh.demo.springai.rag.retrievers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.syh.demo.springai.rag.clients.search.SearchClientInterface;
import org.syh.demo.springai.rag.clients.search.SearchResponsePayload;

public class WebSearchDocumentRetriever implements DocumentRetriever {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSearchDocumentRetriever.class);

    private static final int DEFAULT_RESULT_LIMIT = 3;

    private final SearchClientInterface searchClient;
    private final int resultLimit;

    private WebSearchDocumentRetriever(SearchClientInterface searchClient, int resultLimit) {
        this.searchClient = searchClient;
        this.resultLimit = resultLimit;
    }

    @Override
    public List<Document> retrieve(Query query) {
        LOGGER.info("Processing query: {}", query.text());
        Assert.notNull(query, "query cannot be null");

        String queryText = query.text();
        Assert.hasText(queryText, "query.text() cannot be empty");

        SearchResponsePayload response = searchClient.search(queryText, resultLimit);

        if (response == null || CollectionUtils.isEmpty(response.results())) {
            return List.of();
        }
        
        List<Document> documents = new ArrayList<>(response.results().size());
        for (SearchResponsePayload.Hit hit : response.results()) {
            Document document = Document.builder()
                .text(hit.content())
                .metadata("title", hit.title())
                .metadata("url", hit.url())
                .score(hit.score())
                .build();
            documents.add(document);
        }

        return documents;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private SearchClientInterface searchClient;
        private int resultLimit = DEFAULT_RESULT_LIMIT;

        private Builder() {}

        public Builder searchClient(SearchClientInterface searchClient) {
            this.searchClient = searchClient;
            return this;
        }

        public Builder maxResults(int resultLimit) {
            if (resultLimit <= 0) {
                throw new IllegalArgumentException("resultLimit must be greater than 0");
            }
            this.resultLimit = resultLimit;
            return this;
        }

        public WebSearchDocumentRetriever build() {
            return new WebSearchDocumentRetriever(searchClient, resultLimit);
        }
    }
}
