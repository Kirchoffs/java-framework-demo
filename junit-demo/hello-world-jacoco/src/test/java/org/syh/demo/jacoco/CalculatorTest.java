package org.syh.demo.jacoco;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {
    private Calculator calculator = new Calculator();

    @Test
    public void testAdd() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    public void testSubtract() {
        assertEquals(1, calculator.subtract(4, 3));
    }

    @Test
    public void testMultiply() {
        assertEquals(6, calculator.multiply(2, 3));
    }
}
