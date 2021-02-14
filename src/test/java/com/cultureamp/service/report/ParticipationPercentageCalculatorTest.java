package com.cultureamp.service.report;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class ParticipationPercentageCalculatorTest {
  @Test
  void shouldReturnParticipationPercentageWithTwoDecimals() {
    ParticipationPercentageCalculator percentageCalculator = new ParticipationPercentageCalculator();

    BigDecimal participationPercentage = percentageCalculator.participationPercentageFor(2L, 3);

    assertThat(participationPercentage, is(new BigDecimal("66.67")));
  }

  @Test
  void shouldReturn75ParticipationPercentage() {

    ParticipationPercentageCalculator percentageCalculator = new ParticipationPercentageCalculator();

    BigDecimal participationPercentage = percentageCalculator.participationPercentageFor(3L, 4);

    assertThat(participationPercentage, is(new BigDecimal("75.00")));
  }

}