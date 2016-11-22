package com.objectstyle.bootique.demo;

import com.google.inject.Binder;
import com.google.inject.Module;
import io.bootique.Bootique;
import io.bootique.jersey.JerseyModule;

public class Application implements Module {
    public static void main(String[] args) {
        Bootique.app(args)
                .autoLoadModules()
                .module(Application.class)
                .run();
    }

    @Override
    public void configure(Binder binder) {
        JerseyModule.contributeResources(binder).addBinding().to(HelloResource.class);
    }
}
