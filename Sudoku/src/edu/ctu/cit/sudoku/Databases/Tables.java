/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Databases;

/**
 *
 * @author charlie
 */
public class Tables {
    public static class HighScore {
        public static final String DATABASE_NAME = "highscore.db";
        
        public static class Cols {
            public static final String ID = "id";
            public static final String USER_NAME = "name";
            public static final String SOCRE = "score";
        }
    }
}
