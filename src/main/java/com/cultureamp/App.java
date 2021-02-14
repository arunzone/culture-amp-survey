package com.cultureamp;

import com.cultureamp.output.ConsoleOutput;
import com.cultureamp.service.report.ParticipationCount;
import com.cultureamp.service.report.ParticipationPercentageCalculator;
import com.cultureamp.service.report.ParticipationRatingCalculator;
import com.cultureamp.service.report.ParticipationReport;
import com.cultureamp.service.report.SurveyRatingReport;

public class App {
  public static void main(String[] args) {
    RepositoryProcessor repositoryProcessor = new RepositoryProcessor(new FileNameProcessor(args));
    ParticipationReport participationReport = getParticipationReport(repositoryProcessor);
    System.out.println("Participation report");
    participationReport.generate();

    SurveyRatingReport surveyRatingReport = getRatingReport(repositoryProcessor);
    System.out.println("\nRating report");
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
