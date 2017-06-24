# &infin; do more, more easily

[Jooby](http://jooby.org) a scalable, fast and modular micro web framework for Java and [Kotlin](http://jooby.org/doc/lang-kotlin).

* **Simple, effective and easy to learn**. Ideal for small but also large scale applications.

* **Scalable**. Stateless application development.

* **Fast**. Thanks to the most popular [NIO web servers](http://jooby.org/doc/servers).

* **Modular**. Make it **full-stack** via the extensive [module eco-system](http://jooby.org/modules).

* **Ready for the modern web**, with the awesome and powerful [asset module](https://github.com/jooby-project/jooby/tree/master/jooby-assets)

## hello world!

Java:

```java
import org.jooby.Jooby;

public class App extends Jooby {

  {
    get("/", () -> "Hey Jooby!");
  }

  public static void main(final String[] args) {
    run(App::new, args);
  }
}

```

[Kotlin](http://jooby.org/doc/lang-kotlin):

```kotlin

import org.jooby.*

fun main(args: Array<String>) {
  run(*args) {
    get {
      "Hello Jooby!"
    }
  }
}

```

More at http://jooby.org
