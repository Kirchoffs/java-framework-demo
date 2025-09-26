package org.syh.demo.springai.rag.clients.search;

public interface SearchClientInterface {
    SearchResponsePayload search(String query, int resultLimit);
}
