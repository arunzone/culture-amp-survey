package com.cultureamp.output.dto;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class RatingAverage {
  private String question;
  private Double average;

  public RatingAverage(String question, Double average) {
    this.question = question;
    this.average = average;
  }

  public String getQuestion() {
    return question;
  }

  public Double getAverage() {
    return average;
  }
}
