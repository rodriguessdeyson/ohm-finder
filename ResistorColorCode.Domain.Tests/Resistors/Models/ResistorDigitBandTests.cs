using ResistorColorCode.Domain.Resistors.Models;
using FizzWare.NBuilder;
using FluentAssertions;
using Xunit;
using System.Reflection.Emit;

namespace ResistorColorCode.Domain.Tests.Resistors.Models
{
    public class ResistorDigitBandTests
    {
        private ResistorDigitBand sut;

        public ResistorDigitBandTests()
        {
            sut = Builder<ResistorDigitBand>.CreateNew().Build();
        }

        public class Constructor : ResistorDigitBandTests
        {
            [Fact]
            public void When_Valid_Expect_NewObject()
            {
                int id = 1;
                string label = "Teste 1";
                string color = "red";
                int value = 10;
                var digitBand = new ResistorDigitBand(id, label, color, value);

                digitBand.Id.Should().Be(id);
                digitBand.Label.Should().Be(label);
                digitBand.Color.Should().Be(color);
                digitBand.Value.Should().Be(value);
            }
        }

        public class SetId : ResistorDigitBandTests
        {
            [Xunit.Theory]
            [InlineData(1)]
            [InlineData(2)]
            public void When_IdValid_Expect_IdDefined(int id)
            {
                sut.SetId(id);
                sut.Id.Should().Be(id);
            }
        }

        public class SetLabel : ResistorDigitBandTests
        {
            [Xunit.Theory]
            [InlineData("")]
            [InlineData("    ")]
            public void When_LabelIsInvalid_Expect_ArgumentException(string label)
            {
                sut.Invoking(m => m.SetLabel(label)).Should().Throw<ArgumentException>();
            }
        }

        public class SetColor : ResistorDigitBandTests
        {
            [Xunit.Theory]
            [InlineData("lime")]
            [InlineData("")]
            [InlineData("rose")]
            public void When_ColorIsInvalid_Expect_ArgumentException(string color)
            {
                sut.Invoking(m => m.SetColor(color)).Should().Throw<ArgumentException>();
            }
        }

        public class SetValue : ResistorDigitBandTests
        {
            [Xunit.Theory]
            [InlineData(-58)]
            [InlineData(15)]
            public void When_ValueIsInvalid_Expect_ArgumentException(int value)
            {
                sut.Invoking(m => m.SetValue(value)).Should().Throw<ArgumentException>();
            }
        }
    }
}
