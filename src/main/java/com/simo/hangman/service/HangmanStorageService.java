package com.simo.hangman.service;

import com.simo.hangman.domain.Game;

public interface HangmanStorageService {

    Game saveGame(Game game);

    Game fetchGameById(String gameId);
}
