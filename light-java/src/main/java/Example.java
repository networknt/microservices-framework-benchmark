import com.networknt.server.HandlerProvider;
import io.undertow.Handlers;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;


public class Example implements HandlerProvider {
    private static final String MESSAGE = "Hello World!";

    public HttpHandler getHandler() {
        return Handlers.path()
        .addPrefixPath("/", new HttpHandler() {
                public void handleRequest(HttpServerExchange exchange) {
                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                    exchange.getResponseSender().send(MESSAGE);
            	}
            }
        );
    }
}
