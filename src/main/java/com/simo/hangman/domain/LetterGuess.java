package com.simo.hangman.domain;

import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class LetterGuess {

  @Size(min = 1, max = 1)
  private String letter;
}
