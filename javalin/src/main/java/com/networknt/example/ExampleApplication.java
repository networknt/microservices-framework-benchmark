package com.networknt.example;

import io.javalin.Javalin;

public class ExampleApplication {

    public static void main(String[] args) {
        Javalin app = Javalin.create(/*config*/)
                .get("/", ctx -> ctx.result("Hello World"))
                .start(8080);

    }
}
