/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Utils;

import edu.ctu.cit.sudoku.Models.Cell;
import static edu.ctu.cit.sudoku.Models.Puzzle.BOARD_SIZE;
import java.util.ArrayList;

/**
 *
 * @author charlie
 */
public class PuzzleSolverUtils {

    public static boolean exhaustedSearch(
            int currIndex,
            ArrayList<Cell> candidateCells,
            int[][] board,
            int[][] resultBoard,
            short[] colMark,
            short[] rowMark,
            short[][] groupMark) {

        if (currIndex >= candidateCells.size()) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                System.arraycopy(board[i], 0, resultBoard[i], 0, BOARD_SIZE);
            }
            return true;
        }

        int currentX = candidateCells.get(currIndex).getX();
        int currentY = candidateCells.get(currIndex).getY();

        short mark = (short) (colMark[currentY] | rowMark[currentX] | groupMark[currentX / 3][currentY / 3]);
        for (int i = 0; i < 9; i++) {
            if (((mark >> i) & 1) == 0) {
                int candidateNumbers = (int) i + 1;
                board[currentX][currentY] = candidateNumbers;
                colMark[currentY] |= 1 << i;
                rowMark[currentX] |= 1 << i;
                groupMark[currentX / 3][currentY / 3] |= 1 << i;
                if (exhaustedSearch(currIndex + 1, candidateCells, board, resultBoard, colMark, rowMark, groupMark)) {
                    return true;
                }
                board[currentX][currentY] = 0;
                colMark[currentY] &= ~(1 << i);
                rowMark[currentX] &= ~(1 << i);
                groupMark[currentX / 3][currentY / 3] &= ~(1 << i);
            }
        }

        return false;
    }

}
