# Notes

## Test
### Start Kafka Server
```
>> KAFKA_CLUSTER_ID="$(bin/kafka-storage.sh random-uuid)"
>> bin/kafka-storage.sh format -t $KAFKA_CLUSTER_ID -c config/kraft/server.properties
>> bin/kafka-server-start.sh config/kraft/server.properties
```

### Start Spring Boot Application
```
>> mvn spring-boot:run
```

### Start Consumer
```
>> bin/kafka-console-consumer.sh --topic alpha --partition 1 --from-beginning --bootstrap-server localhost:9092
```