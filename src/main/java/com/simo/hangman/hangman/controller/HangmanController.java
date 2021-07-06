package com.simo.hangman.hangman.controller;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.simo.hangman.hangman.domain.Hangman;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/hangman")
@RestController
public class HangmanController {

    @GetMapping
    public Hangman getHangmanWords() throws IOException {

        XmlMapper xmlMapper = new XmlMapper();
        Hangman hangman = xmlMapper.readValue(ResourceUtils.getFile(
                "classpath:words.xml"), Hangman.class);

        return hangman;
    }
}
