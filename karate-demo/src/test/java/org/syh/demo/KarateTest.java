package org.syh.demo;

import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KarateTest {
    @LocalServerPort
    int port;

    @Karate.Test
    Karate testGreeting() {
        return Karate
            .run("classpath:karate")
            .systemProperty("demo.server.port", String.valueOf(port));
    }
}
