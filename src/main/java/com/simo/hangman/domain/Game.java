package com.simo.hangman.domain;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.simo.hangman.util.StringUtil.findAllIndexes;

@Data
@Builder
public class Game {

    private String gameId;
    private String word;
    @Builder.Default
    private Set<String> guesses = new HashSet<>();
    private String gameStatus;
    @Builder.Default
    private Integer wrongGuessesCount = 0;
    @Builder.Default
    private Integer hits = 0;
    private Integer maxMissesAllowed = 0;

    public static Game buildGame(GameConfig gameConfig) {
        Game game =  Game.builder()
                .gameId(UUID.randomUUID().toString())
                .word(gameConfig.chooseRandomWord())
                .maxMissesAllowed(gameConfig.getMaxMissesAllowed())
                .build();
        game.setGameStatus("_".repeat(game.getWord().length()));
        return game;
    }

    public void guessLetter(Character letter) {
        guesses.add(letter.toString());
        if (word.contains(letter.toString())) {
            handleLetterHit(letter);
        } else {
            wrongGuessesCount++;
        }
    }

    private void handleLetterHit(Character letter) {
        hits++;
        updateStatus(letter);
    }

    private void updateStatus(Character letter) {
        StringBuilder newStatus = new StringBuilder(gameStatus);
        findAllIndexes(letter.toString(), word).forEach(index -> newStatus.setCharAt(index, letter));
        gameStatus = newStatus.toString();
    }

    public Boolean wonGame() {
        return hits.equals(word.length());
    }

    public Boolean lostGame() {
        return wrongGuessesCount.equals(maxMissesAllowed);
    }
}
