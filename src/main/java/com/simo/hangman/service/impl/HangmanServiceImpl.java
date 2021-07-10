package com.simo.hangman.service.impl;

import com.simo.hangman.service.HangmanStorageService;
import com.simo.hangman.util.ConfigLoader;
import com.simo.hangman.converter.GameConverter;
import com.simo.hangman.domain.Game;
import com.simo.hangman.domain.GameConfig;
import com.simo.hangman.domain.PublicGameStatus;
import com.simo.hangman.service.HangmanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HangmanServiceImpl implements HangmanService {

    private final HangmanStorageService storageService;

    private static final GameConfig gameConfig = ConfigLoader.getGameconfig();

    @Override
    public PublicGameStatus createNewGame() {
        log.info("Creating new game");
        Game game = Game.buildGame(gameConfig);
        return GameConverter.fromGame(storageService.saveGame(game));
    }

    @Override
    public PublicGameStatus guessLetter(String gameId, Character character) {
        Game game = storageService.fetchGameById(gameId);
        return null;
    }

    @Override
    public GameConfig getGameConfig() {
        log.info("Fetching game configuration");
        return gameConfig;
    }
}
