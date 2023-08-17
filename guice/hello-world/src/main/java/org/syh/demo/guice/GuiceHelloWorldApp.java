package org.syh.demo.guice;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class GuiceHelloWorldApp {
    @Inject
    private UserService userService;

    public void start() {
        userService.createUser("ben");
    }

    public static void main( String[] args ) {
        Injector injector = Guice.createInjector(new UserModuleConfigure());
        GuiceHelloWorldApp app = injector.getInstance(GuiceHelloWorldApp.class);
        app.start();
    }
}
