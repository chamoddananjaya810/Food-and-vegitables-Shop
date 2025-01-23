/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.food.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JTextField productIdField;
    private JTextField productNameField;
    private JTextField categoryField;
    private JTextField measureField;
    private TargetFrame targetFrame;  // Reference to the target frame

    public MainFrame() {
        setTitle("Main Frame");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        // Create input fields
        productIdField = new JTextField();
        productNameField = new JTextField();
        categoryField = new JTextField();
        measureField = new JTextField();

        add(new JLabel("Product ID:"));
        add(productIdField);
        add(new JLabel("Product Name:"));
        add(productNameField);
        add(new JLabel("Category:"));
        add(categoryField);
        add(new JLabel("Measure:"));
        add(measureField);

        JButton sendButton = new JButton("Send Data to Table");
        add(sendButton);

        // Instantiate the TargetFrame
        targetFrame = new TargetFrame();
        targetFrame.setVisible(true);

        // Add action listener to the button
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Collect the data from input fields
                String productId = productIdField.getText();
                String productName = productNameField.getText();
                String category = categoryField.getText();
                String measure = measureField.getText();

                // Prepare the row data
                Object[] rowData = {productId, productName, category, measure};

                // Send data to the TargetFrame's table
                targetFrame.addRowToTableInPanel(rowData);

                // Optionally clear the input fields after sending the data
                clearInputFields();
            }
        });
    }

    // Method to clear input fields after sending the data
    private void clearInputFields() {
        productIdField.setText("");
        productNameField.setText("");
        categoryField.setText("");
        measureField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
