package com.cultureamp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class FileNameProcessorTest {
  @Test
  void shouldReturnValidQuestionFileName() {
    String[] arguments = {"/some/valid.csv"};
    FileNameProcessor fileNameProcessor = new FileNameProcessor(arguments);

    String fileName = fileNameProcessor.getQuestionFileName();

    assertThat(fileName, is("/some/valid.csv"));
  }

  @Test
  void shouldReturnGenerateResponseFileName() {
    String[] arguments = {"/some/valid.csv"};
    FileNameProcessor fileNameProcessor = new FileNameProcessor(arguments);

    String fileName = fileNameProcessor.getResponseFileName();

    assertThat(fileName, is("/some/valid-responses.csv"));
  }

  @Test
  void shouldReturnGenerateResponseFileNameWithoutExtension() {
    String[] arguments = {"/some/valid"};
    FileNameProcessor fileNameProcessor = new FileNameProcessor(arguments);

    String fileName = fileNameProcessor.getResponseFileName();

    assertThat(fileName, is("/some/valid-responses"));
  }

  @Test
  void shouldReturnGivenResponseFileName() {
    String[] arguments = {"/some/valid.csv", "some/other.csv"};
    FileNameProcessor fileNameProcessor = new FileNameProcessor(arguments);

    String fileName = fileNameProcessor.getResponseFileName();

    assertThat(fileName, is("some/other.csv"));
  }

  @Test
  void shouldReturnDefaultResponseFileName() {
    String[] arguments = {};
    FileNameProcessor fileNameProcessor = new FileNameProcessor(arguments);

    String fileName = fileNameProcessor.getResponseFileName();

    assertThat(fileName, is("src/test/resources/survey-1-responses.csv"));
  }

  @Test
  void shouldReturnDefaultQuestionFileNameWhenNotProvided() {
    String[] arguments = {};
    FileNameProcessor fileNameProcessor = new FileNameProcessor(arguments);

    String fileName = fileNameProcessor.getQuestionFileName();

    assertThat(fileName, is("src/test/resources/survey-1.csv"));
  }

}