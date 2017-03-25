package networknt;

import org.jooby.Jooby;
import org.jooby.banner.Banner;

/**
 * @author jooby generator
 */
public class App extends Jooby {

  {
    use(new Banner("networknt benchmark"));
    get("/", (req, rsp) -> rsp.send("Hello World!"));
  }

  public static void main(final String[] args) {
    run(App::new, args);
  }

}
