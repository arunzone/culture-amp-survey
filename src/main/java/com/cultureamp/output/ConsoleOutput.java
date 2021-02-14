package com.cultureamp.output;

import com.cultureamp.output.dto.Participation;

public class ConsoleOutput implements Output {

  @Override
  public void print(Participation participation) {
    System.out.printf("Participation percentage: %s\n", participation.getPercentage());
    System.out.printf("Total participant counts: %s\n", participation.getTotalCount());
  }
}
