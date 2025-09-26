# Notes
## Test Process
```
>> cd lib-z-1 && mvn clean install
>> cd lib-z-2 && mvn clean install

>> cd lib-y-1 && mvn clean install
>> cd lib-y-2 && mvn clean install

>> cd lib-x && mvn clean install
>> cd lib-x && mvn dependency:tree -Dincludes=org.syh.dummy:lib-y -Dverbose
>> cd lib-x && mvn dependency:tree -Dincludes=org.syh.dummy:lib-z -Dverbose
```

## Test Results
### Case 1
x depends on y. In x's `dependencyManagement`, y's version is v1; but in x's `dependencies`, y's version is v2.  
Then the final version of y in x is v2.

### Case 2
x depends on y, and y depends on z. In x's `dependencyManagement`, z's version is v1; but in y's dependencies, z's version is v2.  
Then the final version of z in x is v1.