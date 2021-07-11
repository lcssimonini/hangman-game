package com.simo.hangman.controller;

import com.simo.hangman.domain.GameConfig;
import com.simo.hangman.domain.LetterGuess;
import com.simo.hangman.domain.PublicGameStatus;
import com.simo.hangman.service.HangmanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/game")
@RestController
@RequiredArgsConstructor
public class GameController {

    private final HangmanService hangmanService;

    @PostMapping
    public PublicGameStatus createNewGame() {
        return hangmanService.createNewGame();
    }

    @PostMapping("/{gameId}")
    public PublicGameStatus guessLetter(@PathVariable("gameId")  String gameId, @RequestBody LetterGuess letterGuess) {
        return hangmanService.guessLetter(gameId, letterGuess.getLetter());
    }

    @DeleteMapping("/{gameId}")
    public void deleteGame(@PathVariable("gameId")  String gameId) {
        hangmanService.deleteGame(gameId);
    }

    @GetMapping("/available-words")
    public GameConfig getHangmanWords() {
        return  hangmanService.getGameConfig();
    }
}
