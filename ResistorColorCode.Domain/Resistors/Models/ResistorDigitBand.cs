namespace ResistorColorCode.Domain.Resistors.Models
{
    public class ResistorDigitBand
    {
        /// <summary>
        /// Identificador da faixa.
        /// </summary>
        public int Id { get; protected set; }

        /// <summary>
        /// Nome da faixa.
        /// </summary>
        public string? Label { get; protected set; }

        /// <summary>
        /// Cor da faixa.
        /// </summary>
        public string? Color { get; protected set; } = "cyan";

        /// <summary>
        /// Valor da faixa
        /// </summary>
        public int Value { get; protected set; }

        /// <summary>
        /// Inicializa um objeto do tipo ResistorBandModel.
        /// </summary>
        public ResistorDigitBand() { }

        /// <summary>
        /// Inicializa um objeto do tipo ResistorBandModel.
        /// </summary>
        /// <param name="id">Identificador da faixa</param>
        /// <param name="label">Nome da faixa</param>
        /// <param name="color">Cor da faixa</param>
        /// <param name="value">Valor da faixa</param>
        public ResistorDigitBand(int id, string label, string color, int value)
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

            string[] allowedColors = ["black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"];

            if (!allowedColors.Contains(color))
                throw new ArgumentException($"A cor não é permitida. Cor: {color}: Permitidas: {string.Join(',', allowedColors)}", nameof(color));

            Color = color;
        }

        public void SetValue(int value)
        {
            if (value < 0 || value > 9)
                throw new ArgumentOutOfRangeException(nameof(value), "Value deve estar entre 0 e 9");

            Value = value;
        }
    }
}
