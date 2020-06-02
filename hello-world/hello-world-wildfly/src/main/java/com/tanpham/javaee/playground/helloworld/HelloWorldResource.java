package com.tanpham.javaee.playground.helloworld;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

@Path("hello-world")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class HelloWorldResource {
	
	private static final Logger LOGGER = Logger.getLogger(HelloWorldResource.class.getName());
	
	private AtomicLong counter = new AtomicLong(1);
    private Float failRatio = 0.5f;
	
	@GET
	@Path("/")
	public Response getMessage() {
		Map<String, String> entity = new HashMap<>();
		entity.put("message", "Hello Tan Pham");
		return Response.ok(entity).build();
	}
	
	@GET
	@Path("/fault-tolerance-timeout")
	@Timeout(value = 250)
	public Response checkTimeout() {
		long started = System.currentTimeMillis();
        final long invocationNumber = counter.getAndIncrement();
        try {
			randomDelay();
			Map<String, String> entity = new HashMap<>();
			entity.put("message", "Hello from fault tolerance API");
			LOGGER.log(Level.INFO, String.format("HelloWorldResource#fault-tolerance-timeout() invocation #%d returning successfully", invocationNumber));
			return Response.ok(entity).build();
		} catch (InterruptedException e) {
			LOGGER.log(Level.INFO, String.format("CoffeeResource#recommendations() invocation #%d timed out after %d ms",
                    invocationNumber, System.currentTimeMillis() - started));
            return null;
		}
	}
	
	@GET
	@Path("/fault-tolerance-retry")
	@Retry(maxRetries = 4, retryOn = RuntimeException.class)
	public Response retry() {
		Map<String, String> entity = new HashMap<>();
		entity.put("message", "Hello from fault tolerance API");
		final Long invocationNumber = counter.getAndIncrement();
		maybeFail(String.format("HelloWorldResource#fault-tolerance-retry() invocation #%d failed", invocationNumber));
		return Response.ok(entity).build();
	}

	private void maybeFail(String failureLogMessage) {
		// introduce some artificial failures
        if (new Random().nextFloat() < failRatio) {
            LOGGER.log(Level.SEVERE, failureLogMessage);
            throw new RuntimeException("Resource failure.");
        }
	}
	
	private void randomDelay() throws InterruptedException {
        // introduce some artificial delay
		int nextInt = new Random().nextInt(500);
		LOGGER.log(Level.INFO, String.format("delay time: %s", nextInt));
		Thread.sleep(nextInt);
//		Thread.sleep(1000);
    }

}
