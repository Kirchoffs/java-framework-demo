package org.syh.demo.springai.mcpserver.remote.figma.clients.figma.models;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetImagesResponse {
    private String err;
    private Map<String, String> images;
}
