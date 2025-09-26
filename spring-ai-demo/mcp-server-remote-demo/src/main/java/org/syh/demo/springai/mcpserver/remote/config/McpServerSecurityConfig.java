package org.syh.demo.springai.mcpserver.remote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class McpServerSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .with(OAuth2AuthorizationServerConfigurer.authorizationServer(), Customizer.withDefaults())
            .oauth2ResourceServer(resource -> resource.jwt(Customizer.withDefaults()))
            .csrf(CsrfConfigurer::disable)
            .cors(Customizer.withDefaults())
            .build();
    }
}
