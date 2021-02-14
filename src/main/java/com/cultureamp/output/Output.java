package com.cultureamp.output;

import com.cultureamp.output.dto.Participation;
import com.cultureamp.output.dto.RatingAverage;

import java.util.List;

public interface Output {
  void print(Participation participation);

  void print(List<RatingAverage> ratingAverage);
}
