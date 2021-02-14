package com.cultureamp;

import com.cultureamp.entity.SurveyResponse;
import com.cultureamp.output.ConsoleOutput;
import com.cultureamp.repository.SurveyFileRepository;
import com.cultureamp.repository.SurveyRepository;
import com.cultureamp.repository.mapper.SurveyResponseCsvMapper;
import com.cultureamp.service.report.ParticipationCount;
import com.cultureamp.service.report.ParticipationPercentageCalculator;
import com.cultureamp.service.report.ParticipationReport;

public class App {
  public static void main(String[] args) {
    ParticipationReport participationReport = new ParticipationReport(
        surveyResponseRepository(args),
        new ConsoleOutput(),
        new ParticipationPercentageCalculator(),
        new ParticipationCount());

    participationReport.generate();
  }

  private static SurveyRepository<SurveyResponse> surveyResponseRepository(String[] args) {
    String fileName = null;
    if (args.length > 0) {
      fileName = args[0];
    }
    return new SurveyFileRepository(fileName, new SurveyResponseCsvMapper());
  }
}
