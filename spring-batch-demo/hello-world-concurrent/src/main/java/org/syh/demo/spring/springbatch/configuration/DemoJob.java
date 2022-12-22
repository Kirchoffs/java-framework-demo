package org.syh.demo.spring.springbatch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@EnableBatchProcessing
public class DemoJob {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job demoJobForTest() {
        return jobBuilderFactory.get("demoJobForTest")
            .start(splitFlowAlpha())
            .split(new SimpleAsyncTaskExecutor()).add(splitFlowBeta())
            .end()
            .build();
    }

    @Bean
    public Flow splitFlowAlpha() {
        return new FlowBuilder<Flow>("splitFlowAlpha")
            .start(stepAlpha())
            .next(stepBeta())
            .build();
    }

    @Bean
    public Flow splitFlowBeta() {
        return new FlowBuilder<Flow>("splitFlowBeta")
            .start(stepGamma())
            .next(stepDelta())
            .build();
    }

    @Bean
    public Step stepAlpha() {
        return stepBuilderFactory.get("step-alpha").allowStartIfComplete(true)
            .tasklet(
                new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
                        System.out.println("Step Alpha");
                        return RepeatStatus.FINISHED;
                    }
                }
            ).build();
    }

    @Bean
    public Step stepBeta() {
        return stepBuilderFactory.get("step-beta").allowStartIfComplete(true)
            .tasklet(
                new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
                        System.out.println("Step Beta");
                        return RepeatStatus.FINISHED;
                    }
                }
            ).build();
    }

    @Bean
    public Step stepGamma() {
        return stepBuilderFactory.get("step-gamma").allowStartIfComplete(true)
            .tasklet(
                new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
                        System.out.println("Step Gamma");
                        return RepeatStatus.FINISHED;
                    }
                }
            ).build();
    }

    @Bean
    public Step stepDelta() {
        return stepBuilderFactory.get("step-delta").allowStartIfComplete(true)
            .tasklet(
                new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
                        System.out.println("Step Delta");
                        return RepeatStatus.FINISHED;
                    }
                }
            ).build();
    }
}
