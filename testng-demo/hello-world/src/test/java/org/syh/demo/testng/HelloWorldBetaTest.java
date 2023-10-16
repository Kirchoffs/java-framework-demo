package org.syh.demo.testng;

import org.testng.annotations.Test;

public class HelloWorldBetaTest {
    @Test(groups = {"anthus"})
    public void helloWorldBetaTest() {
        System.out.println("Hi World!");
    }
}
