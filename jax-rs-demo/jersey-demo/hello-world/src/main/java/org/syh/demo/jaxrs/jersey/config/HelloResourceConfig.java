package org.syh.demo.jaxrs.jersey.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.syh.demo.jaxrs.jersey.resources.HelloResource;

public class HelloResourceConfig extends ResourceConfig {
    public HelloResourceConfig() {
        register(HelloResource.class);
    }
}
