package com.rad.models;

import static org.junit.Assert.assertEquals;
import static java.text.DateFormat.getDateTimeInstance;

import com.rad.models.types.ResistorType;

import org.junit.Test;

import java.text.DateFormat;
import java.util.Date;

public class ResistorTests
{
	int id = 1;
	ResistorType type = ResistorType.SIX_BAND;
	int firstBand = 25;
	int secondBand = 50;
	Integer thirdBand = 30;
	int multiplierBand = 45;
	int toleranceBand = 6;
	Integer ppmBand = 10;
	double value = 250;
	double toleranceValue = 4;
	Double ppmValue = 1.0;
	String dateTime = getDateTimeInstance().format(new Date());
	
	private Resistor sut;
	
	public ResistorTests()
	{
		sut = new Resistor(id, type, firstBand, secondBand, thirdBand, multiplierBand, toleranceBand, ppmBand, value, toleranceValue, ppmValue, dateTime);
	}
	
	@Test
	public void when_constructorIsCalled_then_objectIsCreate()
	{
		sut = new Resistor(id, type, firstBand, secondBand, thirdBand, multiplierBand, toleranceBand, ppmBand, value, toleranceValue, ppmValue, dateTime);
		assertEquals(id, sut.getId());
		assertEquals(type, sut.getType());
		assertEquals(firstBand, sut.getFirstBand());
		assertEquals(secondBand, sut.getSecondBand());
		assertEquals(thirdBand, sut.getThirdBand());
		assertEquals(multiplierBand, sut.getMultiplierBand());
		assertEquals(toleranceBand, sut.getToleranceBand());
		assertEquals(ppmBand, sut.getPpmBand());
		assertEquals(value, sut.getValue(), 0.01);
		assertEquals(toleranceValue, sut.getToleranceValue(), 0.01);
		assertEquals(ppmValue, sut.getPpmValue(), 0.01);
		assertEquals(dateTime, sut.getDateTime());
	}
	
	@Test
	public void when_setIdMethodIsCalled_then_idIsChanged()
	{
		int id = 2;
		sut.setId(id);
		assertEquals(id, sut.getId());
	}

	@Test
	public void when_setType_then_typeIsChanged()
	{
		ResistorType type = ResistorType.FIVE_BAND;
		sut.setType(type);
		assertEquals(type, sut.getType());
	
	}
	@Test
	public void when_setDateTime_then_dateTimeIsChanged()
	{
		Date date = new Date();
		DateFormat dateFormat = getDateTimeInstance();
		sut.setDateTime(dateFormat.format(date));
	}

	@Test
	public void when_setFirstBand_then_firstBandIsChanged()
	{
		int firstBand = 50;
		sut.setFirstBand(firstBand);
		assertEquals(firstBand, sut.getFirstBand());
	}

	@Test
	public void when_setSecondBand_then_secondBandIsChanged()
	{
		int secondBand = 100;
		sut.setSecondBand(secondBand);
		assertEquals(secondBand, sut.getSecondBand());
	}

	@Test
	public void when_setThirdBand_then_thirdBandIsChanged()
	{
		Integer thirdBand = 150;
		sut.setThirdBand(thirdBand);
		assertEquals(thirdBand, sut.getThirdBand());
	}

	@Test
	public void when_setMultiplierBand_then_multiplierBandIsChanged()
	{
		int multiplierBand = 200;
		sut.setMultiplierBand(multiplierBand);
		assertEquals(multiplierBand, sut.getMultiplierBand());
	}

	@Test
	public void when_setToleranceBand_then_toleranceBandIsChanged()
	{
		int toleranceBand = 250;
		sut.setToleranceBand(toleranceBand);
		assertEquals(toleranceBand, sut.getToleranceBand());
	}

	@Test
	public void when_setPpmBand_then_ppmBandIsChanged()
	{
		Integer ppmBand = 300;
		sut.setPpmBand(ppmBand);
		assertEquals(ppmBand, sut.getPpmBand());
	}

	@Test
	public void when_setValue_then_valueIsChanged()
	{
		int value = 350;
		sut.setValue(value);
		assertEquals(value, sut.getValue(), 0.01);
	}
	@Test
	public void when_setToleranceValue_then_toleranceValueIsChanged()
	{
		double toleranceValue = 400.5;
		sut.setToleranceValue(toleranceValue);
		assertEquals(toleranceValue, sut.getToleranceValue(), 0.01);
	}
	@Test
	public void when_setPpmValue_then_ppmValueIsChanged()
	{
		double ppmValue = 450.0;
		sut.setPpmValue(ppmValue);
		assertEquals(ppmValue, sut.getPpmValue(), 0.01);
	}
}
