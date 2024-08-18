﻿using FizzWare.NBuilder;
using FluentAssertions;
using ResistorColorCode.Domain.Resistors.Models;
using Xunit;

namespace ResistorColorCode.Domain.Tests.Resistors.Models
{
    public class ResistorMultiplierBandTests
    {
        private ResistorMultiplierBand sut;

        public ResistorMultiplierBandTests()
        {
            sut = Builder<ResistorMultiplierBand>.CreateNew().Build();
        }

        public class Constructor : ResistorMultiplierBandTests
        {
            [Fact]
            public void When_Valid_Expect_NewObject()
            {
                int id = 1;
                string label = "Teste 1";
                string color = "silver";
                double value = 0.01;
                var multiplierBand = new ResistorMultiplierBand(id, label, color, value);

                multiplierBand.Id.Should().Be(id);
                multiplierBand.Label.Should().Be(label);
                multiplierBand.Color.Should().Be(color);
                multiplierBand.Value.Should().Be(value);
            }
        }

        public class SetId : ResistorMultiplierBandTests
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

        public class SetLabel : ResistorMultiplierBandTests
        {
            [Xunit.Theory]
            [InlineData("")]
            [InlineData("    ")]
            public void When_LabelIsInvalid_Expect_ArgumentException(string label)
            {
                sut.Invoking(m => m.SetLabel(label)).Should().Throw<ArgumentException>();
            }
        }

        public class SetColor : ResistorMultiplierBandTests
        {
            [Xunit.Theory]
            [InlineData("")]
            [InlineData("cyan")]
            [InlineData("lime")]
            public void When_ColorIsInvalid_Expect_ArgumentException(string color)
            {
                sut.Invoking(m => m.SetColor(color)).Should().Throw<ArgumentException>();
            }
        }

        public class SetValue : ResistorMultiplierBandTests
        {
            [Xunit.Theory]
            [InlineData(-58)]
            [InlineData(15)]
            [InlineData(10^-3)]
            public void When_ValueIsInvalid_Expect_ArgumentException(int value)
            {
                sut.Invoking(m => m.SetValue(value)).Should().Throw<ArgumentException>();
            }
        }
    }
}
