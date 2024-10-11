using ResistorColorCode.Domain.Resistors.Models;
using ResistorColorCode.Domain.Resources.Languages;

namespace ResistorColorCode.Domain.Resistors.Services
{
    public class ResistorService
    { 
        public static IList<ResistorDigitBand> DigitsBandValues()
        {
            return
            [
                new (1,  DomainResources.BlackBand,  "black",  0),
                new (2,  DomainResources.BrownBand,  "brown",  1),
                new (3,  DomainResources.RedBand,    "red",    2),
                new (4,  DomainResources.OrangeBand, "orange", 3),
                new (5,  DomainResources.YellowBand, "yellow", 4),
                new (6,  DomainResources.GreenBand,  "green",  5),
                new (7,  DomainResources.BlueBand,   "blue",   6),
                new (8,  DomainResources.VioletBand, "violet", 7),
                new (9,  DomainResources.GreyBand,   "grey",   8),
                new (10, DomainResources.WhiteBand,  "white",  9),
            ];
        }

        public static IList<ResistorMultiplierBand> MultiplierBandValues()
        {
            return
            [
                new (1,  DomainResources.BlackMultiplierBand,  "black",  Math.Pow(10, 0)),
                new (2,  DomainResources.BrownMultiplierBand,  "brown",  Math.Pow(10, 1)),
                new (3,  DomainResources.RedMultiplierBand,    "red",    Math.Pow(10, 2)),
                new (4,  DomainResources.OrangeMultiplierBand, "orange", Math.Pow(10, 3)),
                new (5,  DomainResources.YellowMultiplierBand, "yellow", Math.Pow(10, 4)),
                new (6,  DomainResources.GreenMultiplierBand,  "green",  Math.Pow(10, 5)),
                new (7,  DomainResources.BlueMultiplierBand,   "blue",   Math.Pow(10, 6)),
                new (8,  DomainResources.VioletMultiplierBand, "violet", Math.Pow(10, 7)),
                new (9,  DomainResources.GoldMultiplierBand,   "gold",   Math.Pow(10, -1)),
                new (10, DomainResources.SilverMultiplierBand, "silver", Math.Pow(10, -2))
            ];
        }

        public static IList<ResistorToleranceBand> ToleranceBandValues()
        {
            return
            [
                new (1, DomainResources.GoldToleranceBand,   "gold",     5),
                new (2, DomainResources.SilverToleranceBand, "silver",  10),
                new (3, DomainResources.BrownToleranceBand,  "brown",    1),
                new (4, DomainResources.RedToleranceBand,    "red",      2),
                new (5, DomainResources.GreenToleranceBand,  "green",  0.5),
                new (6, DomainResources.BlueToleranceBand,   "blue",  0.25),
                new (7, DomainResources.VioletToleranceBand, "violet", 0.1),
            ];
        }

        public static IList<ResistorPPMBand> TemperatureCoeficientBandValues()
        {
            return
            [
                new (1,  DomainResources.BrownBand,  "brown", 100),
                new (2,  DomainResources.RedBand,    "red", 50),
                new (3,  DomainResources.OrangeBand, "orange", 15),
                new (4,  DomainResources.YellowBand, "yellow", 25),
            ];
        }
    }
}