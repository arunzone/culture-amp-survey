package com.cultureamp.service.report;

import com.cultureamp.entity.SurveyResponse;
import com.cultureamp.output.Output;
import com.cultureamp.output.dto.Participation;
import com.cultureamp.repository.SurveyResponseRepository;

import java.math.BigDecimal;
import java.util.List;

public class ParticipationReport {
  private final SurveyResponseRepository surveyResponseRepository;
  private final Output output;
  private final ParticipationPercentageCalculator percentageCalculator;
  private final ParticipationCount participationCount;

  public ParticipationReport(SurveyResponseRepository surveyResponseRepository, Output output, ParticipationPercentageCalculator percentageCalculator, ParticipationCount participationCount) {
    this.surveyResponseRepository = surveyResponseRepository;
    this.output = output;
    this.percentageCalculator = percentageCalculator;
    this.participationCount = participationCount;
  }


  public void generate() {
    List<SurveyResponse> responses = surveyResponseRepository.responses();

    long totalParticipationCount = participationCount.participationCountFrom(responses);
    BigDecimal percentage = percentageCalculator.participationPercentageFor(totalParticipationCount, responses.size());

    output.print(new Participation(percentage, totalParticipationCount));
  }
}
