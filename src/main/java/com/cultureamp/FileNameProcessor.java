package com.cultureamp;

public class FileNameProcessor {

  private final String[] arguments;

  public FileNameProcessor(String[] arguments) {
    this.arguments = arguments;
  }

  private String getFileName(String[] args) {
    if (args.length > 0) {
      return args[0];
    }
    return "src/test/resources/survey-1.csv";
  }

  private String getSurveyResponseFileNameFrom(String[] args) {
    if (args.length > 1) {
      return args[1];
    }
    return responseFileNameFrom(args);
  }

  private String responseFileNameFrom(String[] args) {
    String fileName = getFileName(args);
    if (fileName.indexOf(".") > 0) {
      return fileName.replace(".", "-responses.");
    } else {
      return fileName + "-responses";
    }
  }

  public String getQuestionFileName() {
    return getFileName(arguments);
  }

  public String getResponseFileName() {
    return getSurveyResponseFileNameFrom(arguments);
  }
}
