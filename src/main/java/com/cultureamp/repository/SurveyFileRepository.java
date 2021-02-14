package com.cultureamp.repository;

import com.cultureamp.entity.SurveyResponse;
import com.cultureamp.repository.exception.InvalidInputFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class SurveyFileRepository implements SurveyRepository<SurveyResponse> {
  private final String inputFileName;
  private final Function<String, SurveyResponse> mapper;

  public SurveyFileRepository(String inputFileName, Function<String, SurveyResponse> mapper) {
    this.inputFileName = inputFileName;
    this.mapper = mapper;
  }

  @Override
  public List<SurveyResponse> all() {
    try {
      Path path = Path.of(inputFileName);
      return Files.readAllLines(path).
          stream().
          filter(line -> line.trim().length() > 2).
          map(mapper).
          collect(Collectors.toList());
    } catch (IOException e) {
      throw new InvalidInputFileException(format("Invalid input file: %s", inputFileName));
    }
  }

}

