package org.syh.demo.mini.spring.context.models;

public class XServiceImpl implements XService {
    @Override
    public String sayHello() {
        System.out.println("Hello from XServiceImpl!");
        return "Hello from XServiceImpl!";
    }
    
}
