package com.cultureamp.repository;

import com.cultureamp.entity.SurveyQuestion;
import com.cultureamp.repository.exception.InvalidInputFileException;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static java.lang.String.format;

public class SurveyQuestionRepository implements SurveyRepository<SurveyQuestion> {
  private final String inputFileName;

  public SurveyQuestionRepository(String inputFileName) {
    this.inputFileName = inputFileName;
  }

  @Override
  public List<SurveyQuestion> all() {
    try {
      return new CsvToBeanBuilder(new FileReader(inputFileName)).
          withType(SurveyQuestion.class).
          build().
          parse();
    } catch (FileNotFoundException e) {
      throw new InvalidInputFileException(format("Invalid input file: %s", inputFileName));
    }
  }
}
