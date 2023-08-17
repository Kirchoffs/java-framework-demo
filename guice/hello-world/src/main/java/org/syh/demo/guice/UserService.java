package org.syh.demo.guice;

import com.google.inject.Inject;

public class UserService {
    private final UserRepository userRepository;

    @Inject
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(String username) {
        System.out.println("Creating user: " + username);
        userRepository.saveUser(username);
    }
}
