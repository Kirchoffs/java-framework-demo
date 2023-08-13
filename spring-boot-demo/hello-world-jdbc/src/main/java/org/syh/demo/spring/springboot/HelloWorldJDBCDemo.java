package org.syh.demo.spring.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloWorldJDBCDemo implements CommandLineRunner {
    @Autowired
    private StockDao stockDao;

    @Autowired
    private StockBatchDao stockBatchDao;

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldJDBCDemo.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        stockDao.insertData();
        stockBatchDao.batchInsert();
        stockDao.listData();
    }
}
