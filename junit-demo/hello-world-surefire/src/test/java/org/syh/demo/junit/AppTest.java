package org.syh.demo.junit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest {
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void shouldAnswerWithFalseFirst() {
        assertTrue(false);
    }

    @Test
    public void shouldAnswerWithFalseSecond() {
        assertTrue(false);
    }
}
