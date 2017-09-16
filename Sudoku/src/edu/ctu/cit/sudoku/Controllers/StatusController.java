/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Controllers;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author charlie
 */
public class StatusController {

    private JLabel view = null;

    public static final int STATUS_WARNING = 0;
    public static final int STATUS_NORMAL = 1;
    public static final int STATUS_ERROR = 2;

    private static final int FONT_SIZE = 12;

    StatusController(JLabel view) {
        this.view = view;
    }

    private void setFontRelativeToStatus(int status) {
        switch (status) {
            case STATUS_ERROR:
                this.view.setForeground(Color.red);
                this.view.setFont(new Font("Dialog", Font.BOLD, FONT_SIZE));
                break;
            case STATUS_NORMAL:
                this.view.setForeground(new Color(187, 187, 187));
                this.view.setFont(new Font("Dialog", Font.BOLD, FONT_SIZE));
                break;
            case STATUS_WARNING:
                this.view.setForeground(Color.yellow);
                this.view.setFont(new Font("Dialog", Font.ITALIC, FONT_SIZE));
                break;
        }
    }

    public void setStatus(String message) {
        this.setFontRelativeToStatus(STATUS_NORMAL);
        this.view.setText(message);
    }

    public void setStatus(String message, int status) {
        this.setFontRelativeToStatus(status);
        this.view.setText(message);
    }
}
