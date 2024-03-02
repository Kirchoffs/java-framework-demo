package org.syh.demo.spring.springboot;

import org.springframework.aop.framework.AopContext;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class RetryGenerateRandomNumberConfigSelfHelperBeta implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            ((RetryGenerateRandomNumberConfigSelfHelperBeta)(event.getApplicationContext().getBean("retryGenerateRandomNumberConfigSelfHelperBeta"))).init();
        } catch (Exception e) {
            System.out.println("RetryGenerateRandomNumberConfigSelfHelperBeta: " + e.getMessage());
            SpringApplication.exit(event.getApplicationContext(), () -> 1);
        }
    }

    @Retryable(maxAttempts = 5, value = Exception.class)
    public void init() throws Exception {
        System.out.println("RetryGenerateRandomNumberConfigSelfHelperBeta: Start generating random number...");
        double randomNum = Math.random();
        System.out.println("RetryGenerateRandomNumberConfigSelfHelperBeta: Random number is " + randomNum);
        if (randomNum > 0.1) {
            throw new Exception("RetryGenerateRandomNumberConfigSelfHelperBeta: Random number is greater than 0.1");
        }
        System.out.println("RetryGenerateRandomNumberConfigSelfHelperBeta: Hello, World!");
    }
}
