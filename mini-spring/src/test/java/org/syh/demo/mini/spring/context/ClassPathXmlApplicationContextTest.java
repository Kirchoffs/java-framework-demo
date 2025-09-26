package org.syh.demo.mini.spring.context;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.syh.demo.mini.spring.context.models.XService;

public class ClassPathXmlApplicationContextTest {
    @Test
    public void testReadXmlAndInstanceBeans() throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        XService xService = (XService) ctx.getBean("x-service-bean");
        String message = xService.sayHello();
        Assertions.assertEquals("Hello from XServiceImpl!", message);
    }
}
