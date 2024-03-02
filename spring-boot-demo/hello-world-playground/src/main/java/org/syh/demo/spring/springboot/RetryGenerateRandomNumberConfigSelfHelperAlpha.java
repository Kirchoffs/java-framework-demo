package org.syh.demo.spring.springboot;

import org.springframework.aop.framework.AopContext;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class RetryGenerateRandomNumberConfigSelfHelperAlpha implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            ((RetryGenerateRandomNumberConfigSelfHelperAlpha)(AopContext.currentProxy())).init();
        } catch (Exception e) {
            System.out.println("RetryGenerateRandomNumberConfigSelfHelperAlpha: " + e.getMessage());
            SpringApplication.exit(event.getApplicationContext(), () -> 1);
        }
    }

    @Retryable(maxAttempts = 5, value = Exception.class)
    public void init() throws Exception {
        System.out.println("RetryGenerateRandomNumberConfigSelfHelperAlpha: Start generating random number...");
        double randomNum = Math.random();
        System.out.println("RetryGenerateRandomNumberConfigSelfHelperAlpha: Random number is " + randomNum);
        if (randomNum > 0.9) {
            throw new Exception("RetryGenerateRandomNumberConfigSelfHelperAlpha: Random number is greater than 0.9");
        }
        System.out.println("RetryGenerateRandomNumberConfigSelfHelperAlpha: Hello, World!");
    }
}
