package com.cultureamp.entity;

import com.opencsv.bean.CsvBindByName;

@SuppressWarnings("unused")
public class SurveyQuestion {
  @CsvBindByName(column = "type")
  private String type;
  @CsvBindByName(column = "theme")
  private String theme;
  @CsvBindByName(column = "text")
  private String text;

  public String getType() {
    return type;
  }

  public String getText() {
    return text;
  }

}
