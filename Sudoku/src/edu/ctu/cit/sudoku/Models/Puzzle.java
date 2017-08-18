/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author charlie
 */
public final class Puzzle {
    public static final int BOARD_SIZE = 9;

    private int[][] board;

    public Puzzle() {
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
        this.clear();
    }

    private boolean isValidCoordinate(int x, int y) {
        return (x >= 0) && (y >= 0) && (x < BOARD_SIZE) && (y < BOARD_SIZE);
    }

    public int get(int x, int y) {
        if (!isValidCoordinate(x, y)) {
            throw new ArrayIndexOutOfBoundsException("Board coordinate must be between 0 to 8");
        }
        return board[x][y];
    }

    public void set(int x, int y, int value) {
        if (!isValidCoordinate(x, y)) {
            throw new ArrayIndexOutOfBoundsException("Board coordinate must be between 0 to 8");
        }
        board[x][y] = value;
    }

    public void clear() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = 0;
            }
        }
    }

    private int[][] randomBoard(int nCell) {
        Random r = new Random(System.currentTimeMillis());
        int[][] resultBoard = new int[BOARD_SIZE][BOARD_SIZE];
        boolean[][] colMark = new boolean[BOARD_SIZE][20];
        boolean[][] rowMark = new boolean[BOARD_SIZE][20];
        boolean[][][] groupMark = new boolean[BOARD_SIZE][BOARD_SIZE][20];
        ArrayList<Cell> can = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                can.add(new Cell(i, j));
            }
        }

        while (nCell > 0 && !can.isEmpty()) {
            int randomCellIndex = Math.abs(r.nextInt()) % can.size();
            int x = can.get(randomCellIndex).getX();
            int y = can.get(randomCellIndex).getY();
            can.remove(randomCellIndex);

            int number = 1;
            while (colMark[y][number] || rowMark[x][number] || groupMark[x / 3][y / 3][number]) {
                number++;
                if (number > 9) {
                    break;
                }
            }

            if (number <= 9) {
                colMark[y][number] = true;
                rowMark[x][number] = true;
                groupMark[x / 3][y / 3][number] = true;
                resultBoard[x][y] = number;
                nCell--;
            }
        }

        return can.isEmpty() ? null : resultBoard;
    }

    private boolean checkSolution(int[][] board) {
        boolean[][] colMark = new boolean[BOARD_SIZE][20];
        boolean[][] rowMark = new boolean[BOARD_SIZE][20];
        boolean[][][] groupMark = new boolean[BOARD_SIZE][BOARD_SIZE][20];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                int number = board[i][j];
                if (groupMark[i / 3][j / 3][number] || colMark[j][number] || rowMark[i][number]) {
                    return false;
                }
                groupMark[i / 3][j / 3][number] = true;
                colMark[j][number] = true;
                rowMark[i][number] = true;
            }
        }
        return true;
    }

    private boolean exhaustedSearch(
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

    public void generateNewPuzzle(int level) throws Exception {        
        while (true) {
            this.board = this.randomBoard(30);
            if (this.solve() != null) {
                break;
            }
        }
    }

    public int[][] solve() {
        ArrayList<Cell> candidateCells = new ArrayList<>();
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
        int[][] resultBoard = new int[BOARD_SIZE][BOARD_SIZE];
        short[] colMark = new short[BOARD_SIZE];
        short[] rowMark = new short[BOARD_SIZE];
        short[][] groupMark = new short[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == 0) {
                    candidateCells.add(new Cell(i, j));
                } else {
                    colMark[j] |= 1 << (board[i][j] - 1);
                    rowMark[i] |= 1 << (board[i][j] - 1);
                    groupMark[i / 3][j / 3] |= 1 << (board[i][j] - 1);
                }
            }
        }

        boolean isSolvable = this.exhaustedSearch(
                0,
                candidateCells,
                board,
                resultBoard,
                colMark,
                rowMark,
                groupMark
        );

        return isSolvable ? resultBoard : null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                builder.append(String.format("%5d", this.board[i][j]));
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
