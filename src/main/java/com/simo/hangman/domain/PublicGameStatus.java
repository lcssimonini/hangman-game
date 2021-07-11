package com.simo.hangman.domain;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PublicGameStatus {

  private String gameId;
  private Set<String> guesses;
  private String gameStatus;
  private Integer wrongGuessesCount;
  private Integer hits;
  private GameResult gameResult;
}
