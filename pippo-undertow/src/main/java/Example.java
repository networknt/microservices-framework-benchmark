/**
 * Created by rygel on 31/01/17.
 */
import ro.pippo.core.Application;

public class Example extends Application {
    @Override
    protected void onInit() {
        GET("/", routeContext -> routeContext.send("Hello World!"));
    }
}
