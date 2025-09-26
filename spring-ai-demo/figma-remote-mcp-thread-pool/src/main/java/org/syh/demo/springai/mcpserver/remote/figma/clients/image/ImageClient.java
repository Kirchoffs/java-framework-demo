package org.syh.demo.springai.mcpserver.remote.figma.clients.image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ImageClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageClient.class);
    
    private final RestClient restClient;

    public ImageClient(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    public byte[] downloadImage(String url) {
        try {
            LOGGER.info("Downloading image from: {}", url);
            
            byte[] imageBytes = restClient.get()
                .uri(url)
                .retrieve()
                .body(byte[].class);
            
            if (imageBytes == null || imageBytes.length == 0) {
                throw new RuntimeException("Image is empty");
            }
            
            return imageBytes;
        } catch (Exception e) {
            LOGGER.error("Failed to download image", e);
            throw new RuntimeException("Failed to download image: " + e.getMessage());
        }
    }
}
