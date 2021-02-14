package com.cultureamp.entity;

import com.opencsv.bean.CsvBindByName;

@SuppressWarnings("unused")
public class SurveyQuestion {
  public SurveyQuestion() {

  }

  public SurveyQuestion(String type, String theme, String text) {
    this.type = type;
    this.theme = theme;
    this.text = text;
  }

  @CsvBindByName
  private String type;
  @CsvBindByName
  private String theme;
  @CsvBindByName
  private String text;
}
