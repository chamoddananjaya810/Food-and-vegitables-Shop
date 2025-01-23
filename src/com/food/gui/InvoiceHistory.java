/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.food.gui;

import com.food.model.MySQL;
import java.awt.Color;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ABC
 */
public class InvoiceHistory extends javax.swing.JFrame {

    /**
     * Creates new form InvoiceHistory
     */
    public InvoiceHistory() {
        initComponents();
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loadInvoice("");
        cal();
          DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        for (int i = 0; i < jTable2.getColumnCount(); i++) {
            jTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void loadInvoice(String text) {

        try {

            ResultSet inviceRs = MySQL.search("SELECT * FROM `invoice`  INNER JOIN "
                    + "`cashier` ON `cashier`.`id`=`invoice`.`cashier_id` INNER JOIN "
                    + "`payment` ON `payment`.`id`=`invoice`.`Payment_id`  "
                    + "WHERE `invoice`.`id`  LIKE '%" + text + "%'  ");

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (inviceRs.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(inviceRs.getString("invoice.id"));
                vector.add(inviceRs.getString("invoice.date"));
                vector.add(inviceRs.getString("invoice.bill_price"));
                vector.add(inviceRs.getString("invoice.discount"));
                vector.add(inviceRs.getString("invoice.total_price"));
                vector.add(inviceRs.getString("cashier.full name"));
                vector.add(inviceRs.getString("payment.type"));

                dtm.addRow(vector);

            }
            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void loadInvoiceHistory(Date sdate, Date edate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {

            ResultSet inviceRs = MySQL.search("SELECT * FROM `invoice`  INNER JOIN "
                    + "`cashier` ON `cashier`.`id`=`invoice`.`cashier_id` INNER JOIN "
                    + "`payment` ON `payment`.`id`=`invoice`.`Payment_id`  "
                    + "WHERE  `invoice`.`date` BETWEEN '" + format.format(jDateChooser1.getDate()) + " 00:00:00' AND "
                    + "'" + format.format(jDateChooser2.getDate()) + " 23:59:59'  ");

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (inviceRs.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(inviceRs.getString("invoice.id"));
                vector.add(inviceRs.getString("invoice.date"));
                vector.add(inviceRs.getString("invoice.bill_price"));
                vector.add(inviceRs.getString("invoice.discount"));
                vector.add(inviceRs.getString("invoice.total_price"));
                vector.add(inviceRs.getString("cashier.full name"));
                vector.add(inviceRs.getString("payment.type"));

                dtm.addRow(vector);

            }
            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void loadInvoiceItem(String inId) {
        try {

            ResultSet inviceItemRs = MySQL.search("SELECT * FROM `invoice_item` "
                    + "INNER JOIN `stock` ON `invoice_item`.`stock_id`=`stock`.`id` "
                    + "INNER JOIN `product` ON `product`.`id`=`stock`.`product_id` "
                    + "INNER JOIN `mesurse_type` ON  `invoice_item`.`mesurse_type_id`=`mesurse_type`.`id`"
                    + " WHERE `invoice_item`.`invoice_id`LIKE '%" + inId + "%'");

            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);

            while (inviceItemRs.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(inviceItemRs.getString("invoice_item.id"));
                vector.add(inviceItemRs.getString("product.product_name"));
                vector.add(inviceItemRs.getString("stock.unit_per_product"));
                vector.add(inviceItemRs.getString("mesurse_type.mesurse_type"));
                vector.add(inviceItemRs.getString("invoice_item.size_of_product"));
                vector.add(inviceItemRs.getString("invoice_item.selling_price"));
                vector.add(inviceItemRs.getString("invoice_item.total"));

                dtm.addRow(vector);

            }
            jTable2.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void cal() {
        double billtotal = 0;
        double total = 0;
        DecimalFormat priceFormat = new DecimalFormat("0.00");
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            billtotal += Double.valueOf(jTable1.getValueAt(i, 2).toString());
            total += Double.valueOf(jTable1.getValueAt(i, 4).toString());
        }

        jLabel2.setText("Bill Price : " + String.valueOf(priceFormat.format(billtotal)));
        jLabel1.setText("Total :" + String.valueOf(priceFormat.format(total)));

    }

    private void clear() {
        jTextField1.setText("Search :");
        jTable1.clearSelection();
        jTable2.clearSelection();
        jDateChooser1.setCalendar(null);
        jDateChooser2.setCalendar(null);
        DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
        dtm.setRowCount(0);
        loadInvoice("");

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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Invice History", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dubai Medium", 1, 18), new java.awt.Color(0, 122, 255))); // NOI18N

        jTextField1.setText("Search :");
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

        jTable1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Invoice Id", "Date time", "Bill Price", "Discout", "Total", "Cashier", "Payment Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
                "id", "Product", "Size", "Mesure", "Unit", "Selling Price", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setSelectionBackground(new java.awt.Color(0, 122, 255));
        jTable2.setSelectionForeground(new java.awt.Color(251, 255, 255));
        jScrollPane2.setViewportView(jTable2);

        jButton1.setText("Bill Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Date:");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int sr = jTable1.getSelectedRow();
        DecimalFormat priceFormat = new DecimalFormat("0.00");
        if (evt.getClickCount() == 1) {
            if (sr == -1) {
                JOptionPane.showMessageDialog(this, "Please Select The Row.", "Warning", JOptionPane.QUESTION_MESSAGE);

            } else {

                loadInvoiceItem(String.valueOf(jTable1.getValueAt(sr, 0)));

            }
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int sr = jTable1.getSelectedRow();
        if (jTable1.getSelectedRow() == -1) {

            JOptionPane.showMessageDialog(this, "Please Select The Invoice.", "Warning", JOptionPane.QUESTION_MESSAGE);

        } else if (jTable2.getRowCount() <= 0) {

            JOptionPane.showMessageDialog(this, "Invalid Invoice ", "Warning", JOptionPane.QUESTION_MESSAGE);

        } else {

            try {
                HashMap<String, Object> parametes = new HashMap<>();
                //            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                parametes.put("Parameter3", jTable1.getValueAt(sr, 0).toString());
                parametes.put("Parameter2", jTable1.getValueAt(sr, 5).toString());
                parametes.put("Parameter1", jTable1.getValueAt(sr, 1).toString());
                parametes.put("Parameter4", jTable1.getValueAt(sr, 2).toString());
                parametes.put("Parameter5", jTable1.getValueAt(sr, 6).toString());
                parametes.put("Parameter6", jTable1.getValueAt(sr, 3).toString());
                parametes.put("Parameter7", jTable1.getValueAt(sr, 4).toString());

                JRTableModelDataSource datasourse = new JRTableModelDataSource(jTable2.getModel());
                //            JasperViewer.viewReport(JasperFillManager.fillReport("src/com/food/report/shopgrn.jasper", paramerters, datasourse));
                JasperViewer.viewReport(JasperFillManager.fillReport("src/com/food/report/shopInvoice.jasper", parametes, datasourse));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        if (jTextField1.getText().length() == 0) {
            jTextField1.setText("Search :");
        }
    }//GEN-LAST:event_jTextField1FocusLost

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if (!jTextField1.getText().equals("Search  :")) {
            loadInvoice(jTextField1.getText());
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        if (jTextField1.getText().equals("Search :")) {
            jTextField1.setText("");
        }
    }//GEN-LAST:event_jTextField1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clear();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        if (jDateChooser1.getDate() != null && jDateChooser2.getDate() != null) {

            if (jDateChooser1.getDate().after(jDateChooser2.getDate())) {
                jDateChooser2.setBorder(new LineBorder(Color.red, 1));
            } else if (jDateChooser1.getDate().before(jDateChooser2.getDate())) {
                jDateChooser2.setBorder(BorderFactory.createEmptyBorder());
                jDateChooser1.getDate();
                loadInvoiceHistory(jDateChooser1.getDate(), jDateChooser2.getDate());

            }

        }
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser2PropertyChange
        if (jDateChooser1.getDate() != null && jDateChooser2.getDate() != null) {

            if (jDateChooser1.getDate().after(jDateChooser2.getDate())) {
                jDateChooser2.setBorder(new LineBorder(Color.red, 1));
            } else if (jDateChooser1.getDate().before(jDateChooser2.getDate())) {
                jDateChooser2.setBorder(BorderFactory.createEmptyBorder());
                jDateChooser1.getDate();
                jDateChooser2.getDate();
                loadInvoiceHistory(jDateChooser1.getDate(), jDateChooser2.getDate());
//                jComboBox1.setEnabled(false);
//                jTextField1.setEditable(false);
            }

        }
    }//GEN-LAST:event_jDateChooser2PropertyChange

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
            java.util.logging.Logger.getLogger(InvoiceHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InvoiceHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InvoiceHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InvoiceHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InvoiceHistory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
