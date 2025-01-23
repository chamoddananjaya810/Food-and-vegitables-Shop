/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.food.gui;

import com.food.model.MySQL;
import com.mysql.cj.protocol.Resultset;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ABC
 */
public class Cashier extends javax.swing.JFrame {

    public static HashMap<String, Integer> cashiertyperymap = new HashMap();

    /**
     * Creates new form Cashier
     */
    public Cashier() {
        initComponents();
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loadCashier("");
        loadCashierCombo();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

    }

    private void loadCashier(String text) {

        try {

            ResultSet suplierRs = MySQL.search("SELECT * FROM `cashier` "
                    + "INNER JOIN `cashiertype` ON `cashier`.`cashiertype_id`=`cashiertype`.`id`"
                    + "INNER JOIN `gender` ON `gender`.`id`=`cashier`.`gender_id` "
                    + "INNER JOIN `status` ON `cashier`.`Status_id`=`status`.`id`  "
                    + " WHERE `cashier`.`id`  LIKE '" + text + "%'  OR `cashier`.`full name`  "
                    + "LIKE '" + text + "%' OR `cashier`.`nic`  LIKE '" + text + "%'"
                    + "ORDER BY `cashier`.`id` ASC");

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (suplierRs.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(suplierRs.getString("id"));
                vector.add(suplierRs.getString("Nic"));
                vector.add(suplierRs.getString("full name"));
                vector.add(suplierRs.getString("Address"));
                vector.add(suplierRs.getString("Mobile"));
                vector.add(suplierRs.getString("age"));
                vector.add(suplierRs.getString("username"));
                vector.add(suplierRs.getString("passworld"));
                vector.add(suplierRs.getString("gender.gender_name"));
                vector.add(suplierRs.getString("cashiertype.type"));
                vector.add(suplierRs.getString("status.status"));

                dtm.addRow(vector);

            }
            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void loadCashierCombo() {
        try {

            ResultSet genderRs = MySQL.search("SELECT * FROM `cashiertype`");

            Vector<String> vector = new Vector<>();
            vector.add("Select Cashier Type");

            while (genderRs.next()) {
                vector.add(genderRs.getString("type"));
                cashiertyperymap.put(genderRs.getString("type"), genderRs.getInt("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);

        } catch (Exception e) {

            //System.out.println("Connection Problem");
            e.printStackTrace();

        }
    }

//    public CtrlZJFrame() {
//        setTitle("Ctrl + Z Example");
//        setSize(300, 200);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        // Setting up the InputMap and ActionMap for the root pane
//        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
//        ActionMap actionMap = getRootPane().getActionMap();
//
//        // Define the KeyStroke for Ctrl + Z
//        KeyStroke ctrlZKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK);
//
//        // Bind the Ctrl + Z key event to an action
//        inputMap.put(ctrlZKeyStroke, "openNewJFrame");
//
//        // Define the action that should be triggered when Ctrl + Z is pressed
//        actionMap.put("openNewJFrame", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Action to open the new JFrame
//                openNewJFrame();
//            }
//        });
//    }
    public void keyPressed(KeyEvent e) {
        // Example of handling different KeyCodes
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("Enter key was pressed.");
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.out.println("Escape key was pressed.");
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("The 'A' key was pressed.");
        } else if (e.getKeyCode() == KeyEvent.VK_F1) {
            System.out.println("The F1 key was pressed.");
        }
    }

    private void reset() {
        jTextField1.setText("Search  :");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jPasswordField1.setText("");
        buttonGroup1.clearSelection();
        jComboBox2.setSelectedIndex(0);
        jTextField2.setBorder(BorderFactory.createEmptyBorder());
        jTextField3.setBorder(BorderFactory.createEmptyBorder());
        jTextField4.setBorder(BorderFactory.createEmptyBorder());
        jTextField5.setBorder(BorderFactory.createEmptyBorder());
        jTextField6.setBorder(BorderFactory.createEmptyBorder());
        jTextField7.setBorder(BorderFactory.createEmptyBorder());
        jPasswordField1.setBorder(BorderFactory.createEmptyBorder());
        loadCashier("");
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
        jPanel3 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cashier Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dubai Medium", 1, 18), new java.awt.Color(0, 122, 255))); // NOI18N

        jTextField5.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jTextField5.setPreferredSize(new java.awt.Dimension(64, 40));
        jTextField5.setSelectedTextColor(new java.awt.Color(251, 255, 255));
        jTextField5.setSelectionColor(new java.awt.Color(0, 122, 255));
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField5FocusLost(evt);
            }
        });
        jTextField5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField5MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField5MousePressed(evt);
            }
        });
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jPasswordField1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jPasswordField1.setPreferredSize(new java.awt.Dimension(64, 35));
        jPasswordField1.setSelectedTextColor(new java.awt.Color(251, 255, 255));
        jPasswordField1.setSelectionColor(new java.awt.Color(0, 122, 255));
        jPasswordField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusLost(evt);
            }
        });
        jPasswordField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPasswordField1MousePressed(evt);
            }
        });
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        jTextField3.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jTextField3.setMinimumSize(new java.awt.Dimension(64, 35));
        jTextField3.setOpaque(true);
        jTextField3.setPreferredSize(new java.awt.Dimension(64, 40));
        jTextField3.setSelectedTextColor(new java.awt.Color(251, 255, 255));
        jTextField3.setSelectionColor(new java.awt.Color(0, 122, 255));
        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField3FocusLost(evt);
            }
        });
        jTextField3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField3MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField3MousePressed(evt);
            }
        });
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jTextField4.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jTextField4.setMinimumSize(new java.awt.Dimension(64, 40));
        jTextField4.setPreferredSize(new java.awt.Dimension(64, 40));
        jTextField4.setSelectedTextColor(new java.awt.Color(251, 255, 255));
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField4MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField4MousePressed(evt);
            }
        });
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jTextField7.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jTextField7.setPreferredSize(new java.awt.Dimension(64, 40));
        jTextField7.setSelectedTextColor(new java.awt.Color(251, 255, 255));
        jTextField7.setSelectionColor(new java.awt.Color(0, 122, 255));
        jTextField7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField7FocusLost(evt);
            }
        });
        jTextField7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField7MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField7MousePressed(evt);
            }
        });
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jTextField6.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jTextField6.setPreferredSize(new java.awt.Dimension(64, 40));
        jTextField6.setSelectedTextColor(new java.awt.Color(251, 255, 255));
        jTextField6.setSelectionColor(new java.awt.Color(0, 122, 255));
        jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField6FocusLost(evt);
            }
        });
        jTextField6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField6MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField6MousePressed(evt);
            }
        });
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jRadioButton1.setText("Male");
        jRadioButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 122, 255)));
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jComboBox2.setPreferredSize(new java.awt.Dimension(64, 40));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nic", "full name", "Address", "Mobile", "Age", "Username", "Passworld", "Gender", "Cashiertype", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionBackground(new java.awt.Color(0, 122, 255));
        jTable1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTextField1.setFont(new java.awt.Font("Dubai Medium", 0, 12)); // NOI18N
        jTextField1.setText("Search  :");
        jTextField1.setSelectedTextColor(new java.awt.Color(251, 255, 255));
        jTextField1.setSelectionColor(new java.awt.Color(0, 122, 255));
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField1MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField1MousePressed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/arrow.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/clear_1.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jRadioButton2.setText("Female");
        jRadioButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jCheckBox1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jCheckBox1.setText("Show");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/toggle-on_1.png"))); // NOI18N
        jButton7.setText("Status");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel1.setText("Nic :");
        jLabel1.setPreferredSize(new java.awt.Dimension(63, 16));

        jLabel2.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel2.setText("Mobile :");
        jLabel2.setPreferredSize(new java.awt.Dimension(63, 16));

        jLabel3.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel3.setText("Gender  :");

        jLabel4.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel4.setText("Full Name  :");

        jLabel5.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel5.setText("Age :");

        jLabel6.setText("Type :");

        jLabel7.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel7.setText("Address  :");
        jLabel7.setPreferredSize(new java.awt.Dimension(63, 16));

        jLabel8.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel8.setText("User Name  :");

        jButton1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/add (1).png"))); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(73, 35));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel9.setText("Password :");

        jTextField2.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jTextField2.setMinimumSize(new java.awt.Dimension(64, 35));
        jTextField2.setOpaque(true);
        jTextField2.setPreferredSize(new java.awt.Dimension(64, 40));
        jTextField2.setSelectedTextColor(new java.awt.Color(251, 255, 255));
        jTextField2.setSelectionColor(new java.awt.Color(0, 122, 255));
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });
        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField2MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField2MousePressed(evt);
            }
        });
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addGap(43, 43, 43)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)))
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, 0, 144, Short.MAX_VALUE)
                                .addGap(96, 96, 96)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jButton7))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton2)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusLost
//        if (jTextField5.getText().length() == 0) {
//            jTextField5.setText("Mobile  :");
//        }
    }//GEN-LAST:event_jTextField5FocusLost

    private void jTextField5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5MouseClicked

    private void jTextField5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5MouseEntered

    private void jTextField5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField5MousePressed
        if (jTextField5.getText().equals("Mobile  :")) {
            jTextField5.setText("");
        }
    }//GEN-LAST:event_jTextField5MousePressed

    private void jPasswordField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusLost

    }//GEN-LAST:event_jPasswordField1FocusLost

    private void jPasswordField1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField1MousePressed

    }//GEN-LAST:event_jPasswordField1MousePressed

    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusLost
//        if (jTextField3.getText().length() == 0) {
//            jTextField3.setText("Full Name  :");
//        }
    }//GEN-LAST:event_jTextField3FocusLost

    private void jTextField3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3MouseClicked

    private void jTextField3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3MouseEntered

    private void jTextField3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField3MousePressed
        if (jTextField3.getText().equals("Full Name  :")) {
            jTextField3.setText("");
        }
    }//GEN-LAST:event_jTextField3MousePressed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed

        jTextField4.grabFocus();

//        jTextField2.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                // Get the KeyCode and perform actions
//                int keyCode = e.getKeyCode();
//
//                switch (keyCode) {
//                    case KeyEvent.VK_ENTER:
//                        jTextField1.setText("Enter key pressed!");
//                        break;
//                    case KeyEvent.VK_ESCAPE:
//                        jTextField1.setText("Escape key pressed!");
//                        break;
//                    case KeyEvent.VK_A:
//                        jTextField1.setText("The 'A' key was pressed!");
//                        break;
//                    case KeyEvent.VK_UP:
//                        jTextField1.setText("Up arrow key pressed!");
//                        break;
//                    case KeyEvent.VK_DOWN:
//                        jTextField1.setText("Down arrow key pressed!");
//                        break;
//                    case KeyEvent.VK_LEFT:
//                        jTextField1.setText("Left arrow key pressed!");
//                        break;
//                    case KeyEvent.VK_RIGHT:
//                        jTextField1.setText("Right arrow key pressed!");
//                        break;
//                    case KeyEvent.VK_F1:
//                        jTextField1.setText("F1 key pressed!");
//                        break;
//                    default:
//                        jTextField1.setText("Some other key pressed...");
//                        break;
//                }
//            }
//        });
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            jButton1.grabFocus();
        }
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusLost
//        if (jTextField4.getText().length() == 0) {
//            jTextField4.setText("Address  :");
//        }
    }//GEN-LAST:event_jTextField4FocusLost

    private void jTextField4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4MouseClicked

    private void jTextField4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4MouseEntered

    private void jTextField4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MousePressed
//        if (jTextField4.getText().equals("Address  :")) {
//            jTextField4.setText("");
//        }
    }//GEN-LAST:event_jTextField4MousePressed

    private void jTextField7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField7FocusLost
//        if (jTextField7.getText().length() == 0) {
//            jTextField7.setText("User Name  :");
//        }
    }//GEN-LAST:event_jTextField7FocusLost

    private void jTextField7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7MouseClicked

    private void jTextField7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7MouseEntered

    private void jTextField7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField7MousePressed
//        if (jTextField7.getText().equals("User Name  :")) {
//            jTextField7.setText("");
//        }
    }//GEN-LAST:event_jTextField7MousePressed

    private void jTextField6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField6FocusLost
//        if (jTextField6.getText().length() == 0) {
//            jTextField6.setText("Age :");
//        }
    }//GEN-LAST:event_jTextField6FocusLost

    private void jTextField6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6MouseClicked

    private void jTextField6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6MouseEntered

    private void jTextField6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField6MousePressed
//        if (jTextField6.getText().equals("Age :")) {
//            jTextField6.setText("");
//        }
    }//GEN-LAST:event_jTextField6MousePressed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        jTextField7.grabFocus();
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int sr = jTable1.getSelectedRow();
        //        DecimalFormat priceFormat = new DecimalFormat("0.00");
        if (evt.getClickCount() == 1) {
            if (sr == -1) {
                JOptionPane.showMessageDialog(this, "Please Select The Row.", "Warning", JOptionPane.QUESTION_MESSAGE);

            } else {

                jTextField2.setText(jTable1.getValueAt(sr, 1).toString());
                jTextField3.setText(jTable1.getValueAt(sr, 2).toString());

                jTextField4.setText(jTable1.getValueAt(sr, 3).toString());
                jTextField5.setText(jTable1.getValueAt(sr, 4).toString());
                jTextField6.setText(jTable1.getValueAt(sr, 5).toString());
                jTextField7.setText(jTable1.getValueAt(sr, 6).toString());
                jPasswordField1.setText(jTable1.getValueAt(sr, 7).toString());
                String gender = jTable1.getValueAt(sr, 8).toString();

                if (gender.equals("Male")) {

                    jRadioButton1.setSelected(true);
                } else {
                    jRadioButton2.setSelected(true);

                }

                jComboBox2.setSelectedItem(jTable1.getValueAt(sr, 9).toString());

            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost

        if (jTextField1.getText().length() == 0) {
            jTextField1.setText("Search  :");
        }
    }//GEN-LAST:event_jTextField1FocusLost

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked

    }//GEN-LAST:event_jTextField1MouseClicked

    private void jTextField1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseEntered
        //     jTextField1.setText("");
    }//GEN-LAST:event_jTextField1MouseEntered

    private void jTextField1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MousePressed

        if (jTextField1.getText().equals("Search  :")) {
            jTextField1.setText("");
        }
    }//GEN-LAST:event_jTextField1MousePressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Plese select a row", "Warning", JOptionPane.QUESTION_MESSAGE);
        } else {

            String Id = String.valueOf(jTable1.getValueAt(selectedRow, 0));

            String Nic = jTextField2.getText();
            String Name = jTextField3.getText();
            String Address = jTextField4.getText();

            String Mobile = jTextField5.getText();

            String age = jTextField6.getText();
            String username = jTextField7.getText();
            String password = String.valueOf(jPasswordField1.getPassword());

            if (Nic.isBlank()) {

                JOptionPane.showMessageDialog(this, "Nic Number is required", "Warning", JOptionPane.QUESTION_MESSAGE);
                jTextField2.setBorder(new LineBorder(Color.red, 1));
            } else if (Name.isBlank()) {
                jTextField2.setBorder(BorderFactory.createEmptyBorder());
                JOptionPane.showMessageDialog(this, "Full Name is required...", "Warning", JOptionPane.QUESTION_MESSAGE);
                jTextField3.setBorder(new LineBorder(Color.red, 1));
            } else if (Address.isBlank()) {
                jTextField3.setBorder(BorderFactory.createEmptyBorder());

                JOptionPane.showMessageDialog(this, "Address is required", "Warning", JOptionPane.QUESTION_MESSAGE);
                jTextField4.setBorder(new LineBorder(Color.red, 1));
            } else if (Mobile.isBlank()) {
                jTextField4.setBorder(BorderFactory.createEmptyBorder());
                JOptionPane.showMessageDialog(this, "Mobile is required", "Warning", JOptionPane.QUESTION_MESSAGE);
                jTextField5.setBorder(new LineBorder(Color.red, 1));
            } else if (!Pattern.compile("^(0|[+]94)(11|3(1|3)|7(1|2|3|4|5|6|7|8))([0-9]{7})$").matcher(Mobile).matches()) {
                JOptionPane.showMessageDialog(this, "Please Enter The Valid Mobile Number", "warning", JOptionPane.QUESTION_MESSAGE);

            } else if (age.isBlank()) {

                jTextField5.setBorder(BorderFactory.createEmptyBorder());
                JOptionPane.showMessageDialog(this, "Age is required...", "Warning", JOptionPane.QUESTION_MESSAGE);
                jTextField6.setBorder(new LineBorder(Color.red, 1));

            } else if (username.isBlank()) {

                jTextField6.setBorder(BorderFactory.createEmptyBorder());
                JOptionPane.showMessageDialog(this, "Username is required...", "Warning", JOptionPane.QUESTION_MESSAGE);
                jTextField7.setBorder(new LineBorder(Color.red, 1));

            } else if (password.isBlank()) {

                jTextField7.setBorder(BorderFactory.createEmptyBorder());
                JOptionPane.showMessageDialog(this, "Passworld is required...", "Warning", JOptionPane.QUESTION_MESSAGE);
                jPasswordField1.setBorder(new LineBorder(Color.red, 1));

            } else if (!jRadioButton1.isSelected() & !jRadioButton2.isSelected()) {
                jPasswordField1.setBorder(BorderFactory.createEmptyBorder());

                JOptionPane.showMessageDialog(this, "Select a gender", "Warning", JOptionPane.WARNING_MESSAGE);

            } else {
                int genderButton;

                if (jRadioButton1.isSelected()) {
                    genderButton = 1;
                } else {
                    genderButton = 2;
                }
                System.out.println(genderButton);
                try {
                    MySQL.iud("UPDATE `cashier` SET "
                            + "`nic` = '" + Nic + "',"
                            + "`full name` = '" + Name + "',"
                            + "`Address` = '" + Address + "',"
                            + "`Mobile` = '" + Mobile + "',"
                            + "`age` = '" + age + "',"
                            + "`username` = '" + username + "',"
                            + "`passworld` = '" + password + "',"
                            + "`gender_id` = '" + genderButton + "'"
                            + "WHERE `id` = '" + Id + "'");
                    JOptionPane.showMessageDialog(this, "Supplier Updated!", "Supplier", JOptionPane.INFORMATION_MESSAGE);
                    reset();
                    loadCashier("");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        reset();
        //
        //        String Name = jTextField1.getText();
        //        if (Name.equals("Search :")) {
        //            JOptionPane.showMessageDialog(this, "Full Name is required...", "Warning", JOptionPane.QUESTION_MESSAGE);
        //            jTextField1.setBorder(new LineBorder(Color.red, 1));
        //
        //        } else {
        //
        //            jTextField1.setBorder(BorderFactory.createEmptyBorder());
        //
        //        }

        //        jTextField1.setCaretColor(Color.red);
        //        Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
        // set the border of this component
        //        field.setBorder(border);
        //jTextField1.setBorder();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        char[] Password = jPasswordField1.getPassword();

        if (jCheckBox1.isSelected()) {
            jPasswordField1.setEchoChar((char) 0);
            jCheckBox1.setText("Hide");
        } else {

            jPasswordField1.setEchoChar('*');
            jCheckBox1.setText("Show");
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Plese Select Change cashier Status...", "Warning", JOptionPane.QUESTION_MESSAGE);
        } else {
            if (selectedRow == 0) {
                JOptionPane.showMessageDialog(this, "Can't change status...", "Warning", JOptionPane.QUESTION_MESSAGE);
            } else {
                
                

                String id = jTable1.getValueAt(selectedRow, 0).toString();
                String status = jTable1.getValueAt(selectedRow, 10).toString();
                //            String Status = jTable1.getValueAt(selectedRow, 5).toString();
                System.out.println(jTable1.getValueAt(selectedRow, 0).toString());
                System.out.println(status);
                if (status.equals("Active")) {

                    MySQL.iud("UPDATE `cashier`"
                            + "SET `Status_id` = '0' "
                            + "WHERE `id`='" + id + "';");

                    reset();
                    loadCashier("");

                } else {
                    MySQL.iud("UPDATE `cashier`"
                            + "SET `Status_id` = '1' "
                            + "WHERE `id`='" + id + "';");

                    reset();
                    loadCashier("");
                    System.out.println("ok");
                }

            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nic = jTextField2.getText();
        String fullname = jTextField3.getText();
        String address = jTextField4.getText();
        String mobile = jTextField5.getText();
        String age = jTextField6.getText();
        String username = jTextField7.getText();
        String password = String.valueOf(jPasswordField1.getPassword());
        String cashiertype = String.valueOf(jComboBox2.getSelectedItem());

        if (nic.isBlank()) {

            JOptionPane.showMessageDialog(this, "Nic Number is required...", "Warning", JOptionPane.QUESTION_MESSAGE);
            jTextField2.setBorder(new LineBorder(Color.red, 1));

        } else if (fullname.isBlank()) {

            jTextField2.setBorder(BorderFactory.createEmptyBorder());
            JOptionPane.showMessageDialog(this, "Full Name is required..", "Warning", JOptionPane.QUESTION_MESSAGE);
            jTextField3.setBorder(new LineBorder(Color.red, 1));

        } else if (address.isBlank()) {

            jTextField3.setBorder(BorderFactory.createEmptyBorder());
            JOptionPane.showMessageDialog(this, "Address is required...", "Warning", JOptionPane.QUESTION_MESSAGE);
            jTextField4.setBorder(new LineBorder(Color.red, 1));

        } else if (mobile.isBlank()) {

            jTextField4.setBorder(BorderFactory.createEmptyBorder());
            JOptionPane.showMessageDialog(this, "Mobile is required...", "Warning", JOptionPane.QUESTION_MESSAGE);
            jTextField5.setBorder(new LineBorder(Color.red, 1));

        } else if (!Pattern.compile("^(0|[+]94)(11|3(1|3)|7(1|2|3|4|5|6|7|8))([0-9]{7})$").matcher(mobile).matches()) {

            jTextField5.setBorder(BorderFactory.createEmptyBorder());
            JOptionPane.showMessageDialog(this, "Please Enter The Valid Mobile Number", "warning", JOptionPane.QUESTION_MESSAGE);
            jTextField5.setBorder(new LineBorder(Color.red, 1));

        } else if (age.isBlank()) {

            jTextField5.setBorder(BorderFactory.createEmptyBorder());
            JOptionPane.showMessageDialog(this, "Age is required...", "Warning", JOptionPane.QUESTION_MESSAGE);
            jTextField6.setBorder(new LineBorder(Color.red, 1));

        } else if (username.isBlank()) {

            jTextField6.setBorder(BorderFactory.createEmptyBorder());
            JOptionPane.showMessageDialog(this, "Username is required...", "Warning", JOptionPane.QUESTION_MESSAGE);
            jTextField7.setBorder(new LineBorder(Color.red, 1));

        } else if (password.isBlank()) {

            jTextField7.setBorder(BorderFactory.createEmptyBorder());
            JOptionPane.showMessageDialog(this, "Passworld is required...", "Warning", JOptionPane.QUESTION_MESSAGE);
            jPasswordField1.setBorder(new LineBorder(Color.red, 1));

        } else if (cashiertype.equals("Select Cashier Type")) {

            jPasswordField1.setBorder(BorderFactory.createEmptyBorder());
            JOptionPane.showMessageDialog(this, "Select Cashier Your Type..", "Warning", JOptionPane.QUESTION_MESSAGE);

        } else if (!jRadioButton1.isSelected() & !jRadioButton2.isSelected()) {
            JOptionPane.showMessageDialog(this, "Select a gender", "Warning", JOptionPane.QUESTION_MESSAGE);

        } else {

            int genderButton;

            if (jRadioButton1.isSelected()) {
                genderButton = 1;
            } else {
                genderButton = 2;
            }

            int countryId = cashiertyperymap.get(cashiertype);
            try {
                ResultSet cashierRs = MySQL.search("SELECT * FROM `cashier` WHERE `nic`='" + nic + "' OR `Mobile`='" + mobile + "' OR `passworld`='" + password + "'");

                boolean canUpdate = false;

                if (cashierRs.next()) {
                    JOptionPane.showMessageDialog(this, "Cashier is Already Exist...", "Warning", JOptionPane.QUESTION_MESSAGE);

                } else {
                    canUpdate = true;
                }

                if (canUpdate) {
                    MySQL.iud("INSERT INTO "
                            + "`cashier` (`nic`,`full name`,`Address`,`Mobile`,`age`,"
                            + "`username`,`passworld`,`cashiertype_id`,`gender_id`,`Status_id`)"
                            + "VALUES('" + nic + "','" + fullname + "','" + address + "'"
                            + ",'" + mobile + "','" + age + "','" + username + "','" + password + "',"
                            + "'" + cashiertyperymap.get(cashiertype) + "','" + genderButton + "','1')");

                    JOptionPane.showMessageDialog(this, "Cashier Registration Successfull.....!", "Sucsess", JOptionPane.INFORMATION_MESSAGE);
                    reset();
                    loadCashier("");
                }

            } catch (Exception e) {

                e.printStackTrace();

            }
            //
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed


    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        jTextField5.grabFocus();
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        jTextField6.grabFocus();
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        jComboBox2.grabFocus();
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        jPasswordField1.grabFocus();
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
//       jRadioButton1.grabFocus();
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed

    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if (!jTextField1.getText().equals("Search  :")) {
            loadCashier(jTextField1.getText());
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2FocusLost

    private void jTextField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2MouseClicked

    private void jTextField2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2MouseEntered

    private void jTextField2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2MousePressed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyPressed

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
            java.util.logging.Logger.getLogger(Cashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cashier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
