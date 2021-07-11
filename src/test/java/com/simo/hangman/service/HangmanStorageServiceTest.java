package com.simo.hangman.service;

import static org.junit.jupiter.api.Assertions.*;

import com.simo.hangman.domain.Game;
import com.simo.hangman.service.impl.HangmanMemoryStorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HangmanStorageServiceTest {

  @Autowired private HangmanStorageService storageService;

  @Test
  void shouldSaveAndRetrieveGame() {
    String gameId = "gameId";
    Game game = Game.builder().gameId(gameId).build();
    storageService.saveGame(game);

    Game savedGame = storageService.fetchGameById(gameId);
    assertEquals(game, savedGame);
  }

  @Test
  void shouldthrowGameDoesNotExist() {
    String gameId = "inexistent";
    assertThrows(
        HangmanMemoryStorageService.GameDoesNotExistException.class,
        () -> storageService.fetchGameById(gameId));
  }

  @Test
  void shouldDeleteGame() {
    String gameId = "gameId";
    Game game = Game.builder().gameId(gameId).build();
    storageService.saveGame(game);

    storageService.deleteGame(gameId);

    assertThrows(
        HangmanMemoryStorageService.GameDoesNotExistException.class,
        () -> storageService.fetchGameById(gameId));
  }

  @Test
  void shouldNotThrowWhenDeletingNullKey() {
    assertDoesNotThrow(() -> storageService.deleteGame(null));
  }
}
