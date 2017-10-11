/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author charlie
 */
public class PuzzleCell extends JLabel {

    public static final int STATE_ENABLE = 0;
    public static final int STATE_DISABLE = 1;
    public static final int STATE_SELECTED = 2;
    public static final int STATE_SOLVED = 3;

    public interface OnPuzzleCellClicked {

        public void onPuzzleCellClicked(PuzzleCell cell);
    }

    public interface OnPuzzleCellValueChanged {

        public void onPuzzleCellValueChanged(PuzzleCell cell, int oldValue, int newValue);
    }

    private int state = 0;
    private boolean isRepeated = false;
    private Point currentLocation = null;
    private OnPuzzleCellClicked onPuzzleCellClicked = null;
    private OnPuzzleCellValueChanged onPuzzleCellValueChanged = null;

    public PuzzleCell() {
        super();
        setDefaultProperties();
        setState(STATE_ENABLE);
    }

    public PuzzleCell(int value) throws Exception {
        super();
        if (value < 1 && value > 9) {
            throw new Exception("value must be in [1, 9]");
        }
        setDefaultProperties();
        setState(STATE_DISABLE);
        setText("" + value);
    }

    private void setDefaultProperties() {
        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        setToolTipText("");
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)));
        setFocusable(true);
        setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        setFont(new java.awt.Font("Dialog", 1, 36));
        setForeground(new java.awt.Color(0, 0, 0));
        setOpaque(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (PuzzleCell.this.isEnableCell()) {
                    PuzzleCell.this.requestFocus();

                    currentLocation = new Point();
                    Component currentComponent = PuzzleCell.this;
                    while (currentComponent != null) {
                        currentLocation.translate(currentComponent.getX(), currentComponent.getY());
                        if ((currentComponent instanceof JDialog) || (currentComponent instanceof Frame)) {
                            break;
                        }
                        currentComponent = currentComponent.getParent();
                    }
                    currentLocation.translate(0, PuzzleCell.this.getHeight());

                    if (PuzzleCell.this.onPuzzleCellClicked != null) {
                        PuzzleCell.this.onPuzzleCellClicked.onPuzzleCellClicked(PuzzleCell.this);
                    }
                }
            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (PuzzleCell.this.isEnableCell()) {
                    PuzzleCell.this.setValue(e.getKeyChar());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    private void changeColorCorrespondsToState(int state) {
        if (isRepeated) {
            setForeground(Color.red);
        } else {
            setForeground(Color.BLACK);
        }

        switch (state) {
            case STATE_ENABLE:
                setBackground(new java.awt.Color(255, 255, 204));
                break;
            case STATE_DISABLE:
                setBackground(new java.awt.Color(255, 204, 0));
                break;
            case STATE_SOLVED:
                setBackground(new java.awt.Color(38, 204, 124));
                break;
            case STATE_SELECTED:
                setBackground(new java.awt.Color(153, 255, 204));
                break;
            default:
                break;
        }
    }

    public void setState(int state) {
        if (!PuzzleCell.this.isEnableCell()) {
            return;
        }
        changeColorCorrespondsToState(state);
        this.state = state;
    }

    public int getState() {
        return this.state;
    }

    public void setRepeated(boolean isRepeated) {
        this.isRepeated = isRepeated;
        changeColorCorrespondsToState(state);
    }

    public boolean isRepeated() {
        return this.isRepeated;
    }

    public boolean isEnableCell() {
        return (this.state != PuzzleCell.STATE_DISABLE) && (this.state != PuzzleCell.STATE_SOLVED);
    }

    public int getValue() {
        if (getText().trim().isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(getText());
        }
    }

    public void setBorderConfig(int[] config) {
        this.setBorder(BorderFactory.createMatteBorder(config[0], config[1], config[2], config[3], Color.BLACK));
    }

    public void setValue(char keyChar) {
        if (PuzzleCell.this.isEnableCell()) {
            if (Character.isDigit(keyChar)) {
                int oldValue = this.getValue();
                int newValue = (int) (keyChar - '0');
                if (newValue >= 0 && newValue <= 9) {
                    PuzzleCell.this.setText("" + (newValue == 0 ? "" : newValue));
                    if (this.onPuzzleCellValueChanged != null) {
                        this.onPuzzleCellValueChanged.onPuzzleCellValueChanged(this, oldValue, newValue);
                    }
                }
            }
        }
    }

    public Point getCurrentLocation() {
        return currentLocation;
    }

    public void setOnPuzzleCellClicked(OnPuzzleCellClicked onPuzzleCellClicked) {
        this.onPuzzleCellClicked = onPuzzleCellClicked;
    }

    public void setOnPuzzleCellValueChanged(OnPuzzleCellValueChanged onPuzzleCellValueChanged) {
        this.onPuzzleCellValueChanged = onPuzzleCellValueChanged;
    }

    public void reset() {
        state = STATE_ENABLE;
        isRepeated = false;
        currentLocation = null;
        setState(STATE_ENABLE);
        setText("");
    }
}
