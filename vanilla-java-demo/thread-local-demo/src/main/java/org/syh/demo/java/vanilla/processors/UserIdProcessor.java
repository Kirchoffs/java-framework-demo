package org.syh.demo.java.vanilla.processors;

import org.syh.demo.java.vanilla.context.UserContext;

public class UserIdProcessor implements BaseProcessor {
    @Override
    public void process() {
        UserContext userContext = UserContext.get();
        
        String userId = userContext.getUserId();
        if (userId == null || userId.isEmpty()) {
            userContext.setUserId("42");
        }

        System.out.printf("Thread %s processing for user: %s%n", Thread.currentThread().getName(), userContext.getUserId());
    }
}
