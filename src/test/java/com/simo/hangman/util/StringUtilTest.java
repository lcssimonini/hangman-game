package com.simo.hangman.util;

import static com.simo.hangman.util.StringUtil.findAllIndexes;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class StringUtilTest {

  @Test
  void shouldFindCharIndexes() {

    assertEquals(Arrays.asList(1, 3, 5), findAllIndexes("batata", "a"));
    assertEquals(Arrays.asList(0, 2), findAllIndexes("ovo", "o"));
    assertEquals(Arrays.asList(5), findAllIndexes("viagem", "m"));
  }
}
