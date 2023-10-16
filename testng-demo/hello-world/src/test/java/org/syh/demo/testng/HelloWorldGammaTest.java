package org.syh.demo.testng;

import org.testng.annotations.Test;

public class HelloWorldGammaTest {
    @Test(groups = {"anthemus"})
    public void helloWorldGammaTest() {
        System.out.println("Hola Mundo!");
    }
}
