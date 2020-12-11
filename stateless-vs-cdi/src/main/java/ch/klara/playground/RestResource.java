package ch.klara.playground;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/action")
public class RestResource {
    
    @Inject
    private StatelessServiceBean statelessServiceBean;

    @Inject
    private CdiServiceBean cdiServiceBean;
    
    @GET
    @Path("/stateless-loop-call")
    @Produces(MediaType.APPLICATION_JSON)
    public Response statelessLoopCall() {
        for (int i = 0; i < 1e5; i++) {
            statelessServiceBean.doNothing();
        }
        return Response.ok("stateless-loop-call").build();
    }
    
    @GET
    @Path("/cdi-loop-call")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cdiLoopCall() {
        for (int i = 0; i < 1e5; i++) {
            cdiServiceBean.doNothing();
        }
        return Response.ok("cdi-loop-call").build();
    }

}
