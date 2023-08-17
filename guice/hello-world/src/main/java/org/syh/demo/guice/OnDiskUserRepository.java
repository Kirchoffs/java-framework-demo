package org.syh.demo.guice;

public class OnDiskUserRepository implements UserRepository {
    @Override
    public void saveUser(String username) {
        System.out.println("Saving user on disk: " + username);
    }
}
