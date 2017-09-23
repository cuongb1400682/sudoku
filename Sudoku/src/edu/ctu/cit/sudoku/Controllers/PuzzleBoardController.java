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
        if (!puzzle.isValidPuzzleBoard()) {
            throw new Puzzle.InvalidPuzzleException("Selected file contains invalid puzzle!");
        }
        this.puzzleBoard.setPuzzle(puzzle);
    }
}
