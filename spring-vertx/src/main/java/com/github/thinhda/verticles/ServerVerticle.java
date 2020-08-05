package com.github.thinhda.verticles;

import com.github.thinhda.service.PingService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Created by thinhda. Date: 8/4/20 */
@Component
public class ServerVerticle extends AbstractVerticle {

  @Autowired private Integer defaultPort;
  @Autowired private PingService pingService;
  private static final String TEXT = "Hello World!";

  private void ping(RoutingContext routingContext) {
    pingService
        .ping()
        .setHandler(
            res -> {
              routingContext
                  .response()
                  .putHeader("content-type", "application/json")
                  .setStatusCode(200)
                  .end(res.result());
            });
  }

  @Override
  public void start() throws Exception {
    super.start();

    Router router = Router.router(vertx);
    router.get("/").handler(this::ping);

    vertx
        .createHttpServer()
        .requestHandler(req -> req.response().end(TEXT))
        .listen(config().getInteger("http.port", defaultPort));
  }
}
