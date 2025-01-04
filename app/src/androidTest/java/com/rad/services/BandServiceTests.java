package com.rad.services;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.rad.models.Band;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class BandServiceTests {

    private final BandService sut;

    public BandServiceTests()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        sut = new BandService(appContext);
    }

    @Test
    public void when_getSignificantBandsCodesIsCalled_then_return_10_bands()
    {
        ArrayList<Band> bandColorCodes = sut.getSignificantBandsCodes();
        assertEquals(10, bandColorCodes.size());
    }

    @Test
    public void when_getMultiplierBandColorCodesIsCalled_then_return_12_bands()
    {
        ArrayList<Band> bandColorCodes = sut.getMultiplierBandColorCodes();
        assertEquals(12, bandColorCodes.size());
    }

    @Test
    public void when_getToleranceBandColorCodesIsCalled_then_return_8_bands()
    {
        ArrayList<Band> bandColorCodes = sut.getToleranceBandColorCodes();
        assertEquals(8, bandColorCodes.size());
    }

    @Test
    public void when_getPPMBandColorCodesIsCalled_then_return_6_bands()
    {
        ArrayList<Band> bandColorCodes = sut.getPPMBandColorCodes();
        assertEquals(6, bandColorCodes.size());
    }
    @Test
    public void when_getFourBandsResistorValueIsCalled_then_return_expected_value()
    {
        double firstBand = 5;
        double secondBand = 2;
        double multiplierBand = 1000;
        double expectedValue = 52000;

        double actualValue = sut.getFourBandsResistorValue(firstBand, secondBand, multiplierBand);
        assertEquals(expectedValue, actualValue, 0.01);
    }

    @Test
    public void when_getFormatedResistorNotationIsCalled_then_return_expected_value()
    {
        double resistorValue = 52000;
        double tolerance = 5;
        String expectedValue = "Resistor value: 52k Ω 5%";

        String actualValue = sut.getFormatedResistorNotation(resistorValue, tolerance);
        assertEquals(expectedValue, actualValue);
    }

}