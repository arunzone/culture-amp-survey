package com.cultureamp.repository.mapper;

import com.cultureamp.entity.SurveyResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class SurveyResponseCsvMapperTest {

  @Test
  void shouldReturnSurveyResponseWhenAllTheValuesPresent() {
    SurveyResponseCsvMapper mapper = new SurveyResponseCsvMapper();
    List<Short> ratings = List.of((short) 5, (short) 5, (short) 5, (short) 4, (short) 4);
    OffsetDateTime dateTime = OffsetDateTime.parse("2014-07-28T20:35:41+00:00");
    SurveyResponse expectedSurveyResponse = new SurveyResponse(
        1L,
        "employee1@abc.xyz",
        dateTime,
        ratings);

    SurveyResponse surveyResponse = mapper.apply("employee1@abc.xyz,1,2014-07-28T20:35:41+00:00,5,5,5,4,4");

    assertThat(surveyResponse, is(Matchers.samePropertyValuesAs(expectedSurveyResponse)));
  }

  @Test
  void shouldReturnSurveyResponseWithEmptyFirstRating() {
    SurveyResponseCsvMapper mapper = new SurveyResponseCsvMapper();
    List<?> ratings = List.of("", (short) 5, (short) 5, (short) 4, (short) 4);
    OffsetDateTime dateTime = OffsetDateTime.parse("2014-07-28T20:35:41+00:00");
    SurveyResponse expectedSurveyResponse = new SurveyResponse(
        1L,
        "employee1@abc.xyz",
        dateTime,
        ratings);

    SurveyResponse surveyResponse = mapper.apply("employee1@abc.xyz,1,2014-07-28T20:35:41+00:00,5,5,5,4,4");

    assertThat(surveyResponse, is(Matchers.samePropertyValuesAs(expectedSurveyResponse)));
  }

  @Test
  void shouldReturnSurveyResponseWithoutId() {
    SurveyResponseCsvMapper mapper = new SurveyResponseCsvMapper();
    List<Short> ratings = List.of((short) 5, (short) 5, (short) 5, (short) 4, (short) 4);
    OffsetDateTime dateTime = OffsetDateTime.parse("2014-07-28T20:35:41+00:00");
    SurveyResponse expectedSurveyResponse = new SurveyResponse(
        null,
        "employee1@abc.xyz",
        dateTime,
        ratings);

    SurveyResponse surveyResponse = mapper.apply("employee1@abc.xyz,,2014-07-28T20:35:41+00:00,5,5,5,4,4");

    assertThat(surveyResponse, is(Matchers.samePropertyValuesAs(expectedSurveyResponse)));
  }

  @Test
  void shouldReturnSurveyResponseWithEmptyEmail() {
    SurveyResponseCsvMapper mapper = new SurveyResponseCsvMapper();
    List<Short> ratings = List.of((short) 5, (short) 5, (short) 5, (short) 4, (short) 4);
    OffsetDateTime dateTime = OffsetDateTime.parse("2014-07-28T20:35:41+00:00");
    SurveyResponse expectedSurveyResponse = new SurveyResponse(
        1L,
        "",
        dateTime,
        ratings);

    SurveyResponse surveyResponse = mapper.apply(",1,2014-07-28T20:35:41+00:00,5,5,5,4,4");

    assertThat(surveyResponse, is(Matchers.samePropertyValuesAs(expectedSurveyResponse)));
  }

  @Test
  void shouldReturnSurveyResponseWithoutDatetime() {
    SurveyResponseCsvMapper mapper = new SurveyResponseCsvMapper();
    List<Short> ratings = List.of((short) 5, (short) 5, (short) 5, (short) 4, (short) 4);
    SurveyResponse expectedSurveyResponse = new SurveyResponse(
        1L,
        "employee1@abc.xyz",
        null,
        ratings);

    SurveyResponse surveyResponse = mapper.apply("employee1@abc.xyz,1,,5,5,5,4,4");

    assertThat(surveyResponse, is(Matchers.samePropertyValuesAs(expectedSurveyResponse)));
  }

}