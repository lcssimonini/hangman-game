package com.simo.hangman.service;

import com.simo.hangman.domain.Game;

public interface HangmanStorageService {

  Game saveGame(Game game);

  void deleteGame(String gameId);

  Game fetchGameById(String gameId);
}
