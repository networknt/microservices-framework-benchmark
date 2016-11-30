import ratpack.server.RatpackServer;

public class Example {
    public static void main(String... args) throws Exception {

        RatpackServer
            .start(server -> server
                .handlers(chain -> chain
                    .get(ctx -> ctx.render("Hello World!"))));
    }
}
