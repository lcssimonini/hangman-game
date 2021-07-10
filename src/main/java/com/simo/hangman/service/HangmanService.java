package com.simo.hangman.service;

import com.simo.hangman.domain.GameConfig;
import com.simo.hangman.domain.PublicGameStatus;

public interface HangmanService {

    PublicGameStatus createNewGame();

    PublicGameStatus guessLetter(String gameId, Character character);

    GameConfig getGameConfig();
}
