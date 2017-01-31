package benchmark;

import act.Version;
import act.app.ActionContext;
import act.boot.app.RunApp;
import act.handler.NonBlock;
import org.osgl.http.H;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.SessionFree;

/**
 * The simple hello world app.
 * <p>Run this app, try to update some of the code, then
 * press F5 in the browser to watch the immediate change
 * in the browser!</p>
 */
@SuppressWarnings("unused")
public class ActBenchmark {

    /**
     * This interface can be used to benchmark ActFramework's performance on generating
     * a JSON response, in this method it will be `{"result": "Hello World!"}`
     *
     * An `ActionContext` typed parameter is declared and it will be injected by ActFramework
     * automatically. The context can be used to amend the response `content-type`, which
     * by default is `text/html` if not specified with the request
     *
     * @param context an ActionContext instance to be injected into the argument list
     * @return a JSON response body: `Hello World!`
     */
    @GetAction("/json")
    @SessionFree
    @NonBlock
   public static String jsonSerialization(ActionContext context) {
        context.accept(H.Format.JSON);
        return "Hello World!";
    }

    /**
     * Note the {@link act.handler.builtin.Echo} handler is responsible for
     * request sent to `/` and echo back `Hello World!`.
     *
     * The logic is defined in the `/resources/routs.conf` file
     */
    public static void main(String[] args) throws Exception {
        RunApp.start("Act Benchmark", Version.appVersion(), ActBenchmark.class);
    }

}
