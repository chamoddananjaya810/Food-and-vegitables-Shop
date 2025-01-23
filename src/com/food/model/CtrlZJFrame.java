/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.food.model;

/**
 *
 * @author ABC
 */
import javax.swing.*;
import java.awt.event.*;

public class CtrlZJFrame extends JFrame {

    public CtrlZJFrame() {
        setTitle("Ctrl + Z Example");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Setting up the InputMap and ActionMap for the root pane
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();

        // Define the KeyStroke for Ctrl + Z
        KeyStroke ctrlZKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK);

        // Bind the Ctrl + Z key event to an action
        inputMap.put(ctrlZKeyStroke, "openNewJFrame");
        
        // Define the action that should be triggered when Ctrl + Z is pressed
        actionMap.put("openNewJFrame", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to open the new JFrame
                openNewJFrame();
            }
        });
    }

    private void openNewJFrame() {
        // Create and open the new JFrame
        JFrame newFrame = new JFrame("New Frame");
        newFrame.setSize(200, 150);
        newFrame.setLocationRelativeTo(null);
        newFrame.setVisible(true);
    }

    public static void main(String[] args) {
        // Run the application
        SwingUtilities.invokeLater(() -> {
            CtrlZJFrame frame = new CtrlZJFrame();
            frame.setVisible(true);
        });
    }
}
