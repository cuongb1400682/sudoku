/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Views;

import java.awt.AWTEventMulticaster;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

/**
 *
 * @author charlie
 */
public class PuzzleCell extends JLabel {

    public static final int STATE_ENABLE = 0;
    public static final int STATE_DISABLE = 1;
    public static final int STATE_SELECTED = 2;

    private int state = 0;

    public PuzzleCell() {
        super();
        setDefaultProperties();
        changeState(STATE_ENABLE);
    }

    public PuzzleCell(int value) throws Exception {
        if (value < 1 && value > 9) {
            throw new Exception("value must be in [1, 9]");
        }
        setDefaultProperties();
        changeState(STATE_DISABLE);
        setText("" + value);
    }

    private void setDefaultProperties() {
        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        setToolTipText("");
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)));
        setFocusable(false);
        setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        setFont(new java.awt.Font("Dialog", 1, 36));
        setForeground(new java.awt.Color(0, 0, 0));
        setOpaque(true);
    }

    private void changeState(int state) {
        switch (state) {
            case STATE_ENABLE:
                setBackground(new java.awt.Color(255, 255, 204));
                break;
            case STATE_DISABLE:
                setBackground(new java.awt.Color(255, 204, 0));
                break;
            case STATE_SELECTED:
                setBackground(new java.awt.Color(153, 255, 204));
                break;
            default:
                break;
        }
        this.state = state;
    }

    private int getValue() {
        if (getText().trim().isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(getText());
        }
    }
}
