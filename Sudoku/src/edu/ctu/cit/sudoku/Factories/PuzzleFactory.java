/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Factories;

import edu.ctu.cit.sudoku.Models.Cell;
import edu.ctu.cit.sudoku.Models.Puzzle;
import static edu.ctu.cit.sudoku.Models.Puzzle.BOARD_SIZE;
import edu.ctu.cit.sudoku.Utils.ArrayShuffle;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author charlie
 */
public class PuzzleFactory {

    private static final int TERMINAL_PATTERN_GIVENS_LIMIT = 11;

    public enum GameDifficulties {
        EXTREMELY_EASY,
        EASY,
        MEDIUM,
        DIFFICULT,
        EVIL
    }

    public enum DiggingHoleSequences {
        LEFT_RIGHT_TOP_BOTTOM,
        S_WANDERING,
        JUMP_ON_ONE_CELL,
        RANDOMLY
    }

    public Puzzle createPuzzle(GameDifficulties difficulties) {
        switch (difficulties) {
            case EXTREMELY_EASY:
                break;
            case EASY:
                break;
            case MEDIUM:
                break;
            case DIFFICULT:
                break;
            case EVIL:
                break;
            default:
                throw new EnumConstantNotPresentException(GameDifficulties.class, difficulties.toString());
        }

        return null;
    }

    private Puzzle lasVegas() {
        Random random = new Random(System.currentTimeMillis());
        int[][] resultPuzzle = new int[BOARD_SIZE][BOARD_SIZE];
        boolean[][] colMark = new boolean[BOARD_SIZE][10];
        boolean[][] rowMark = new boolean[BOARD_SIZE][10];
        boolean[][][] groupMark = new boolean[BOARD_SIZE][BOARD_SIZE][10];
        ArrayList<Cell> candidates = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                candidates.add(new Cell(i, j));
            }
        }

        for (int cellLimit = PuzzleFactory.TERMINAL_PATTERN_GIVENS_LIMIT; cellLimit > 0 && !candidates.isEmpty();) {
            int randomCellIndex = Math.abs(random.nextInt()) % candidates.size();
            int x = candidates.get(randomCellIndex).getX();
            int y = candidates.get(randomCellIndex).getY();
            candidates.remove(randomCellIndex);

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
                resultPuzzle[x][y] = number;
                cellLimit--;
            }
        }

        return candidates.isEmpty() ? null : new Puzzle(resultPuzzle);
    }

    public Puzzle createTerminalPattern() {
        while (true) {
            Puzzle puzzle = this.lasVegas();
            int[][] solvedMatrix = puzzle.solve();
            if (solvedMatrix != null) {
                return new Puzzle(solvedMatrix);
            }
        }
    }

    public ArrayList<Cell> generateCellJumpingSequence(DiggingHoleSequences diggingHoleSequences) {
        ArrayList<Cell> orderedCells = new ArrayList<>();
        switch (diggingHoleSequences) {
            case RANDOMLY:
                for (int i = 0; i < BOARD_SIZE; i++) {
                    for (int j = 0; j < BOARD_SIZE; j++) {
                        orderedCells.add(new Cell(i, j));
                    }
                }   ArrayShuffle.shuffle(orderedCells);
                break;
            case S_WANDERING:
                break;
            case JUMP_ON_ONE_CELL:
                break;
            case LEFT_RIGHT_TOP_BOTTOM:
                break;
            default:
                break;
        }
        return orderedCells;
    }
}
