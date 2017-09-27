/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Views;

import com.sun.org.apache.xpath.internal.FoundIndex;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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

    private final FocusListener focusListener = new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            NumberChooser.this.close();

        }

        @Override
        public void focusLost(FocusEvent e) {
            NumberChooser.this.close();
        }
    };

    private javax.swing.JButton buttonClear;
    private javax.swing.JPanel panelLower;
    private javax.swing.JPanel panelUpper;
    private OnNumberSelected onNumberSelected = null;

    public NumberChooser(Frame owner) {
        super(owner, false);
        owner.addComponentListener(componentListener);
        owner.addFocusListener(focusListener);
        initComponents();
        initButtons();
    }

    public NumberChooser(JDialog owner) {
        super(owner, false);
        owner.addComponentListener(componentListener);
        owner.addFocusListener(focusListener);
        initComponents();
        initButtons();
    }

    @Override
    public void dispose() {
        this.getOwner().removeComponentListener(componentListener);
        this.getOwner().removeFocusListener(focusListener);
        super.dispose(); //To change body of generated methods, choose Tools | Templates.        
    }

    private void initComponents() {
        panelUpper = new javax.swing.JPanel();
        panelLower = new javax.swing.JPanel();
        buttonClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(140, 140+64));
        setType(java.awt.Window.Type.POPUP);

        panelUpper.setBackground(new java.awt.Color(0, 51, 51));
        panelUpper.setPreferredSize(new java.awt.Dimension(140, 140));

        javax.swing.GroupLayout panelUpperLayout = new javax.swing.GroupLayout(panelUpper);
        panelUpper.setLayout(panelUpperLayout);
        panelUpperLayout.setHorizontalGroup(
                panelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 140, Short.MAX_VALUE)
        );
        panelUpperLayout.setVerticalGroup(
                panelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 140, Short.MAX_VALUE)
        );

        getContentPane().add(panelUpper, java.awt.BorderLayout.NORTH);

        panelLower.setPreferredSize(new java.awt.Dimension(140, 34));
        panelLower.setLayout(new java.awt.BorderLayout());

        buttonClear.setText("Clear");
        panelLower.add(buttonClear, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelLower, java.awt.BorderLayout.SOUTH);

        pack();
    }                      

    private void initButtons() {
        int label = 1;
        GridLayout layout = new GridLayout(3, 3);
        panelUpper.setLayout(layout);
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
                numberButtons[i][j].setFont(new Font("Dialog", Font.BOLD, 12));
                panelUpper.add(numberButtons[i][j]);
            }
        }

        buttonClear.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (onNumberSelected != null) {
                    onNumberSelected.onNumberSelected(0);
                }
                close();
            }
        });
    }

    public void setNumberSelected(OnNumberSelected onNumberSelected) {
        this.onNumberSelected = onNumberSelected;
    }

    public void close() {
        this.setVisible(false);
    }
}
