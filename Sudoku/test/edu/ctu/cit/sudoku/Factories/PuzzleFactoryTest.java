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
     * Test of lasVegas method, of class PuzzleFactory.
     */
    @Test
    public void testLasVegas() {
        System.out.println("lasVegas");
        PuzzleFactory instance = new PuzzleFactory();
        int[] limits = {
            6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 20, 25, 30, 35, 40, 50, 60, 70, 80
        };
        for (Integer lim : limits) {
            int count = 0;
            for (int i = 1; i <= 1000; i++) {
                Puzzle result = instance.lasVegas(lim);
                if (result.isSolvable()) {
                    count++;
                }
                //System.out.println("i = " + i);
            }
            System.out.printf("%-5s: %5s\n", lim, count);
        }
    }

}
