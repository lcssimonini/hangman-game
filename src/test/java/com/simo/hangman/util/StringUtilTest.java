package com.simo.hangman.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.simo.hangman.util.StringUtil.findAllIndexes;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilTest {

  @Test
  public void shouldFindCharIndexes() {

    assertEquals(Arrays.asList(1, 3, 5), findAllIndexes("batata", "a"));
    assertEquals(Arrays.asList(0, 2), findAllIndexes("ovo", "o"));
    assertEquals(Arrays.asList(5), findAllIndexes("viagem", "m"));
  }
}
