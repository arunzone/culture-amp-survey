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
    SurveyResponseFileRepository surveyResponseFileRepository = new SurveyResponseFileRepository("src/test/resources/response.csv");

    List<SurveyResponse> responses = surveyResponseFileRepository.responses(new SurveyResponseCsvMapper());

    assertThat(responses.size(), is(1));
  }

  @Test
  void shouldReturnResponsesForDefaultFile() {
    SurveyResponseFileRepository surveyResponseFileRepository = new SurveyResponseFileRepository(null);

    List<SurveyResponse> responses = surveyResponseFileRepository.responses(new SurveyResponseCsvMapper());

    assertThat(responses.size(), is(6));
  }

  @Test
  void shouldThrowExceptionForInvalidFile() {
    SurveyResponseFileRepository surveyResponseFileRepository = new SurveyResponseFileRepository("unavailable");

    InvalidInputFileException invalidInputFileException = assertThrows(InvalidInputFileException.class, () -> {
      surveyResponseFileRepository.responses(new SurveyResponseCsvMapper());
    });

    assertThat(invalidInputFileException.getMessage(), is("Invalid input file: unavailable"));
  }

}