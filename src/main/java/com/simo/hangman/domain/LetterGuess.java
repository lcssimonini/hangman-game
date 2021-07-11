package com.simo.hangman.domain;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class LetterGuess {

    @Size(min = 1, max = 1)
    private String letter;
}
