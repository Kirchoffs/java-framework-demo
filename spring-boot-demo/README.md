# Notes

# Spring Actuator
By default, `/actuator/health`  and `/actuator/info` are exposed. To expose all endpoints, add the following to `application.properties` or `application.yml`:
```
management.endpoints.web.exposure.include=*
```

Also, you can customize the endpoints to be exposed:
```
management.endpoints.web.exposure.include=health,beans
``` 