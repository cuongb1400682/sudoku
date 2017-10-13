/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
public class ArrayShuffleTest {

    public ArrayShuffleTest() {
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
     * Test of shuffle method, of class ArrayShuffle.
     */
    @Test
    public void testShuffle() {
        System.out.println("shuffle");
        ArrayList<Integer> initalArray = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        ArrayList<Integer> shuffledArray = (ArrayList<Integer>) initalArray.clone();
        ArrayShuffle.shuffle(shuffledArray);

        System.out.println("initalArray   = " + initalArray.toString());
        System.out.println("shuffledArray = " + shuffledArray.toString());

        boolean assertStatement1 = !initalArray.equals(shuffledArray);

        Collections.sort(shuffledArray);
        System.out.println("sorted shuffledArray = " + shuffledArray.toString());

        boolean assertStatement2 = shuffledArray.equals(initalArray);

        assertTrue(assertStatement1 && assertStatement2);
    }

}
