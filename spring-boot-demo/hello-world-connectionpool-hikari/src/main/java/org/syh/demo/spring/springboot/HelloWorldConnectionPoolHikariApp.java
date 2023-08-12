package org.syh.demo.spring.springboot;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloWorldConnectionPoolHikariApp implements CommandLineRunner {
    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(dataSource.getClass().getName());
    }

    public static void main( String[] args ) {
        SpringApplication.run(HelloWorldConnectionPoolHikariApp.class, args);
    }
}
