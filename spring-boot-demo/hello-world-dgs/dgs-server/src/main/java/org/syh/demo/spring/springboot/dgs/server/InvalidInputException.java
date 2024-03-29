package org.syh.demo.spring.springboot.dgs.server;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String field, String value) {
        super("Invalid input: " + field + " = " + "'" + value + "'");
    }
}
