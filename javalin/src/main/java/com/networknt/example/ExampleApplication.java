package com.networknt.example;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.eclipse.jetty.alpn.server.ALPNServerConnectionFactory;
import org.eclipse.jetty.http2.HTTP2Cipher;
import org.eclipse.jetty.http2.server.HTTP2ServerConnectionFactory;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.ssl.SslContextFactory;

public class ExampleApplication {

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            // Manual Jetty configuration required for SSL/TLS/HTTP2
            config.server(ExampleApplication::createServer);
        }).start();
        app.get("/", ExampleApplication::helloWorld);
    }

    public static void helloWorld(Context ctx) {
        ctx.result("Hello world!");
    }

    private static Server createServer() {
        Server server = new Server();

        // HTTP
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.addConnector(connector);

        // HTTPS
        ServerConnector sslConnector = new ServerConnector(server, getSslContextFactory());
        sslConnector.setPort(8443);
        server.addConnector(sslConnector);


        // HTTP/2
        HttpConfiguration httpsConfig = createHttpsConfig();

        SslContextFactory sslContextFactory = getSslContextFactory();
        sslContextFactory.setCipherComparator(HTTP2Cipher.COMPARATOR);
        sslContextFactory.setProvider("Conscrypt");

        HttpConnectionFactory httpConnectionFactory = new HttpConnectionFactory(httpsConfig);

        HTTP2ServerConnectionFactory h2 = new HTTP2ServerConnectionFactory(httpsConfig);
        ALPNServerConnectionFactory alpn = new ALPNServerConnectionFactory();
        alpn.setDefaultProtocol(httpConnectionFactory.getProtocol());

        SslConnectionFactory ssl = new SslConnectionFactory(sslContextFactory, alpn.getProtocol());

        ServerConnector http2Connector = new ServerConnector(
                server,
                ssl,
                alpn,
                h2,
                httpConnectionFactory
        );
        http2Connector.setPort(9443);
        server.addConnector(http2Connector);

        return server;
    }

    private static HttpConfiguration createHttpsConfig() {
        HttpConfiguration httpConfig = new HttpConfiguration();
        httpConfig.setSendXPoweredBy(false);
        httpConfig.setSendServerVersion(false);
        httpConfig.setSecureScheme("https");
        httpConfig.addCustomizer(new SecureRequestCustomizer());
        return httpConfig;
    }

    private static SslContextFactory getSslContextFactory() {
        SslContextFactory sslContextFactory = new SslContextFactory.Server();
        sslContextFactory.setKeyStorePath(ExampleApplication.class.getResource("/server.keystore").toExternalForm());
        sslContextFactory.setKeyStorePassword("password");
        sslContextFactory.setUseCipherSuitesOrder(true);
        return sslContextFactory;
    }
}
