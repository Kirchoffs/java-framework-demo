package org.syh.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.syh.demo.models.Greeting;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GreetingController {
    @GetMapping("/greeting")
    public Map<String, Object> getGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", 1);
        response.put("content", "Hello, " + name + "!" + " " + "(GET)");
        return response;
    }

    @PostMapping("/greeting")
    public Map<String, Object> postGreeting(@RequestBody Greeting greeting) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", 1);
        response.put("content", "Hello, " + greeting.getName() + "!" + " " + "(POST)");
        return response;
    }
}
