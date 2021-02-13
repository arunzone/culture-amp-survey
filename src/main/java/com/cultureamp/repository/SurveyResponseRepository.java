package com.cultureamp.repository;

import com.cultureamp.entity.SurveyResponse;

import java.util.List;
import java.util.function.Function;

public interface SurveyResponseRepository {
  List<SurveyResponse> responses(Function<String, SurveyResponse> mapper);
}
