package org.syh.demo.spring.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableBatchProcessing
@SpringBootApplication
public class HelloWorldApplication {
    @Autowired
    private JobBuilderFactory JobBuilderFactory;

    @Autowired
    private StepBuilderFactory StepBuilderFactory;

    @Bean
    public Step step() {
        return this.StepBuilderFactory.get("step-alpha").tasklet(
            new Tasklet() {
                @Override
                public RepeatStatus execute(StepContribution contribution, ChunkContext context) {
                    System.out.println("Hello, World");
                    return RepeatStatus.FINISHED;
                }
            }
        ).build();
    }

    @Bean
    public Job job() {
        return this.JobBuilderFactory.get("job").start(step()).build();
    }

    public static void main( String[] args )
    {
        SpringApplication.run(HelloWorldApplication.class, args);
    }
}
