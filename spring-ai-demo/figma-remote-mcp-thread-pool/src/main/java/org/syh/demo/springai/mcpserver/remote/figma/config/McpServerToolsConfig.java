package org.syh.demo.springai.mcpserver.remote.figma.config;

import java.util.List;

import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.syh.demo.springai.mcpserver.remote.figma.tools.FigmaTools;

@Configuration
public class McpServerToolsConfig {
    @Bean
    List<ToolCallback> toolCallbacks(FigmaTools figmaTools) {
        return List.of(ToolCallbacks.from(figmaTools));
    }
}
