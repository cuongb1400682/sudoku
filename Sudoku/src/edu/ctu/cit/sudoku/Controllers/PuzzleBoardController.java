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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JMenuItem;

/**
 *
 * @author charlie
 */
public class PuzzleBoardController {
    private Puzzle puzzle = null;
    private PuzzleBoard puzzleBoard = null;

    public PuzzleBoardController(Component parent) {
        this.puzzle = new Puzzle();
        this.puzzleBoard = new PuzzleBoard(parent);
        this.puzzleBoard.setPuzzle(puzzle);
    }

    public void newPuzzleBoard() {
        this.puzzle.generateNewPuzzle();
        this.puzzleBoard.setPuzzle(puzzle);
    }

    public PuzzleBoard getPuzzleBoard() {
        return puzzleBoard;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.puzzleBoard.setPuzzle(this.puzzle);
    }

    public void setRepeatedCellCheck(boolean b) {
        this.puzzleBoard.setRepeatedCellCheck(b);
    }

    public void closeNumberChooser() {
        this.puzzleBoard.closeNumberChooser();
    }

    public void hidePuzzleBoard() {
        this.puzzleBoard.setVisible(false);
    }

    public void showPuzzleBoard() {
        this.puzzleBoard.setVisible(true);
    }

    public void fromFile(File file) throws FileNotFoundException, Puzzle.InvalidPuzzleException {
        Puzzle puzzle = Puzzle.fromFile(file.getAbsolutePath());
        if (!puzzle.isValidPuzzle()) {
            throw new Puzzle.InvalidPuzzleException("Selected file contains invalid puzzle!");
        }
        this.puzzleBoard.setPuzzle(puzzle);
        this.puzzle = puzzle;
    }

    public void toFile(File file) throws IOException {
        Puzzle.toFile(puzzle, file.getAbsolutePath());
    }

    public void undo() {
        this.puzzleBoard.undo();
    }

    public void redo() {
        this.puzzleBoard.redo();
    }
    
    public void setMenuUndo(JMenuItem menuUndo) {
        this.puzzleBoard.setMenuUndo(menuUndo);
    }
    
    public void setMenuRedo(JMenuItem menuRedo) {
        this.puzzleBoard.setMenuRedo(menuRedo);
    }

    public boolean isSolved() {
        return this.puzzle.isValidPuzzle() && 
                (this.puzzle.countNonEmptyCells() == Puzzle.BOARD_SIZE * Puzzle.BOARD_SIZE);
    }
   
    public void setUseWonTheGameHandler(PuzzleBoard.OnUserWonTheGame onUserWonTheGame) {
        this.puzzleBoard.setOnUserWonTheGame(onUserWonTheGame);
    }
    
    public void solveTheGame() {
        this.puzzleBoard.showResult();
    }

    public void clearPuzzle() {
        this.puzzleBoard.setPuzzle(this.puzzle);
    }
}
    