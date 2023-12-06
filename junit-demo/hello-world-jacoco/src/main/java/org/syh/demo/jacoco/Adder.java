package org.syh.demo.jacoco;

public class Adder {
    private Calculator calculator;

    public Adder(Calculator calculator) {
        this.calculator = calculator;
    }

    public int addTwoNumbers(int a, int b) {
        return calculator.add(a, b);
    }
}
