package com.objectstyle.bootique.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class HelloResource {

    @GET
    public String hello() {
        return "Hello World!";
    }
}