namespace ResistorColorCode.Domain.Resistors.Models
{
    public class ResistorMultiplierBand
    {
        /// <summary>
        /// Identificador da faixa.
        /// </summary>
        public int Id { get; set; }

        /// <summary>
        /// Nome da faixa.
        /// </summary>
        public string? Label { get; set; }

        /// <summary>
        /// Cor da faixa.
        /// </summary>
        public string? Color { get; set; } = "cyan";

        /// <summary>
        /// Valor da faixa
        /// </summary>
        public double Value { get; set; }

        /// <summary>
        /// Inicializa um objeto do tipo ResistorBandModel.
        /// </summary>
        public ResistorMultiplierBand() {}

        /// <summary>
        /// Inicializa um objeto do tipo ResistorBandModel.
        /// </summary>
        /// <param name="id">Identificador da faixa</param>
        /// <param name="label">Nome da faixa</param>
        /// <param name="color">Cor da faixa</param>
        /// <param name="value">Valor da faixa</param>
        public ResistorMultiplierBand(int id, string label, string color, double value)
        {
            SetId(id);
            SetLabel(label);
            SetColor(color);
            SetValue(value);
        }

        public void SetId(int id)
        {
            Id = id;
        }

        public void SetLabel(string label)
        {
            ArgumentException.ThrowIfNullOrWhiteSpace(label);

            Label = label;
        }

        public void SetColor(string color)
        {
            ArgumentException.ThrowIfNullOrWhiteSpace(color);

            string[] allowedColors = ["silver", "gold", "black", "brown", "red", "orange", "yellow", "green", "blue", "violet"];

            if (!allowedColors.Contains(color))
                throw new ArgumentException($"A cor não é permitida. Cor: {color}: Permitidas: {string.Join('n', allowedColors)}", nameof(color));

            Color = color;
        }

        public void SetValue(double value)
        {
            double[] allowedValues = [
                Math.Pow(10, 0),
                Math.Pow(10, 1),
                Math.Pow(10, 2),
                Math.Pow(10, 3),
                Math.Pow(10, 4),
                Math.Pow(10, 5),
                Math.Pow(10, 6),
                Math.Pow(10, 7),
                Math.Pow(10, -1),
                Math.Pow(10, 2),
            ];

            if (!allowedValues.Contains(value))
                throw new ArgumentException($"O valor não é permitida. Valor: {value}: Permitidos: {string.Join(',', allowedValues)}", nameof(value));

            Value = value;
        }
    }
}
