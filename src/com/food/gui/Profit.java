/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.food.gui;

import com.food.model.MySQL;
import com.mysql.cj.protocol.Resultset;
import java.awt.Color;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ABC
 */
public class Profit extends javax.swing.JFrame {

    /**
     * Creates new form Profit
     */
    public Profit() {
        initComponents();
        setSize(1100, 700);
        setLocationRelativeTo(null);
      
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
          DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void loadprofite(Date sdate, Date edate) {

        double total = 0;
        double mesuretotal = 0;
        double qtytotal = 0;
        DecimalFormat df = new DecimalFormat("0.000");
        DecimalFormat pf = new DecimalFormat("0.00");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {

            ResultSet profitRs = MySQL.search("SELECT * FROM `product` INNER JOIN `stock` ON "
                    + "`stock`.`product_id`=`product`.`id`  INNER JOIN "
                    + "`invoice_item` ON `invoice_item`.`stock_id`=`stock`.`id` "
                    + "INNER JOIN `invoice` ON `invoice`.`id`=`invoice_item`."
                    + "`invoice_id`  INNER JOIN `mesurse_type` ON `invoice_item`.`mesurse_type_id`"
                    + "=`mesurse_type`.`id` WHERE `invoice`.`date`BETWEEN "
                    + "'" + format.format(sdate) + " 00:00:00' AND "
                    + "'" + format.format(edate) + " 23:59:59'; ");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (profitRs.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(profitRs.getString("stock.id"));
                vector.add(profitRs.getString("product.product_name"));
                vector.add(profitRs.getString("invoice_item.size_of_product"));
                vector.add(profitRs.getString("mesurse_type.mesurse_type"));
                vector.add(profitRs.getString("stock.buying_price"));

                //buying total cal
                if (profitRs.getString("invoice_item.size_of_product").equals("KG") || profitRs.getString("invoice_item.size_of_product").equals("G")) {

                    mesuretotal = (Double.valueOf(profitRs.getString("stock.buying_price")) / Double.valueOf(profitRs.getString("stock.unit_per_product"))) * Double.valueOf(profitRs.getString("invoice_item.size_of_product"));

                    vector.add(String.valueOf(mesuretotal));

                } else {

                    mesuretotal = Double.valueOf(profitRs.getString("invoice_item.size_of_product")) * Double.valueOf(profitRs.getString("stock.buying_price"));

                    vector.add(String.valueOf(mesuretotal));

                }

                vector.add(profitRs.getString("stock.selling_price"));

                //selling total cal
                if (profitRs.getString("invoice_item.size_of_product").equals("KG") || profitRs.getString("invoice_item.size_of_product").equals("G")) {

                    qtytotal = (Double.valueOf(profitRs.getString("stock.selling_price")) / Double.valueOf(profitRs.getString("stock.unit_per_product"))) * Double.valueOf(profitRs.getString("invoice_item.size_of_product"));

                    vector.add(String.valueOf(qtytotal));

                } else {

                    qtytotal = Double.valueOf(profitRs.getString("invoice_item.size_of_product")) * Double.valueOf(profitRs.getString("stock.selling_price"));

                    vector.add(String.valueOf(qtytotal));

                }

                vector.add(String.valueOf(qtytotal - mesuretotal));

                dtm.addRow(vector);

            }
            
            jTable1.setModel(dtm);
            cal();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void cal() {
        double Prifit = 0;

        DecimalFormat priceFormat = new DecimalFormat("0.00");
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            Prifit += Double.valueOf(jTable1.getValueAt(i, 8).toString());
  
        }

        jLabel3.setText("Profit  : " + String.valueOf(priceFormat.format(Prifit)));
    

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
        jLabel1 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Profit", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dubai Medium", 1, 18), new java.awt.Color(0, 122, 255))); // NOI18N

        jLabel1.setText("Date :");

        jLabel2.setText("To :");

        jDateChooser2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser2PropertyChange(evt);
            }
        });
        jDateChooser2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDateChooser2KeyReleased(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Product", "Size of Product", "Mesure Type", "Buying Price", "Buying Total", "Selling Price", "Selling total", "Profit price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionBackground(new java.awt.Color(0, 122, 255));
        jTable1.setSelectionForeground(new java.awt.Color(251, 255, 255));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 7, Short.MAX_VALUE)
                .addGap(18, 18, 18))
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

    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser2PropertyChange

        if (jDateChooser1.getDate() != null && jDateChooser2.getDate() != null) {

            if (jDateChooser1.getDate().after(jDateChooser2.getDate())) {
                jDateChooser2.setBorder(new LineBorder(Color.red, 1));
            } else if (jDateChooser1.getDate().before(jDateChooser2.getDate())) {
                jDateChooser2.setBorder(BorderFactory.createEmptyBorder());
                jDateChooser1.getDate();
                jDateChooser2.getDate();
                loadprofite(jDateChooser1.getDate(), jDateChooser2.getDate());

            }

        }

    }//GEN-LAST:event_jDateChooser2PropertyChange

    private void jDateChooser2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser2KeyReleased

    }//GEN-LAST:event_jDateChooser2KeyReleased

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
            java.util.logging.Logger.getLogger(Profit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Profit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Profit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Profit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Profit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
