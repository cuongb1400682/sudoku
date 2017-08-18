/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Models;

import static edu.ctu.cit.sudoku.Models.Puzzle.BOARD_SIZE;
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
     * Test of solve method, of class Puzzle.
     */
    @Test
    public void testSolve() {
        System.out.println("solve");
        Puzzle instance = new Puzzle();
        instance.solve();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    void printBoard(int[][] board) {
        System.out.println("the random board is:");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println();
    }

    int[][] randomBoard(int nCell) throws Exception {
        Random r = new Random(System.currentTimeMillis());
        int[][] resultBoard = new int[BOARD_SIZE][BOARD_SIZE];
        boolean[][] colMark = new boolean[BOARD_SIZE][20];
        boolean[][] rowMark = new boolean[BOARD_SIZE][20];
        boolean[][][] groupMark = new boolean[BOARD_SIZE][BOARD_SIZE][20];
        ArrayList<Cell> can = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                can.add(new Cell(i, j));
            }
        }

        while (nCell > 0 && !can.isEmpty()) {
            int randomCellIndex = Math.abs(r.nextInt()) % can.size();
            int x = can.get(randomCellIndex).getX();
            int y = can.get(randomCellIndex).getY();
            can.remove(randomCellIndex);

            int number = 1;
            while (colMark[y][number] || rowMark[x][number] || groupMark[x / 3][y / 3][number]) {
                number++;
                if (number > 9) {
                    break;
                }
            }

            if (number <= 9) {
                colMark[y][number] = true;
                rowMark[x][number] = true;
                groupMark[x / 3][y / 3][number] = true;
                resultBoard[x][y] = number;
                nCell--;
            }
        }

        if (can.isEmpty()) {
            throw new Exception("Cannot generate random board");
        }

        printBoard(resultBoard);

        return resultBoard;
    }

    boolean checkSolution(int[][] board) {
        boolean[][] colMark = new boolean[BOARD_SIZE][20];
        boolean[][] rowMark = new boolean[BOARD_SIZE][20];
        boolean[][][] groupMark = new boolean[BOARD_SIZE][BOARD_SIZE][20];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                int number = board[i][j];
                if (groupMark[i/3][j/3][number] || colMark[j][number] || rowMark[i][number]) {
                    return false;
                }
                groupMark[i/3][j/3][number] = true;
                colMark[j][number] = true;
                rowMark[i][number] = true;
            }
        }
        return true;
    }

    /**
     * Test of exhaustedSearch method, of class Puzzle.
     */
    @Test
    public void testExhaustedSearch() throws Exception {
        System.out.println("exhaustedSearch");
        int currIndex = 0;
        ArrayList<Cell> candidateCells = new ArrayList<>();

        int[][] board = randomBoard(25);

        int[][] resultBoard = new int[BOARD_SIZE][BOARD_SIZE];
        short[] colMark = new short[BOARD_SIZE];
        short[] rowMark = new short[BOARD_SIZE];
        short[][] groupMark = new short[BOARD_SIZE][BOARD_SIZE];
        Puzzle instance = new Puzzle();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == 0) {
                    candidateCells.add(new Cell(i, j));
                    //System.out.printf("add candidate %d, %d\n", i, j);
                } else {
                    colMark[j] |= 1 << (board[i][j] - 1);
                    rowMark[i] |= 1 << (board[i][j] - 1);
                    groupMark[i / 3][j / 3] |= 1 << (board[i][j] - 1);
                    //System.out.printf("mark %d at col %d, row %d, group %d-%d\n", board[i][j], j, i, i/3,j/3);
                }
            }
        }

        boolean result = instance.exhaustedSearch(
                currIndex,
                candidateCells,
                board,
                resultBoard,
                colMark,
                rowMark,
                groupMark
        );
        
        assertEquals(result, true);
        assertTrue(checkSolution(resultBoard));
        
        System.out.println("OK");

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.printf("%5d", resultBoard[i][j]);
            }
            System.out.println();
        }
    }

}
