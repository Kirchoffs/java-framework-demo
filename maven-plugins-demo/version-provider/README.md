# Notes
## Test
```
>> mvn clean install
```

```
>> mvn org.syh.demo.plugin:version-provider:version
>> mvn org.syh.demo.plugin:version-provider:version -Dgit.command="git rev-parse --short=8 HEAD"
```

## Test from Another Maven Project
```
>> mvn package -f plugin-usage-example/pom.xml
```
