import ro.pippo.core.Pippo;

/**
 * Created by Alexander on 31.01.2017.
 */
public class ExampleLauncher {

    public static void main(String[] args) {
        Pippo pippo = new Pippo(new Example());
        pippo.start(8080);
    }

}
