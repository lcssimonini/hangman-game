package com.simo.hangman.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;
import java.util.Random;

@JacksonXmlRootElement(localName = "hangman")
public class GameConfig {

  private static final Integer MAX_MISSES_ALLOWED = 7;

  @JsonProperty("word_list")
  public List<String> wordList;

  public Integer getMaxMissesAllowed() {
    return MAX_MISSES_ALLOWED;
  }

  public String chooseRandomWord() {
    return wordList.get(new Random().nextInt(wordList.size()));
  }
}
