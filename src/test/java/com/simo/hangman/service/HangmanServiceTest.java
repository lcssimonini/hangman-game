package com.simo.hangman.service;

import static com.simo.hangman.domain.GameResult.LOST;
import static com.simo.hangman.domain.GameResult.ONGOING;
import static org.junit.jupiter.api.Assertions.*;

import com.simo.hangman.domain.GameConfig;
import com.simo.hangman.domain.PublicGameStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HangmanServiceTest {

  @Autowired private HangmanService gameService;

  @Test
  public void shouldCreateGame() {
    PublicGameStatus publicGameStatus = gameService.createNewGame();
    assertNotNull(publicGameStatus.getGameId());
    assertTrue(publicGameStatus.getGuesses().isEmpty());
    assertEquals(0, (int) publicGameStatus.getWrongGuessesCount());
    assertEquals(0, (int) publicGameStatus.getHits());
    assertEquals(ONGOING, publicGameStatus.getGameResult());
  }

  @Test
  public void shouldDeleteGame() {
    PublicGameStatus publicGameStatus = gameService.createNewGame();
    assertDoesNotThrow(() -> gameService.deleteGame(publicGameStatus.getGameId()));
  }

  @Test
  public void shouldHandleLetterGuess() {
    PublicGameStatus publicGameStatus = gameService.createNewGame();
    String character = "a";
    PublicGameStatus newStatus = gameService.guessLetter(publicGameStatus.getGameId(), character);
    assertTrue(newStatus.getGuesses().contains(character.toUpperCase()));
    assertEquals(ONGOING, newStatus.getGameResult());
  }

  @Test
  public void shouldHandleGameLost() {
    PublicGameStatus publicGameStatus = gameService.createNewGame();
    String character = "z";
    gameService.guessLetter(publicGameStatus.getGameId(), character);
    gameService.guessLetter(publicGameStatus.getGameId(), character);
    gameService.guessLetter(publicGameStatus.getGameId(), character);
    gameService.guessLetter(publicGameStatus.getGameId(), character);
    gameService.guessLetter(publicGameStatus.getGameId(), character);
    gameService.guessLetter(publicGameStatus.getGameId(), character);
    gameService.guessLetter(publicGameStatus.getGameId(), character);
    PublicGameStatus newStatus = gameService.guessLetter(publicGameStatus.getGameId(), character);
    assertTrue(newStatus.getGuesses().contains(character.toUpperCase()));
    assertEquals(LOST, newStatus.getGameResult());
  }

  @Test
  public void getGameConfig() {
    GameConfig gameConfig = gameService.getGameConfig();
    assertFalse(gameConfig.getWordList().isEmpty());
  }
}
