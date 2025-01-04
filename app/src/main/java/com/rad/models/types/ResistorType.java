package com.rad.models.types;

public enum ResistorType
{
    /**
     * 4 band resistor.
     */
    FOUR_BAND(0),

    /**
     * 5 band resistor.
     */
    FIVE_BAND(1),

    /**
     * 6 band resistor.
     */
    SIX_BAND(2),

    /**
     * None selected.
     */
    NONE(3);

    /**
     * Selected enum.
     */
    private final int resistorType;

    /**
     * Enum constructor.
     * @param control Index of the enum.
     */
    ResistorType(int control)
    {
        this.resistorType = control;
    }
}
