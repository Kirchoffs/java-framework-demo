package org.syh.demo.springai.mcpserver.remote.figma.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.syh.demo.springai.mcpserver.remote.figma.context.McpServerContextHolder;

@Component
public class FigmaTokenInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(FigmaTokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        LOGGER.info("Thread (FigmaTokenInterceptor): {}", Thread.currentThread().getName());

        String token = request.getHeader("X-Figma-Token");
        
        if (token != null) {
            McpServerContextHolder.setFigmaToken(token);
        }
        
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        McpServerContextHolder.clear();
    }
}
