package com.cultureamp.output;

import com.cultureamp.output.dto.Participation;
import com.cultureamp.output.dto.RatingAverage;

public interface Output {
  void print(Participation participation);

  void print(RatingAverage ratingAverage);
}
