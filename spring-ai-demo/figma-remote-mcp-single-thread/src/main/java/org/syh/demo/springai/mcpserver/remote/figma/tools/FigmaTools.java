package org.syh.demo.springai.mcpserver.remote.figma.tools;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import org.syh.demo.springai.mcpserver.remote.figma.clients.FigmaClient;
import org.syh.demo.springai.mcpserver.remote.figma.context.McpServerContextHolder;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FigmaTools {
    private static final Logger LOGGER = LoggerFactory.getLogger(FigmaTools.class);

    private final FigmaClient figmaClient;

    @Tool(name = "get_file", description =
        """
        Returns the document identified by `fileKey` as a JSON object. 
        The file key can be parsed from any Figma file url: `https://www.figma.com/file/{fileKey}/{title}`.
        If no `ids` are provided, prioritize calling this tool.
        """
    )
    public String getFile(
        @ToolParam(description = "The key of the file") String fileKey, 
        @ToolParam(description = "A list of nodes that you care about in the document", required = false) List<String> ids) {
        LOGGER.info("Parameters Information - fileKey: {}, ids: {}", fileKey, ids);

        String userToken = McpServerContextHolder.getFigmaToken();
        return figmaClient.getFile(userToken, fileKey, ids);
    }

    @Tool(name = "get_file_nodes", description =
        """
        Returns the nodes referenced by `ids` as a JSON object.
        The node ID and file key can be parsed from any Figma node url: `https://www.figma.com/file/{fileKey}/{title}?node-id={id}`.
        Generally, if you have the Node IDs and you don't care about the ancestral path to the nodes, you should use this method instead of `get_file`.
        Because it is more efficient because it fetches only the exact data you need without the overhead of the surrounding document structure.
        """
    )
    public String getFileNodes(
        @ToolParam(description = "The key of the file") String fileKey, 
        @ToolParam(description = "A list of nodes that you care about in the document", required = false) List<String> ids) {
        LOGGER.info("Thread (FigmaTool): {}", Thread.currentThread().getName());
        LOGGER.info("Parameters Information - fileKey: {}, ids: {}", fileKey, ids);

        String userToken = McpServerContextHolder.getFigmaToken();
        return figmaClient.getFileNodes(userToken, fileKey, ids);
    }

    @Tool(name = "get_images", description =
        """
        Render images of file nodes. Returns the URLs for the images of the nodes identified by `ids`.
        Important: The `ids` parameter is required to know which nodes to convert into images.
        """
    )
    public String getImages(
        @ToolParam(description = "The key of the file") String fileKey,
        @ToolParam(description = "A list of node IDs to render as images") List<String> ids) {
        LOGGER.info("Parameters Information - getImages - fileKey: {}, ids: {}", fileKey, ids);

        String userToken = McpServerContextHolder.getFigmaToken();
        return figmaClient.getImages(userToken, fileKey, ids);
    }

    @Tool(name = "get_file_metadata", description =
        """
        Get file metadata. Returns high-level metadata about the file (name, last modified, creator, version, url, etc).
        """
    )
    public String getFileMetadata(
        @ToolParam(description = "The key of the file") String fileKey) {
        LOGGER.info("Thread (FigmaTool): {}", Thread.currentThread().getName());
        LOGGER.info("Parameters Information - getFileMetadata - fileKey: {}", fileKey);

        String userToken = McpServerContextHolder.getFigmaToken();
        return figmaClient.getFileMeta(userToken, fileKey);
    }

    @Tool(name = "get_local_variables", description =
        """
        Get local variables. Returns the local variables stored in the file identified by `fileKey`.
        This includes variable collections and the variables themselves.
        This endpoint requires the file_variables:read scope.
        """
    )
    public String getLocalVariables(
        @ToolParam(description = "The key of the file to fetch variables from") String fileKey) {
        LOGGER.info("Parameters Information - getLocalVariables - fileKey: {}", fileKey);

        String userToken = McpServerContextHolder.getFigmaToken();
        return figmaClient.getLocalVariables(userToken, fileKey);
    }
}
