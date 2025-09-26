package org.syh.demo.springai.mcpserver.remote.figma.config;

import io.modelcontextprotocol.server.McpServerFeatures;
import org.springframework.ai.mcp.server.common.autoconfigure.properties.McpServerProperties;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MimeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
public class CustomMcpConfig {
    @Bean
    @ConditionalOnProperty(
        prefix = McpServerProperties.CONFIG_PREFIX,
        name = "type",
        havingValue = "SYNC",
        matchIfMissing = true
    )
    public List<McpServerFeatures.SyncToolSpecification> syncTools(
        ObjectProvider<List<ToolCallback>> toolCalls,
        List<ToolCallback> toolCallbackList,
        ObjectProvider<List<ToolCallbackProvider>> tcbProviderList,
        ObjectProvider<ToolCallbackProvider> tcbProviders, 
        McpServerProperties serverProperties
    ) {
        List<ToolCallback> tools = this.aggregateToolCallbacks(
            toolCalls,
            toolCallbackList,
            tcbProviderList,
            tcbProviders
        );

        return this.toSyncToolSpecifications(tools, serverProperties);
    }

    private List<ToolCallback> aggregateToolCallbacks(
        ObjectProvider<List<ToolCallback>> toolCalls,
        List<ToolCallback> toolCallbackList,
        ObjectProvider<List<ToolCallbackProvider>> tcbProviderList,
        ObjectProvider<ToolCallbackProvider> tcbProviders
    ) {
        // Merge ToolCallbackProviders from both ObjectProviders.
        List<ToolCallbackProvider> totalToolCallbackProviders = new ArrayList<>(
            tcbProviderList.stream().flatMap(List::stream).toList()
        );
        totalToolCallbackProviders.addAll(tcbProviders.stream().toList());

        // De-duplicate ToolCallbackProviders
        totalToolCallbackProviders = totalToolCallbackProviders.stream().distinct().toList();

        List<ToolCallback> tools = new ArrayList<>(toolCalls.stream().flatMap(List::stream).toList());

        if (!CollectionUtils.isEmpty(toolCallbackList)) {
            tools.addAll(toolCallbackList);
        }

        List<ToolCallback> providerToolCallbacks = totalToolCallbackProviders.stream()
            .map(pr -> List.of(pr.getToolCallbacks()))
            .flatMap(List::stream)
            .filter(Objects::nonNull)
            .map(fc -> (ToolCallback) fc)
            .toList();

        tools.addAll(providerToolCallbacks);
        return tools;
    }

    private List<McpServerFeatures.SyncToolSpecification> toSyncToolSpecifications(
        List<ToolCallback> tools,
        McpServerProperties serverProperties
    ) {
        // De-duplicate tools by their name, keeping the first occurrence of each tool name
        return tools.stream()
            .collect(
                Collectors.toMap(tool -> tool.getToolDefinition().name(),
                tool -> tool,
                (existing, replacement) -> existing)
            )
            .values()
            .stream()
            .map(tool -> {
                String toolName = tool.getToolDefinition().name();
                MimeType mimeType = (serverProperties.getToolResponseMimeType().containsKey(toolName)) ?
                    MimeType.valueOf(serverProperties.getToolResponseMimeType().get(toolName)) : null;
                return CustomMcpToolUtils.toSyncToolSpecification(tool, mimeType);
            })
            .toList();
    }
}
