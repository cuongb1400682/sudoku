/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Utils;

import edu.ctu.cit.sudoku.Models.Cell;
import static edu.ctu.cit.sudoku.Models.Puzzle.BOARD_SIZE;
import edu.ctu.cit.sudoku.Utils.ArrayListRandomUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author charlie
 */
public class DiggingSequencesUtils {
    public enum DiggingHoleSequenceType {
        LEFT_RIGHT_TOP_BOTTOM,
        S_WANDERING,
        JUMP_ON_ONE_CELL,
        RANDOMLY
    }

    private static ArrayList<Cell> leftRightTopBottomSequence() {
        ArrayList<Cell> orderedCells = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                orderedCells.add(new Cell(i, j));
            }
        }
        return orderedCells;
    }

    private static ArrayList<Cell> sWanderingSequence() {
        ArrayList<Cell> sequence = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                sequence.add(new Cell(i, (i & 1) == 0 ? j : 8 - j));
            }
        }
        return sequence;
    }
    
    private static ArrayList<Cell> jumpOnOneCell() {
        ArrayList<Cell> sequence = new ArrayList<>();
        ArrayList<Cell> sWanderSequence = sWanderingSequence();
        for (int i = 0; i < sWanderSequence.size(); i += 2) {
            sequence.add(sWanderSequence.get(i));
        }
        for (int i = 1; i < sWanderSequence.size(); i += 2) {
            sequence.add(sWanderSequence.get(i));
        }                
        return sequence;
    }

    public static ArrayList<Cell> generateCellJumpingSequence(DiggingHoleSequenceType diggingHoleSequenceType) {
        switch (diggingHoleSequenceType) {
            case RANDOMLY:
                return ArrayListRandomUtils.shuffle(leftRightTopBottomSequence());
            case S_WANDERING:
                return sWanderingSequence();
            case JUMP_ON_ONE_CELL:
                return jumpOnOneCell();
            case LEFT_RIGHT_TOP_BOTTOM:
                return leftRightTopBottomSequence();
        }
        return new ArrayList<>();
    }

}
