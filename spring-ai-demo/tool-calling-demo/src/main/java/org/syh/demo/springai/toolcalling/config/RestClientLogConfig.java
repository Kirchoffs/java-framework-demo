package org.syh.demo.springai.toolcalling.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;

import java.nio.charset.StandardCharsets;

@Configuration
public class RestClientLogConfig { 
    private static final Logger LOGGER = LoggerFactory.getLogger(RestClientLogConfig.class);

    @Bean
    public RestClientCustomizer restClientCustomizer() {
        return restClientBuilder -> restClientBuilder.requestInterceptor(loggingInterceptor());
    }

    private ClientHttpRequestInterceptor loggingInterceptor() {
        return (request, body, execution) -> {
            LOGGER.info("=========================== REQUEST START ===========================");
            
            LOGGER.info("URI      : {}", request.getURI());
            LOGGER.info("Method   : {}", request.getMethod());
            
            String requestBody = new String(body, StandardCharsets.UTF_8);
            LOGGER.info("Request Body: {}", requestBody);
            
            LOGGER.info("=========================== REQUEST END =============================");

            return execution.execute(request, body);
        };
    }
}
