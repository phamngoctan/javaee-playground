package com.tanpham.playground.wildflyswarm.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("hello")
public class HelloWorldResource {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getAll() {
        Map<String, String> result = new HashMap<>();
        result.put("name", "Tan Pham");
		return result;
    }
	
}
