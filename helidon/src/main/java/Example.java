import io.helidon.webserver.WebServer;

public class Example {

    public static void main(String[] args) {
        WebServer webServer = WebServer.builder()
                .routing((rules) -> {
                    rules.get("/", ((req, res) -> {
                        res.send("Hello World");
                    }));
                })
                .port(8080)
                .build()
                .start();
    }
}
