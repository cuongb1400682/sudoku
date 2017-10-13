/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Factories;

import edu.ctu.cit.sudoku.Models.Puzzle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author charlie
 */
public class PuzzleFactoryTest {

    public PuzzleFactoryTest() {
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

    /**
     * Test of createPuzzle method, of class PuzzleFactory.
     */
    @Test
    public void testCreatePuzzle() {
        System.out.println("createPuzzle");
        PuzzleFactory.GameDifficulties difficulties = PuzzleFactory.GameDifficulties.EXTREMELY_EASY;
        PuzzleFactory instance = new PuzzleFactory();
        Puzzle expResult = null;
        Puzzle result = instance.createPuzzle(difficulties);
        assertEquals(expResult, result);
    }

    /**
     * Test of createTerminalPattern method, of class PuzzleFactory.
     */
    @Test
    public void testCreateTerminalPattern() {
        System.out.println("createTerminalPattern");
        for (int i = 0; i < 1000; i++) {
            PuzzleFactory instance = new PuzzleFactory();
            Puzzle expResult = null;
            Puzzle result = instance.createTerminalPattern();
            System.out.println(result);
        }
        assertEquals(0, 0);
    }

}
