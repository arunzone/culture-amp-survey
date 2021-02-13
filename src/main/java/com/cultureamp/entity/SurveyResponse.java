package com.cultureamp.entity;

import lombok.AllArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
public class SurveyResponse {
  private Long id;
  private String email;
  private OffsetDateTime date;
  private List<?> ratings;

}
