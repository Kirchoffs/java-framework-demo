package org.syh.demo.testng;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HelloWorldBetaTest {
    @Test(groups = {"anthus"})
    @Parameters({"userInternal"})
    public void helloWorldBetaTest(String userInternal) {
        System.out.println("Hello, " + userInternal + "(internal user)" + "!");
        System.out.println("Hi World!");
    }
}
