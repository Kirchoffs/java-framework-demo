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
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    public Step stepAlpha() {
        return this.stepBuilderFactory.get("step-alpha").tasklet(
            new Tasklet() {
                @Override
                public RepeatStatus execute(StepContribution contribution, ChunkContext context) {
                    System.out.println("Hello, World");
                    return RepeatStatus.FINISHED;
                }
            }
        ).build();
    }

    @Bean("job-alpha")
    public Job jobAlpha() {
        return this.jobBuilderFactory.get("job-alpha").start(stepAlpha()).build();
    }

    public Step stepBeta() {
        return this.stepBuilderFactory.get("step-beta").tasklet(
            new Tasklet() {
                @Override
                public RepeatStatus execute(StepContribution contribution, ChunkContext context) {
                    String name = (String) context.getStepContext().getJobParameters().get("name");
                    int id = Integer.parseInt((String) context.getStepContext().getJobParameters().get("id"));
                    System.out.println(String.format("Hello, %s with ID %d", name, id));
                    return RepeatStatus.FINISHED;
                }
            }
        ).build();
    }

    @Bean("job-beta")
    public Job jobBeta() {
        return this.jobBuilderFactory.get("job-beta").start(stepBeta()).build();
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }
}
