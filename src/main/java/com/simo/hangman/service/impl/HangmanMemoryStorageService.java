package com.simo.hangman.service.impl;

import com.simo.hangman.domain.Game;
import com.simo.hangman.service.HangmanStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class HangmanMemoryStorageService implements HangmanStorageService {

    private static final Map<String, Game> gameStorage = new HashMap<>();

    @Override
    public Game saveGame(Game game) {
        return gameStorage.put(game.getGameId(), game);
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

    static class GameDoesNotExistException extends RuntimeException {
    }
}
