package com.cultureamp.report;

import com.cultureamp.entity.SurveyQuestion;
import com.cultureamp.entity.SurveyResponse;
import com.cultureamp.output.Output;
import com.cultureamp.output.dto.RatingAverage;
import com.cultureamp.repository.SurveyRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SurveyRatingReport {
  private final SurveyRepository surveyRepository;
  private final Output output;
  private final ParticipationRatingCalculator participationRatingCalculator;
  private final SurveyRepository surveyResponseRepository;

  public SurveyRatingReport(SurveyRepository<SurveyQuestion> surveyRepository, Output output, ParticipationRatingCalculator participationRatingCalculator, SurveyRepository<SurveyResponse> surveyResponseRepository) {
    this.surveyRepository = surveyRepository;
    this.output = output;
    this.participationRatingCalculator = participationRatingCalculator;
    this.surveyResponseRepository = surveyResponseRepository;
  }

  public void generate() {
    List<SurveyResponse> responses = surveyResponseRepository.all();
    List<SurveyQuestion> questions = surveyRepository.all();
    List<RatingAverage> ratingAverages = IntStream.range(0, questions.size()).
        filter(i -> questions.get(i).getType().equals("ratingquestion")).
        mapToObj(i -> getRatingAverage(responses, questions, i)).
        collect(Collectors.toList());
    output.print(ratingAverages);
  }

  private RatingAverage getRatingAverage(List<SurveyResponse> responses, List<SurveyQuestion> questions, int i) {
    return new RatingAverage(
        questions.get(i).getText(),
        participationRatingCalculator.ratingFrom(responses, i));
  }
}
