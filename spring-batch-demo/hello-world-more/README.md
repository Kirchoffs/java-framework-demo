# Notes

## Run
```
>> mvn clean install
>> mvn spring-boot:run -Dspring-boot.run.arguments=name=ben
>> mvn spring-boot:run -Dspring-boot.run.arguments="name=ben gift=petoi"
```
or if we use shade plugin:
```
>> java -cp target/hello-world-more.jar org.springframework.batch.core.launch.support.CommandLineJobRunner org.syh.demo.spring.springbatch.HelloWorldApplication job name=ben
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

## Others
### Shade
The shade:shade Mojo will create dependency-reduced-pom.xml.

dependency-reduced-pom.xml is more like missing-dependencies.xml and lists the dependencies which are missing in the uber-jar which is output by the shade plugin.

To disable this file, add the following into the pom file:
```
<configuration>
    <createDependencyReducedPom>false</createDependencyReducedPom>
</configuration>
```
