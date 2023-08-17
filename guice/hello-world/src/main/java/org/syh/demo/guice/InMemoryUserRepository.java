package org.syh.demo.guice;

public class InMemoryUserRepository implements UserRepository {
    @Override
    public void saveUser(String username) {
        System.out.println("Saving user in memory: " + username);
    }    
}
