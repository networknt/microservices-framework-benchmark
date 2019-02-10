package hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpHeaders;

public class Server extends AbstractVerticle {

    private static final int INSTANCES = Runtime.getRuntime().availableProcessors() * 2;
    private static final Buffer TEXT = Buffer.buffer("Hello World!");

    private static final String TEXT_PLAIN = "text/plain";
    private static final String VERTX = "Vertx";

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        DeploymentOptions options = new DeploymentOptions().setInstances(INSTANCES);
        Vertx.vertx().deployVerticle(Server.class.getName(), options);
    }

    @Override
    public void start() throws Exception {
        vertx.createHttpServer().requestHandler(req -> {
            req.response()
                .putHeader(HttpHeaders.SERVER, VERTX)
                .putHeader(HttpHeaders.CONTENT_TYPE, TEXT_PLAIN)
                .end(TEXT);
        }).listen(8080);
    }
}
