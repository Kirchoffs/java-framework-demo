package org.syh.demo.springai.mcpserver.remote.figma.clients;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class FigmaClient {
    private static final String BASE_URL = "https://api.figma.com";
    
    private static final String GET_FILE = "/v1/files/{fileKey}";
    private static final String GET_FILE_NODES = "/v1/files/{fileKey}/nodes";
    private static final String GET_IMAGES = "/v1/images/{fileKey}";
    private static final String GET_FILE_META = "/v1/files/{fileKey}/meta";
    private static final String GET_LOCAL_VARIABLES = "/v1/files/{fileKey}/variables/local";

    private final RestClient restClient;

    public FigmaClient(RestClient.Builder builder) {
        this.restClient = builder.baseUrl(BASE_URL).build();
    }

    public String getFile(String figmaToken, String fileKey, List<String> ids) {
         return restClient.get()
            .uri(uriBuilder -> {
                uriBuilder.path(GET_FILE);

                if (ids != null && !ids.isEmpty()) {
                    uriBuilder.queryParam("ids", String.join(",", ids));
                }
                
                return uriBuilder.build(fileKey);
            })
            .header("X-Figma-Token", figmaToken)
            .retrieve()
            .body(String.class);
    }

    public String getFileNodes(String figmaToken, String fileKey, List<String> ids) {
         return restClient.get()
            .uri(uriBuilder -> {
                uriBuilder.path(GET_FILE_NODES);

                if (ids != null && !ids.isEmpty()) {
                    uriBuilder.queryParam("ids", String.join(",", ids));
                }
                
                return uriBuilder.build(fileKey);
            })
            .header("X-Figma-Token", figmaToken)
            .retrieve()
            .body(String.class);
    }

    public String getImages(String figmaToken, String fileKey, List<String> ids) {
        return restClient.get()
            .uri(uriBuilder -> {
                uriBuilder.path(GET_IMAGES);
                if (ids != null && !ids.isEmpty()) {
                    uriBuilder.queryParam("ids", String.join(",", ids));
                }
                return uriBuilder.build(fileKey);
            })
            .header("X-Figma-Token", figmaToken)
            .retrieve()
            .body(String.class);
    }

    public String getFileMeta(String figmaToken, String fileKey) {
        return restClient.get()
            .uri(uriBuilder -> {
                uriBuilder.path(GET_FILE_META);
                return uriBuilder.build(fileKey);
            })
            .header("X-Figma-Token", figmaToken)
            .retrieve()
            .body(String.class);
    }

    public String getLocalVariables(String figmaToken, String fileKey) {
        return restClient.get()
            .uri(uriBuilder -> {
                uriBuilder.path(GET_LOCAL_VARIABLES);
                
                return uriBuilder.build(fileKey);
            })
            .header("X-Figma-Token", figmaToken)
            .retrieve()
            .body(String.class);
    }
}
