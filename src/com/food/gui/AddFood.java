/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.food.gui;

import static com.food.gui.AddGrn.suppliermap;
import static com.food.gui.AddSupplier.categorymap;
import com.food.model.MySQL;
import com.mysql.cj.protocol.Resultset;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Pattern;
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
public class AddFood extends javax.swing.JFrame {

    public static HashMap<String, Integer> categorymap = new HashMap();

    /**
     * Creates new form AddFood
     */
    public AddFood() {
        initComponents();
        setResizable(false); // maximize button disable
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setSize(1000, 600);
//        setLocation(200, 80);
        setSize(1100, 700);
        setLocationRelativeTo(null);

        loadCategoryCombo();
        loadSupplier("");
        loadProduct("");
        loadCategory("");
  DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        for (int i = 0; i < jTable2.getColumnCount(); i++) {
            jTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        for (int i = 0; i < jTable4.getColumnCount(); i++) {
            jTable4.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private AddGrn grn;

    public void setID(AddGrn grn) {
        this.grn = grn;
    }

    public void setGrn(AddGrn grn) {
        this.grn = grn;
    }

    public void setMobile(AddGrn grn) {
        this.grn = grn;
    }

    //mobile
    public void setNic(AddGrn grn) {
        this.grn = grn;
    }

    private stock grn1;

    public void setGrn(stock grn) {
        this.grn1 = grn;
    }

    public void setMobile(stock grn) {
        this.grn1 = grn;
    }

    //mobile
    public void setNic(stock grn) {
        this.grn1 = grn;
    }
    //mobile

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

    private void loadProduct(String text) {

        try {

            ResultSet productRs = MySQL.search("SELECT * FROM `product`  "
                    + "INNER JOIN `category` ON `product`.`category_id`=`category`.`id` WHERE `product`.`product_name`  LIKE '%" + text + "%' ");

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

    public void loadCategory(String text) {

        try {

            ResultSet CategoryRs = MySQL.search("SELECT * FROM `category` WHERE `category`.`category_name`  LIKE '%" + text + "%' ");

            DefaultTableModel dtm = (DefaultTableModel) jTable4.getModel();
            dtm.setRowCount(0);

            while (CategoryRs.next()) {
                Vector vector = new Vector();
                vector.add(CategoryRs.getString("category.id"));
                vector.add(CategoryRs.getString("category_name"));
                dtm.addRow(vector);

            }
            jTable4.setModel(dtm);

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
        jTextField11.setText("Category  :");
        jTable1.clearSelection();
        jTable2.clearSelection();
    jTable4.clearSelection();
        jComboBox1.setSelectedIndex(0);

        jTextField7.setText("Product  :");
        jTextField7.setBorder(BorderFactory.createEmptyBorder());

//        jPasswordField1.setText("");
//        jComboBox1.setSelectedIndex(0);
//        buttonGroup1.clearSelection();
//        jTextField1.grabFocus();
    }
    private String supplierName;

    public void setGrn(String name) {
        this.supplierName = name;
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
        jButton9 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField6 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jTextField11 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Supplier Registation", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dubai Medium", 1, 18), new java.awt.Color(0, 122, 255))); // NOI18N
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

        jTextField4.setText("Search  :");
        jTextField4.setSelectionColor(new java.awt.Color(0, 122, 255));
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField4FocusLost(evt);
            }
        });
        jTextField4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4MouseClicked(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/clear_1.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/arrow.png"))); // NOI18N
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

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/supplier (2).png"))); // NOI18N
        jButton9.setText("Select");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3)
                            .addComponent(jTextField1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2)
                            .addComponent(jTextField5)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextField4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField3)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
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

        jButton4.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/add (1).png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/arrow.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/clear_1.png"))); // NOI18N
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

        jTextField6.setText("Search  :");
        jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField6FocusLost(evt);
            }
        });
        jTextField6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField6MouseClicked(evt);
            }
        });
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Category", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dubai Medium", 1, 18), new java.awt.Color(0, 122, 255))); // NOI18N

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Category"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.setSelectionBackground(new java.awt.Color(0, 122, 255));
        jTable4.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jTextField11.setFont(new java.awt.Font("Dubai Medium", 0, 12)); // NOI18N
        jTextField11.setText("Search  :");
        jTextField11.setSelectedTextColor(new java.awt.Color(251, 255, 255));
        jTextField11.setSelectionColor(new java.awt.Color(0, 122, 255));
        jTextField11.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField11FocusLost(evt);
            }
        });
        jTextField11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField11MousePressed(evt);
            }
        });
        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField11KeyReleased(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/add (1).png"))); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/arrow.png"))); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/clear_1.png"))); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField11)
                        .addGap(28, 28, 28)
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
             if (!jTextField4.getText().equals("Search  :")) {
           loadSupplier(jTextField4.getText());
        }
        
        
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        reset();
    }//GEN-LAST:event_jButton2ActionPerformed

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
               
            }
        }

        if (evt.getClickCount() == 2) {
            if (sr == -1) {
                JOptionPane.showMessageDialog(this, "Please Select The Row.", "Warning", JOptionPane.QUESTION_MESSAGE);

            } else {

                if (grn != null) {
                    grn.id = jTable1.getValueAt(sr, 1).toString();
                    grn.getjComboBox1().setSelectedItem(jTable1.getValueAt(sr, 1));
                    grn.getjLabel1().setText(jTable1.getValueAt(sr, 3).toString());
                    grn.getjLabel2().setText(jTable1.getValueAt(sr, 4).toString());

                    this.dispose();
                }

            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

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
                    loadProduct("");
                    loadSupplier("");
                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
                        loadProduct("");
                        loadSupplier("");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        reset();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField7FocusLost
        if (jTextField7.getText().length() == 0) {
            jTextField7.setText("Product  :");
        }
    }//GEN-LAST:event_jTextField7FocusLost

    private void jTextField7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField7MousePressed
        if (jTextField7.getText().equals("Product  :")) {
            jTextField7.setText("");
        }
    }//GEN-LAST:event_jTextField7MousePressed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Plese select a row", "Warning", JOptionPane.QUESTION_MESSAGE);
        } else {
            reset();
            AddSuplierProductttt Sproduct = new AddSuplierProductttt();
            Sproduct.setVisible(true);

            Sproduct.suplierId = Integer.valueOf(jTable1.getValueAt(selectedRow, 0).toString());

            Sproduct.setSupplierName(jTable1.getValueAt(selectedRow, 1).toString());
            //                Sproduct.jLabel1.setText(jTable1.getValueAt(sr, 1).toString());
            Sproduct.loadSuppliertype("");

        }


    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTextField11FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField11FocusLost
        if (jTextField11.getText().length() == 0) {
            jTextField11.setText("Search  :");
        }
    }//GEN-LAST:event_jTextField11FocusLost

    private void jTextField11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField11MousePressed
        if (jTextField11.getText().equals("Search  :")) {
            jTextField11.setText("");
        }
    }//GEN-LAST:event_jTextField11MousePressed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        String category = jTextField11.getText();

        if (category.isBlank() || category.equals("Category  :")) {

            JOptionPane.showMessageDialog(this, "Category is  required...", "Warning", JOptionPane.QUESTION_MESSAGE);
            jTextField11.setBorder(new LineBorder(Color.red, 1));

        } else {

            try {

                ResultSet rs = MySQL.search("SELECT * FROM `category` "
                        + " WHERE `category_name`='" + category + "'  ");

                boolean canUpdate = false;

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "category is Already Exist...", "Warning", JOptionPane.QUESTION_MESSAGE);

                } else {
                    canUpdate = true;
                }

                if (canUpdate) {
                    MySQL.iud("INSERT INTO "
                            + "`category` (`category_name`)"
                            + "VALUES('" + category + "')");

                    JOptionPane.showMessageDialog(this, "Category Registration Successfull!", "Sucsess", JOptionPane.INFORMATION_MESSAGE);

                    reset();
                    loadCategory("");
                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        int selectedRow = jTable4.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Plese select a row", "Warning", JOptionPane.QUESTION_MESSAGE);
        } else {

            String id = String.valueOf(jTable4.getValueAt(selectedRow, 0));

            String Category = jTextField11.getText();

            if (Category.isBlank() || Category.equals("Category  :")) {

                JOptionPane.showMessageDialog(this, "Food or  Vegetablese  required...", "Warning", JOptionPane.QUESTION_MESSAGE);
                jTextField11.setBorder(new LineBorder(Color.red, 1));

            } else {

                boolean canUpdate = false;
                try {

                    ResultSet rs = MySQL.search("SELECT * FROM `category` "
                            + " WHERE `category_name`='" + categorymap.get(Category) + "'  "
                            + "AND `id`='" + id + "';");

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this, "Category is Already Exist...", "Warning", JOptionPane.QUESTION_MESSAGE);

                    } else {
                        canUpdate = true;
                    }
                    if (canUpdate) {

                        MySQL.iud("UPDATE `category` SET "
                                + "`category_name` = '" + Category + "'"
                                + "WHERE `id` = '" + id + "';");
                        JOptionPane.showMessageDialog(this, "Category Update Successfull!", "Food or  Vegetablese", JOptionPane.INFORMATION_MESSAGE);
                        reset();
                        loadCategory("");

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
           reset();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        int sr = jTable4.getSelectedRow();
        //        DecimalFormat priceFormat = new DecimalFormat("0.00");
        if (evt.getClickCount() == 1) {
            if (sr == -1) {
                JOptionPane.showMessageDialog(this, "Please Select The Row.", "Warning", JOptionPane.QUESTION_MESSAGE);

            } else {
                jTextField11.setText(jTable4.getValueAt(sr, 1).toString());

            }
        }

//        if (evt.getClickCount() == 2) {
//            if (sr == -1) {
//                JOptionPane.showMessageDialog(this, "Please Select The Row.", "Warning", JOptionPane.QUESTION_MESSAGE);
//
//            } else {
//                reset();
//                AddMeasureType mesure = new AddMeasureType();
//                mesure.setVisible(true);
//
//                mesure.productId = Integer.valueOf(jTable2.getValueAt(sr, 0).toString());
//                mesure.setMesureType(jTable2.getValueAt(sr, 1).toString());
//                //                mesure.jLabel1.setText(jTable2.getValueAt(sr, 1).toString());
//                mesure.loadProduct("");
//
//            }
//        }
    }//GEN-LAST:event_jTable4MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
      jTextField2.grabFocus();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
       jTextField3.grabFocus();
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
       jTextField5.grabFocus();
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyReleased
       if (!jTextField11.getText().equals("Search  :")) {
            loadCategory(jTextField11.getText());
        }
    }//GEN-LAST:event_jTextField11KeyReleased

    private void jTextField6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField6FocusLost
       if (jTextField6.getText().length() == 0) {
            jTextField6.setText("Search  :");
        }
    }//GEN-LAST:event_jTextField6FocusLost

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
        if (!jTextField6.getText().equals("Search  :")) {
            loadProduct(jTextField6.getText());
        }
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jTextField6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField6MouseClicked
      if (jTextField6.getText().equals("Search  :")) {
            jTextField6.setText("");
        }
    }//GEN-LAST:event_jTextField6MouseClicked

    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusLost
       if (jTextField4.getText().length() == 0) {
            jTextField4.setText("Search  :");
        }
    }//GEN-LAST:event_jTextField4FocusLost

    private void jTextField4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseClicked
        if (jTextField4.getText().equals("Search  :")) {
            jTextField4.setText("");
        }
    }//GEN-LAST:event_jTextField4MouseClicked

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
            java.util.logging.Logger.getLogger(AddFood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddFood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddFood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddFood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddFood().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables

}
