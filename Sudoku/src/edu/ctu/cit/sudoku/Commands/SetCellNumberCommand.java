/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Commands;

import edu.ctu.cit.sudoku.Models.Puzzle;
import edu.ctu.cit.sudoku.Views.PuzzleBoard;
import edu.ctu.cit.sudoku.Views.PuzzleCell;

/**
 *
 * @author charlie
 */
public class SetCellNumberCommand implements Command {
    private Puzzle puzzle = null;
    private PuzzleCell cell = null;
    private int oldValue = 0;
    private int newValue = 0;
    private int x = 0;
    private int y = 0;

    public SetCellNumberCommand(Puzzle puzzle, PuzzleCell cell, int x, int y, int newValue) {
        this.puzzle = puzzle;
        this.cell = cell;
        this.oldValue = this.puzzle.get(x, y);
        this.newValue = newValue;
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void execute() {
        this.puzzle.set(this.x, this.y, this.newValue);
        this.cell.setValue("0123456789".charAt(this.newValue));
    }

    @Override
    public void undo() {
        this.puzzle.set(this.x, this.y, this.oldValue);
        this.cell.setValue("0123456789".charAt(this.oldValue));
    }
    
}
