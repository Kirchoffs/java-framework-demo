package org.syh.demo.spring.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(final KafkaTemplate<String, String> kafkaTemplate) {
        return args -> {
            for (int i = 0; i < 10; i++) {
                kafkaTemplate.send("alpha", 1, "key-" + i, "val-" + i);
            }
        };
    }
}
