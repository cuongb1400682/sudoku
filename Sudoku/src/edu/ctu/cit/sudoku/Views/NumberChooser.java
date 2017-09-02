/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Views;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author charlie
 */
public class NumberChooser extends JDialog {

    public interface OnNumberSelected {

        public void onNumberSelected(int number);
    }

    private final JButton[][] numberButtons = new JButton[3][3];
    private final ComponentListener componentListener = new ComponentListener() {
        @Override
        public void componentResized(ComponentEvent e) {
            NumberChooser.this.close();
        }

        @Override
        public void componentMoved(ComponentEvent e) {
            NumberChooser.this.close();
        }

        @Override
        public void componentShown(ComponentEvent e) {
            NumberChooser.this.close();
        }

        @Override
        public void componentHidden(ComponentEvent e) {
            NumberChooser.this.close();
        }
    };

    private JPanel panel;
    private OnNumberSelected onNumberSelected = null;

    public NumberChooser(Frame owner) {
        super(owner, false);
        owner.addComponentListener(componentListener);
        initComponents();
        addButtons();
    }

    @Override
    public void dispose() {
        this.getOwner().removeComponentListener(componentListener);
        super.dispose(); //To change body of generated methods, choose Tools | Templates.        
    }
    
    private void initComponents() {
        panel = new javax.swing.JPanel();

        setAutoRequestFocus(false);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(128, 128));
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 151, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 140, Short.MAX_VALUE)
        );

        getContentPane().add(panel, java.awt.BorderLayout.CENTER);

        pack();
    }

    private void addButtons() {
        int label = 1;
        GridLayout layout = new GridLayout(3, 3);
        panel.setLayout(layout);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                numberButtons[i][j] = new JButton();
                numberButtons[i][j].setText("" + label++);
                final int number = Integer.parseInt(numberButtons[i][j].getText());
                numberButtons[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (onNumberSelected != null) {
                            onNumberSelected.onNumberSelected(number);
                        }
                        close();
                    }
                });
                panel.add(numberButtons[i][j]);
            }
        }
    }

    public void setNumberSelected(OnNumberSelected onNumberSelected) {
        this.onNumberSelected = onNumberSelected;
    }

    public void close() {
        this.setVisible(false);
    }
}
