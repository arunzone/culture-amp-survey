package com.cultureamp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemOut;

public class AppTest {

  @Test
  public void shouldGenerateReportFromGivenFile() throws Exception {

    String text = tapSystemOut(() -> {
      new App().main(new String[]{"src/test/resources/survey-1-responses.csv"});
    });

    assertThat(text, is("Participation percentage: 83.33\nTotal participant counts: 5\n"));
  }

  @Test
  public void shouldGenerateReportFromDefaultFile() throws Exception {

    String text = tapSystemOut(() -> {
      new App().main(new String[]{});
    });

    assertThat(text, is("Participation percentage: 83.33\nTotal participant counts: 5\n"));
  }
}
