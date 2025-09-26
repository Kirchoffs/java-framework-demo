package org.syh.demo.springai.mcpserver.remote.figma.context;

public class McpServerContextHolder {
    private static final ThreadLocal<String> CURRENT_FIGMA_TOKEN = new ThreadLocal<>();

    public static void setFigmaToken(String token) {
        CURRENT_FIGMA_TOKEN.set(token);
    }

    public static String getFigmaToken() {
        return CURRENT_FIGMA_TOKEN.get();
    }

    public static void clear() {
        CURRENT_FIGMA_TOKEN.remove();
    }
}
