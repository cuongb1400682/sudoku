/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Models;

import static edu.ctu.cit.sudoku.Models.Puzzle.BOARD_SIZE;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.omg.CORBA.Environment;

/**
 *
 * @author charlie
 */
public class PuzzleTest {

    public PuzzleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

 
    void printBoard(int[][] board) {
        System.out.println("the random board is:");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println();
    }

    /**
     * Test of enumerateRepeatedCellsInColumn method, of class Puzzle.
     */
    @Test
    public void testCheckColumn() {
       
    }
    
    @Test
    public void testFromFile() throws FileNotFoundException {
        Puzzle instance = new Puzzle();
        instance.fromFile("/home/charlie/Desktop/puzzle.txt");
        System.out.println(instance.toString());
    }
}
