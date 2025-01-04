package com.rad.utils;

import java.util.Locale;

public class Parser
{
    /**
     * Parse a string to a double.
     * @param value String to be parsed.
     * @return The parsed double.
     */
    public static Double getDouble(String value)
    {
        return Double.parseDouble(value);
    }

    /**
     * Parse a string to a integer.
     * @param value String to be parsed.
     * @return The parsed integer.
     */
    public static Integer getInt(String value)
    {
        return Integer.parseInt(value);
    }

    /**
     * Removes decimal part from a double if it is an integer.
     * @param d Double to be sanitized.
     * @return The string formated value.
     */
    public static String sanitizeDouble(double d)
    {
        Locale locale = Locale.getDefault();
        if (d == Math.floor(d))
            return String.format(locale, "%.0f", d);
        else
            return Double.toString(d);
    }

    /**
     * Convert a color to a hex string.
     * @param color Color to be converted.
     * @return The hex notation of the color as a string.
     */
    public static String getHexColor(int color)
    {
        return String.format("#%06X", (0xFFFFFF & color));
    }
}
