package com.simo.hangman.service;

import com.simo.hangman.domain.GameConfig;
import com.simo.hangman.domain.PublicGameStatus;

public interface HangmanService {

    PublicGameStatus createNewGame();

    void deleteGame(String gameId);

    PublicGameStatus guessLetter(String gameId, String character);

    GameConfig getGameConfig();
}
