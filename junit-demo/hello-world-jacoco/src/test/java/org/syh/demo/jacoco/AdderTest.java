package org.syh.demo.jacoco;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AdderTest {
    private Adder adder;
    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
        adder = new Adder(calculator);
    }

    @Test
    public void testAddTwoNumbers() {
        assertEquals(7, adder.addTwoNumbers(3, 4));
    }
}
