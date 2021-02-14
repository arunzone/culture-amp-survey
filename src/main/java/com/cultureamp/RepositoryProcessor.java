package com.cultureamp;

import com.cultureamp.entity.SurveyQuestion;
import com.cultureamp.entity.SurveyResponse;
import com.cultureamp.repository.SurveyFileRepository;
import com.cultureamp.repository.SurveyQuestionRepository;
import com.cultureamp.repository.SurveyRepository;
import com.cultureamp.repository.mapper.SurveyResponseCsvMapper;

public class RepositoryProcessor {
  private final FileNameProcessor fileNameProcessor;

  public RepositoryProcessor(FileNameProcessor fileNameProcessor) {
    this.fileNameProcessor = fileNameProcessor;
  }

  public SurveyRepository<SurveyResponse> surveyResponseRepository() {
    String fileName = fileNameProcessor.getResponseFileName();
    return new SurveyFileRepository(fileName, new SurveyResponseCsvMapper());
  }

  public SurveyRepository<SurveyQuestion> surveyQuestionRepository() {
    String fileName = fileNameProcessor.getQuestionFileName();
    return new SurveyQuestionRepository(fileName);
  }

}