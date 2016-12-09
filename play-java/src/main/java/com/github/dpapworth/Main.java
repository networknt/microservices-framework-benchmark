package com.github.dpapworth;

import play.routing.Router;
import play.routing.RoutingDsl;
import play.server.Server;

import static play.mvc.Results.ok;

public class Main {

    public static void main(String[] args) {
        Router router = new RoutingDsl().
                GET("/").routeTo(() -> ok("Hello World!")).
                build();
        Server server = Server.forRouter(router, 8080);

        System.out.println("Server started on " + server.httpPort());
    }

}
