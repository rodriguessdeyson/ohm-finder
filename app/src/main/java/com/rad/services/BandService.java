package com.rad.services;


import android.content.Context;

import com.rad.models.Band;
import com.rad.resistorcolorcode.R;
import com.rad.utils.Parser;

import java.util.ArrayList;

public class BandService
{
    private final Context context;

    public BandService(Context ctx)
    {
        context = ctx;
    }

    public ArrayList<Band> getSignificantBandsCodes()
    {
        String[] bandNames = context.getResources().getStringArray(R.array.band);
        int[] bandColors = context.getResources().getIntArray(R.array.band_colors);

        short maxBandValue = 10;
        ArrayList<Band> firstBandColorCodes = new ArrayList<>();
        for (int bandIndex = 0; bandIndex < maxBandValue; bandIndex++)
        {
            String name = bandNames[bandIndex];
            int value = bandColors[bandIndex];
            firstBandColorCodes.add(new Band(name, value, bandIndex));
        }

        return firstBandColorCodes;
    }

    public ArrayList<Band> getMultiplierBandColorCodes()
    {
        String[] bandNames = context.getResources().getStringArray(R.array.multiplier_band);
        int[] bandColors = context.getResources().getIntArray(R.array.multiplier_band_colors);

        short maxBandValue = 10;
        ArrayList<Band> multiplierBandColorCodes = new ArrayList<>();
        for (int bandIndex = 0; bandIndex < maxBandValue; bandIndex++)
        {
            String name = bandNames[bandIndex];
            int value = bandColors[bandIndex];
            multiplierBandColorCodes.add(new Band(name, value, Math.pow(10, bandIndex)));
        }

        // Add gold and silver colors
        multiplierBandColorCodes.add(new Band(bandNames[10], bandColors[10], 0.1));
        multiplierBandColorCodes.add(new Band(bandNames[11], bandColors[11], 0.01));

        return multiplierBandColorCodes;
    }

    public ArrayList<Band> getToleranceBandColorCodes()
    {
        String[] bandNames = context.getResources().getStringArray(R.array.tolerance_band);
        int[] bandColors = context.getResources().getIntArray(R.array.tolerance_band_colors);
        int[] bandValues = context.getResources().getIntArray(R.array.tolerance_value);

        short maxBandValue = 8;
        ArrayList<Band> toleranceBandColorCodes = new ArrayList<>();
        for (int bandIndex = 0; bandIndex < maxBandValue; bandIndex++)
        {
            String name = bandNames[bandIndex];
            int color = bandColors[bandIndex];
            int value = bandValues[bandIndex];
            toleranceBandColorCodes.add(new Band(name, color, (double) value / 100));
        }

        return toleranceBandColorCodes;
    }

    public ArrayList<Band> getPPMBandColorCodes()
    {
        String[] bandNames = context.getResources().getStringArray(R.array.ppm_band);
        int[] bandColors = context.getResources().getIntArray(R.array.ppm_band_colors);
        int[] bandValues = context.getResources().getIntArray(R.array.ppm_value);

        short maxBandValue = 6;

        ArrayList<Band> ppmBandColorCodes = new ArrayList<>();
        for (int bandIndex = 0; bandIndex < maxBandValue; bandIndex++)
        {
            String name = bandNames[bandIndex];
            int color = bandColors[bandIndex];
            int value = bandValues[bandIndex];
            ppmBandColorCodes.add(new Band(name, color, (double) value));
        }

        return ppmBandColorCodes;
    }

    public double getFourBandsResistorValue(double firstBandValue, double secondBandValue,
                                            double multiplierBandValue)
    {
        double significantDigit = Parser.getDouble(String.format("%s%s",
                Parser.sanitizeDouble(firstBandValue),
                Parser.sanitizeDouble(secondBandValue)));

        return significantDigit * multiplierBandValue;
    }

    public double getFiveBandsResistorValue(double firstBandValue, double secondBandValue,
                                            double thirdBandValue, double multiplierBandValue)
    {
        double significantDigit = Parser.getDouble(String.format("%s%s%s",
                Parser.sanitizeDouble(firstBandValue),
                Parser.sanitizeDouble(secondBandValue),
                Parser.sanitizeDouble(thirdBandValue)));

        return significantDigit * multiplierBandValue;
    }

    private String formatResistorValue(double resistorValue)
    {
        if (resistorValue > 1000000)
        {
            String v = Parser.sanitizeDouble(resistorValue / 1000000);
            return String.format("%sM Ω", v);
        }
        else if (resistorValue > 1000)
        {
            String v = Parser.sanitizeDouble(resistorValue / 1000);
            return String.format("%sk Ω", v);
        }
        else
        {
            String v = Parser.sanitizeDouble(resistorValue);
            return String.format("%s Ω", v);
        }
    }

    public String getFormatedResistorNotation(double resistorValue)
    {
        String msg = context.getString(R.string.resistor_value);
        String resistorValueFormatted = formatResistorValue(resistorValue);
        return String.format("%s %s ", msg, resistorValueFormatted);
    }

    public String getFormatedResistorNotation(double resistorValue, double tolerance)
    {
        String msg = context.getString(R.string.resistor_value);
        String resistorValueFormatted = formatResistorValue(resistorValue);
        String t = Parser.sanitizeDouble(tolerance);
        return String.format("%s %s ±%s%s", msg, resistorValueFormatted, t, '%');
    }

    public String getFormatedResistorNotation(double resistorValue, double tolerance, double ppm)
    {
        String msg = context.getString(R.string.resistor_value);
        String resistorValueFormatted = formatResistorValue(resistorValue);
        String t = Parser.sanitizeDouble(tolerance);
        String p = Parser.sanitizeDouble(ppm);
        return String.format("%s %s ±%s%s %sppm", msg, resistorValueFormatted, t, '%', p);
    }

    public String getRangeValue(double resistorValue, double tolerance)
    {
        double percent = tolerance / 100;
        double range = resistorValue * percent;
        String min = formatResistorValue(resistorValue - range);
        String max = formatResistorValue(resistorValue + range);
        return context.getString(R.string.resistor_value_range, min, max);
    }
}
