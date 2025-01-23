/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.food.model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ImprovedDashboard {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        
        // Navigation Bar
        JPanel navBar = new JPanel();
        navBar.setBackground(new Color(45, 45, 45)); // Dark gray background
        navBar.setPreferredSize(new Dimension(600, 50));
        
        JLabel titleLabel = new JLabel("Dashboard");
        titleLabel.setForeground(Color.WHITE); // White font color
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        navBar.add(titleLabel);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        buttonPanel.setBackground(new Color(230, 230, 230)); // Light background
        
        // Creating Buttons
        String[] buttonNames = {"Profit", "Invoice History", "GRN History", "Cashier", "Supplier", "GRN", "Invoice", "Stock", "Day End"};
        for (String name : buttonNames) {
            JButton button = new JButton(name);
            button.setFont(new Font("Arial", Font.PLAIN, 12));
            button.setBackground(Color.LIGHT_GRAY); // Button color
            button.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 2)); // Blue border
            button.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(new Color(220, 220, 220)); // Hover effect
                }
                public void mouseExited(MouseEvent e) {
                    button.setBackground(Color.LIGHT_GRAY);
                }
            });
            buttonPanel.add(button);
        }

        // Logout Button
        JButton logoutButton = new JButton("Log Out");
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(new Color(217, 30, 24)); // Dark red
        logoutButton.setBorder(BorderFactory.createLineBorder(new Color(217, 30, 24), 3));

        frame.add(navBar, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(logoutButton, BorderLayout.SOUTH);
        
        frame.setVisible(true);
    }
}