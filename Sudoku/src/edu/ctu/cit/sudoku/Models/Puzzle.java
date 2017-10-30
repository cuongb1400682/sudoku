/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Models;

import edu.ctu.cit.sudoku.Utils.PuzzleSolverUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author charlie
 */
public final class Puzzle {
    public static final int BOARD_SIZE = 9;

    public boolean isSolvable() {
        return this.solve() != null;
    }

    public static class InvalidPuzzleException extends Exception {

        public InvalidPuzzleException(String message) {
            super(message);
        }
    }

    private int[][] board;

    public Puzzle() {
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
        this.clear();
    }

    public Puzzle(Puzzle that) {
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
        this.clear();
        for (int i = 0; i < Puzzle.BOARD_SIZE; i++) {
            System.arraycopy(that.board[i], 0, this.board[i], 0, BOARD_SIZE);
        }
    }

    public Puzzle(int[][] board) {
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
        this.clear();
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.arraycopy(board[i], 0, this.board[i], 0, BOARD_SIZE);
        }
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

    public static Puzzle fromFile(String inputFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(inputFile));
        int rowIndex = 0;
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(line);
            int columnIndex = 0;

            while (tokenizer.hasMoreTokens()) {
                board[rowIndex][columnIndex] = Integer.parseInt(tokenizer.nextToken());
                columnIndex++;
            }

            rowIndex++;
        }

        return new Puzzle(board);
    }

    public static void toFile(Puzzle puzzle, String outputFile) throws FileNotFoundException, IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            writer.print(puzzle.toString());
        }
    }

    public ArrayList<Cell> enumerateRepeatedCellsInColumn(int columnNumber) {
        Cell[] colMark = new Cell[20];
        boolean[][] inResult = new boolean[BOARD_SIZE][BOARD_SIZE];
        ArrayList<Cell> violatedCells = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < BOARD_SIZE; rowIndex++) {
            int value = this.board[rowIndex][columnNumber];
            if (value > 0) {
                Cell previousCell = colMark[value];
                if (previousCell != null) {
                    if (!inResult[previousCell.getX()][previousCell.getY()]) {
                        violatedCells.add(previousCell);
                        inResult[previousCell.getX()][previousCell.getY()] = true;
                    }
                    if (!inResult[rowIndex][columnNumber]) {
                        violatedCells.add(new Cell(rowIndex, columnNumber));
                        inResult[rowIndex][columnNumber] = true;
                    }
                }
                colMark[value] = new Cell(rowIndex, columnNumber);
            }
        }

        return violatedCells;
    }

    public ArrayList<Cell> enumerateRepeatedCellsInRow(int rowNumber) {
        Cell[] rowMark = new Cell[20];
        boolean[][] inResult = new boolean[BOARD_SIZE][BOARD_SIZE];
        ArrayList<Cell> violatedCells = new ArrayList<>();

        for (int colIndex = 0; colIndex < BOARD_SIZE; colIndex++) {
            int value = this.board[rowNumber][colIndex];
            if (value > 0) {
                Cell previousCell = rowMark[value];
                if (previousCell != null) {
                    if (!inResult[previousCell.getX()][previousCell.getY()]) {
                        violatedCells.add(previousCell);
                        inResult[previousCell.getX()][previousCell.getY()] = true;
                    }
                    if (!inResult[rowNumber][colIndex]) {
                        violatedCells.add(new Cell(rowNumber, colIndex));
                        inResult[rowNumber][colIndex] = true;
                    }
                }
                rowMark[value] = new Cell(rowNumber, colIndex);
            }
        }

        return violatedCells;
    }

    public ArrayList<Cell> enumerateRepeatedCellsInGroup(int rowIndex, int colIndex) {
        Cell[] groupMark = new Cell[20];
        boolean[][] inResult = new boolean[BOARD_SIZE][BOARD_SIZE];
        ArrayList<Cell> violatedCells = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (i / 3 == rowIndex && j / 3 == colIndex) {
                    int value = this.board[i][j];
                    if (value > 0) {
                        Cell previousCell = groupMark[value];
                        if (previousCell != null) {
                            if (!inResult[previousCell.getX()][previousCell.getY()]) {
                                violatedCells.add(previousCell);
                                inResult[previousCell.getX()][previousCell.getY()] = true;
                            }
                            if (!inResult[i][j]) {
                                violatedCells.add(new Cell(i, j));
                                inResult[i][j] = true;
                            }
                        }
                        groupMark[value] = new Cell(i, j);
                    }
                }
            }
        }

        return violatedCells;
    }

    public int countNonEmptyCells() {
        int nonZeroCount = 0;
        for (int i = 0; i < Puzzle.BOARD_SIZE; i++) {
            for (int j = 0; j < Puzzle.BOARD_SIZE; j++) {
                if (this.get(i, j) > 0) {
                    nonZeroCount++;
                }
            }
        }
        return nonZeroCount;
    }

    public boolean isValidPuzzle() {
        for (int i = 0; i < Puzzle.BOARD_SIZE; i++) {
            if (this.enumerateRepeatedCellsInRow(i).size() > 0) {
                return false;
            }

            if (this.enumerateRepeatedCellsInColumn(i).size() > 0) {
                return false;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.enumerateRepeatedCellsInGroup(i, j).size() > 0) {
                    return false;
                }
            }
        }

        //return this.countNonEmptyCells() == Puzzle.N_PRESET_CELLS;
        return true;
    }

    public int[][] solve() {
        ArrayList<Cell> candidateCells = new ArrayList<>();
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
        int[][] resultBoard = new int[BOARD_SIZE][BOARD_SIZE];
        short[] colMark = new short[BOARD_SIZE];
        short[] rowMark = new short[BOARD_SIZE];
        short[][] groupMark = new short[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            System.arraycopy(this.board[i], 0, board[i], 0, BOARD_SIZE);
        }

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

        PuzzleSolverUtils.beginningTimeTick = System.currentTimeMillis();
        boolean isSolvable = PuzzleSolverUtils.exhaustedSearch(
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

    public ArrayList<Integer> enumerateCellProbabilities(final int rowIndex, final int colIndex) {
        boolean[] isSelected = new boolean[10];
        
        for (int index = 0; index < BOARD_SIZE; index++) {
            isSelected[this.get(rowIndex, index)] = true;
            isSelected[this.get(index, colIndex)] = true;
        }
        
        final int rowGroupIndex = 3 * (int) (rowIndex / 3);
        final int colGroupIndex = 3 * (int) (colIndex / 3);
        for (int i = rowGroupIndex; i < rowGroupIndex + 3; i++) {
            for (int j = colGroupIndex; j < colGroupIndex + 3; j++) {
                isSelected[this.get(i, j)] = true;
            }
        }
        
        ArrayList<Integer> probabilities = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (!isSelected[i]) {
                probabilities.add(i);
            }
        }
        
        return probabilities;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                final int value = this.board[i][j];
                builder.append(String.format("%5s", value));
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
