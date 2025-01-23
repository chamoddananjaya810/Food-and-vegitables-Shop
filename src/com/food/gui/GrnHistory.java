/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.food.gui;

import static com.food.gui.Supplier.categorymap;
import com.food.model.MySQL;
import java.awt.Color;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ABC
 */
public class GrnHistory extends javax.swing.JFrame {
    
    public static HashMap<String, Integer> suppliermap = new HashMap();

    /**
     * Creates new form GrnHistory
     */
    public GrnHistory() {
        initComponents();
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loadSupplierCombo();
        loadGrn("");
        
        
          DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        for (int i = 0; i < jTable2.getColumnCount(); i++) {
            jTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    
    private void loadGrn(String text) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            
            String query = "SELECT * FROM `grn` INNER JOIN "
                    + "`cashier` ON `grn`.`cashier_id`=`cashier`.`id`  INNER JOIN "
                    + "`supplier` ON `supplier`.`id`=`grn`.`supplier_id`"
                    + "WHERE `supplier`.`full_name` = '" + text + "' OR `grn`.`id` LIKE '%" + text + "%'";
            
            ResultSet grneRs = MySQL.search(query);
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            
            while (grneRs.next()) {
                
                Vector<String> vector = new Vector<>();
                vector.add(grneRs.getString("grn.id"));
                vector.add(grneRs.getString("grn.datetime"));
                vector.add(grneRs.getString("grn.paid_amount"));
                vector.add(grneRs.getString("grn.total_amount"));
                vector.add(grneRs.getString("supplier.full_name"));
                
                vector.add(grneRs.getString("cashier.full name"));
                
                dtm.addRow(vector);
                
            }
            jTable1.setModel(dtm);
            cal();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
    
    private void loadGrnHistory(Date sdate, Date edate) {
        
        double total = 0;
        double mesuretotal = 0;
        double qtytotal = 0;
        DecimalFormat df = new DecimalFormat("0.000");
        DecimalFormat pf = new DecimalFormat("0.00");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            
            if (jTextField1.getText().equals("Search :")) {
                
            }
            ResultSet grneRs = MySQL.search("SELECT * FROM `grn` INNER JOIN "
                    + "`cashier` ON `grn`.`cashier_id`=`cashier`.`id`  INNER JOIN "
                    + "`supplier` ON `supplier`.`id`=`grn`.`supplier_id`"
                    + "WHERE  `grn`.`datetime` BETWEEN '" + format.format(jDateChooser1.getDate()) + " 00:00:00' AND "
                    + "'" + format.format(jDateChooser2.getDate()) + " 23:59:59'"
                    + " OR `supplier`.`full_name` = '" + jComboBox1.getSelectedItem().toString() + "' ; ");
            
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            
            dtm.setRowCount(0);
            
            while (grneRs.next()) {
                
                Vector<String> vector = new Vector<>();
                vector.add(grneRs.getString("grn.id"));
                vector.add(grneRs.getString("grn.datetime"));
                vector.add(grneRs.getString("grn.paid_amount"));
                vector.add(grneRs.getString("grn.total_amount"));
                vector.add(grneRs.getString("supplier.full_name"));
                
                vector.add(grneRs.getString("cashier.full name"));
                
                dtm.addRow(vector);
                
            }
            cal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private void loadSupplierCombo() {
        try {
            
            ResultSet supplierRs = MySQL.search("SELECT * FROM `supplier`");
            
            Vector<String> vector = new Vector<>();
            vector.add("Select Supplier");
            
            while (supplierRs.next()) {
                vector.add(supplierRs.getString("full_name"));
                suppliermap.put(supplierRs.getString("full_name"), supplierRs.getInt("id"));
            }
            
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);
            
        } catch (Exception e) {

            //System.out.println("Connection Problem");
            e.printStackTrace();
            
        }
    }
    
    private void loadGrnItem(String text) {
        double total = 0;
        DecimalFormat df = new DecimalFormat("0.000");
        DecimalFormat pf = new DecimalFormat("0.00");
        try {
            
            ResultSet grnItemRs = MySQL.search("SELECT * FROM `grn_item` INNER JOIN "
                    + "`stock` ON `grn_item`.`stock_id`=`stock`.`id` INNER JOIN "
                    + "`product` ON `product`.`id`=`stock`.`product_id` INNER JOIN "
                    + "`mesurse_type` ON `mesurse_type`.`id`=`grn_item`.`mesurse_type_id`"
                    + " WHERE `grn_item`.`grn_id` LIKE '%" + text + "%'");
            
            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);
            
            while (grnItemRs.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(grnItemRs.getString("grn_item.id"));
                vector.add(grnItemRs.getString("product.product_name"));
                vector.add(grnItemRs.getString("stock.unit_per_product"));
                vector.add(grnItemRs.getString("mesurse_type.mesurse_type"));
                vector.add(grnItemRs.getString("grn_item.size_of_product"));
                vector.add(grnItemRs.getString("stock.buying_price"));
                vector.add(grnItemRs.getString("stock.selling_price"));
                
                if (grnItemRs.getString("mesurse_type.mesurse_type").equals("KG") || grnItemRs.getString("mesurse_type.mesurse_type").equals("G")) {
                    
                    double itemTotal = (Double.valueOf(grnItemRs.getString("stock.buying_price")) / Double.valueOf(grnItemRs.getString("stock.unit_per_product"))) * Double.valueOf(grnItemRs.getString("grn_item.size_of_product"));
                    total += itemTotal;
                    vector.add(String.valueOf(pf.format(itemTotal)));
                    
                } else {
                    double itemTotal = Double.valueOf(grnItemRs.getString("grn_item.size_of_product")) * Double.valueOf(grnItemRs.getString("stock.buying_price"));
                    total += itemTotal;
                    vector.add(String.valueOf(itemTotal));
                    
                }
                dtm.addRow(vector);
                
            }
            jTable2.setModel(dtm);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private void cal() {
        double paidamount = 0;
        double total = 0;
        DecimalFormat priceFormat = new DecimalFormat("0.00");
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            paidamount += Double.valueOf(jTable1.getValueAt(i, 2).toString());
            total += Double.valueOf(jTable1.getValueAt(i, 3).toString());
        }
        
        jLabel2.setText("Paid Amount  : " + String.valueOf(priceFormat.format(paidamount)));
        jLabel1.setText("Total :" + String.valueOf(priceFormat.format(total)));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Grn Histry", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dubai Medium", 1, 18), new java.awt.Color(0, 122, 255))); // NOI18N

        jTextField1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jTextField1.setText("Search :");
        jTextField1.setSelectedTextColor(new java.awt.Color(0, 122, 255));
        jTextField1.setSelectionColor(new java.awt.Color(251, 255, 255));
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Grn Id", "datetime", "paid_amount", "total_amount", "Supplier", "Cashier"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionBackground(new java.awt.Color(0, 122, 255));
        jTable1.setSelectionForeground(new java.awt.Color(251, 255, 255));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTable2.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Product", "Unit Price", "Mesure Type", "Mesure Value", "Buying Price", "Selling Price", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setSelectionBackground(new java.awt.Color(0, 122, 255));
        jTable2.setSelectionForeground(new java.awt.Color(251, 255, 255));
        jScrollPane2.setViewportView(jTable2);

        jLabel1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N

        jComboBox1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        jDateChooser2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser2PropertyChange(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel3.setText("Date:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)))))
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        if (jTextField1.getText().length() == 0) {
            jTextField1.setText("Search :");
        }
    }//GEN-LAST:event_jTextField1FocusLost

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if (!jTextField1.getText().equals("Search :")) {
            loadGrn(jTextField1.getText());
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        if (jTextField1.getText().equals("Search :")) {
            jTextField1.setText("");
        }
    }//GEN-LAST:event_jTextField1MouseClicked

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if (jComboBox1.getSelectedItem().toString().equals("Select Supplier")) {
            loadGrn("");
        } else {
            loadGrn(jComboBox1.getSelectedItem().toString());
            
        }
        

    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int sr = jTable1.getSelectedRow();
        DecimalFormat priceFormat = new DecimalFormat("0.00");
        if (evt.getClickCount() == 1) {
            if (sr == -1) {
                JOptionPane.showMessageDialog(this, "Please Select The Row.", "Warning", JOptionPane.QUESTION_MESSAGE);
                
            } else {
                
                loadGrnItem(String.valueOf(jTable1.getValueAt(sr, 0)));
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser2PropertyChange
        if (jDateChooser1.getDate() != null && jDateChooser2.getDate() != null) {
            
            if (jDateChooser1.getDate().after(jDateChooser2.getDate())) {
                jDateChooser2.setBorder(new LineBorder(Color.red, 1));
            } else if (jDateChooser1.getDate().before(jDateChooser2.getDate())) {
                jDateChooser2.setBorder(BorderFactory.createEmptyBorder());
                jDateChooser1.getDate();
                jDateChooser2.getDate();
                loadGrnHistory(jDateChooser1.getDate(), jDateChooser2.getDate());
                jComboBox1.setEnabled(false);
                jTextField1.setEditable(false);
            }
            
        }

    }//GEN-LAST:event_jDateChooser2PropertyChange

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        if (jDateChooser1.getDate() != null && jDateChooser2.getDate() != null) {
            
            if (jDateChooser1.getDate().after(jDateChooser2.getDate())) {
                jDateChooser2.setBorder(new LineBorder(Color.red, 1));
            } else if (jDateChooser1.getDate().before(jDateChooser2.getDate())) {
                jDateChooser2.setBorder(BorderFactory.createEmptyBorder());
                jDateChooser1.getDate();
                jDateChooser2.getDate();
                loadGrnHistory(jDateChooser1.getDate(), jDateChooser2.getDate());
                jComboBox1.setEnabled(false);
                jTextField1.setEditable(false);
            }
            
        }
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jDateChooser1.setCalendar(null);
        jDateChooser2.setCalendar(null);
        jComboBox1.setEnabled(true);
        jTextField1.setEnabled(true);
        jComboBox1.setSelectedIndex(0);
        jTextField1.setText("Search :");
        loadGrn("");
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GrnHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GrnHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GrnHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GrnHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GrnHistory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
