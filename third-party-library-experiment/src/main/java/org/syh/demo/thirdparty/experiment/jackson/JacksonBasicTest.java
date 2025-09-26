package org.syh.demo.thirdparty.experiment.jackson;

import java.util.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonBasicTest {
    public static void main(String[] args) {
        serializeString();
        serializeBytes();
        serializeObject();
    }

    private static void serializeString() {
        String str = "Hello, Jackson!";
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(str);
            System.out.println("Serialized JSON String: " + jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Jackson serializes byte[] as a Base64 encoded string by default.
    private static void serializeBytes() {
        String str = "Hello, Jackson!";
        byte[] strBytes = str.getBytes();

        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(strBytes);
            System.out.println("Serialized JSON String: " + jsonString);
            String strBytesBase64 = Base64.getEncoder().encodeToString(strBytes);
            System.out.println("Base64 String: " + strBytesBase64);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void serializeObject() {
        Employee employee = new Employee("Alice", "Engineering");
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(employee);
            System.out.println("Serialized Employee JSON: " + jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class Employee {
        public String name;
        public String department;

        public Employee(String name, String department) {
            this.name = name;
            this.department = department;
        }
    }
}
