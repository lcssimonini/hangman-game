package com.simo.hangman.util;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class StringUtil {

    public static List<Integer> findAllIndexes(String word, String letter) {

        List<Integer> indexes = new ArrayList<>();
        int index = 0;
        while(index != -1){
            index = word.indexOf(letter, index);
            if (index != -1) {
                indexes.add(index);
                index++;
            }
        }
        return indexes;
    }
}
