package com.networknt.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class ExampleApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ExampleApplication.class);
    }
}
