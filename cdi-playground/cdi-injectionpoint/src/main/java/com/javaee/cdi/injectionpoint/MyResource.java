package com.javaee.cdi.injectionpoint;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/endpoint")
public class MyResource {
	
	@Inject
	@HttpParam("search")
	private String searchQueryParam;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getWithQuery(@DefaultValue("bar") @QueryParam("search") String searchQueryParamValue) {
        return searchQueryParam;
    }
    
}
