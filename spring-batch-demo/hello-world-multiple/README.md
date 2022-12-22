# Notes

## Run
```
>> mvn clean install
>> java -cp "target/dependency-jars/*:target/hello-world-multiple-1.0-SNAPSHOT.jar" org.springframework.batch.core.launch.support.CommandLineJobRunner org.syh.demo.spring.springbatch.HelloWorldApplication job-alpha

or

>> java -cp "target/dependency-jars/*:target/hello-world-multiple-1.0-SNAPSHOT.jar" org.springframework.batch.core.launch.support.CommandLineJobRunner org.syh.demo.spring.springbatch.HelloWorldApplication job-beta name=Ben
```

The general command line is like:
```
>> CommandLineJobRunner jobPath jobIdentifier (jobParameters)
```
In our case, it's:
```
>> org.springframework.batch.core.launch.support.CommandLineJobRunner org.syh.demo.spring.springbatch.HelloWorldApplication job-alpha

or

>> org.springframework.batch.core.launch.support.CommandLineJobRunner org.syh.demo.spring.springbatch.HelloWorldApplication job-beta name=Ben
```

In IntelliJ, to specify the job, we can add `--spring.batch.job.names=job-alpha` to `program arguments`.

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
