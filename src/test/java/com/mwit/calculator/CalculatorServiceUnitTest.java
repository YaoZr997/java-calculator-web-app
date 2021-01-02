package com.mwit.calculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorServiceUnitTest {

    @Test
    public void testPing() {
        assertTrue(new CalculatorService().ping().startsWith("Welcome to Java Calculator Web App!"));
    }

    @Test
    public void testAdd() {
        assertEquals(34, new CalculatorService().Add(8, 26).getResult());
    }

    @Test
    public void testSub() {
        assertEquals(4, new CalculatorService().Sub(12, 8).getResult());
    }

    @Test
    public void testMul() {
        assertEquals(88, new CalculatorService().Mul(11, 8).getResult());
    }

    @Test
    public void testDiv() {
        assertEquals(1, new CalculatorService().Div(12, 12).getResult());
    }
}
