/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Commands;

import edu.ctu.cit.sudoku.Controllers.PuzzleBoardController;
import edu.ctu.cit.sudoku.Views.PuzzleBoard;

/**
 *
 * @author charlie
 */
public class SetCellNumberCommand implements Command {
    private PuzzleBoard board = null;
    private int oldValue = 0;
    private int newValue = 0;
    private int x = 0;
    private int y = 0;

    public SetCellNumberCommand(PuzzleBoard board, int x, int y, int newValue) {
        this.board = board;
        this.oldValue = this.board.getValue(x, y);
        this.newValue = newValue;
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void execute() {
        this.board.setValue(this.x, this.y, this.newValue);
    }

    @Override
    public void undo() {
        this.board.setValue(this.x, this.y, this. oldValue);
    }
    
}
