# Notes

## Run
```
>> mvn clean install
>> mvn spring-boot:run -Dspring-boot.run.arguments=identifier=x
```

## Database
### Configuration
In application.properties:
```
spring.datasource.url=jdbc:mysql://localhost/spring_batch_demo?serverTimezone=America/Los_Angeles
spring.datasource.username=username
spring.datasource.password=password
spring.datasource.schema=classpath:/org/springframework/batch/core/schema-mysql.sql
spring.batch.jdbc.initialize-schema=always
spring.datasource.driverClass=com.mysql.cj.jdbc.Driver
```

### Check the database
```
>> use spring_batch_demo;
>> select * from BATCH_JOB_INSTANCE;
...
>> select * from BATCH_STEP_EXECUTION;
...
```

## Other Notes
### Error
The name of Job class cannot be the same as the name of the Job bean name.
The following code will throw an error:
```
public class DemoJob {
    @Bean
    public Job demoJob() {
        return jobBuilderFactory.get("demoJobForTest")
            .start(stepAlpha())
            .next(stepBeta())
            .next(stepGamma())
            .build();
    }
}
```