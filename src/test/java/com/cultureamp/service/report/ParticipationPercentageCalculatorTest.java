package com.cultureamp.service.report;

import com.cultureamp.entity.SurveyResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ParticipationPercentageCalculatorTest {
  @Test
  void shouldReturnParticipationPercentage() {
    OffsetDateTime dateTime = OffsetDateTime.parse("2014-07-28T20:35:41+00:00");
    List<SurveyResponse> responses = List.of(
        new SurveyResponse(1L, "abc@ab.com", dateTime, List.of(4, 5)),
        new SurveyResponse(1L, "xyz@ab.com", dateTime, List.of(5, 5)),
        new SurveyResponse(1L, "efg@ab.com", null, List.of(4, 5))
    );

    ParticipationCount participationCount = mock(ParticipationCount.class);
    ParticipationPercentageCalculator percentageCalculator = new ParticipationPercentageCalculator(participationCount);
    when(participationCount.participationCountFrom(responses)).thenReturn(2L);

    BigDecimal participationPercentage = percentageCalculator.participationPercentageFor(responses);

    assertThat(participationPercentage, is(new BigDecimal("66.67")));
  }

  @Test
  void shouldReturn75ParticipationPercentage() {
    OffsetDateTime dateTime = OffsetDateTime.parse("2014-07-28T20:35:41+00:00");
    List<SurveyResponse> responses = List.of(
        new SurveyResponse(1L, "abc@ab.com", dateTime, List.of(4, 5)),
        new SurveyResponse(1L, "xyz@ab.com", dateTime, List.of(5, 5)),
        new SurveyResponse(1L, "xyz@ab.com", dateTime, List.of(5, 5)),
        new SurveyResponse(1L, "efg@ab.com", null, List.of(4, 5))
    );

    ParticipationCount participationCount = mock(ParticipationCount.class);
    ParticipationPercentageCalculator percentageCalculator = new ParticipationPercentageCalculator(participationCount);
    when(participationCount.participationCountFrom(responses)).thenReturn(3L);

    BigDecimal participationPercentage = percentageCalculator.participationPercentageFor(responses);

    assertThat(participationPercentage, is(new BigDecimal("75.00")));
  }

}