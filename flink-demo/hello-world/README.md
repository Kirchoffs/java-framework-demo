# Notes
## Run WordCountStreamUnboundedDemo
### Basic
On HadoopAlpha (or any other instance that your machine can connet to):
```
>> nc -lk 6174
```
-l: --listen  
-k: keep the connection open

Run WordCountStreamUnboundedDemo.java, then type text in Netcat
```
>> nc -lk 6174
hello world
hello flink
```

### Standalone
Standalone with application mode:
```
Start:

>> bin/standalone-job.sh start --job-classname org.syh.demo.flink.hello.WordCountStreamUnboundedDemo
>> bin/taskmanager.sh start

Stop:

>> bin/taskmanager.sh stop
>> bin/standalone-job.sh stop
```

## Submit to WebUI
Add shade plugin:
```
<plugin>
    <groupId>org.apache.mavem.plugins</groupId>
    <argifactId>maven-shade-plugin</argifactId>
    <version>3.2.4</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>shade</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

Change Flink dependency scope to __provided__:
```
<dependency>
    <groupId>org.apache.flink</groupId>
    <artifactId>flink-streaming-java</artifactId>
    <version>${flink.version}</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>org.apache.flink</groupId>
    <artifactId>flink-clients</artifactId>
    <version>${flink.version}</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>org.apache.flink</groupId>
    <artifactId>flink-connector-files</artifactId>
    <version>${flink.version}</version>
    <scope>provided</scope>
</dependency>
```

Go to `Submit New Job`, submit the jar file in `target` directory.

Side note: the Java version for compiling the Flink job should be the same as the Flink runtime's Java version. Check log folder for more information.

## Command Line
```
>> bin/flink run -m HadoopAlpha:8081 -c org.syh.demo.flink.hello.WordCountStreamUnboundedDemo hello-world-1.0-SNAPSHOT.jar
```

## Netcat
On Mac OS, you can install nmap to use ncat:
```
>> brew install nmap
>> ncat -lk 6174
```

ncat is part of nmap, which is a network scanning tool. ncat is a utility that can be used to read and write data across network connections using the TCP or UDP protocol.
