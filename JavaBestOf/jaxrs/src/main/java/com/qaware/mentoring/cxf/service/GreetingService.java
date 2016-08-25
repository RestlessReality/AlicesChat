package com.qaware.mentoring.cxf.service;

import com.qaware.mentoring.cxf.api.Greeting;
import com.qaware.mentoring.cxf.api.Status;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/greetings")
@Consumes("application/json")
@Produces("application/json")
public interface GreetingService {

    @GET
    Status statusCheck();

    @POST
    @Path("/{user}")
    Greeting getUser(@PathParam("user") String user);

}
