package com.cultureamp.report;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

class ReportFactoryTest {
  @Test
  void shouldReturnParticipationReport() {
    String[] arguments = {};
    ReportFactory reportFactory = new ReportFactory(arguments);

    ParticipationReport participationReport = reportFactory.getParticipationReport();

    assertThat(participationReport, is(instanceOf(ParticipationReport.class)));
  }

  @Test
  void shouldReturnSurveyRatingReport() {
    String[] arguments = {};
    ReportFactory reportFactory = new ReportFactory(arguments);

    SurveyRatingReport ratingReport = reportFactory.getRatingReport();

    assertThat(ratingReport, is(instanceOf(SurveyRatingReport.class)));
  }

}