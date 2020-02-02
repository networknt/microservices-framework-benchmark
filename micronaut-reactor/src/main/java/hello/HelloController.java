package hello;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import reactor.core.publisher.Mono;

@Controller("/")
public class HelloController {

    @Get("/")
    public Mono<String> greet() {
        return Mono.just("Hello World!");
    }

}
