package com.cultureamp.repository;

import com.cultureamp.entity.SurveyResponse;
import com.cultureamp.repository.exception.InvalidInputFileException;
import com.cultureamp.repository.mapper.SurveyResponseCsvMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SurveyResponseFileRepositoryTest {
  @Test
  void shouldReturnResponsesForSpecificFile() {
    SurveyResponseFileRepository surveyResponseFileRepository = new SurveyResponseFileRepository("src/test/resources/response.csv", new SurveyResponseCsvMapper());

    List<SurveyResponse> responses = surveyResponseFileRepository.responses();

    assertThat(responses.size(), is(1));
  }

  @Test
  void shouldReturnResponsesForDefaultFile() {
    SurveyResponseFileRepository surveyResponseFileRepository = new SurveyResponseFileRepository(null, new SurveyResponseCsvMapper());

    List<SurveyResponse> responses = surveyResponseFileRepository.responses();

    assertThat(responses.size(), is(6));
  }

  @Test
  void shouldThrowExceptionForInvalidFile() {
    SurveyResponseFileRepository surveyResponseFileRepository = new SurveyResponseFileRepository("unavailable", new SurveyResponseCsvMapper());

    InvalidInputFileException invalidInputFileException = assertThrows(InvalidInputFileException.class, () -> {
      surveyResponseFileRepository.responses();
    });

    assertThat(invalidInputFileException.getMessage(), is("Invalid input file: unavailable"));
  }

}