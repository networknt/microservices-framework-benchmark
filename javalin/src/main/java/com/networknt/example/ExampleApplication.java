package com.networknt.example;

import io.javalin.Javalin;

public class ExampleApplication {

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);
        app.get("/", ctx -> ctx.result("Hello world!"));
    }

}
