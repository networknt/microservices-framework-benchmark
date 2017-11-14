import static org.rapidoid.setup.On.get;

public class Example {

  public static void main(String[] args) {

  get("/").json(() -> "Hello World!");

  }
}

