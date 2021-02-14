package com.cultureamp;

import com.cultureamp.output.ConsoleOutput;
import com.cultureamp.repository.SurveyResponseFileRepository;
import com.cultureamp.repository.SurveyResponseRepository;
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

  private static SurveyResponseRepository surveyResponseRepository(String[] args) {
    String fileName = null;
    if (args.length > 0) {
      fileName = args[0];
    }
    return new SurveyResponseFileRepository(fileName, new SurveyResponseCsvMapper());
  }
}
