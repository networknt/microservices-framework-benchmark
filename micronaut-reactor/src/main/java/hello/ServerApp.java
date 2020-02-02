package hello;

import io.micronaut.runtime.Micronaut;

public class ServerApp {

    public static void main(String[] args) {
        Micronaut.run(ServerApp.class);
    }
}