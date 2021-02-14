package com.cultureamp.output;

import com.cultureamp.output.dto.Participation;
import com.cultureamp.output.dto.RatingAverage;

import java.text.DecimalFormat;

public class ConsoleOutput implements Output {

  @Override
  public void print(Participation participation) {
    System.out.printf("Participation percentage: %s\n", participation.getPercentage());
    System.out.printf("Total participant counts: %s\n", participation.getTotalCount());
  }

  @Override
  public void print(RatingAverage ratingAverage) {
    DecimalFormat formatter = new DecimalFormat("#.##");
    System.out.printf("%s: %s\n", ratingAverage.getQuestion(), formatter.format(ratingAverage.getAverage()));
  }
}
