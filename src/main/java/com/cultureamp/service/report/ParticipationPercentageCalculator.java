package com.cultureamp.service.report;

import java.math.BigDecimal;
import java.math.MathContext;

public class ParticipationPercentageCalculator {
  public static final MathContext PRECISION = new MathContext(4);
  public static final BigDecimal HUNDRED = BigDecimal.valueOf(100);

  public BigDecimal participationPercentageFor(long totalParticipationCount, int totalResponse) {
    BigDecimal divisor = BigDecimal.valueOf(totalResponse);
    return new BigDecimal(totalParticipationCount).
        divide(divisor, PRECISION).
        multiply(HUNDRED, PRECISION);
  }

}
