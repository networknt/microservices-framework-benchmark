[Jooby 2.x](https://jooby.io)

## hello world!

Java:

```java
import io.jooby.Jooby;

public class App extends Jooby {

  {
    get("/", ctx -> ctx.sendString("Hello World!"));
  }

  public static void main(final String[] args) {
    run(App::new, args);
  }
}

```

## build

```
mvn package
```

# run

```
java -jar target/jooby-2.x.jar
```
