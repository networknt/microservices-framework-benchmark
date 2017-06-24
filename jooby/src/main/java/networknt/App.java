package networknt;

import org.jooby.Jooby;

import static org.jooby.MediaType.plain;

import org.jooby.Results;
import org.jooby.banner.Banner;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author jooby generator
 */
public class App extends Jooby {

  static final ByteBuffer HELLO_WORLD = ByteBuffer
      .wrap("Hello World!".getBytes(StandardCharsets.UTF_8));

  {
    use(new Banner("networknt benchmark"));

    get("/", (req, rsp) -> {
      rsp.send(Results.ok(HELLO_WORLD).type(plain));
    }).renderer("byteBuffer");
  }

  public static void main(final String[] args) {
    run(App::new, args);
  }

}
