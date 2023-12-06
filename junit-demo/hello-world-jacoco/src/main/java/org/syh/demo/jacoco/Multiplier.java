package org.syh.demo.jacoco;

public class Multiplier {
    private Calculator calculator;

    public Multiplier(Calculator calculator) {
        this.calculator = calculator;
    }

    public int multiplyTwoNumbers(int a, int b) {
        return calculator.multiply(a, b);
    }
}
