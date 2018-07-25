package com.networknt.example;

import org.reactivestreams.Publisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

@Controller
public class Example {

    @GetMapping("/")
    @ResponseBody
    public Publisher<String> handler() {
        return Mono.just("Hello world!");
    }
}