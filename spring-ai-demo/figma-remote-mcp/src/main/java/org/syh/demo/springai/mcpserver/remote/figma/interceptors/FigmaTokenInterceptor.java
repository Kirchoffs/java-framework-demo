package org.syh.demo.springai.mcpserver.remote.figma.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.syh.demo.springai.mcpserver.remote.figma.context.McpServerContextHolder;

@Component
public class FigmaTokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
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
