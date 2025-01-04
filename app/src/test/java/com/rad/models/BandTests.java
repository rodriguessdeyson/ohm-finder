package com.rad.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BandTests
{
	private final Band sut;
	
	public BandTests()
	{
		String colorName = "black";
		int color = -16777216;
		int colorValue = 0;
		
		sut = new Band(colorName, color, colorValue);
	}

	@Test
	public void when_getNameIsCalled_then_return_black()
	{
		String result = sut.getName();
		assertEquals("black", result);
	}
	
	@Test
	public void when_getColorIsCalled_then_return_black()
	{
		int color = sut.getColor();
		assertEquals(-16777216, color);
	}
	
	@Test
	public void when_getValueIsCalled_then_return_Value()
	{
		double value = sut.getValue();
		assertEquals(0, value, 0.01);
	}
}