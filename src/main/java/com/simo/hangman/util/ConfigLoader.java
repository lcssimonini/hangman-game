package com.simo.hangman.util;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.simo.hangman.domain.GameConfig;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

@Slf4j
@UtilityClass
public class ConfigLoader {

  public static final String WORDS_CONFIG_FILE = "words.xml";

  public static GameConfig getGameconfig() {
    XmlMapper xmlMapper = new XmlMapper();
    try {
      Reader in = new InputStreamReader(new ClassPathResource(WORDS_CONFIG_FILE).getInputStream());
      return xmlMapper.readValue(in, GameConfig.class);
    } catch (IOException e) {
      log.error("problem loading game configuration", e);
      throw new GameConfigLoadingException();
    }
  }

  private static class GameConfigLoadingException extends RuntimeException {}
}
