package com.cultureamp.service.report;

import com.cultureamp.entity.SurveyResponse;

import java.util.List;

public class ParticipationCount {
  public long participationCountFrom(List<SurveyResponse> responses) {
    return responses.stream().
        filter(response -> response.getDate() != null).
        count();
  }
}
