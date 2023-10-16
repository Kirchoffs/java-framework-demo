package org.syh.demo.testng;

import org.testng.annotations.Test;

public class HelloWorldAlphaTest {
    @Test(groups = {"anthemus", "anthus"})
    public void helloWorldAlphaTest() {
        System.out.println("Hello World!");
    }
}
