package org.syh.demo.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

interface Car {
    String drive();
}

final class BMW implements Car {
    @Override
    public String drive() {
        return "BMW";
    }
}

class Owner {
    private final Car car;

    @Inject
    public Owner(Car car) {
        this.car = car;
    }

    public String drive() {
        return "I am driving " + car.drive();
    }
}

class CarModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Car.class).to(BMW.class);
    }
}

public class GuiceHelloWorldAppHitotsu {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new CarModule());
        Owner owner = injector.getInstance(Owner.class);
        System.out.println(owner.drive());
    }
}
