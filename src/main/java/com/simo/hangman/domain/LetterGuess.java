package com.simo.hangman.domain;

import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LetterGuess {

  @Size(min = 1, max = 1)
  private String letter;
}
