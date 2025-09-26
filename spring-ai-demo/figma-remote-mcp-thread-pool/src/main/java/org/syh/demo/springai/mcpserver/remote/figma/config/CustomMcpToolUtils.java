package org.syh.demo.springai.mcpserver.remote.figma.config;

import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.model.ModelOptionsUtils;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.util.MimeType;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static org.springframework.ai.mcp.McpToolUtils.TOOL_CONTEXT_MCP_EXCHANGE_KEY;

public class CustomMcpToolUtils {
    public static McpServerFeatures.SyncToolSpecification toSyncToolSpecification(
        ToolCallback toolCallback,
        MimeType mimeType
    ) {
        SharedSyncToolSpecification sharedSpec = toSharedSyncToolSpecification(toolCallback, mimeType);

        return new McpServerFeatures.SyncToolSpecification(
            sharedSpec.tool(),
            (exchange, map) ->
                sharedSpec.sharedHandler().apply(exchange, new McpSchema.CallToolRequest(sharedSpec.tool().name(), map)),
            (exchange, request) -> sharedSpec.sharedHandler().apply(exchange, request)
        );
    }

    private static SharedSyncToolSpecification toSharedSyncToolSpecification(
        ToolCallback toolCallback,
        MimeType mimeType
    ) {
        var tool = McpSchema.Tool.builder()
            .name(toolCallback.getToolDefinition().name())
            .description(toolCallback.getToolDefinition().description())
            .inputSchema(
                ModelOptionsUtils.jsonToObject(
                    toolCallback.getToolDefinition().inputSchema(),
                    McpSchema.JsonSchema.class
                )
            )
            .build();

        return new SharedSyncToolSpecification(tool, (exchangeOrContext, request) -> {
            try {
                String callResult = toolCallback.call(
                    ModelOptionsUtils.toJsonString(request.arguments()),
                    new ToolContext(Map.of(TOOL_CONTEXT_MCP_EXCHANGE_KEY, exchangeOrContext))
                );
                if (mimeType != null && mimeType.toString().startsWith("image")) {
                    callResult = callResult.substring(1, callResult.length() - 1);
                    McpSchema.Annotations annotations = new McpSchema.Annotations(List.of(McpSchema.Role.ASSISTANT), null);
                    return new McpSchema.CallToolResult(
                        List.of(new McpSchema.ImageContent(annotations, callResult, mimeType.toString())), false
                    );
                }
                return new McpSchema.CallToolResult(List.of(new McpSchema.TextContent(callResult)), false);
            }
            catch (Exception e) {
                return new McpSchema.CallToolResult(List.of(new McpSchema.TextContent(e.getMessage())), true);
            }
        });
    }

    private record SharedSyncToolSpecification(
        McpSchema.Tool tool,
        BiFunction<Object, McpSchema.CallToolRequest,
        McpSchema.CallToolResult> sharedHandler
    ) {}
}
