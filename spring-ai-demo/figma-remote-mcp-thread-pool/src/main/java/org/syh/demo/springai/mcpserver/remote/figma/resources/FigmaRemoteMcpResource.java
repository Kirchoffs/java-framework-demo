package org.syh.demo.springai.mcpserver.remote.figma.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/v1")
public class FigmaRemoteMcpResource {
    @GET
    @Path("/version")
    public String figma() {
        return "v1";
    }
}
