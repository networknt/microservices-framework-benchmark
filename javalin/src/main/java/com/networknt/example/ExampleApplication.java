package com.networknt.example;

import io.javalin.Javalin;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.ssl.SslContextFactory;

public class ExampleApplication {

    public static void main(String[] args) {
        // Get example from https://github.com/javalin/javalin/blob/master/javalin/src/test/java/io/javalin/examples/HelloWorldSecure.java#L22-L30
        Javalin.create(config -> {
                    config.jetty.addConnector((server, httpConfiguration) -> {
                        ServerConnector sslConnector = new ServerConnector(server, getSslContextFactory());
                        sslConnector.setPort(443);
                        return sslConnector;
                    });
                    config.jetty.addConnector((server, httpConfiguration) -> {
                        ServerConnector connector = new ServerConnector(server);
                        connector.setPort(80);
                        return connector;
                    });

                })
                .start()
                .get("/", ctx -> ctx.result("Hello World"));

    }

    private static SslContextFactory.Server getSslContextFactory() {
        SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
        sslContextFactory.setKeyStorePath(ExampleApplication.class.getResource("/keystore.jks").toExternalForm());
        sslContextFactory.setKeyStorePassword("password");
        return sslContextFactory;
    }
}
