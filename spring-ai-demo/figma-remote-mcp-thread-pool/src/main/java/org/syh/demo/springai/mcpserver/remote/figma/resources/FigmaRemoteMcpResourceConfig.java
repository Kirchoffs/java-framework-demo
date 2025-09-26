package org.syh.demo.springai.mcpserver.remote.figma.resources;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/figma-mcp")
public class FigmaRemoteMcpResourceConfig extends ResourceConfig {
    @PostConstruct
    public void init() {
        register(FigmaRemoteMcpResource.class);
    }
}
