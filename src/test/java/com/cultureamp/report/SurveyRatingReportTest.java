package com.cultureamp.report;

import com.cultureamp.entity.SurveyQuestion;
import com.cultureamp.entity.SurveyResponse;
import com.cultureamp.output.Output;
import com.cultureamp.output.dto.RatingAverage;
import com.cultureamp.repository.SurveyRepository;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.OffsetDateTime;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SurveyRatingReportTest {

  @Test
  void shouldReturnAverageRatingForAllColumns() throws NoSuchFieldException, IllegalAccessException {
    SurveyRepository surveyRepository = mock(SurveyRepository.class);
    SurveyRepository surveyResponseRepository = mock(SurveyRepository.class);
    Output output = mock(Output.class);
    ParticipationRatingCalculator participationRatingCalculator = mock(ParticipationRatingCalculator.class);
    SurveyRatingReport surveyRatingReport = new SurveyRatingReport(surveyRepository, output, participationRatingCalculator, surveyResponseRepository);

    List<SurveyQuestion> questions = List.of(expectedQuestion());
    when(surveyRepository.all()).thenReturn(questions);

    List<SurveyResponse> responses = List.of(
        new SurveyResponse(1L, "abc@ab.com", OffsetDateTime.parse("2014-07-28T20:35:41+00:00"), List.of(4, 5)));
    when(surveyResponseRepository.all()).thenReturn(responses);

    when(participationRatingCalculator.ratingFrom(responses, 0)).thenReturn(4.0);

    surveyRatingReport.generate();

    verify(output).print(List.of(new RatingAverage("I like the kind of work I do.", 4.0)));

  }

  private SurveyQuestion expectedQuestion() throws NoSuchFieldException, IllegalAccessException {
    SurveyQuestion question = new SurveyQuestion();
    Class<? extends SurveyQuestion> aClass = question.getClass();
    setField(question, aClass, "ratingquestion", "type");
    setField(question, aClass, "", "theme");
    setField(question, aClass, "I like the kind of work I do.", "text");
    return question;
  }

  private void setField(SurveyQuestion question, Class<? extends SurveyQuestion> aClass, String value, String fieldName) throws NoSuchFieldException, IllegalAccessException {
    Field type = aClass.getDeclaredField(fieldName);
    type.setAccessible(true);
    type.set(question, value);
  }
}