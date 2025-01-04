package com.rad.models;

import java.io.Serializable;

public class Band implements Serializable
{
    //#region Attributes

    /**
     * The name of the band color.
     */
    private final String name;

    /**
     * The color of the band.
     */
    private final int color;

    /**
     * The value of the band color.
     */
    private final double value;

    //#endregion

    //#region Constructor

    /**
     * Initialize a new object of type Band.
     * @param name The name of the band color.
     * @param color The color of the band.
     * @param value The value of the band color.
     */
    public Band(String name, int color, double value)
    {
        this.name = name;
        this.value = value;
        this.color = color;
    }

    //#endregion

    //#region Getters

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public double getValue() { return value;}

    //#endregion
}
