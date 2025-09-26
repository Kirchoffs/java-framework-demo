package org.syh.demo.springai.mcpserver.remote.figma.tools;

import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import org.syh.demo.springai.mcpserver.remote.figma.clients.figma.FigmaClient;
import org.syh.demo.springai.mcpserver.remote.figma.clients.image.ImageClient;
import org.syh.demo.springai.mcpserver.remote.figma.clients.figma.models.GetImagesResponse;
import org.syh.demo.springai.mcpserver.remote.figma.context.McpServerContextHolder;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FigmaTools {
    private static final Logger LOGGER = LoggerFactory.getLogger(FigmaTools.class);

    private final FigmaClient figmaClient;
    private final ImageClient imageClient;

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
        LOGGER.info("FigmaTools.getFile - fileKey: {}, ids: {}", fileKey, ids);

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
        LOGGER.info("FigmaTools.getFileNodes - fileKey: {}, ids: {}", fileKey, ids);

        String userToken = McpServerContextHolder.getFigmaToken();
        return figmaClient.getFileNodes(userToken, fileKey, ids);
    }

    @Tool(name = "get_image_urls", description =
        """
            Render images of file nodes.
            Returns URLs pointing to the image resources identified by `ids`.
            Important: The `ids` parameter is required to know which nodes to convert into images.
        """
    )
    public GetImagesResponse getImageUrls(
        @ToolParam(description = "The key of the file") String fileKey,
        @ToolParam(description = "A list of node IDs to render as images") List<String> ids) {
        LOGGER.info("FigmaTools.getImages - fileKey: {}, ids: {}", fileKey, ids);

        String userToken = McpServerContextHolder.getFigmaToken();
        return figmaClient.getImages(userToken, fileKey, ids);
    }

    @Tool(name = "get_image", returnDirect = true, description =
        """
            Render image of a file node.
            Downloads and returns the actual image data as binary bytes.
            Important: The `id` parameter is required to know which node to convert into a image.
        """
    )
    public String getImage(
        @ToolParam(description = "The key of the file") String fileKey,
        @ToolParam(description = "A node ID to render as image") String id) {
        LOGGER.info("FigmaTools.getImage - fileKey: {}, ids: {}", fileKey, id);

        String userToken = McpServerContextHolder.getFigmaToken();
        String imageUrl = figmaClient.getImage(userToken, fileKey, id);

        byte[] imageBytes = imageClient.downloadImage(imageUrl);
        LOGGER.info("Returning image of a file node: {}", imageUrl);
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    @Tool(name = "get_file_metadata", description =
        """
            Get file metadata. Returns high-level metadata about the file (name, last modified, creator, version, url, etc).
        """
    )
    public String getFileMetadata(
        @ToolParam(description = "The key of the file") String fileKey) {
        LOGGER.info("FigmaTools.getFileMetadata - fileKey: {}", fileKey);

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
        LOGGER.info("FigmaTools.getLocalVariables - fileKey: {}", fileKey);

        String userToken = McpServerContextHolder.getFigmaToken();
        return figmaClient.getLocalVariables(userToken, fileKey);
    }
}
