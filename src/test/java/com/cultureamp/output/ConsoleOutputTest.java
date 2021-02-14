package com.cultureamp.output;

import com.cultureamp.output.dto.Participation;
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
}