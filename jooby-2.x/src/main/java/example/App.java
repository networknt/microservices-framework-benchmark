package example;

import io.jooby.Jooby;
import static io.jooby.ExecutionMode.EVENT_LOOP;

public class App extends Jooby {

  {
    get("/", ctx -> ctx.sendString("Hello World!"));
  }

  public static void main(final String[] args) {
    run(App::new, EVENT_LOOP, args);
  }

}
