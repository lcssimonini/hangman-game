package com.simo.hangman.service.impl;

import com.simo.hangman.domain.Game;
import com.simo.hangman.service.HangmanStorageService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@Service
@RequiredArgsConstructor
public class HangmanMemoryStorageService implements HangmanStorageService {

  private static final Map<String, Game> gameStorage = new HashMap<>();

  @Override
  public Game saveGame(Game game) {
    gameStorage.put(game.getGameId(), game);
    return game;
  }

  @Override
  public void deleteGame(String gameId) {
    if (gameId != null) {
      gameStorage.remove(gameId);
    }
  }

  @Override
  public Game fetchGameById(String gameId) {
    Game game = gameStorage.get(gameId);
    if (game == null) {
      log.error("The game with id {} does not exist", gameId);
      throw new GameDoesNotExistException();
    }
    return game;
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Game does not exist")
  public static class GameDoesNotExistException extends RuntimeException {}
}
