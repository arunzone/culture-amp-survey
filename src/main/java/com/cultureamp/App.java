package com.cultureamp;

import com.cultureamp.report.ParticipationReport;
import com.cultureamp.report.ReportFactory;
import com.cultureamp.report.SurveyRatingReport;

public class App {
  public static void main(String[] args) {
    ReportFactory reportFactory = new ReportFactory(args);
    try {

      ParticipationReport participationReport = reportFactory.getParticipationReport();
      participationReport.generate();

      SurveyRatingReport ratingReport = reportFactory.getRatingReport();
      ratingReport.generate();

    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
  }

}
