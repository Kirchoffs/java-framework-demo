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

## Features
__Step Relation__
```
jobBuilderFactory.get("job").start(stepOne()).next(stepTwo()).build()
```

__Validator__
```
jobBuilderFactory.get("job").start(step()).validator(validator()).build()
```

__Listener__
```
stepBuilderFactory.get("step").tasklet(tasklet()).listener(listener()).build()
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

dependency-reduced-pom.xml is more like missing-dependencies.xml and lists the dependencies which are missing in the uber-jar.

If your intention is to utilize the shaded JAR rather than the regular JAR as a dependency for another module, the dependency-reduced-pom.xml file can be valuable because it excludes the JARs that are already present in the shaded JAR, which avoids unnecessary duplication.

To disable this file, add the following into the pom file:
```
<configuration>
    <createDependencyReducedPom>false</createDependencyReducedPom>
</configuration>
```
