package com.cultureamp.repository;

import com.cultureamp.entity.SurveyQuestion;
import com.cultureamp.repository.exception.InvalidInputFileException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SurveyQuestionRepositoryTest {

  @Test
  void shouldReturnDefaultQuestions() {
    SurveyQuestionRepository surveyQuestionRepository = new SurveyQuestionRepository("src/test/resources/survey-1.csv");

    List<SurveyQuestion> questions = surveyQuestionRepository.all();

    assertThat(questions.size(), is(5));
  }

  @Test
  void shouldReturnAllAttributesForAQuestion() throws NoSuchFieldException, IllegalAccessException {
    SurveyQuestionRepository surveyQuestionRepository = new SurveyQuestionRepository("src/test/resources/survey-1.csv");

    List<SurveyQuestion> questions = surveyQuestionRepository.all();

    SurveyQuestion question = expectedQuestion();

    assertThat(questions.get(0), is(Matchers.samePropertyValuesAs(question)));
  }

  private SurveyQuestion expectedQuestion() throws NoSuchFieldException, IllegalAccessException {
    SurveyQuestion question = new SurveyQuestion();
    Class<? extends SurveyQuestion> aClass = question.getClass();
    setField(question, aClass, "ratingquestion", "type");
    setField(question, aClass, "The Work", "theme");
    setField(question, aClass, "I like the kind of work I do.", "text");
    return question;
  }

  private void setField(SurveyQuestion question, Class<? extends SurveyQuestion> aClass, String value, String fieldName) throws NoSuchFieldException, IllegalAccessException {
    Field type = aClass.getDeclaredField(fieldName);
    type.setAccessible(true);
    type.set(question, value);
  }

  @Test
  void shouldThrowExceptionForInvalidFile() {
    SurveyQuestionRepository surveyQuestionRepository = new SurveyQuestionRepository("unavailable");

    InvalidInputFileException invalidInputFileException = assertThrows(InvalidInputFileException.class, () -> surveyQuestionRepository.all());

    assertThat(invalidInputFileException.getMessage(), is("Invalid input file: unavailable"));
  }


}