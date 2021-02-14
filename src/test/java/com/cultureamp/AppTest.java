package com.cultureamp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemOut;

public class AppTest {

  @Test
  public void shouldGenerateReportFromGivenFile() throws Exception {

    String text = tapSystemOut(() -> {
      new App().main(new String[]{"src/test/resources/survey-1.csv"});
    });

    assertThat(text, is("Participation report\nParticipation percentage: 83.33\nTotal participant counts: 5\n\nRating report\nI like the kind of work I do.: 4.6\nIn general, I have the resources (e.g., business tools, information, facilities, IT or functional support) I need to be effective.: 5\nWe are working at the right pace to meet our goals.: 5\nI feel empowered to get the work done for which I am responsible.: 3.6\nI am appropriately involved in decisions that affect my work.: 3.6\n"));
  }

  @Test
  public void shouldGenerateReportFromDefaultFile() throws Exception {

    String text = tapSystemOut(() -> {
      App.main(new String[]{});
    });

    assertThat(text, is("Participation report\nParticipation percentage: 83.33\nTotal participant counts: 5\n\nRating report\nI like the kind of work I do.: 4.6\nIn general, I have the resources (e.g., business tools, information, facilities, IT or functional support) I need to be effective.: 5\nWe are working at the right pace to meet our goals.: 5\nI feel empowered to get the work done for which I am responsible.: 3.6\nI am appropriately involved in decisions that affect my work.: 3.6\n"));
  }

  @Test
  public void shouldHandleException() throws Exception {

    String text = tapSystemOut(() -> {
      App.main(new String[]{"invalid/FileName"});
    });

    assertThat(text, is("Invalid input file: invalid/FileName-responses\n"));
  }
}
