/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.food.model;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class ShadowButtonExample extends JFrame {

    public ShadowButtonExample() {
        JButton button = new JButton("Click Me");

        // Create shadow border and default border
        Border shadowBorder = BorderFactory.createCompoundBorder(
                new LineBorder(new Color(100, 100, 100, 100), 5, true),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );
        Border normalBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        // Set initial button properties
        button.setFocusPainted(false);
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setBorder(normalBorder);

        // Add mouse listener for click effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button.setBorder(shadowBorder); // Add shadow effect
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBorder(normalBorder); // Remove shadow effect
            }
        });

        // Adding button to the frame
        setLayout(new FlowLayout());
        add(button);

        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ShadowButtonExample().setVisible(true);
        });
    }
}
