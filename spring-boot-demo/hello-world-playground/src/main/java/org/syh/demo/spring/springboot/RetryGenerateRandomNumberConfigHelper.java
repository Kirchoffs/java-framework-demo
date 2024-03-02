package org.syh.demo.spring.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class RetryGenerateRandomNumberConfigHelper implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private RetryGenerateRandomNumberConfig retryGenerateRandomNumberConfig;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            retryGenerateRandomNumberConfig.init();
        } catch (Exception e) {
            System.out.println("RetryGenerateRandomNumberConfigHelper: " + e.getMessage());
            SpringApplication.exit(event.getApplicationContext(), () -> 1);
        }
    }
}
