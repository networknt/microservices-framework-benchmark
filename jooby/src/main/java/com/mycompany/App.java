package com.mycompany;

import org.jooby.Jooby;

/**
 * @author jooby generator
 */
public class App extends Jooby {

  {
    get("/", (req, rsp) -> rsp.send("Hello World!"));
  }

  public static void main(final String[] args) {
    run(App::new, args);
  }

}
