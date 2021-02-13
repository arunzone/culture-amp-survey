package com.cultureamp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {

  @Test
  public void testApp() {

    new App().main(new String[]{});
    assertTrue(true);
  }
}
