package com.cultureamp.entity;

import lombok.AllArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
public class SurveyResponse {
  private final Long id;
  private final String email;
  private final OffsetDateTime date;
  private final List<?> ratings;

  public OffsetDateTime getDate() {
    return date;
  }
}
