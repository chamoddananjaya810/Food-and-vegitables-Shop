/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.food.model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DataPanel extends JPanel {
    private JTable dataTable;
    private DefaultTableModel tableModel;

    public DataPanel() {
        setLayout(new BorderLayout());

        // Setup table and model
        String[] columns = {"Product ID", "Product Name", "Category", "Measure"};
        tableModel = new DefaultTableModel(columns, 0);
        dataTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(dataTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Public method to encapsulate adding data to the JTable
    public void addRowToTable(Object[] rowData) {
        tableModel.addRow(rowData);
    }
}
