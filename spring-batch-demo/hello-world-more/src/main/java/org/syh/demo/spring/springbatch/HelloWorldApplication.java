package org.syh.demo.spring.springbatch;

import java.util.Arrays;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
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

    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("job")
            .start(stepAlpha())
            .validator(validator())
            .next(stepBeta())
            .build();
    }

    @Bean
    public Step stepAlpha() {
        String stepName = "step-alpha";
        return this.stepBuilderFactory.get(stepName).tasklet(taskletAlpha()).listener(new StepResultListener(stepName)).build();
    }

    @Bean
    public Step stepBeta() {
        return this.stepBuilderFactory.get("step-beta").tasklet(taskletBeta()).build();
    }

    @Bean
    public Tasklet taskletAlpha() {
        return (contribution, chunkContext) -> {
            Map<String, Object> parameters = chunkContext.getStepContext().getJobParameters();
            String name = (String) parameters.get("name");
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            System.out.println(String.format("Hello, %s!", name));
            ExecutionContext executionContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
            String gift = (String) parameters.getOrDefault("gift", "Lego");
            executionContext.putString("gift", gift);
            System.out.println("------------");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Tasklet taskletBeta() {
        return (contribution, chunkContext) -> {
            String name = (String) chunkContext.getStepContext().getJobParameters().get("name");
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            System.out.println(String.format("Hi, %s!", name));
            ExecutionContext executionContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
            System.out.println("I have recevied the gift from myself: " + executionContext.getString("gift"));
            System.out.println("------------");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public CompositeJobParametersValidator validator() {
        CompositeJobParametersValidator validator = new CompositeJobParametersValidator();

        DefaultJobParametersValidator defaultJobParametersValidator = new DefaultJobParametersValidator();
        defaultJobParametersValidator.setRequiredKeys(new String[] { "name" });
        defaultJobParametersValidator.setOptionalKeys(new String[] { "gift" });
        defaultJobParametersValidator.afterPropertiesSet();
        
        ValueValidator valueValidator = new ValueValidator();

        validator.setValidators(Arrays.asList(defaultJobParametersValidator, valueValidator));
        return validator;
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }
}
