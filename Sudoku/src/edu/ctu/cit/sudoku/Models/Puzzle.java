/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Models;

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
    public static final int N_BOARD_PRESET_CELLS = 30;
    
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

    public Puzzle(int[][] board)  {
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
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

    public ArrayList<Cell> checkColumn(int columnNumber) {
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

    public ArrayList<Cell> checkRow(int rowNumber) {
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

    public ArrayList<Cell> checkGroup(int rowIndex, int colIndex) {
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
    
        public int countNonZero() {
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

    public boolean isValidPuzzleBoard() {
        for (int i = 0; i < Puzzle.BOARD_SIZE; i++) {
            if (this.checkRow(i).size() > 0) {
                return false;
            }

            if (this.checkColumn(i).size() > 0) {
                return false;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.checkGroup(i, j).size() > 0) {
                    return false;
                }
            }
        }

        return this.countNonZero() == Puzzle.N_BOARD_PRESET_CELLS;
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

    public void generateNewPuzzle() {
        do {
            this.board = this.randomBoard(N_BOARD_PRESET_CELLS);
        } while (this.solve() == null);
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
