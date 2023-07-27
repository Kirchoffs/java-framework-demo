package org.syh.demo.jaxrs.jersey.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

@Path("/hello")
public class HelloResource {
    @Context
    private ContainerRequestContext requestContext;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        UriInfo uriInfo = requestContext.getUriInfo();
        String baseUrl = uriInfo.getBaseUri().toString();
        System.out.println(baseUrl);
        return "Hello World";
    }
}
