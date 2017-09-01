/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Views;

import edu.ctu.cit.sudoku.Models.Puzzle;
import java.awt.GridLayout;

/**
 *
 * @author charlie
 */
public class PuzzleBoard extends javax.swing.JPanel {
    private final NumberChooser numberChooser = new NumberChooser();

    private PuzzleCell[][] grid = new PuzzleCell[Puzzle.BOARD_SIZE][Puzzle.BOARD_SIZE];
    private PuzzleCell selectedPuzzleCell = null;
    private Puzzle puzzle = null;
    private Puzzle puzzleUserAnswer = null;
    private boolean isRepeatedCellCheck = false;

    /**
     * Creates new form PuzzleBoard
     */
    public PuzzleBoard() {
        initComponents();
        addPuzzleCells();
        numberChooser.setNumberSelected(new NumberChooser.OnNumberSelected() {
            @Override
            public void onNumberSelected(int number) {
                if (PuzzleBoard.this.selectedPuzzleCell != null) {
                    PuzzleBoard.this.selectedPuzzleCell.setValue("0123456789".charAt(number % 10));
                }
            }
        });
    }

    private void addPuzzleCells() {
        GridLayout layout = new GridLayout(Puzzle.BOARD_SIZE, Puzzle.BOARD_SIZE);
        this.setLayout(layout);

        for (int i = 0; i < Puzzle.BOARD_SIZE; i++) {
            for (int j = 0; j < Puzzle.BOARD_SIZE; j++) {
                grid[i][j] = new PuzzleCell();

                final PuzzleCell finalGridIJ = grid[i][j];
                final int finalI = i;
                final int finalJ = j;

                grid[i][j].setOnPuzzleCellClicked((PuzzleCell cell) -> {
                    if (selectedPuzzleCell != null) {
                        selectedPuzzleCell.setState(PuzzleCell.STATE_ENABLE);
                    }
                    finalGridIJ.setState(PuzzleCell.STATE_SELECTED);
                    selectedPuzzleCell = finalGridIJ;

                    PuzzleBoard.this.numberChooser.setLocation(cell.getCurrentLocation());
                    PuzzleBoard.this.numberChooser.setVisible(true);
                });

                grid[i][j].setOnPuzzleCellValueChanged((PuzzleCell cell, int oldValue, int newValue) -> {
                    puzzleUserAnswer.set(finalI, finalJ, newValue);
                    checkRepeatedCells();
                });
                
                this.add(grid[i][j]);
            }
        }
    }

    public void checkRepeatedCells() {
        if (PuzzleBoard.this.isRepeatedCellCheck) {
            clearMistakes();
            for (int i = 0; i < Puzzle.BOARD_SIZE; i++) {
                puzzleUserAnswer
                        .checkRow(i)
                        .forEach(c -> {
                            PuzzleCell repeatedCell = grid[c.getX()][c.getY()];
                            repeatedCell.setRepeated(true);
                        });
                puzzleUserAnswer
                        .checkColumn(i)
                        .forEach(c -> {
                            PuzzleCell repeatedCell = grid[c.getX()][c.getY()];
                            repeatedCell.setRepeated(true);
                        });
            }
        }
    }

    public void clearMistakes() {
        for (int i = 0; i < Puzzle.BOARD_SIZE; i++) {
            for (int j = 0; j < Puzzle.BOARD_SIZE; j++) {
                if (grid[i][j].isRepeated()) {
                    grid[i][j].setRepeated(false);
                }
            }
        }
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.puzzleUserAnswer = new Puzzle(puzzle);
        
        clearMistakes();
        for (int i = 0; i < Puzzle.BOARD_SIZE; i++) {
            for (int j = 0; j < Puzzle.BOARD_SIZE; j++) {
                grid[i][j].reset();
                if (puzzle.get(i, j) != 0) {
                    grid[i][j].setText("" + puzzle.get(i, j));
                    grid[i][j].setState(PuzzleCell.STATE_DISABLE);
                } else {
                    grid[i][j].setState(PuzzleCell.STATE_ENABLE);
                }
            }
        }
    }

    public boolean isRepeatedCellCheck() {
        return isRepeatedCellCheck;
    }

    public void setRepeatedCellCheck(boolean isRepeatedCellCheck) {
        this.isRepeatedCellCheck = isRepeatedCellCheck;
        if (isRepeatedCellCheck) {
            checkRepeatedCells();
        } else {
            clearMistakes();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
