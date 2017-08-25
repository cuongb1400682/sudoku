/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku;

import edu.ctu.cit.sudoku.Models.Puzzle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charlie
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        this.p = new Puzzle();
        initComponents();
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
        lableTime = new javax.swing.JLabel();
        lowerPanel = new javax.swing.JPanel();
        mainMenu = new javax.swing.JMenuBar();
        menuGame = new javax.swing.JMenu();
        menuNewGame = new javax.swing.JMenuItem();
        menuManuallyEnterPuzzle = new javax.swing.JMenuItem();
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

        upperPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        upperPanel.setPreferredSize(new java.awt.Dimension(384, 50));
        upperPanel.setLayout(new java.awt.BorderLayout());

        lableTime.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lableTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lableTime.setText("00:00");
        upperPanel.add(lableTime, java.awt.BorderLayout.CENTER);

        getContentPane().add(upperPanel, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout lowerPanelLayout = new javax.swing.GroupLayout(lowerPanel);
        lowerPanel.setLayout(lowerPanelLayout);
        lowerPanelLayout.setHorizontalGroup(
            lowerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 384, Short.MAX_VALUE)
        );
        lowerPanelLayout.setVerticalGroup(
            lowerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 357, Short.MAX_VALUE)
        );

        getContentPane().add(lowerPanel, java.awt.BorderLayout.CENTER);

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

        menuManuallyEnterPuzzle.setText("Manually enter puzzle");
        menuGame.add(menuManuallyEnterPuzzle);
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
        menuGame.add(menuPause);

        menuHintForRepeatedNumbers.setText("Hint for repeated numbers");
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

    private Puzzle p;

    private void menuNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNewGameActionPerformed
        try {
            p.generateNewPuzzle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(p.toString());
    }//GEN-LAST:event_menuNewGameActionPerformed

    private void menuLoadPuzzleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLoadPuzzleActionPerformed
        try {
            this.p.fromFile("/home/charlie/Desktop/puzzle.txt");
            System.out.println(this.p.checkRow(0));
            System.out.println(this.p.toString());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuLoadPuzzleActionPerformed

    private void menuSavePuzzleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSavePuzzleActionPerformed
        try {
            this.p.toFile("/home/charlie/Desktop/new_puzzle.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuSavePuzzleActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JLabel lableTime;
    private javax.swing.JPanel lowerPanel;
    private javax.swing.JMenuBar mainMenu;
    private javax.swing.JMenuItem menuAbout;
    private javax.swing.JMenuItem menuClearPuzzle;
    private javax.swing.JMenuItem menuExit;
    private javax.swing.JMenu menuGame;
    private javax.swing.JMenuItem menuGiveUp;
    private javax.swing.JMenuItem menuHighScore;
    private javax.swing.JCheckBoxMenuItem menuHintForRepeatedNumbers;
    private javax.swing.JMenuItem menuLoadPuzzle;
    private javax.swing.JMenuItem menuManuallyEnterPuzzle;
    private javax.swing.JMenuItem menuNewGame;
    private javax.swing.JCheckBoxMenuItem menuPause;
    private javax.swing.JMenuItem menuRedo;
    private javax.swing.JMenuItem menuSavePuzzle;
    private javax.swing.JMenuItem menuUndo;
    private javax.swing.JPanel upperPanel;
    // End of variables declaration//GEN-END:variables
}
