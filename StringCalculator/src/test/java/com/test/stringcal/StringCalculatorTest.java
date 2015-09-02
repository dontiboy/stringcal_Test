package com.test.stringcal;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {

    private StringCalculator stringCalculator=null;

    @Before
    public void setUp() throws Exception {
        stringCalculator= new StringCalculator();
    }

    @Test
    public void testEmptyString() {
        assertEquals(0, stringCalculator.add(""));
    }

    @Test
    public void testSingleValueString() {
        assertEquals(1, stringCalculator.add("1"));
    }

    @Test
    public void testMultpleValuesWithCommasDelimiterString() {
        assertEquals(3, stringCalculator.add("1,2"));
    }
}
