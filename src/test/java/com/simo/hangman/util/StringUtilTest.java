package com.simo.hangman.util;

import static com.simo.hangman.util.StringUtil.findAllIndexes;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class StringUtilTest {

  @Test
  public void shouldFindCharIndexes() {
    assertEquals(Arrays.asList(1, 3, 5), findAllIndexes("batata", "a"));
  }
}
