package com.simo.hangman.util;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.simo.hangman.domain.GameConfig;
import java.io.IOException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

@Slf4j
@UtilityClass
public class ConfigLoader {

  public static GameConfig getGameconfig() {
    XmlMapper xmlMapper = new XmlMapper();
    try {
      return xmlMapper.readValue(ResourceUtils.getFile("classpath:words.xml"), GameConfig.class);
    } catch (IOException e) {
      log.error("problem loading game configuration", e);
      throw new GameConfigLoadingException();
    }
  }

  private static class GameConfigLoadingException extends RuntimeException {}
}
