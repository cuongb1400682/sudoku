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
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    /**
     * Test of get method, of class Puzzle.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int x = 0;
        int y = 0;
        Puzzle instance = new Puzzle();
        int expResult = 0;
        int result = instance.get(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set method, of class Puzzle.
     */
    @Test
    public void testSet() {
        System.out.println("set");
        int x = 0;
        int y = 0;
        int value = 0;
        Puzzle instance = new Puzzle();
        instance.set(x, y, value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clear method, of class Puzzle.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        Puzzle instance = new Puzzle();
        instance.clear();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enumerateRepeatedCellsInColumn method, of class Puzzle.
     */
    @Test
    public void testEnumerateRepeatedCellsInColumn() {
        System.out.println("enumerateRepeatedCellsInColumn");
        int columnNumber = 0;
        Puzzle instance = new Puzzle();
        ArrayList<Cell> expResult = null;
        ArrayList<Cell> result = instance.enumerateRepeatedCellsInColumn(columnNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enumerateRepeatedCellsInRow method, of class Puzzle.
     */
    @Test
    public void testEnumerateRepeatedCellsInRow() {
        System.out.println("enumerateRepeatedCellsInRow");
        int rowNumber = 0;
        Puzzle instance = new Puzzle();
        ArrayList<Cell> expResult = null;
        ArrayList<Cell> result = instance.enumerateRepeatedCellsInRow(rowNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enumerateRepeatedCellsInGroup method, of class Puzzle.
     */
    @Test
    public void testEnumerateRepeatedCellsInGroup() {
        System.out.println("enumerateRepeatedCellsInGroup");
        int rowIndex = 0;
        int colIndex = 0;
        Puzzle instance = new Puzzle();
        ArrayList<Cell> expResult = null;
        ArrayList<Cell> result = instance.enumerateRepeatedCellsInGroup(rowIndex, colIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of countNonEmptyCells method, of class Puzzle.
     */
    @Test
    public void testCountNonEmptyCells() {
        System.out.println("countNonEmptyCells");
        Puzzle instance = new Puzzle();
        int expResult = 0;
        int result = instance.countNonEmptyCells();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidPuzzle method, of class Puzzle.
     */
    @Test
    public void testIsValidPuzzle() {
        System.out.println("isValidPuzzle");
        Puzzle instance = new Puzzle();
        boolean expResult = false;
        boolean result = instance.isValidPuzzle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateNewPuzzle method, of class Puzzle.
     */
    @Test
    public void testGenerateNewPuzzle() {
        System.out.println("generateNewPuzzle");
        Puzzle instance = new Puzzle();
        instance.generateNewPuzzle();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of solve method, of class Puzzle.
     */
    @Test
    public void testSolve() {
        System.out.println("solve");
        Puzzle instance = new Puzzle();
        int[][] expResult = null;
        int[][] result = instance.solve();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enumerateCellProbabilities method, of class Puzzle.
     */
    @Test
    public void testEnumerateCellProbabilities() {
        System.out.println("enumerateCellProbabilities");
        int rowIndex = 8;
        int colIndex = 4;
        Puzzle instance = new Puzzle();
        try {
            instance = Puzzle.fromFile("/home/charlie/Desktop/my_puzzle.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PuzzleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Integer> expResult = new ArrayList<>(Arrays.asList(7,8,9));
        ArrayList<Integer> result = instance.enumerateCellProbabilities(rowIndex, colIndex);
        System.out.println(expResult);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Puzzle.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Puzzle instance = new Puzzle();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
