package com.simo.hangman.domain;

import static com.simo.hangman.domain.GameResult.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class GameTest {

  private final GameConfig gameConfiguration = getGameConfiguration();

  @Test
  public void shouldBuildGameFromConfig() {
    Game game = Game.buildGame(gameConfiguration);
    assertNotNull(game.getGameId());
    assertNotNull(game.getWord());
    assertNotNull(game.getGameStatus());
    assertEquals(game.getMaxMissesAllowed(), gameConfiguration.getMaxMissesAllowed());
    assertEquals(game.getWord().length(), game.getGameStatus().length());
    assertEquals(game.getGameResult(), ONGOING);
    assertTrue(gameConfiguration.getWordList().contains(game.getWord()));
  }

  @Test
  public void shouldHandleCorrectGuess() {
    Game game = Game.buildGame(gameConfiguration);
    String word = game.getWord();
    String letter = word.substring(0, 1);
    game.guessLetter(letter);
    assertTrue(game.getGuesses().contains(letter));
    assertEquals(0, (int) game.getWrongGuessesCount());
    assertEquals(1, (int) game.getHits());
    assertTrue(game.getGameStatus().contains(letter));
    assertEquals(game.getGameResult(), ONGOING);
  }

  @Test
  public void shouldHandleWrongGuess() {
    Game game = Game.buildGame(getOneWordConfig());
    String letter = "X";
    game.guessLetter(letter);
    assertTrue(game.getGuesses().contains(letter));
    assertEquals(1, (int) game.getWrongGuessesCount());
    assertEquals(0, (int) game.getHits());
    assertFalse(game.getGameStatus().contains(letter));
    assertEquals(game.getGameResult(), ONGOING);
  }

  @Test
  public void shouldWinGame() {
    Game game = Game.buildGame(getOneWordConfig());
    String letter01 = "O";
    String letter02 = "I";
    game.guessLetter(letter01);
    game.guessLetter(letter02);
    assertTrue(game.getGuesses().contains(letter01));
    assertTrue(game.getGuesses().contains(letter02));
    assertEquals(0, (int) game.getWrongGuessesCount());
    assertEquals(2, (int) game.getHits());
    assertTrue(game.getGameStatus().contains(letter01));
    assertTrue(game.getGameStatus().contains(letter02));
    assertEquals(game.getGameResult(), WON);
    game.guessLetter(letter02);
    assertEquals(2, (int) game.getHits());
  }

  @Test
  public void shouldLooseGame() {
    Game game = Game.buildGame(getOneWordConfig());
    String letter = "x";
    game.guessLetter(letter);
    game.guessLetter(letter);
    game.guessLetter(letter);
    game.guessLetter(letter);
    game.guessLetter(letter);
    game.guessLetter(letter);
    game.guessLetter(letter);
    assertTrue(game.getGuesses().contains(letter));
    assertEquals(7, (int) game.getWrongGuessesCount());
    assertEquals(0, (int) game.getHits());
    assertEquals(game.getGameResult(), LOST);
    game.guessLetter(letter);
    assertEquals(7, (int) game.getWrongGuessesCount());
  }

  private GameConfig getGameConfiguration() {
    GameConfig gameConfig = new GameConfig();
    gameConfig.wordList = Arrays.asList("BATATA", "CENOURA", "BETERRABA");
    return gameConfig;
  }

  private GameConfig getOneWordConfig() {
    GameConfig gameConfig = new GameConfig();
    gameConfig.wordList = Collections.singletonList("OI");
    return gameConfig;
  }
}
