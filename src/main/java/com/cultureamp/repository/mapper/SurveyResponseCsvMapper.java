package com.cultureamp.repository.mapper;

import com.cultureamp.entity.SurveyResponse;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class SurveyResponseCsvMapper implements Function<String, SurveyResponse> {
  private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

  private List<?> ratingsFrom(String[] columns) {
    return range(3, columns.length).
        mapToObj(ratingFrom(columns)).
        collect(toList());
  }

  private IntFunction<?> ratingFrom(String[] columns) {
    return index ->
    {
      String rating = columns[index];
      return validNumber(rating) ? Short.valueOf(rating) : rating;
    };
  }

  private boolean validNumber(String rating) {
    return NUMBER_PATTERN.matcher(rating).matches();
  }

  @Override
  public SurveyResponse apply(String response) {
    String[] columns = response.split("\\s*,\\s*", -1);
    return new SurveyResponse(idFrom(columns[1]), columns[0], dateFrom(columns[2]), ratingsFrom(columns));
  }

  private OffsetDateTime dateFrom(String column) {
    return column.length() > 0 ? OffsetDateTime.parse(column) : null;
  }

  private Long idFrom(String column) {
    return validNumber(column) ? Long.valueOf(column) : null;
  }
}