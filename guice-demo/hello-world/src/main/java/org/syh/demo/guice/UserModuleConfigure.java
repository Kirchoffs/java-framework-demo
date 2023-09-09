package org.syh.demo.guice;

import com.google.inject.AbstractModule;

public class UserModuleConfigure extends AbstractModule {
    @Override
    protected void configure() {
        bind(UserRepository.class).to(OnDiskUserRepository.class);
    }
}
