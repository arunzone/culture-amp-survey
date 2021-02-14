package com.cultureamp.service.report;

import com.cultureamp.entity.SurveyResponse;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class ParticipationCountTest {
  @Test
  void shouldReturnTotalParticipant() {
    ParticipationCount participationCount = new ParticipationCount();
    OffsetDateTime dateTime = OffsetDateTime.parse("2014-07-28T20:35:41+00:00");
    List<SurveyResponse> responses = List.of(
        new SurveyResponse(1L, "abc@ab.com", dateTime, List.of(4, 5)),
        new SurveyResponse(1L, "xyz@ab.com", dateTime, List.of(5, 5)),
        new SurveyResponse(1L, "efg@ab.com", null, List.of(4, 5))
    );

    long count = participationCount.participationCountFrom(responses);

    assertThat(count, is(2L));
  }


}