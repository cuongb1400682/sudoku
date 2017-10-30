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
import edu.ctu.cit.sudoku.Utils.PuzzleSolverUtils;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author charlie
 */
public class PuzzleFactory {

    private static final int TERMINAL_PATTERN_GIVENS_LIMIT = 30;

    public static enum GameDifficulties {
        EXTREMELY_EASY(31, 4),
        EASY(45, 5),
        MEDIUM(49, 6),
        DIFFICULT(53, 7),
        EVIL(59, 9);

        private GameDifficulties(int boardGivenLimit, int rowColGivenLimit) {
            this.boardGivenLimit = boardGivenLimit;
            this.rowColEmptyCellLowerBound = rowColGivenLimit;
        }

        public final int boardGivenLimit;
        public final int rowColEmptyCellLowerBound;
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

    public Puzzle __createPuzzle(GameDifficulties difficulties) {
        Puzzle result = createTerminalPattern();
        //System.out.println(result);
        int dugCellCount = 0;
        for (Cell cell : this.determineSequenceOfDiggingHoles(difficulties)) {
            //System.out.println(cell);
            int x = cell.getX();
            int y = cell.getY();
            if (result.get(x, y) > 0) {
                if (!violateRestriction(x, y, difficulties, result)) {
                    if (existUniqueSolutionAfterDigging(x, y, result)) {
                        result.set(x, y, 0);
                        dugCellCount++;
                    }
                }
            }
            if (dugCellCount >= difficulties.boardGivenLimit) {
                break;
            }
        }
        return result;
    }

    public Puzzle createPuzzle(GameDifficulties difficulties) {
        while (true) {
            Puzzle p = this.__createPuzzle(difficulties);
            if (p.isSolvable()) {
                return p;
            }
        }
    }

    private boolean violateRestriction(int x, int y, GameDifficulties difficulties, Puzzle result) {
        int rowEmptyCellCount = -1;
        int colEmptyCellCount = -1;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (result.get(x, i) == 0) {
                rowEmptyCellCount++;
            }
            if (result.get(i, y) == 0) {
                colEmptyCellCount++;
            }
        }
        return (rowEmptyCellCount >= difficulties.rowColEmptyCellLowerBound) || (colEmptyCellCount >= difficulties.rowColEmptyCellLowerBound);
    }

    private boolean existUniqueSolutionAfterDigging(int x, int y, Puzzle result) {
        final int oldValue = result.get(x, y);
        //System.out.println(result);
        //System.out.println(result.enumerateCellProbabilities(x, y));
        for (Integer probability : result.enumerateCellProbabilities(x, y)) {
            if (probability.equals(oldValue)) {
                continue;
            }
            result.set(x, y, probability);
            if (result.isSolvable()) {
                result.set(x, y, oldValue);
                return false;
            }
        }
        return true;
    }

    public Puzzle lasVegas(final int __lim) {
//        System.out.println("in lasVegas():");
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

        for (int cellLimit = __lim; cellLimit > 0 && !candidates.isEmpty();) {
            int randomCellIndex = Math.abs(random.nextInt()) % candidates.size();
            int x = candidates.get(randomCellIndex).getX();
            int y = candidates.get(randomCellIndex).getY();
            candidates.remove(randomCellIndex);

            // System.out.println("choose: " + x + ", " + y);
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

        //System.out.println("return from lasVegas()");
        return candidates.isEmpty() ? null : new Puzzle(resultPuzzle);
    }
    
    private Puzzle lasVegas() {
        return lasVegas(PuzzleFactory.TERMINAL_PATTERN_GIVENS_LIMIT);
    }

    public Puzzle createTerminalPattern() {
        while (true) {
            //System.out.println("createTermPatt");
            Puzzle puzzle = this.lasVegas();
            //System.out.println("puzzle = " + puzzle);
            int[][] solvedMatrix = puzzle.solve();
            //System.out.println("solvedMatrix != null: " + (solvedMatrix != null));
            if (solvedMatrix != null) {
                return new Puzzle(solvedMatrix);
            }
        }
    }

}
