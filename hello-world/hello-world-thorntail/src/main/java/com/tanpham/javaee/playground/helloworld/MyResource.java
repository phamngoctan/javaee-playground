package com.tanpham.javaee.playground.helloworld;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/endpoint")
public class MyResource {
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWithQuery(@DefaultValue("bar") @QueryParam("search") String searchQueryParamValue) {
        return Response.status(Status.ACCEPTED).entity(searchQueryParamValue).build();
    }
    
    @GET
    @Path("/messages")
    @Produces("text/plain")
    public Response doGet() {
        return Response.ok("Hello from Thorntail!").build();
    }
}
