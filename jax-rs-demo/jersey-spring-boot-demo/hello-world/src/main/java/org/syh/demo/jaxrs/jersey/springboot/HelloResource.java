package org.syh.demo.jaxrs.jersey.springboot;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Component
@Path("/hello")
public class HelloResource {
    @GET
    public String hello() {
        return "Hello, World!";
    }
}
