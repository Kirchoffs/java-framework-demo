package org.syh.demo.springai.mcpserver.remote.figma.filters;

import java.io.IOException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.syh.demo.springai.mcpserver.remote.figma.context.McpServerContextHolder;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class FigmaTokenServletFilter extends OncePerRequestFilter {
    public static final String FIGMA_TOKEN_HEADER = "X-Figma-Token";

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        String figmaToken = request.getHeader(FIGMA_TOKEN_HEADER);
        if (figmaToken != null && !figmaToken.isEmpty()) {
            McpServerContextHolder.setFigmaToken(figmaToken);
        } else {
            McpServerContextHolder.clear();
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            McpServerContextHolder.clear();
        }
    }
}
