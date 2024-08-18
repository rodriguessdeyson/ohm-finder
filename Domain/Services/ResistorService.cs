using ResistorColorCode.Domain.Models;

namespace ResistorColorCode.Domain.Services
{
    public class ResistorService
    {
        public static IList<ResistorDigitBandModel> DigitsBandValues()
        {
            return
            [
                new (0,  "",         "cyan",   -1),
                new (1,  "Preto",    "black", 0),
                new (2,  "Marrom",   "brown", 1),
                new (3,  "Vermelho", "red", 2),
                new (4,  "Laranja",  "orange", 3),
                new (5,  "Amarelo",  "yellow", 4),
                new (6,  "Verde",    "green", 5),
                new (7,  "Azul",     "blue", 6),
                new (8,  "Violeta",  "violet", 7),
                new (9,  "Cinza",    "grey", 8),
                new (10, "Branco",   "white", 9),
            ];
        }

        public static IList<ResistorDigitBandModel> MultiplierBandValues()
        {
            return
            [
                new (0,  "",         "cyan",   -1),
                new (1,  "Preto",    "black",  1),
                new (2,  "Marrom",   "brown",  10),
                new (3,  "Vermelho", "red",    100),
                new (4,  "Laranja",  "orange", 1000),
                new (5,  "Amarelo",  "yellow", 10000),
                new (6,  "Verde",    "green",  100000),
                new (7,  "Azul",     "blue",   1000000),
                new (8,  "Violeta",  "violet", 10000000),
                new (9,  "Cinza",    "grey",   100000000),
                new (10, "Branco",   "white",  1000000000),
                new (11, "Ouro",     "gold",   0.1),
                new (11, "Prata",    "silver", 0.01),

            ];
        }
    <option class="multiplier" value="1">black</option>
    <option class="multiplier" value="10">brown</option>
    <option class="multiplier" value="100">red</option>
    <option class="multiplier" value="1000">orange</option>
    <option class="multiplier" value="10000">yellow</option>
    <option class="multiplier" value="100000">green</option>
    <option class="multiplier" value="1000000">blue</option>
    <option class="multiplier" value="100000000">violet</option>
    <option class="multiplier" value="1000000000">grey</option>
    <option class="multiplier" value="10000000000">white</option>
    <option class="multiplier" value="0.1">gold</option>
    <option class="multiplier" value="0.01">silver</option>
        public static IList<ResistorDigitBandModel> ToleranceBandValues()
        {
            return
            [
                new (0,  "",         "cyan",   -1),
                new (1,  "Preto",    "#0F0F0F", 0),
                new (2,  "Marrom",   "#964B00", 1),
                new (3,  "Vermelho", "#FF002F", 2),
                new (4,  "Laranja",  "#FF7B00", 3),
                new (5,  "Amarelo",  "#F2FF00", 4),
                new (6,  "Verde",    "#00FF11", 5),
                new (7,  "Azul",     "#4674FF", 6),
                new (8,  "Violeta",  "#8000FF", 7),
                new (9,  "Cinza",    "#7F7F7F", 8),
                new (10, "Branco",   "#FFFFFF", 9),
            ];
        }

        public static IList<ResistorDigitBandModel> TemperatureCoeficientBandValues()
        {
            return
            [
                new (0,  "",         "cyan",   -1),
                new (0,  "",         "cyan",   -1),
                new (1,  "Preto",    "#0F0F0F", 0),
                new (2,  "Marrom",   "#964B00", 1),
                new (3,  "Vermelho", "#FF002F", 2),
                new (4,  "Laranja",  "#FF7B00", 3),
                new (5,  "Amarelo",  "#F2FF00", 4),
                new (6,  "Verde",    "#00FF11", 5),
                new (7,  "Azul",     "#4674FF", 6),
                new (8,  "Violeta",  "#8000FF", 7),
                new (9,  "Cinza",    "#7F7F7F", 8),
                new (10, "Branco",   "#FFFFFF", 9),
            ];
        }
    }
}

