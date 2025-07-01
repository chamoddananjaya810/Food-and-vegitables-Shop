/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.food.gui;

import com.food.model.MySQL;
import java.awt.Color;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ABC
 */
public class AddSupplier extends javax.swing.JPanel {

    public static HashMap<String, Integer> categorymap = new HashMap();

    /**
     * Creates new form AddSupplier
     */
    public AddSupplier() {
        initComponents();

        loadCategoryCombo();
//        loadMesureTypeCombo();
        loadSupplier("");
        loadProduct();
    }

    public void loadCategoryCombo() {
        try {

            ResultSet caegotyRs = MySQL.search("SELECT * FROM `category`");

            Vector<String> vector = new Vector<>();
            vector.add("Please Select Category");

            while (caegotyRs.next()) {
                vector.add(caegotyRs.getString("category_name"));
                categorymap.put(caegotyRs.getString("category_name"), caegotyRs.getInt("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);

        } catch (Exception e) {

            //System.out.println("Connection Problem");
            e.printStackTrace();

        }
    }

    public void loadSupplier(String text) {

        try {

            ResultSet productRs = MySQL.search("SELECT * FROM `supplier` "
                    + "INNER JOIN `status` ON `supplier`.`Status_id`=`status`.`id` WHERE "
                    + "`supplier`.`nic`  LIKE '%" + text + "%' OR "
                    + "`supplier`.`full_name`  LIKE '%" + text + "%' OR"
                    + "`supplier`.`mobile`  LIKE '%" + text + "%'");

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (productRs.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(productRs.getString("id"));
                vector.add(productRs.getString("full_name"));
                vector.add(productRs.getString("Addres"));
                vector.add(productRs.getString("mobile"));
                vector.add(productRs.getString("nic"));
                vector.add(productRs.getString("status.status"));

                dtm.addRow(vector);

            }
            jTable1.setModel(dtm);
//            [255,54,11]
//            [251,82,62]
//            [0,122,255]  green 34, 139, 34

//            jTable1.getColumnModel().getColumn(0).setCellRenderer(CENTER_ALIGNMENT);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void loadProduct() {

        try {

            ResultSet productRs = MySQL.search("SELECT * FROM `product`  "
                    + "INNER JOIN `category` ON `product`.`category_id`=`category`.`id` ");

//WHERE `product_name`='' AND `mesurse_type_id`=''\n" +
//"AND category_id='';
            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);

            while (productRs.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(productRs.getString("id"));
                vector.add(productRs.getString("product_name"));
                vector.add(productRs.getString("category_name"));

                dtm.addRow(vector);

            }
            jTable2.setModel(dtm);
//            [255,54,11]
//            [251,82,62]
//            [0,122,255]  green 34, 139, 34

//            jTable1.getColumnModel().getColumn(0).setCellRenderer(CENTER_ALIGNMENT);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void reset() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField7.setText("");
        jTable1.clearSelection();
        jTable2.clearSelection();
        jButton7.setText("Status");
        jComboBox1.setSelectedIndex(0);

        jTextField7.setText("Product  :");
        jTextField7.setBorder(BorderFactory.createEmptyBorder());
        jTextField1.setBorder(BorderFactory.createEmptyBorder());
        jTextField2.setBorder(BorderFactory.createEmptyBorder());
        jTextField3.setBorder(BorderFactory.createEmptyBorder());
        jTextField4.setBorder(BorderFactory.createEmptyBorder());
        jTextField5.setBorder(BorderFactory.createEmptyBorder());

//        jPasswordField1.setText("");
//        jComboBox1.setSelectedIndex(0);
//        buttonGroup1.clearSelection();
//        jTextField1.grabFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Supplier Registation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 122, 255)), "Supplier Registation", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dubai Medium", 1, 18), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(332, 371));

        jLabel1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel1.setText("Full Name :");

        jTextField1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField1.setSelectedTextColor(new java.awt.Color(251, 255, 255));
        jTextField1.setSelectionColor(new java.awt.Color(0, 122, 255));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel2.setText("Address :");

        jTextField2.setSelectedTextColor(new java.awt.Color(251, 255, 255));
        jTextField2.setSelectionColor(new java.awt.Color(0, 122, 255));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel3.setText("Nic :");
        jLabel3.setPreferredSize(new java.awt.Dimension(64, 24));

        jTextField3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTextField3.setSelectedTextColor(new java.awt.Color(251, 255, 255));
        jTextField3.setSelectionColor(new java.awt.Color(0, 122, 255));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel5.setText("Mobile :");

        jTextField5.setSelectedTextColor(new java.awt.Color(251, 255, 255));
        jTextField5.setSelectionColor(new java.awt.Color(0, 122, 255));
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jTextField4.setText("Search :");
        jTextField4.setSelectionColor(new java.awt.Color(0, 122, 255));
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField4FocusLost(evt);
            }
        });
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/clear.png"))); // NOI18N
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/updated.png"))); // NOI18N
        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Full Name", "Address", "Mobile", "Nic", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setSelectionBackground(new java.awt.Color(0, 122, 255));
        jTable1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable1.setShowGrid(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/icons8-active-48 (1).png"))); // NOI18N
        jButton7.setText("Status");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/plus.png"))); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                            .addComponent(jButton7))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                    .addComponent(jTextField1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                    .addComponent(jTextField5)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))))
                    .addComponent(jTextField4))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextField1))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField3)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3)
                        .addComponent(jButton2)
                        .addComponent(jButton1))
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Food and  Vegetables", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dubai Medium", 1, 18), new java.awt.Color(0, 122, 255))); // NOI18N
        jPanel2.setRequestFocusEnabled(false);

        jTable2.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Product", "Category"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setSelectionBackground(new java.awt.Color(0, 122, 255));
        jTable2.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
        }

        jButton4.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/plus.png"))); // NOI18N
        jButton4.setText("Add");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/updated.png"))); // NOI18N
        jButton5.setText("Update");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/clear.png"))); // NOI18N
        jButton6.setText("Clear");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTextField7.setFont(new java.awt.Font("Dubai Medium", 0, 12)); // NOI18N
        jTextField7.setText("Product  :");
        jTextField7.setSelectedTextColor(new java.awt.Color(251, 255, 255));
        jTextField7.setSelectionColor(new java.awt.Color(0, 122, 255));
        jTextField7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField7FocusLost(evt);
            }
        });
        jTextField7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField7MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Plese select a row", "Warning", JOptionPane.QUESTION_MESSAGE);
        } else {

            String Id = String.valueOf(jTable1.getValueAt(selectedRow, 0));

            String Name = jTextField1.getText();
            String Address = jTextField2.getText();
            String Nic = jTextField3.getText();
            String Mobile = jTextField5.getText();

            if (Name.isBlank()) {
                JOptionPane.showMessageDialog(this, "Full Name is required...", "Warning", JOptionPane.QUESTION_MESSAGE);

            } else if (Address.isBlank()) {
                JOptionPane.showMessageDialog(this, "Address is required", "Warning", JOptionPane.QUESTION_MESSAGE);
            } else if (Nic.isBlank()) {
                JOptionPane.showMessageDialog(this, "Nic Number is required", "Warning", JOptionPane.QUESTION_MESSAGE);
            } else if (Mobile.isBlank()) {
                JOptionPane.showMessageDialog(this, "Mobile is required", "Warning", JOptionPane.QUESTION_MESSAGE);
            } else if (!Pattern.compile("^(0|[+]94)(11|3(1|3)|7(1|2|3|4|5|6|7|8))([0-9]{7})$").matcher(Mobile).matches()) {
                JOptionPane.showMessageDialog(this, "Please Enter The Valid Mobile Number", "warning", JOptionPane.QUESTION_MESSAGE);

            } else {

                try {
                    MySQL.iud("UPDATE `supplier` SET "
                            + "`full_name` = '" + Name + "',"
                            + "`Addres` = '" + Address + "',"
                            + "`mobile` = '" + Mobile + "',"
                            + "`nic` = '" + Nic + "'"
                            + "WHERE `id` = '" + Id + "'");
                    JOptionPane.showMessageDialog(this, "Supplier Updated!", "Supplier", JOptionPane.INFORMATION_MESSAGE);
                    reset();
                    loadSupplier("");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int sr = jTable1.getSelectedRow();
//        DecimalFormat priceFormat = new DecimalFormat("0.00");
        if (evt.getClickCount() == 1) {
            if (sr == -1) {
                JOptionPane.showMessageDialog(this, "Please Select The Row.", "Warning", JOptionPane.QUESTION_MESSAGE);

            } else {

                jTextField1.setText(jTable1.getValueAt(sr, 1).toString());
                jTextField2.setText(jTable1.getValueAt(sr, 2).toString());

                jTextField3.setText(jTable1.getValueAt(sr, 4).toString());
                jTextField5.setText(jTable1.getValueAt(sr, 3).toString());
                jTextField3.setEditable(false);
                jTextField5.setEditable(false);
                String status = jTable1.getValueAt(sr, 5).toString();
                if (status.equals("Active")) {
                    jButton7.setText("Deactive");

                } else {
                    jButton7.setText("Active");
                }
            }
        }

        if (evt.getClickCount() == 2) {
            if (sr == -1) {
                JOptionPane.showMessageDialog(this, "Please Select The Row.", "Warning", JOptionPane.QUESTION_MESSAGE);

            } else {
                reset();
                AddSuplierProductttt Sproduct = new AddSuplierProductttt();
                Sproduct.setVisible(true);

                Sproduct.suplierId = Integer.valueOf(jTable1.getValueAt(sr, 0).toString());

                Sproduct.setSupplierName(jTable1.getValueAt(sr, 1).toString());
//                Sproduct.jLabel1.setText(jTable1.getValueAt(sr, 1).toString());
                Sproduct.loadSuppliertype("");

            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Plese Select Change Supplier Status...", "Warning", JOptionPane.QUESTION_MESSAGE);
        } else {

            String status = jTable1.getValueAt(selectedRow, 5).toString();

            String id = jTable1.getValueAt(selectedRow, 0).toString();
//            String Status = jTable1.getValueAt(selectedRow, 5).toString();
            System.out.println(status
            );
            if (status.equals("Active")) {

                MySQL.iud("UPDATE `supplier`"
                        + "SET `Status_id` = '0' "
                        + "WHERE `id`='" + id + "';");

                reset();
                loadSupplier("");

            } else {
                MySQL.iud("UPDATE `supplier`"
                        + "SET `Status_id` = '1' "
                        + "WHERE `id`='" + id + "';");

                reset();
                loadSupplier("");
                System.out.println("ok");
            }

        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        reset();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        if (!jTextField4.getText().equals("Search  :")) {
            loadSupplier(jTextField4.getText());
        }
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jTextField7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField7MousePressed
        if (jTextField7.getText().equals("Product  :")) {
            jTextField7.setText("");
        }
    }//GEN-LAST:event_jTextField7MousePressed

    private void jTextField7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField7FocusLost
        if (jTextField7.getText().length() == 0) {
            jTextField7.setText("Product  :");
        }
    }//GEN-LAST:event_jTextField7FocusLost

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        reset();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int selectedRow = jTable2.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Plese select a row", "Warning", JOptionPane.QUESTION_MESSAGE);
        } else {

            String id = String.valueOf(jTable2.getValueAt(selectedRow, 0));

            String product = jTextField7.getText();

            String category = String.valueOf(jComboBox1.getSelectedItem());
            //            String mesure = String.valueOf(jComboBox2.getSelectedItem());

            if (product.isBlank() || product.equals("Product  :")) {

                JOptionPane.showMessageDialog(this, "Food or  Vegetablese  required...", "Warning", JOptionPane.QUESTION_MESSAGE);
                jTextField7.setBorder(new LineBorder(Color.red, 1));
            } else if (category.equals("Please Select Category")) {

                JOptionPane.showMessageDialog(this, "Category required...", "Warning", JOptionPane.QUESTION_MESSAGE);
                jTextField7.setBorder(BorderFactory.createEmptyBorder());

            } else {

                int categoryId = categorymap.get(category);

                boolean canUpdate = false;
                try {

                    ResultSet rs = MySQL.search("SELECT * FROM `product` "
                            + "INNER JOIN `category` ON `product`.`category_id`=`category`.`id` "
                            + " WHERE `product_name`='" + product + "'  "
                            + "AND category_id='" + categoryId + "';");

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this, "Product is Already Exist...", "Warning", JOptionPane.QUESTION_MESSAGE);

                    } else {
                        canUpdate = true;
                    }
                    if (canUpdate) {

                        MySQL.iud("UPDATE `product` SET "
                                + "`product_name` = '" + product + "',"
                                + "`category_id` = '" + categorymap.get(category) + "'"
                                + "WHERE `id` = '" + id + "'");
                        JOptionPane.showMessageDialog(this, "Food or Vegetablese Update Successfull!", "Food or  Vegetablese", JOptionPane.INFORMATION_MESSAGE);
                        reset();
                        loadProduct();
                        loadSupplier("");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String product = jTextField7.getText();
        String category = String.valueOf(jComboBox1.getSelectedItem());

        if (product.isBlank() || product.equals("Product  :")) {

            JOptionPane.showMessageDialog(this, "Food or  Vegetablese  required...", "Warning", JOptionPane.QUESTION_MESSAGE);
            jTextField7.setBorder(new LineBorder(Color.red, 1));
        } else if (category.equals("Please Select Category")) {

            JOptionPane.showMessageDialog(this, "Category required...", "Warning", JOptionPane.QUESTION_MESSAGE);
            jTextField7.setBorder(BorderFactory.createEmptyBorder());

        } else {

            try {

                int categoryId = categorymap.get(category);
                //                int mesurseId = mesursemap.get(mesure);

                ResultSet rs = MySQL.search("SELECT * FROM `product` "
                        + "INNER JOIN `category` ON `product`.`category_id`=`category`.`id` "
                        + " WHERE `product_name`='" + product + "'  "
                        + "AND category_id='" + categoryId + "';");

                boolean canUpdate = false;

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Product is Already Exist...", "Warning", JOptionPane.QUESTION_MESSAGE);

                } else {
                    canUpdate = true;
                }

                if (canUpdate) {
                    MySQL.iud("INSERT INTO "
                            + "`product` (`product_name`,`category_id`)"
                            + "VALUES('" + product + "','" + categoryId + "')");

                    JOptionPane.showMessageDialog(this, "Food or  Vegetablese Registration Successfull!", "Sucsess", JOptionPane.INFORMATION_MESSAGE);

                    reset();
                    loadProduct();
                    loadSupplier("");
                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int sr = jTable2.getSelectedRow();
        //        DecimalFormat priceFormat = new DecimalFormat("0.00");
        if (evt.getClickCount() == 1) {
            if (sr == -1) {
                JOptionPane.showMessageDialog(this, "Please Select The Row.", "Warning", JOptionPane.QUESTION_MESSAGE);

            } else {
                jTextField7.setText(jTable2.getValueAt(sr, 1).toString());
                jComboBox1.setSelectedItem(jTable2.getValueAt(sr, 2).toString());

            }
        }

        if (evt.getClickCount() == 2) {
            if (sr == -1) {
                JOptionPane.showMessageDialog(this, "Please Select The Row.", "Warning", JOptionPane.QUESTION_MESSAGE);

            } else {
                reset();
                AddMeasureType mesure = new AddMeasureType();
                mesure.setVisible(true);

                mesure.productId = Integer.valueOf(jTable2.getValueAt(sr, 0).toString());
                mesure.setMesureType(jTable2.getValueAt(sr, 1).toString());
//                mesure.jLabel1.setText(jTable2.getValueAt(sr, 1).toString());
                mesure.loadProduct("");

            }
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String Name = jTextField1.getText();
        String Address = jTextField2.getText();
        String Nic = jTextField3.getText();
        String Mobile = jTextField5.getText();

        if (Name.isBlank()) {
            JOptionPane.showMessageDialog(this, "Full Name is required...", "Warning", JOptionPane.QUESTION_MESSAGE);
            System.out.println("ok");
            jTextField1.setBorder(new LineBorder(Color.red, 1));
            System.out.println("ok1");
        } else if (Address.isBlank()) {
            jTextField1.setBorder(BorderFactory.createEmptyBorder());
            JOptionPane.showMessageDialog(this, "Address is required", "Warning", JOptionPane.QUESTION_MESSAGE);
            jTextField2.setBorder(new LineBorder(Color.red, 1));
        } else if (Nic.isBlank()) {
            jTextField2.setBorder(BorderFactory.createEmptyBorder());
            JOptionPane.showMessageDialog(this, "Nic Number is required", "Warning", JOptionPane.QUESTION_MESSAGE);
            jTextField3.setBorder(new LineBorder(Color.red, 1));
        } else if (Mobile.isBlank()) {
            jTextField3.setBorder(BorderFactory.createEmptyBorder());
            JOptionPane.showMessageDialog(this, "Mobile is required", "Warning", JOptionPane.QUESTION_MESSAGE);

            jTextField5.setBorder(new LineBorder(Color.red, 1));
        } else if (!Pattern.compile("^(0|[+]94)(11|3(1|3)|7(1|2|3|4|5|6|7|8))([0-9]{7})$").matcher(Mobile).matches()) {
            jTextField5.setBorder(new LineBorder(Color.red, 1));
            JOptionPane.showMessageDialog(this, "Please Enter The Valid Mobile Number", "warning", JOptionPane.QUESTION_MESSAGE);
            jTextField5.setBorder(BorderFactory.createEmptyBorder());
        } else {

            try {

                MySQL.iud("INSERT INTO "
                        + "`supplier` (`nic`,`full_name`,`Addres`,`mobile`,`Status_id`)"
                        + "VALUES('" + Nic + "','" + Name + "','" + Address + "','" + Mobile + "','1')");

                JOptionPane.showMessageDialog(this, "Supplier Registration Successfull!", "Supplier", JOptionPane.INFORMATION_MESSAGE);

                reset();
                loadSupplier("");
            } catch (Exception e) {

                e.printStackTrace();

            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        jTextField2.grabFocus();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        jTextField3.grabFocus();
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        jTextField4.grabFocus();
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        jButton1.grabFocus();
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusLost
        if (jTextField4.getText().length() == 0) {
            jTextField4.setText("Search :");
        }
    }//GEN-LAST:event_jTextField4FocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
