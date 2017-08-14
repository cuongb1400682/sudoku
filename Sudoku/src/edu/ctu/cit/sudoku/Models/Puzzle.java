/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Models;

/**
 *
 * @author charlie
 */
public class Puzzle {
    public static final int BOARD_SIZE = 9;
            
    private final int[][] board;
    
    public Puzzle() {
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
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
}
