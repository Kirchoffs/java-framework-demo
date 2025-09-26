package org.syh.demo.java.vanilla.context;

public class UserContext {
    private static final ThreadLocal<UserContext> userContextThreadLocal = new ThreadLocal<>();

    private String userId;
    private String userRole;

    public UserContext() {
        userContextThreadLocal.set(this);
    }

    public static UserContext get() {
        return userContextThreadLocal.get();
    }

    public static void clear() {
        userContextThreadLocal.remove();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
