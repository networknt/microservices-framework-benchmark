package benchmark.act;

import act.Act;
import org.osgl.mvc.annotation.GetAction;

/**
 * The simple hello world app.
 * <p>Run this app, try to update some of the code, then
 * press F5 in the browser to watch the immediate change
 * in the browser!</p>
 */
@SuppressWarnings("unused")
public class ActBenchmark {

    /**
     * Test the performance with full feature enabled:
     * * block IO (Yes! normally we do work in block mode)
     * * session resolving (of course, it always needs to resolve/dissolve session/flash, isn't)
     * @return a `Hello World!` string
     */
    @GetAction("/txt")
    public String text() {
        return "Hello World!";
    }

    /**
     * Note the {@link act.handler.builtin.Echo} handler is responsible for
     * request sent to `/` and echo back `Hello World!`.
     *
     * The logic is defined in the `/resources/routs.conf` file
     */
    public static void main(String[] args) throws Exception {
        Act.start();
    }

}
