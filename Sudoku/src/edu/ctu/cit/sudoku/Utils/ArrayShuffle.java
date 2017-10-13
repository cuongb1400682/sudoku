/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this temporary file, choose Tools | Templates
 * and open the temporary in the editor.
 */
package edu.ctu.cit.sudoku.Utils;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author charlie
 */
public class ArrayShuffle {
    public static <T> void shuffle(ArrayList<T> array) {
        Random random = new Random(System.currentTimeMillis());
        for (int currentIndex = 0; currentIndex < array.size(); currentIndex++) {
            int randomIndex = Math.abs(random.nextInt()) % array.size();
            T temporaryValue = array.get(currentIndex);
            array.set(currentIndex, array.get(randomIndex));
            array.set(randomIndex, temporaryValue);
        }
    }
}
