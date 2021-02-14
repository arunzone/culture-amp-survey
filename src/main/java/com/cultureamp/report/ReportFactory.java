package com.cultureamp.report;

import com.cultureamp.FileNameProcessor;
import com.cultureamp.RepositoryProcessor;
import com.cultureamp.entity.SurveyQuestion;
import com.cultureamp.entity.SurveyResponse;
import com.cultureamp.output.ConsoleOutput;
import com.cultureamp.output.Output;
import com.cultureamp.repository.SurveyRepository;

public class ReportFactory {
  private final Output output = new ConsoleOutput();
  private final SurveyRepository<SurveyQuestion> surveyQuestionSurveyRepository;
  private final SurveyRepository<SurveyResponse> surveyResponseSurveyRepository;

  public ReportFactory(String[] arguments) {
    RepositoryProcessor repositoryProcessor = new RepositoryProcessor(new FileNameProcessor(arguments));
    surveyQuestionSurveyRepository = repositoryProcessor.surveyQuestionRepository();
    surveyResponseSurveyRepository = repositoryProcessor.surveyResponseRepository();
  }

  public ParticipationReport getParticipationReport() {
    return new ParticipationReport(
        surveyResponseSurveyRepository,
        output,
        new ParticipationPercentageCalculator(),
        new ParticipationCount());
  }

  public SurveyRatingReport getRatingReport() {
    return new SurveyRatingReport(
        surveyQuestionSurveyRepository,
        output,
        new ParticipationRatingCalculator(),
        surveyResponseSurveyRepository);
  }

}
