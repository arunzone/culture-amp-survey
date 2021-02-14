package com.cultureamp.repository;

import java.util.List;

public interface SurveyRepository<T> {
  List<T> all();
}
