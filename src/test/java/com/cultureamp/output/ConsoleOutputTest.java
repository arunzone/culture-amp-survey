package com.cultureamp.output;

import com.cultureamp.output.dto.Participation;
import com.cultureamp.output.dto.RatingAverage;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemOut;

class ConsoleOutputTest {

  @Test
  void shouldPrintParticipation() throws Exception {
    String text = tapSystemOut(() -> {
      Participation participation = new Participation(new BigDecimal("5.00"), 10L);
      new ConsoleOutput().print(participation);
    });

    assertThat(text, is("Participation percentage: 5.00\nTotal participant counts: 10\n"));
  }

  @Test
  void shouldPrintRatingAverage() throws Exception {
    String text = tapSystemOut(() -> {
      RatingAverage ratingAverage = new RatingAverage("Question a", 4.3333333333);
      new ConsoleOutput().print(ratingAverage);
    });

    assertThat(text, is("Question a: 4.33\n"));
  }
}