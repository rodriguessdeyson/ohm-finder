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
                new (1,  "Preto",    "black",  0),
                new (2,  "Marrom",   "brown",  1),
                new (3,  DomainResources.RedBand, "red",    2),
                new (4,  "Laranja",  "orange", 3),
                new (5,  "Amarelo",  "yellow", 4),
                new (6,  "Verde",    "green",  5),
                new (7,  "Azul",     "blue",   6),
                new (8,  "Violeta",  "violet", 7),
                new (9,  "Cinza",    "grey",   8),
                new (10, "Branco",   "white",  9),
            ];
        }

        public static IList<ResistorMultiplierBand> MultiplierBandValues()
        {
            return
            [
                new (1,  "Preto",    "black",  Math.Pow(10, 0)),
                new (2,  "Marrom",   "brown",  Math.Pow(10, 1)),
                new (3,  "Vermelho", "red",    Math.Pow(10, 2)),
                new (4,  "Laranja",  "orange", Math.Pow(10, 3)),
                new (5,  "Amarelo",  "yellow", Math.Pow(10, 4)),
                new (6,  "Verde",    "green",  Math.Pow(10, 5)),
                new (7,  "Azul",     "blue",   Math.Pow(10, 6)),
                new (8,  "Violeta",  "violet", Math.Pow(10, 7)),
                new (9, "Ouro",      "gold",   Math.Pow(10, -1)),
                new (10, "Prata",    "silver", Math.Pow(10, -2))
            ];
        }

        public static IList<ResistorToleranceBand> ToleranceBandValues()
        {
            return
            [
                new (1, "Ouro",      "gold",     5),
                new (2, "Prata",     "silver",  10),
                new (3,  "Marrom",   "brown",    1),
                new (4,  "Vermelho", "red",      2),
                new (5,  "Verde",    "green",  0.5),
                new (6,  "Azul",     "blue",  0.25),
                new (7,  "Violeta",  "violet", 0.1),
            ];
        }

        public static IList<ResistorDigitBand> TemperatureCoeficientBandValues()
        {
            return
            [
                new (1,  "Marrom",   "#964B00", 100),
                new (2,  "Vermelho", "#FF002F", 50),
                new (3,  "Laranja",  "#FF7B00", 15),
                new (4,  "Amarelo",  "#F2FF00", 25),
            ];
        }
    }
}

