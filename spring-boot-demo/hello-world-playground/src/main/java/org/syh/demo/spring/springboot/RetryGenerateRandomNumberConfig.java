package org.syh.demo.spring.springboot;

import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class RetryGenerateRandomNumberConfig {
    @Retryable(maxAttempts = 5, value = Exception.class)
    public void init() throws Exception {
        System.out.println("RetryGenerateRandomNumberConfig: Start generating random number...");
        double randomNum = Math.random();
        System.out.println("RetryGenerateRandomNumberConfig: Random number is " + randomNum);
        if (randomNum > 0.9) {
            throw new Exception("RetryGenerateRandomNumberConfig: Random number is greater than 0.9");
        }
        System.out.println("RetryGenerateRandomNumberConfig: Hello, World!");
    }
}
