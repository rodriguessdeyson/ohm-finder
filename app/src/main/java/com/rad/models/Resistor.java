package com.rad.models;

import com.rad.models.types.ResistorType;

public class Resistor
{
    //#region Attributes

    /**
     * The id of the resistor.
     */
    private int id;

    /**
     * The type of the resistor.
     */
    private ResistorType type;

    /**
     * The first band of the resistor.
     */
    public int firstBand;

    /**
     * The second band of the resistor.
     */
    public int secondBand;

    /**
     * The third band of the resistor.
     */
    public Integer thirdBand = null;

    /**
     * The multiplier band of the resistor.
     */
    public int multiplierBand;

    /**
     * The tolerance band of the resistor.
     */
    public int toleranceBand;

    /**
     * The ppm band of the resistor.
     */
    public Integer ppmBand = null;

    /**
     * The value of the resistor.
     */
    private double value;

    /**
     * The tolerance value of the resistor.
     */
    private double toleranceValue;

    /**
     * The ppm value of the resistor.
     */
    private Double ppmValue = null;

    /**
     * The date and time of the resistor.
     */
    private String dateTime;

    //#endregion

    //#region Constructor

    public Resistor(ResistorType type, int firstBand, int secondBand, int multiplierBand,
                    int toleranceBand, double value, double toleranceValue)
    {
        setType(type);
        setFirstBand(firstBand);
        setSecondBand(secondBand);
        setMultiplierBand(multiplierBand);
        setToleranceBand(toleranceBand);

        setValue(value);
        setToleranceValue(toleranceValue);
    }

    public Resistor(ResistorType type, int firstBand, int secondBand, Integer thirdBand, int multiplierBand,
                    int toleranceBand, double value, double toleranceValue)
    {
        this(type, firstBand, secondBand, multiplierBand, toleranceBand, value, toleranceValue);
        setThirdBand(thirdBand);
    }

    public Resistor(ResistorType type, int firstBand, int secondBand, Integer thirdBand, int multiplierBand,
                    int toleranceBand, Integer ppmBand, double value, double toleranceValue, Double ppmValue)
    {
        this(type, firstBand, secondBand, thirdBand, multiplierBand, toleranceBand, value, toleranceValue);
        setPpmBand(ppmBand);
        setPpmValue(ppmValue);
    }

    public Resistor(int id, ResistorType type, int firstBand, int secondBand, Integer thirdBand,
                    int multiplierBand, int toleranceBand, Integer ppmBand, double value, double toleranceValue, Double ppmValue, String dateTime)
    {
        this(type, firstBand, secondBand, thirdBand, multiplierBand, toleranceBand, ppmBand, value, toleranceValue, ppmValue);
        setId(id);
        setDateTime(dateTime);
    }

    //#endregion


    public void setId(int id) {
        this.id = id;
    }

    public void setType(ResistorType type)
    {
        this.type = type;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setFirstBand(int firstBand) {
        this.firstBand = firstBand;
    }

    public void setSecondBand(int secondBand) {
        this.secondBand = secondBand;
    }

    public void setThirdBand(Integer thirdBand) {
        this.thirdBand = thirdBand;
    }

    public void setMultiplierBand(int multiplierBand) {
        this.multiplierBand = multiplierBand;
    }

    public void setToleranceBand(int toleranceBand) {
        this.toleranceBand = toleranceBand;
    }

    public void setPpmBand(Integer ppmBand) {
        this.ppmBand = ppmBand;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setToleranceValue(double toleranceValue) {
        this.toleranceValue = toleranceValue;
    }

    public void setPpmValue(Double ppmValue) {
        this.ppmValue = ppmValue;
    }

    public int getId() {
        return id;
    }

    public int getFirstBand() {
        return firstBand;
    }

    public int getSecondBand() {
        return secondBand;
    }

    public int getMultiplierBand() {
        return multiplierBand;
    }

    public int getToleranceBand() {
        return toleranceBand;
    }

    public Integer getPpmBand() {
        return ppmBand;
    }

    public Integer getThirdBand() {
        return thirdBand;
    }

    public double getToleranceValue() {
        return toleranceValue;
    }

    public double getPpmValue() {
        return ppmValue;
    }

    public double getValue() {
        return value;
    }

    public ResistorType getType() {
        return type;
    }

    public String getDateTime() {
        return dateTime;
    }
}
