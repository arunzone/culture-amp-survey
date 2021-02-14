package com.cultureamp.service.report;

import com.cultureamp.entity.SurveyResponse;

import java.util.List;

public class ParticipationRatingCalculator {

  public Double ratingFrom(List<SurveyResponse> responses, int questionIndex) {
    return responses.stream().
        filter(response -> response.getDate() != null).
        map(response -> response.getAnswers().get(questionIndex)).
        filter(answer -> answer instanceof Integer).
        mapToInt(rating -> (int) rating).
        average().orElse(0.0);
  }
}
