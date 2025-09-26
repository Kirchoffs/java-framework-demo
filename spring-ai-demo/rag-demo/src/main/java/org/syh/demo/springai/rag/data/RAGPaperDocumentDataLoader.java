package org.syh.demo.springai.rag.data;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class RAGPaperDocumentDataLoader {
    private final VectorStore vectorStore;

    @Value("classpath:Retrieval-Augmented Black-Box Language Models.pdf")
    private Resource paperDocument;

    public RAGPaperDocumentDataLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void loadDocumentIntoVectorStore() {
        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(paperDocument);
        List<Document> docs = tikaDocumentReader.get();
        TextSplitter textSplitter = TokenTextSplitter.builder().withChunkSize(128).withMaxNumChunks(1024).build();
        vectorStore.add(textSplitter.split(docs));
    }
}
