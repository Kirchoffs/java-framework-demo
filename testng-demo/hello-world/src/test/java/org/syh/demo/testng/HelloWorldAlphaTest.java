package org.syh.demo.testng;

import org.testng.annotations.Test;

public class HelloWorldAlphaTest {
    @Test(groups = {"anthemus", "anthus"})
    public void helloWorldAlphaTest() {
        String user = System.getProperty("userExternal");
        System.out.println("Hello, " + user + "(external user)" + "!");
        System.out.println("Hello World!");
    }
}
