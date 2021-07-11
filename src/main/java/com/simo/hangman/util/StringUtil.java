package com.simo.hangman.util;

import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtil {

  public static List<Integer> findAllIndexes(String word, String letter) {
    List<Integer> indexes = new ArrayList<>();
    int index = 0;
    while (index != -1) {
      index = word.indexOf(letter, index);
      if (index != -1) {
        indexes.add(index);
        index++;
      }
    }
    return indexes;
  }
}
