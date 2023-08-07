package org.syh.demo.spring.springboot;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class HelloWorldDatasourceApp implements CommandLineRunner {
    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        showConnection();
    }

    private void showConnection() throws SQLException {
        log.info("--------------------");
        log.info(dataSource.toString());
        Connection conn = dataSource.getConnection();
        log.info("--------------------");
        log.info(conn.toString());
        conn.close();
    }
    

    public static void main( String[] args ) {
        SpringApplication.run(HelloWorldDatasourceApp.class, args);
    }
}
