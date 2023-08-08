# Notes

## Run
```
>> mvn clean install
>> java -cp target/hello-world-datasource-1.0-SNAPSHOT.jar org.syh.demo.spring.vanilla.HelloWorldDataSourceApp
```

## Plugin
Plugins listed under `<pluginManagement>` in the pom.xml file do not directly affect the current project. The `<pluginManagement>` section is used to define plugin configurations that can be inherited by child modules within a multi-module Maven project.

In a single-module project (without child modules), the `<pluginManagement>` section does not have any direct impact on the current project. The plugins defined in `<pluginManagement>` will not be automatically applied to the current project, and you need to explicitly declare the plugins in the `<plugins>` section for them to take effect.

```
<project>
    <!-- ... other configurations ... -->
    <build>
        <pluginManagement>
            <plugins>
                <!-- Plugin configuration defined in pluginManagement -->
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.8.1</version>
                    <configuration>
                        <source>1.8</source>
                        <target>1.8</target>
                    </configuration>
                </plugin>
                <!-- Other plugins in pluginManagement -->
            </plugins>
        </pluginManagement>

        <plugins>
            <!-- Explicitly declare the plugin in the plugins section -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```