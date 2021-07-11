package com.simo.hangman.converter;

import com.simo.hangman.domain.Game;
import com.simo.hangman.domain.PublicGameStatus;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GameConverter {

  public static PublicGameStatus fromGame(Game game) {
    return PublicGameStatus.builder()
        .gameId(game.getGameId())
        .gameStatus(game.getGameStatus())
        .hits(game.getHits())
        .wrongGuessesCount(game.getWrongGuessesCount())
        .gameResult(game.getGameResult())
        .guesses(game.getGuesses())
            .build();
  }
}
