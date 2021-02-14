package com.cultureamp.service.report;

import com.cultureamp.entity.SurveyResponse;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class ParticipationPercentageCalculator {
  public static final MathContext PRECISION = new MathContext(4);
  public static final BigDecimal HUNDRED = BigDecimal.valueOf(100);
  private final ParticipationCount participationCount;

  public ParticipationPercentageCalculator(ParticipationCount participationCount) {
    this.participationCount = participationCount;
  }

  public BigDecimal participationPercentageFor(List<SurveyResponse> responses) {
    long participatedCount = participationCount.participationCountFrom(responses);
    BigDecimal divisor = BigDecimal.valueOf(responses.size());
    return new BigDecimal(participatedCount).
        divide(divisor, PRECISION).
        multiply(HUNDRED, PRECISION);
  }

}
