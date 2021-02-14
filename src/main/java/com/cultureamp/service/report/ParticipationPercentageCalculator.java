package com.cultureamp.service.report;

import com.cultureamp.entity.SurveyResponse;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class ParticipationPercentageCalculator {
  private final ParticipationCount participationCount;

  public ParticipationPercentageCalculator(ParticipationCount participationCount) {
    this.participationCount = participationCount;
  }

  public BigDecimal participationPercentageFor(List<SurveyResponse> responses) {
    long participatedCount = participationCount.participationCountFrom(responses);
    BigDecimal divisor = BigDecimal.valueOf(responses.size());
    return new BigDecimal(participatedCount).
        divide(divisor, new MathContext(4)).
        multiply(BigDecimal.valueOf(100), new MathContext(4));
  }

}
