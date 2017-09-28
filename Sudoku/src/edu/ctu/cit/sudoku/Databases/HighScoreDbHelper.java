/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author charlie
 */
public class HighScoreDbHelper implements AutoCloseable {
    private static final String URL = "jdbc:sqlite:" + HighScoreSchema.DATABASE_NAME;
    
    private Connection conn = null;    

    public HighScoreDbHelper() {
    }
    
    public void open() throws SQLException {
        this.close();
        this.conn = DriverManager.getConnection(HighScoreDbHelper.URL);
    }

    @Override
    public void close() throws SQLException {
        if (this.conn != null) {
            this.conn.close();
            this.conn = null;
        }
    }
}
