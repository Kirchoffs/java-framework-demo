package org.syh.demo.java.vanilla.processors;

import org.syh.demo.java.vanilla.context.UserContext;

public class UserRoleProcessor implements BaseProcessor {
    @Override
    public void process() {
        UserContext userContext = UserContext.get();
        
        String userRole = userContext.getUserRole();
        if (userRole == null || userRole.isEmpty()) {
            userContext.setUserRole("guest");
        }

        System.out.printf("Thread %s processing for user role: %s%n", Thread.currentThread().getName(), userContext.getUserRole());

        if (userRole.equalsIgnoreCase("admin")) {
            userContext.setUserRole(String.format("admin-%s", userContext.getUserId()));
        }
    }
}
