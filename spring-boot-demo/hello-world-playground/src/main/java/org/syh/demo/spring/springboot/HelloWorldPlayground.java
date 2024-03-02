package org.syh.demo.spring.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
@EnableAspectJAutoProxy(exposeProxy = true)
public class HelloWorldPlayground {
    public static void main(String[] args){
        SpringApplication.run(HelloWorldPlayground.class, args);
    }
}
