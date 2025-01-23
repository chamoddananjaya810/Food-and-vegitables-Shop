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
import java.awt.*;

public class TargetFrame extends JFrame {
    private DataPanel dataPanel;

    public TargetFrame() {
        setTitle("Target Frame");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create the DataPanel that contains the JTable
        dataPanel = new DataPanel();

        // Add the panel to the frame
        add(dataPanel, BorderLayout.CENTER);
    }

    // Method to allow another frame to add a new row to the table
    public void addRowToTableInPanel(Object[] rowData) {
        dataPanel.addRowToTable(rowData);
    }
}
