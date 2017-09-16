/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Controllers;

import edu.ctu.cit.sudoku.Models.Puzzle;
import edu.ctu.cit.sudoku.Views.PuzzleBoard;
import java.awt.Component;
import java.awt.Panel;

/**
 *
 * @author charlie
 */
public class PuzzleBoardController {
    public interface Callback {
        public void callback();
    }
    
    
    private Puzzle puzzle = null;
    private PuzzleBoard puzzleBoard = null;
    private Puzzle stashedPuzzle = null;
    
    public PuzzleBoardController(Component parent) {
        this.puzzle = new Puzzle();
        this.puzzleBoard = new PuzzleBoard(parent);
        this.puzzleBoard.setPuzzle(puzzle);
    }
    
    public void newPuzzleBoard() {
        this.puzzle.generateNewPuzzle();
        this.puzzleBoard.setPuzzle(puzzle);
        this.stashedPuzzle = null;
    }

    public PuzzleBoard getPuzzleBoard() {
        return puzzleBoard;
    }
    
    public void setPuzzle(int[][] board) {
        this.puzzle = new Puzzle(board);
        this.puzzleBoard.setPuzzle(this.puzzle);
    }
    
    public void setRepeatedCellCheck(boolean b) {
        this.puzzleBoard.setRepeatedCellCheck(b);
    }
    
    public void setManuallyNumberInput(boolean isManuallyInput, Callback callback) {
        if (isManuallyInput) {
            this.stashedPuzzle = this.puzzle;
            this.puzzle = new Puzzle();
        } else if (this.stashedPuzzle != null) {
            if (this.puzzleBoard.isEdited() && this.puzzleBoard.isValidPuzzleBoard()) {
                this.puzzle = this.puzzleBoard.getPuzzleUserAnswer();
                callback.callback();
            } else {
                this.puzzle = this.stashedPuzzle;
            }
            this.stashedPuzzle = null;
        }
        this.puzzleBoard.setPuzzle(puzzle);
    }
}
