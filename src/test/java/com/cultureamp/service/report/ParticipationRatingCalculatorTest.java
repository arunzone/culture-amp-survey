package com.cultureamp.service.report;

import com.cultureamp.entity.SurveyResponse;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.time.OffsetDateTime;
import java.util.List;

class ParticipationRatingCalculatorTest {
  @Test
  void shouldReturnAverageValueOfSpecificAnswer() {
    ParticipationRatingCalculator calculator = new ParticipationRatingCalculator();
    OffsetDateTime dateTime = OffsetDateTime.parse("2014-07-28T20:35:41+00:00");
    List<SurveyResponse> responses = List.of(
        new SurveyResponse(1L, "abc@ab.com", dateTime, List.of(4, 5)),
        new SurveyResponse(1L, "xyz@ab.com", dateTime, List.of(5, 5)),
        new SurveyResponse(1L, "efg@ab.com", dateTime, List.of(4, 5))
    );
    Double result = calculator.ratingFrom(responses, 0);
    DecimalFormat df = new DecimalFormat("#.##");

    MatcherAssert.assertThat(df.format(result), Is.is("4.33"));
  }

  @Test
  void shouldReturnAverageValueOfSpecificPartialAnswer() {
    ParticipationRatingCalculator calculator = new ParticipationRatingCalculator();
    OffsetDateTime dateTime = OffsetDateTime.parse("2014-07-28T20:35:41+00:00");
    List<SurveyResponse> responses = List.of(
        new SurveyResponse(1L, "abc@ab.com", dateTime, List.of(4, 5)),
        new SurveyResponse(1L, "xyz@ab.com", dateTime, List.of("", 5)),
        new SurveyResponse(1L, "efg@ab.com", dateTime, List.of(4, 5))
    );
    Double result = calculator.ratingFrom(responses, 0);
    DecimalFormat df = new DecimalFormat("#.##");

    MatcherAssert.assertThat(df.format(result), Is.is("4"));
  }

  @Test
  void shouldReturnZeroForStringAnswer() {
    ParticipationRatingCalculator calculator = new ParticipationRatingCalculator();
    OffsetDateTime dateTime = OffsetDateTime.parse("2014-07-28T20:35:41+00:00");
    List<SurveyResponse> responses = List.of(
        new SurveyResponse(1L, "abc@ab.com", dateTime, List.of("", 5)),
        new SurveyResponse(1L, "xyz@ab.com", dateTime, List.of("", 5)),
        new SurveyResponse(1L, "efg@ab.com", dateTime, List.of("", 5))
    );
    Double result = calculator.ratingFrom(responses, 0);
    DecimalFormat df = new DecimalFormat("#.##");

    MatcherAssert.assertThat(df.format(result), Is.is("0"));
  }

}