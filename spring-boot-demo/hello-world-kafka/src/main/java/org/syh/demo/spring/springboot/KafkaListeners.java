package org.syh.demo.spring.springboot;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @KafkaListener(
        topicPartitions = {
            @TopicPartition(topic = "alpha", partitions = { "1" })
        },
        groupId = "alpha-group"
    )
    public void listener(String data) {
        System.out.println("Receied: " + data + " from alpha");
    }
}
