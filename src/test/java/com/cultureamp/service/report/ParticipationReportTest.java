package com.cultureamp.service.report;

import com.cultureamp.entity.SurveyResponse;
import com.cultureamp.output.Output;
import com.cultureamp.output.dto.Participation;
import com.cultureamp.repository.SurveyResponseRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ParticipationReportTest {

  @Test
  void shouldPrintGeneratedOutput() {
    Output output = mock(Output.class);
    SurveyResponseRepository surveyResponseRepository = mock(SurveyResponseRepository.class);
    ParticipationCount participationCount = mock(ParticipationCount.class);
    ParticipationPercentageCalculator percentageCalculator = mock(ParticipationPercentageCalculator.class);
    ParticipationReport report = new ParticipationReport(surveyResponseRepository, output, percentageCalculator, participationCount);

    BigDecimal percentage = new BigDecimal("20.00");
    List<SurveyResponse> responses = List.of(
        new SurveyResponse(1L, "efg@ab.com", null, List.of(4, 5)),
        new SurveyResponse(1L, "efg@ab.com", null, List.of(4, 5)),
        new SurveyResponse(1L, "efg@ab.com", null, List.of(4, 5)),
        new SurveyResponse(1L, "efg@ab.com", null, List.of(4, 5)),
        new SurveyResponse(1L, "efg@ab.com", OffsetDateTime.parse("2014-07-28T20:35:41+00:00"), List.of(4, 5))
    );
    when(surveyResponseRepository.responses()).thenReturn(responses);
    when(participationCount.participationCountFrom(responses)).thenReturn(1L);
    when(percentageCalculator.participationPercentageFor(1L, 5)).thenReturn(percentage);

    report.generateFrom();

    verify(output).print(new Participation(percentage, 1L));
  }
}