package com.simo.hangman.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class PublicGameStatus {

  private String gameId;
  @Builder.Default
  private Set<String> guesses = new HashSet<>();
  private String gameStatus;
  private Integer wrongGuessesCount;
  private Integer hits;
  private GameResult gameResult;
}
