package com.cultureamp;

import com.cultureamp.output.ConsoleOutput;
import com.cultureamp.report.ParticipationCount;
import com.cultureamp.report.ParticipationPercentageCalculator;
import com.cultureamp.report.ParticipationRatingCalculator;
import com.cultureamp.report.ParticipationReport;
import com.cultureamp.report.SurveyRatingReport;

public class App {
  public static void main(String[] args) {
    RepositoryProcessor repositoryProcessor = new RepositoryProcessor(new FileNameProcessor(args));
    ParticipationReport participationReport = getParticipationReport(repositoryProcessor);
    participationReport.generate();

    SurveyRatingReport surveyRatingReport = getRatingReport(repositoryProcessor);
    surveyRatingReport.generate();
  }

  private static ParticipationReport getParticipationReport(RepositoryProcessor repositoryProcessor) {
    return new ParticipationReport(
        repositoryProcessor.surveyResponseRepository(),
        new ConsoleOutput(),
        new ParticipationPercentageCalculator(),
        new ParticipationCount());
  }

  private static SurveyRatingReport getRatingReport(RepositoryProcessor repositoryProcessor) {
    return new SurveyRatingReport(
        repositoryProcessor.surveyQuestionRepository(),
        new ConsoleOutput(),
        new ParticipationRatingCalculator(),
        repositoryProcessor.surveyResponseRepository());
  }

}
