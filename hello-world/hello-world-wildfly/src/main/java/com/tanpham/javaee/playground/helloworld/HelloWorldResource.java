package com.tanpham.javaee.playground.helloworld;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
	
	@GET
	@Path("/")
	public Response getMessage() {
		Map<String, String> entity = new HashMap<>();
		entity.put("message", "Hello Tan Pham");
		return Response.ok(entity).build();
	}

}
