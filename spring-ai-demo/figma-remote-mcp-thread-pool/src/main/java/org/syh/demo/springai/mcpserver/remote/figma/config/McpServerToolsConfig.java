package org.syh.demo.springai.mcpserver.remote.figma.config;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.syh.demo.springai.mcpserver.remote.figma.tools.FigmaTools;

@Configuration
public class McpServerToolsConfig {
    @Bean
    public ToolCallbackProvider toolCallbacks(FigmaTools figmaTools) {
        return MethodToolCallbackProvider.builder().toolObjects(figmaTools).build();
    }
}
