package com.cultureamp.repository;

import com.cultureamp.entity.SurveyQuestion;
import com.cultureamp.repository.exception.InvalidInputFileException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SurveyQuestionRepositoryTest {

  @Test
  void shouldReturnDefaultQuestions() {
    SurveyQuestionRepository surveyQuestionRepository = new SurveyQuestionRepository("src/test/resources/survey-1-responses.csv");

    List<SurveyQuestion> questions = surveyQuestionRepository.all();

    assertThat(questions.size(), is(5));
  }

  @Test
  void shouldReturnAllAttributesForAQuestion() {
    SurveyQuestionRepository surveyQuestionRepository = new SurveyQuestionRepository("src/test/resources/survey-1-responses.csv");

    List<SurveyQuestion> questions = surveyQuestionRepository.all();

    SurveyQuestion question = new SurveyQuestion("ratingquestion", "The Work", "I like the kind of work I do.");
    assertThat(questions.get(0), is(Matchers.samePropertyValuesAs(question)));
  }

  @Test
  void shouldThrowExceptionForInvalidFile() {
    SurveyQuestionRepository surveyQuestionRepository = new SurveyQuestionRepository("unavailable");

    InvalidInputFileException invalidInputFileException = assertThrows(InvalidInputFileException.class, () -> surveyQuestionRepository.all());

    assertThat(invalidInputFileException.getMessage(), is("Invalid input file: unavailable"));
  }


}