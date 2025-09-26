package org.syh.demo.webflux;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class GreetingController {
    @GetMapping("/hello")
    public Mono<String> getHello() {
        return Mono.just("Hello, Spring WebFlux!");
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map<String, String>> streamEvents() {
        return Flux.interval(Duration.ofSeconds(1))
            .map(sequence -> Map.of(
                "id", String.valueOf(sequence),
                "timestamp", LocalDateTime.now().toString()
            ));
    }
}
