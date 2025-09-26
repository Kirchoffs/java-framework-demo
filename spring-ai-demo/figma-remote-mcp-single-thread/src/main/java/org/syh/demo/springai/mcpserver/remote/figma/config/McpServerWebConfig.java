package org.syh.demo.springai.mcpserver.remote.figma.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.syh.demo.springai.mcpserver.remote.figma.interceptors.FigmaTokenInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class McpServerWebConfig implements WebMvcConfigurer {
    private final FigmaTokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/mcp", "/sse");
    }
}
