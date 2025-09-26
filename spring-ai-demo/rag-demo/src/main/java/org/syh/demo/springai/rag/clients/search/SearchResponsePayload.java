package org.syh.demo.springai.rag.clients.search;

import java.util.List;

public record SearchResponsePayload(List<Hit> results) {
    public record Hit(String title, String url, String content, Double score) {}
}
