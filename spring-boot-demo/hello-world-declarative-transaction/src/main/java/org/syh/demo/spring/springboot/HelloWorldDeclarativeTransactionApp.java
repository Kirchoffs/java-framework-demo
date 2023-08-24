package org.syh.demo.spring.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableTransactionManagement(mode = AdviceMode.PROXY)
@Slf4j
public class HelloWorldDeclarativeTransactionApp implements CommandLineRunner {
    @Autowired
    private FooService fooService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldDeclarativeTransactionApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        fooService.insertRecord();
        log.info("ben: {}", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='ben'", Long.class));

        try {
            fooService.insertThenRollback();
        } catch (Exception e) {
            log.info("tom: {}", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='tom'", Long.class));
        }

        try {
            fooService.invokeInsertThenRollback();
        } catch (Exception e) {
            log.info("tom: {}", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='tom'", Long.class));
        }

        try {
            fooService.invokeInsertThenRollbackByAopContext();
        } catch (Exception e) {
            log.info("tom: {}", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='tom'", Long.class));
        }

        try {
            fooService.invokeInsertThenRollbackBySelfService();
        } catch (Exception e) {
            log.info("tom: {}", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='tom'", Long.class));
        }
    }
}
