/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Factories;

import edu.ctu.cit.sudoku.Models.Cell;
import edu.ctu.cit.sudoku.Models.Puzzle;
import static edu.ctu.cit.sudoku.Models.Puzzle.BOARD_SIZE;
import edu.ctu.cit.sudoku.Utils.ArrayListRandomUtils;
import edu.ctu.cit.sudoku.Utils.DiggingSequencesUtils;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author charlie
 */
public class PuzzleFactory {

    private static final int TERMINAL_PATTERN_GIVENS_LIMIT = 11;

    public static enum GameDifficulties {
        EXTREMELY_EASY(52, 5),
        EASY(40, 4),
        MEDIUM(33, 3),
        DIFFICULT(30, 2),
        EVIL(25, 0);

        private GameDifficulties(int boardGivenLimit, int rowColGivenLimit) {
            this.boardGivenLimit = boardGivenLimit;
            this.rowColGivenLimit = rowColGivenLimit;
        }

        public final int boardGivenLimit;
        public final int rowColGivenLimit;
    }

    private ArrayList<Cell> determineSequenceOfDiggingHoles(GameDifficulties difficulties) {
        ArrayList<Cell> orderedCells = new ArrayList<>();
        switch (difficulties) {
            case EXTREMELY_EASY:
            case EASY:
                orderedCells = DiggingSequencesUtils.generateCellJumpingSequence(DiggingSequencesUtils.DiggingHoleSequenceType.RANDOMLY);
                break;
            case MEDIUM:
                orderedCells = DiggingSequencesUtils.generateCellJumpingSequence(DiggingSequencesUtils.DiggingHoleSequenceType.JUMP_ON_ONE_CELL);
                break;
            case DIFFICULT:
                orderedCells = DiggingSequencesUtils.generateCellJumpingSequence(DiggingSequencesUtils.DiggingHoleSequenceType.S_WANDERING);
                break;
            case EVIL:
                orderedCells = DiggingSequencesUtils.generateCellJumpingSequence(DiggingSequencesUtils.DiggingHoleSequenceType.LEFT_RIGHT_TOP_BOTTOM);
                break;
        }
        return orderedCells;
    }

    public Puzzle createPuzzle(GameDifficulties difficulties) {
        Puzzle result = createTerminalPattern();
        for (Cell cell : this.determineSequenceOfDiggingHoles(difficulties)) {
            int x = cell.getX();
            int y = cell.getY();
            if (result.get(x, y) > 0) {
                if (!violateRestriction(x, y, difficulties, result)) {
                    if (existUniqueSolutionAfterDigging(x, y, result)) {
                        result.set(x, y, 0);
                    }
                }
            }
        }
        return result;
    }

    private boolean violateRestriction(int x, int y, GameDifficulties difficulties, Puzzle result) {
        return false;
    }
    
    private boolean existUniqueSolutionAfterDigging(int x, int y, Puzzle result) {
        return false;
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

}
