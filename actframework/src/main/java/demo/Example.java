package demo;

import act.Version;
import act.boot.app.RunApp;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.SessionFree;

/**
 * The simple hello world app.
 * <p>Run this app, try to update some of the code, then
 * press F5 in the browser to watch the immediate change
 * in the browser!</p>
 */
@SuppressWarnings("unused")
public class Example {

    @GetAction
    @SessionFree
    public String home() {
        return "Hello World!";
    }

    @GetAction("/hello")
    public String helloWithSession() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        RunApp.start("Hello World", Version.appVersion(), Example.class);
    }

}
