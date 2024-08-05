# Notes
## Run
```
>> mvn clean install

or

>> mvn clean install -Pjavax
>> mvn clean install -Pjakarta
```

Put the war file (`target/javax-build.war`) in the webapps folder of Tomcat and start the server.
```
>> ./bin/startup.sh
```

Go to the browser and type the URL `http://localhost:8080/javax-build/hello`

Stop the server
```
>> ./bin/shutdown.sh
```
