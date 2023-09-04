package org.syh.demo.jaxrs.jersey.springboot;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/api")
public class HelloResourceConfig extends ResourceConfig {
    @PostConstruct
    public void init() {
        register(HelloResource.class);
    }
}
