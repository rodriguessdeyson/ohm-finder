namespace ResistorColorCode.Domain.Resistors.Models
{
	public class ResistorPPMBand
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
		/// Inicializa um objeto do tipo ResistorPPMBand.
		/// </summary>
		public ResistorPPMBand() { }

		/// <summary>
		/// Inicializa um objeto do tipo ResistorPPMBand.
		/// </summary>
		/// <param name="id">Identificador da faixa</param>
		/// <param name="label">Nome da faixa</param>
		/// <param name="color">Cor da faixa</param>
		/// <param name="value">Valor da faixa</param>
		public ResistorPPMBand(int id, string label, string color, double value)
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
			ArgumentException.ThrowIfNullOrWhiteSpace(nameof(label));

			Label = label;
		}

		public void SetColor(string color)
		{
			ArgumentException.ThrowIfNullOrWhiteSpace(color);

			string[] allowedColors = ["brown", "red", "orange", "yellow", "blue", "violet"];
			if (!allowedColors.Contains(color))
				throw new ArgumentException($"A cor não é permitida. Cor: {color}: Permitidas: {string.Join(',', allowedColors)}", nameof(color));

			Color = color;
		}

		public void SetValue(double value)
		{
			double[] allowedValues = [15, 25, 50, 100];

			if (!allowedValues.Contains(value))
				throw new ArgumentException($"O valor não é permitida. Valor: {value}: Permitidos: {string.Join(',', allowedValues)}", nameof(value));

			Value = value;
		}
	}
}
