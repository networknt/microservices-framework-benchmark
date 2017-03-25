package com.mycompany;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.inject.Singleton;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
@Singleton
public class HelloWorldResource {
    @GET
    public String sayHello() {
        return "Hello World!";
    }
}
