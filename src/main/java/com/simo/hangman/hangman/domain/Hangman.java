package com.simo.hangman.hangman.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "hangman")
public class Hangman {

    @JsonProperty("word_list")
    public  List<String> wordList;
}
