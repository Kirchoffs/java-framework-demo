# Notes

## Run
```
>> mvn clean install
>> mvn spring-boot:run
```

## Knowledge
1\. In Spring Batch 5, `JobBuilderFactory` is deprecated.

Spring 5, Spring Boot 2, and Spring Batch 4 is together.  
Spring 6, Spring Boot 3, and Spring Batch 5 is together.

2\. `@SpringBootApplication`

`@SpringBootApplication` is a combination of `@SpringBootConfiguration`, `@EnableAutoConfiguration` and `@ComponentScan`.

3\. `@EnableBatchProcessing`

It provides Spring bean definitions for most of the batch infrastructure so you don't have to include:  
- JobRepository
- JobLauncher
- JobExplorer
- JobRepository
- PlatformTransactionManager
- JobBuilderFactory
- StepBuilderFactory

4\. Status

If returning RepeatStatus.FINISHED, you are done with Tasklet.  
If returning RepeatStatus.CONTINUABLE, Tasklet will be called again.

5\. Process

`JobLauncherCommandLineRunner` is loaded at startup when Spring Batch is found on the classpath and it uses `JobLauncher` to run any `Job` definitions found in the `ApplicationContext`.
