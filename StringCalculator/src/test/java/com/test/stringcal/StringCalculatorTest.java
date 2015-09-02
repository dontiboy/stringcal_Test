package com.test.stringcal;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {

    private StringCalculator stringCalculator = null;

    @Before
    public void setUp() throws Exception {
        stringCalculator = new StringCalculator();
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
    public void testTwoValuesWithCommasDelimiterString() {
        assertEquals(3, stringCalculator.add("1,2"));
    }

    @Test
    public void testMultipleValuesWithCommasDelimiterString_3numbers() {
        assertEquals(6, stringCalculator.add("1,2,3"));
    }

    @Test
    public void testMultipleValuesWithCommasDelimiterString_6numbers() {
        assertEquals(21, stringCalculator.add("1,2,3,4,5,6"));
    }

    @Test
    public void testNewLineCharsWithCommasDelimiterString_3numbers() {
        assertEquals(6, stringCalculator.add("1\n2,3"));
    }

    @Test
    public void testToSupportDifferentDelimiterString_3numbers() {
        assertEquals(3, stringCalculator.add("//@\n1@2"));
    }
}
