## Resistor Color Code

Resistors are color-coded to indicate their value and tolerance. These color codes follow international standards , [IEC 60062:2016](https://en.wikipedia.org/wiki/IEC_60062:2016), and help in easily identifying a resistor's characteristics without needing to measure them.

The color bands on a resistor denote the significant digits, the multiplier, and tolerance, with some resistors also indicating reliability or temperature coefficient.

## **Color band system**

To properly read these codes, the resistor should be held so that the color bands are read from left to right. There is typically a **gap** between the third band and the tolerance band to help distinguish the correct reading direction. The following image states it:

![image.png](https://upload.wikimedia.org/wikipedia/commons/thumb/6/6e/4-Band_Resistor.svg/1920px-4-Band_Resistor.svg.png)

1. The first significant figure of component value (left side)
2. The second significant figure (some precision resistors have a third significant figure, and thus five bands).
3. The decimal multiplier (number of trailing zeroes, or power of 10 multiplier)
4. If present, indicates tolerance of value in percent (no band means 20%)

It is safe to state that the reading should start `beginning with the end that has the most bands`.

## Color Code Table:

The standard color code per [IEC 60062:2016](https://en.wikipedia.org/wiki/IEC_60062:2016) is as follows:

| Color | Digit Value | Multiplier (× 10^x) | Tolerance (%) |
| --- | --- | --- | --- |
| Black | 0 | 1 | - |
| Brown | 1 | 10 | ±1% |
| Red | 2 | 100 | ±2% |
| Orange | 3 | 1,000 | - |
| Yellow | 4 | 10,000 | - |
| Green | 5 | 100,000 | ±0.5% |
| Blue | 6 | 1,000,000 | ±0.25% |
| Violet | 7 | 10,000,000 | ±0.1% |
| Gray | 8 | 100,000,000 | ±0.05% |
| White | 9 | 1,000,000,000 | - |
| Gold | - | 0.1 | ±5% |
| Silver | - | 0.01 | ±10% |
| None | - | - | ±20% |

## Resistor Types Reading

There are 3 most comom resistor types, the 4 band resistor, 5 band resistor and the 6 band resistor.

- **4-Band Resistor Color Code**
    
    A 4-band resistor has four color stripes on it:
    
    - **Band 1:** First significant digit.
    - **Band 2:** Second significant digit.
    - **Band 3:** Multiplier (power of 10).
    - **Band 4:** Tolerance (precision of the resistor).
    
    ### Example:
    
    For a 4-band resistor with the following colors:
    
    - **Red, Violet, Orange, Gold**
    
    This can be read as:
    
    - Red = 2 (first digit)
    - Violet = 7 (second digit)
    - Orange = × 1,000 (multiplier)
    - Gold = ±5% (tolerance)
    
    So, the resistor value is: **27 × 1,000 = 27,000 ohms (27 kΩ) ±5%.**
    
- **5-Band Resistor Color Code**
    
    A 5-band resistor has an additional band for more precision:
    
    - **Band 1:** First significant digit.
    - **Band 2:** Second significant digit.
    - **Band 3:** Third significant digit.
    - **Band 4:** Multiplier (power of 10).
    - **Band 5:** Tolerance.
    
    The color code for digits and tolerance is the same as for 4-band resistors, but the third band adds more precision.
    
    ### Example:
    
    For a 5-band resistor with the following colors:
    
    - **Brown, Black, Black, Red, Brown**
    
    This can be read as:
    
    - Brown = 1 (first digit)
    - Black = 0 (second digit)
    - Black = 0 (third digit)
    - Red = × 100 (multiplier)
    - Brown = ±1% (tolerance)
    
    So, the resistor value is: **100 × 100 = 10,000 ohms (10 kΩ) ±1%.**
    
- 6-Band Resistor Color Code
    
    A 6-band resistor is similar to a 5-band resistor but with an extra band that indicates the **temperature coefficient (ppm/°C)**. This helps in determining how much the resistor's value changes with temperature.
    
    - **Band 1:** First significant digit.
    - **Band 2:** Second significant digit.
    - **Band 3:** Third significant digit.
    - **Band 4:** Multiplier (power of 10).
    - **Band 5:** Tolerance.
    - **Band 6:** Temperature coefficient (ppm/°C).
    
    ### Temperature Coefficient Colors:
    
    | Color | Temperature Coefficient (ppm/°C) |
    | --- | --- |
    | Brown | 100 |
    | Red | 50 |
    | Orange | 15 |
    | Yellow | 25 |
    | Blue | 10 |
    | Violet | 5 |
    
    ### Example:
    
    For a 6-band resistor with the following colors:
    
    - **Red, Black, Yellow, Orange, Brown, Blue**
    
    This can be read as:
    
    - Red = 2 (first digit)
    - Black = 0 (second digit)
    - Yellow = 4 (third digit)
    - Orange = × 1,000 (multiplier)
    - Brown = ±1% (tolerance)
    - Blue = 10 ppm/°C (temperature coefficient)
    
    So, the resistor value is:
    
    **204 × 1,000 = 204,000 ohms (204 kΩ) ±1%**, and it has a temperature coefficient of **10 ppm/°C**.