package org.syh.demo.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provides;

interface Car {
    String drive();
}

final class BMW implements Car {
    private BMW() {}

    @Override
    public String drive() {
        return "BMW";
    }

    public static BMW create() {
        return new BMW();
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
    @Provides
    Car provideCar() {
        return BMW.create();
    }
}

public class GuiceHelloWorldAppMittsu {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new CarModule());
        Owner owner = injector.getInstance(Owner.class);
        System.out.println(owner.drive());
    }
}
