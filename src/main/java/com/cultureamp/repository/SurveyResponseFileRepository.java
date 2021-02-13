package com.cultureamp.repository;

import com.cultureamp.entity.SurveyResponse;
import com.cultureamp.repository.exception.InvalidInputFileException;
import com.cultureamp.repository.mapper.SurveyResponseCsvMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class SurveyResponseFileRepository implements SurveyResponseRepository {
  private final String inputFileName;

  public SurveyResponseFileRepository(String inputFileName) {
    this.inputFileName = inputFileName;
  }

  @Override
  public List<SurveyResponse> responses(Function<String, SurveyResponse> mapper) {
    String fileName = fileName();
    try {
      Path path = Path.of(fileName);
      return Files.readAllLines(path).
          stream().
          filter(line -> line.trim().length() > 2).
          map(new SurveyResponseCsvMapper()).
          collect(Collectors.toList());
    } catch (IOException e) {
      throw new InvalidInputFileException(format("Invalid input file: %s", fileName));
    }
  }

  private String fileName() {
    if (inputFileName != null) {
      return inputFileName;
    }
    return "src/test/resources/survey-1-responses.csv";
  }

}

