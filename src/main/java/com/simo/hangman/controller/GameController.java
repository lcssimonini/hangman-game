package com.simo.hangman.controller;

import com.simo.hangman.domain.GameConfig;
import com.simo.hangman.domain.PublicGameStatus;
import com.simo.hangman.service.HangmanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/game")
@RestController
@RequiredArgsConstructor
public class GameController {

    private final HangmanService hangmanService;

    @PostMapping
    public PublicGameStatus createNewGame() {
        return hangmanService.createNewGame();
    }

    @GetMapping("/available-words")
    public GameConfig getHangmanWords() {
        return  hangmanService.getGameConfig();
    }
}
