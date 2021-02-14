package com.cultureamp.output.dto;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode
public class Participation {
  private final BigDecimal percentage;
  private final Long totalCount;

  public Participation(BigDecimal percentage, Long totalCount) {
    this.percentage = percentage;
    this.totalCount = totalCount;
  }

  public BigDecimal getPercentage() {
    return percentage;
  }

  public Long getTotalCount() {
    return totalCount;
  }
}
