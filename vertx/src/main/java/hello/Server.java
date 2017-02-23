package hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class Server extends AbstractVerticle {

    private static final int INSTANCES = 6;
    private static final String TEXT = "Hello World!";

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        DeploymentOptions options = new DeploymentOptions().setInstances(INSTANCES);
        Vertx.vertx().deployVerticle(Server.class.getName(), options);
    }

    @Override
    public void start() throws Exception {
        vertx.createHttpServer().requestHandler(req -> {
            req.response().end(TEXT);
        }).listen(8080);
    }
}
