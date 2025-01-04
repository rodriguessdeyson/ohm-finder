package com.rad.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParserTests {

    @Test
    public void given_decimalNumber_expect_formattedString() {
        String value = "123.45";
        double expectedValue = 123.45;
        double actualValue = Parser.getDouble(value);
        assertEquals(expectedValue, actualValue, 0.01);
    }

    @Test
    public void given_integerNumber_expect_formattedString() {
        String value = "42";
        int expectedValue = 42;
        int actualValue = Parser.getInt(value);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void given_decimalNumber_expect_formattedStringWithTwoDecimals() {
        double d = 123.456;
        String expectedValue = "123.456";
        String actualValue = Parser.sanitizeDouble(d);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void given_decimalNumber_expect_formattedStringWithNoDecimals() {
        double d = 2;
        String expectedValue = "2";
        String actualValue = Parser.sanitizeDouble(d);
        assertEquals(expectedValue, actualValue);
    }
    
    @Test
    public void given_integerColor_expect_hexFormattedColor()
    {
        int color = 0xFF00FF;
        String expectedValue = "#FF00FF";
        String actualValue = Parser.getHexColor(color);
        assertEquals(expectedValue, actualValue);
    }
}