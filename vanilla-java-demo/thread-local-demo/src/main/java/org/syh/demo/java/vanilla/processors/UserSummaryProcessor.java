package org.syh.demo.java.vanilla.processors;

import org.syh.demo.java.vanilla.context.UserContext;

public class UserSummaryProcessor implements BaseProcessor {
    @Override
    public void process() {
        UserContext userContext = UserContext.get();

        String userId = userContext.getUserId();
        String userRole = userContext.getUserRole();
        if (userId == null || userId.isEmpty()) {
            userId = "unknown";
        }
        if (userRole == null || userRole.isEmpty()) {
            userRole = "unknown";
        }
        
        System.out.printf("Thread %s processing for user %s with role %s%n", Thread.currentThread().getName(), userId, userRole);
    }
}
