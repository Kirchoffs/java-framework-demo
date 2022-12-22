# Notes

## Run
```
>> mvn clean install
>> mvn spring-boot:run -Dspring-boot.run.arguments=name=ben

>> mvn spring-boot:run -Dspring-boot.run.arguments="name=ben gift=lego"
```

## Code Notes
Another way for parameter passing, use Spring's EL (Expression Language).
```
@Bean
public Job job() {
    return this.JobBuilderFactory.get("job")
        .start(stepAlpha(null))
        .next(stepBeta())
        .build();
}

@StepScope
@Bean
public Tasklet taskletAlpha(@Value("#{jobParameters['name']}") String name) {
    return (contribution, chunkContext) -> {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        System.out.println(String.format("Hello, %s!", name));
        return RepeatStatus.FINISHED;
    };
}
```