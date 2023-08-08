package org.syh.demo.spring.vanilla;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HelloWorldDataSourceApp {
    @Autowired
    private DataSource dataSource;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() throws Exception {
        Properties properties = new Properties();
    
        properties.setProperty("driverClassName", "org.h2.Driver");
        properties.setProperty("url", "jdbc:h2:mem:testdb");
        properties.setProperty("username", "sa");

        return BasicDataSourceFactory.createDataSource(properties);
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {
        return new DataSourceTransactionManager(dataSource());
    }

    public void showDataSource() throws SQLException {
        System.out.println(dataSource.toString());
        Connection conn = dataSource.getConnection();
        System.out.println(conn.toString());
        conn.close();
    }

    private static void showBeans(ApplicationContext applicationContext) {
        System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));
    }

    private static void dataSourceDemo(ApplicationContext applicationContext) throws SQLException {
        HelloWorldDataSourceApp app = applicationContext.getBean("helloWorldDataSourceApp", HelloWorldDataSourceApp.class);
        app.showDataSource();
    }

    public static void main(String[] args) throws SQLException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        showBeans(applicationContext);
        dataSourceDemo(applicationContext);
    }
}
