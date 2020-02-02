package hello;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;

@Controller("/")
public class HelloController {

    @Get("/")
    public Single<String> greet() {
        return Single.just("Hello World!");
    }

}
