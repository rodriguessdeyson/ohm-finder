namespace ResistorColorCode.Domain.Models
{
    public class ResistorDigitBandModel
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
        public Color? Color { get; set; }

        /// <summary>
        /// Valor da faixa
        /// </summary>
        public int Value { get; set; }

        /// <summary>
        /// Inicializa um objeto do tipo ResistorBandModel.
        /// </summary>
        /// <param name="id">Identificador da faixa</param>
        /// <param name="label">Nome da faixa</param>
        /// <param name="color">Cor da faixa</param>
        /// <param name="value">Valor da faixa</param>
        public ResistorDigitBandModel(int id, string label, string color, int value)
        {
            SetId(id);
            SetLabel(label);
            SetColor(color);
            SetValue(value);
        }

        private void SetId(int id)
        {
            Id = id;
        }

        private void SetLabel(string label)
        {
            ArgumentException.ThrowIfNullOrWhiteSpace(nameof(label));
            Label = label;
        }

        private void SetColor(string color)
        {
            string[] allowedColors = ["black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"];

            if (!allowedColors.Contains(color))
                throw new ArgumentException($"A cor não é permitida. Cor: {color}: Permitidas: {string.Join('n', allowedColors)}", nameof(color));

            ArgumentNullException.ThrowIfNull(color);
            if (Color.TryParse(color, out Color c))
            {
                Color = c;
                return;
            }
        }

        private void SetValue(int value)
        {
            if (value < 0 && value > 9)
                throw new ArgumentOutOfRangeException(nameof(value), "Value deve estar entre 0 e 9");

            Value = value;
        }
    }
}
