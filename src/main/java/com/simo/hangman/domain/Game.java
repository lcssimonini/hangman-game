package com.simo.hangman.domain;

import static com.simo.hangman.domain.GameResult.*;
import static com.simo.hangman.util.StringUtil.findAllIndexes;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Game {

  private String gameId;
  private String word;
  @Builder.Default private Set<String> guesses = new HashSet<>();
  private String gameStatus;
  @Builder.Default private Integer wrongGuessesCount = 0;
  @Builder.Default private Integer hits = 0;
  private Integer maxMissesAllowed;
  @Builder.Default private GameResult gameResult = ONGOING;

  public static Game buildGame(GameConfig gameConfig) {
    Game game =
        Game.builder()
            .gameId(UUID.randomUUID().toString())
            .word(gameConfig.chooseRandomWord())
            .maxMissesAllowed(gameConfig.getMaxMissesAllowed())
            .build();
    game.setGameStatus("_".repeat(game.getWord().length()));
    return game;
  }

  public void guessLetter(String letter) {
    if (ongoingGame()) {
      handleLetterGuess(letter);
    }
  }

  private void handleLetterGuess(String letter) {
    if (word.contains(letter)) {
      handleLetterHit(letter);
    } else {
      handleLetterMiss();
    }
    guesses.add(letter);
  }

  private void handleLetterMiss() {
    wrongGuessesCount++;
    if (lostGame()) {
      setGameResult(LOST);
    }
  }

  private void handleLetterHit(String letter) {
    if (!guesses.contains(letter)) {
      hits++;
    }
    updateGameStatus(letter);
    if (wonGame()) {
      setGameResult(WON);
    }
  }

  private void updateGameStatus(String letter) {
    StringBuilder newStatus = new StringBuilder(gameStatus);
    findAllIndexes(word, letter).forEach(index -> newStatus.setCharAt(index, letter.charAt(0)));
    gameStatus = newStatus.toString();
  }

  private Boolean ongoingGame() {
    return gameResult.equals(ONGOING);
  }

  private Boolean wonGame() {
    return hits.equals(word.length());
  }

  private Boolean lostGame() {
    return wrongGuessesCount.equals(maxMissesAllowed);
  }
}
