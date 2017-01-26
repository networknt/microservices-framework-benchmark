/**
 * Created by steve on 14/11/16.
 */
import static spark.Spark.*;

public class Example {
    public static void main(String[] args) {
        get("/", (req, res) -> "Hello World!");
    }
}
