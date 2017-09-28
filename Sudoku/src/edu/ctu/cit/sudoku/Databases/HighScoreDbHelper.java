/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author charlie
 */
public class HighScoreDbHelper implements AutoCloseable {

    private static final String URL = String.format("jdbc:sqlite:%s", HighScoreSchema.DATABASE_NAME);

    private Connection conn = null;

    public HighScoreDbHelper() throws SQLException {
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

    public void createTable() throws SQLException {
        // SQL statement for creating a new table
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s (\n"
                + "	%s integer PRIMARY KEY,\n"
                + "	%s text NOT NULL,\n"
                + "	%s integer NOT NULL\n"
                + ");",
                HighScoreSchema.HighScoreTable.TABLE_NAME,
                HighScoreSchema.HighScoreTable.Cols.ID,
                HighScoreSchema.HighScoreTable.Cols.USER_NAME,
                HighScoreSchema.HighScoreTable.Cols.SCORE
        );

        try (Statement stmt = this.conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        }
    }

    public void insert(String name, int score) throws SQLException {
        String sql = String.format(
                "INSERT INTO %s(%s, %s) VALUES(?,?)",
                HighScoreSchema.HighScoreTable.TABLE_NAME,
                HighScoreSchema.HighScoreTable.Cols.USER_NAME,
                HighScoreSchema.HighScoreTable.Cols.SCORE
        );

        try (PreparedStatement pstmt = this.conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, score);
            pstmt.executeUpdate();
        }
    }

    public ArrayList<Object[]> selectAll() throws SQLException {
        String sql = String.format(
                "SELECT %s, %s, %s FROM %s ORDER BY %s ASC",
                HighScoreSchema.HighScoreTable.Cols.ID,
                HighScoreSchema.HighScoreTable.Cols.USER_NAME,
                HighScoreSchema.HighScoreTable.Cols.SCORE,
                HighScoreSchema.HighScoreTable.TABLE_NAME,
                HighScoreSchema.HighScoreTable.Cols.SCORE
        );
        ArrayList<Object[]> result = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                result.add(new Object[]{
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3)
                });
            }
        }

        return result;
    }

}
