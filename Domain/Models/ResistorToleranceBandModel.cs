using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ResistorColorCode.Domain.Models
{
    public class ResistorToleranceBandModel
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
        public double Value { get; set; }

        /// <summary>
        /// Inicializa um objeto do tipo ResistorBandModel.
        /// </summary>
        /// <param name="id">Identificador da faixa</param>
        /// <param name="label">Nome da faixa</param>
        /// <param name="color">Cor da faixa</param>
        /// <param name="value">Valor da faixa</param>
        public ResistorToleranceBandModel(int id, string label, string color, double value)
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
            string[] allowedColors = ["silver", "gold", "brown", "red", "green", "blue", "violet"];

            if (!allowedColors.Contains(color))
                throw new ArgumentException($"A cor não é permitida. Cor: {color}: Permitidas: {string.Join('n', allowedColors)}", nameof(color));

            ArgumentNullException.ThrowIfNull(color);
            if (Color.TryParse(color, out Color c))
            {
                Color = c;
                return;
            }
        }

        private void SetValue(double value)
        {
            if (value > 1000000000)
                throw new ArgumentOutOfRangeException(nameof(value), "Value deve ser menor que 1000000000");

            Value = value;
        }
    }
}
