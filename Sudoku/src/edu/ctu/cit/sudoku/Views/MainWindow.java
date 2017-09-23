/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Views;

import edu.ctu.cit.sudoku.Controllers.PuzzleBoardController;
import edu.ctu.cit.sudoku.Controllers.StatusController;
import edu.ctu.cit.sudoku.Models.Puzzle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author charlie
 */
public class MainWindow extends javax.swing.JFrame implements ActionListener {

    private static final int TICK_COUNT_LIMIT = 99 * 60 + 59;

    class TimeLimitExceededException extends Exception {

        private TimeLimitExceededException(String times_up_Game_over) {
            super(times_up_Game_over);
        }
    }

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        statusController = new StatusController(this.labelStatus);
        getContentPane().add(this.puzzleBoardController.getPuzzleBoard(), java.awt.BorderLayout.CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        upperPanel = new javax.swing.JPanel();
        labelTime = new javax.swing.JLabel();
        labelStatus = new javax.swing.JLabel();
        mainMenu = new javax.swing.JMenuBar();
        menuGame = new javax.swing.JMenu();
        menuNewGame = new javax.swing.JMenuItem();
        menuManuallyNumbersInput = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuSavePuzzle = new javax.swing.JMenuItem();
        menuLoadPuzzle = new javax.swing.JMenuItem();
        menuClearPuzzle = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        menuPause = new javax.swing.JCheckBoxMenuItem();
        menuHintForRepeatedNumbers = new javax.swing.JCheckBoxMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        menuUndo = new javax.swing.JMenuItem();
        menuRedo = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        menuGiveUp = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuHighScore = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        menuAbout = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        menuExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(384, 384));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        upperPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        upperPanel.setPreferredSize(new java.awt.Dimension(384, 50));
        upperPanel.setLayout(new java.awt.BorderLayout());

        labelTime.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTime.setText("00:00");
        upperPanel.add(labelTime, java.awt.BorderLayout.CENTER);

        getContentPane().add(upperPanel, java.awt.BorderLayout.PAGE_START);

        labelStatus.setText("Ready");
        labelStatus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        labelStatus.setFocusable(false);
        getContentPane().add(labelStatus, java.awt.BorderLayout.PAGE_END);

        menuGame.setText("Game");

        menuNewGame.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        menuNewGame.setText("New game");
        menuNewGame.setName("menuNewGame"); // NOI18N
        menuNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNewGameActionPerformed(evt);
            }
        });
        menuGame.add(menuNewGame);

        menuManuallyNumbersInput.setText("Manually numbers input");
        menuManuallyNumbersInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuManuallyNumbersInputActionPerformed(evt);
            }
        });
        menuGame.add(menuManuallyNumbersInput);
        menuGame.add(jSeparator1);

        menuSavePuzzle.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuSavePuzzle.setText("Save puzzle...");
        menuSavePuzzle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSavePuzzleActionPerformed(evt);
            }
        });
        menuGame.add(menuSavePuzzle);

        menuLoadPuzzle.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuLoadPuzzle.setText("Load puzzle...");
        menuLoadPuzzle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLoadPuzzleActionPerformed(evt);
            }
        });
        menuGame.add(menuLoadPuzzle);

        menuClearPuzzle.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        menuClearPuzzle.setText("Clear puzzle");
        menuGame.add(menuClearPuzzle);
        menuGame.add(jSeparator5);

        menuPause.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_SPACE, 0));
        menuPause.setText("Pause");
        menuPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPauseActionPerformed(evt);
            }
        });
        menuGame.add(menuPause);

        menuHintForRepeatedNumbers.setText("Hint for repeated numbers");
        menuHintForRepeatedNumbers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuHintForRepeatedNumbersActionPerformed(evt);
            }
        });
        menuGame.add(menuHintForRepeatedNumbers);
        menuGame.add(jSeparator4);

        menuUndo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        menuUndo.setText("Undo");
        menuGame.add(menuUndo);

        menuRedo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuRedo.setText("Redo");
        menuGame.add(menuRedo);
        menuGame.add(jSeparator3);

        menuGiveUp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_BACK_SPACE, java.awt.event.InputEvent.CTRL_MASK));
        menuGiveUp.setText("Give up");
        menuGame.add(menuGiveUp);
        menuGame.add(jSeparator2);

        menuHighScore.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        menuHighScore.setText("High score");
        menuGame.add(menuHighScore);
        menuGame.add(jSeparator7);

        menuAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        menuAbout.setText("About");
        menuGame.add(menuAbout);
        menuGame.add(jSeparator6);

        menuExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        menuExit.setText("Exit");
        menuGame.add(menuExit);

        mainMenu.add(menuGame);

        setJMenuBar(mainMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private final PuzzleBoardController puzzleBoardController = new PuzzleBoardController(this);
    private StatusController statusController = null;
    private Timer timer = new Timer(1000, (ActionListener) this);
    private int tickCount;

    private void setTickCount(int tickCount) throws TimeLimitExceededException {
        if (tickCount > TICK_COUNT_LIMIT) {
            throw new TimeLimitExceededException("Time's up. Game over.");
        }

        this.labelTime.setText(String.format("%02d:%02d", tickCount / 60, tickCount % 60));
        this.tickCount = tickCount;
    }

    private void menuNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNewGameActionPerformed
        newGame();
    }//GEN-LAST:event_menuNewGameActionPerformed

    private void menuLoadPuzzleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLoadPuzzleActionPerformed
    }//GEN-LAST:event_menuLoadPuzzleActionPerformed

    private void menuSavePuzzleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSavePuzzleActionPerformed
    }//GEN-LAST:event_menuSavePuzzleActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        newGame();
    }//GEN-LAST:event_formWindowOpened

    private void menuHintForRepeatedNumbersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuHintForRepeatedNumbersActionPerformed
        this.puzzleBoardController.setRepeatedCellCheck(menuHintForRepeatedNumbers.isSelected());
    }//GEN-LAST:event_menuHintForRepeatedNumbersActionPerformed

    private void menuPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPauseActionPerformed
        this.pauseGame();
    }//GEN-LAST:event_menuPauseActionPerformed

    private void menuManuallyNumbersInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuManuallyNumbersInputActionPerformed
        this.pauseGame();
        ManuallyNewGameDialog dialog = new ManuallyNewGameDialog(this, true);
        dialog.setLocationRelativeTo(this);
        dialog.setOnUserPressOk(puzzle -> {
            System.out.println(puzzle.toString());
        });
        dialog.setVisible(true);
    }//GEN-LAST:event_menuManuallyNumbersInputActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JLabel labelTime;
    private javax.swing.JMenuBar mainMenu;
    private javax.swing.JMenuItem menuAbout;
    private javax.swing.JMenuItem menuClearPuzzle;
    private javax.swing.JMenuItem menuExit;
    private javax.swing.JMenu menuGame;
    private javax.swing.JMenuItem menuGiveUp;
    private javax.swing.JMenuItem menuHighScore;
    private javax.swing.JCheckBoxMenuItem menuHintForRepeatedNumbers;
    private javax.swing.JMenuItem menuLoadPuzzle;
    private javax.swing.JMenuItem menuManuallyNumbersInput;
    private javax.swing.JMenuItem menuNewGame;
    private javax.swing.JCheckBoxMenuItem menuPause;
    private javax.swing.JMenuItem menuRedo;
    private javax.swing.JMenuItem menuSavePuzzle;
    private javax.swing.JMenuItem menuUndo;
    private javax.swing.JPanel upperPanel;
    // End of variables declaration//GEN-END:variables

    private void newGame() {
        this.puzzleBoardController.newPuzzleBoard();
        this.menuManuallyNumbersInput.setSelected(false);
        try {
            this.setTickCount(0);
        } catch (TimeLimitExceededException ex) {
            ex.printStackTrace();
        }
        this.timer.restart();
        this.statusController.showMessage("Ready");
    }

    private void pauseGame() {
        if (this.timer.isRunning()) {
            this.timer.stop();
            this.statusController.showMessage("Pause", StatusController.STATUS_WARNING);
        } else {
            this.timer.start();
            this.statusController.showMessage("Ready");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            this.setTickCount(tickCount + 1);
        } catch (TimeLimitExceededException ex) {
            // todo: game has been over!!!
            ex.printStackTrace();
        }
    }

}
