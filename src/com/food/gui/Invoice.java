/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.food.gui;

import static com.food.gui.AddCashier.cashiertyperymap;
import static com.food.gui.AddGrn.grnItemMap;
import com.food.gui.AddStock.ColorfulCellRenderer;
import static com.food.gui.Cashier.cashiertyperymap;
import com.food.model.MySQL;
import com.food.model.grnItem;
import com.food.model.invoiceitem;
import com.mysql.cj.protocol.Resultset;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ABC
 */
public class Invoice extends javax.swing.JFrame {

    public static HashMap<String, Integer> paymentmap = new HashMap();
    public static HashMap<String, invoiceitem> invoiceItemMap = new HashMap<>();

    /**
     * Creates new form Invoice
     */
    public Invoice() {
        initComponents();
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loadPaymentCombo();
        uniqeId();
        jTextField3.setEditable(false);
        jTextField4.setEditable(false);
        jTextField5.setEditable(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable2.getColumnCount(); i++) {
            jTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();

        KeyStroke productKey = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK);
        inputMap.put(productKey, "Product");

        actionMap.put("Product", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Shortcut triggered!");
                openProduct();
            }
        });
    }

    private void openProduct() {
        AddStock ad = new AddStock();
        ad.setVisible(true);
        System.out.println("AddStock window opened!");
    }

    public JTextField getjTextField1() {
        return jTextField1;
    }

//    }
    public void loadStrock(String id) {
        try {

            ResultSet StockRs = MySQL.search("SELECT * FROM `product` INNER JOIN "
                    + "`stock` ON `product`.`id`=`stock`.product_id  INNER JOIN"
                    + " `mesurse_type` ON `stock`.`mesurse_type_id`=`mesurse_type`.`id` "
                    + "WHERE `stock`.`id`='" + id + "' ");

            if (StockRs.next()) {

                jTextField3.setText(StockRs.getString("product.product_name"));
                jTextField4.setText(StockRs.getString("stock.unit_per_product"));
                jLabel4.setText(StockRs.getString("mesurse_type.mesurse_type"));
                jTextField5.setText(StockRs.getString("stock.selling_price"));

            } else {
                jTextField3.setText("");
                jTextField4.setText("");
                jLabel4.setText("");
                jTextField5.setText("");

            }
//            jTable1.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPaymentCombo() {
        try {

            ResultSet paymentRs = MySQL.search("SELECT * FROM `payment`");

            Vector<String> vector = new Vector<>();
            vector.add("Payment Type");

            while (paymentRs.next()) {
                vector.add(paymentRs.getString("type"));
                paymentmap.put(paymentRs.getString("type"), paymentRs.getInt("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox8.setModel(model);

        } catch (Exception e) {

            //System.out.println("Connection Problem");
            e.printStackTrace();

        }
    }

    private void uniqeId() {

        long uniqueID = System.currentTimeMillis();

        jLabel1.setText("In" + String.valueOf(uniqueID));
    }

    private void invoiceReset() {

        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jLabel4.setText("");
        jTextField5.setText("");
        jTextField2.setText("");

    }

    private void saveReset() {
        DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
        dtm.setRowCount(0);

        jLabel8.setText("");
        jFormattedTextField1.setText("");
        jComboBox8.setSelectedItem("Payment Type");
        jLabel13.setText("");
        jCheckBox1.setSelected(false);
    }

    private void loadINVOICEItems() {
        double total = 0;
        DecimalFormat df = new DecimalFormat("0.000");
        DecimalFormat pf = new DecimalFormat("0.00");
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        for (invoiceitem i : invoiceItemMap.values()) {
            Vector<String> vector = new Vector<>();

            vector.add(i.getSid());
            vector.add(i.getProductname());
            vector.add(i.getSize());
            vector.add(i.getMesureType());
            vector.add(i.getSizeOfProduct());
            vector.add(String.valueOf(pf.format(i.getSelling_Price())));

            if (i.getMesureType().equals("KG") || i.getMesureType().equals("G")) {
                double itemTotal = (Double.valueOf(i.getSelling_Price()) / Double.valueOf(i.getSize()) * Double.valueOf(i.getSizeOfProduct()));
                total += itemTotal;
                vector.add(String.valueOf(pf.format(itemTotal)));
                i.setTotal(total);

            } else {
                double itemTotal = Double.valueOf(i.getSizeOfProduct()) * Double.valueOf(i.getSelling_Price());
                total += itemTotal;
                vector.add(String.valueOf(itemTotal));
                i.setTotal(total);
            }

            model.addRow(vector);

        }

        jLabel8.setText(String.valueOf(pf.format(total)));

    }

    private void clear() {

        uniqeId();

        jLabel2.setText("");
        jLabel4.setText("");
        jLabel8.setText("");
        jFormattedTextField1.setText("");
        jLabel13.setText("");
        jComboBox8.setSelectedIndex(-1);

        DefaultTableModel dtm1 = (DefaultTableModel) jTable2.getModel();
        dtm1.setRowCount(0);
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
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Invoice", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ebrima", 1, 18), new java.awt.Color(0, 122, 255))); // NOI18N

        jButton4.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/add_1.png"))); // NOI18N
        jButton4.setText("Select Product");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable2.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Product", "Size", "Mesure", "Unit", "Selling Price", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setSelectionBackground(new java.awt.Color(0, 122, 255));
        jTable2.setSelectionForeground(new java.awt.Color(251, 255, 255));
        jTable2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTable2FocusLost(evt);
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jTable2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTable2PropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel1.setText("ID");

        jLabel2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabel2PropertyChange(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel5.setText("Product :");

        jLabel6.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel6.setText("ID :");
        jLabel6.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabel6PropertyChange(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel7.setText("Size  :");

        jLabel8.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel9.setText("Total :");

        jLabel10.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel10.setText("Discount :");

        jComboBox8.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Payment Type" }));
        jComboBox8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox8ItemStateChanged(evt);
            }
        });

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        jFormattedTextField1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jFormattedTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jFormattedTextField1MouseClicked(evt);
            }
        });
        jFormattedTextField1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField1PropertyChange(evt);
            }
        });
        jFormattedTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextField1KeyReleased(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jTextField1.setSelectedTextColor(new java.awt.Color(251, 255, 255));
        jTextField1.setSelectionColor(new java.awt.Color(0, 122, 255));
        jTextField1.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                jTextField1HierarchyChanged(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTextField1PropertyChange(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jTextField2.setSelectedTextColor(new java.awt.Color(251, 255, 255));
        jTextField2.setSelectionColor(new java.awt.Color(0, 122, 255));

        jLabel14.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel14.setText("Size of Product :");

        jLabel15.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel15.setText("Selling Price :");

        jTextField3.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jTextField3.setSelectedTextColor(new java.awt.Color(251, 255, 255));
        jTextField3.setSelectionColor(new java.awt.Color(0, 122, 255));
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        jTextField4.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jTextField4.setSelectedTextColor(new java.awt.Color(251, 255, 255));
        jTextField4.setSelectionColor(new java.awt.Color(0, 122, 255));
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jTextField5.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jTextField5.setSelectedTextColor(new java.awt.Color(251, 255, 255));
        jTextField5.setSelectionColor(new java.awt.Color(0, 122, 255));

        jButton5.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/add_1.png"))); // NOI18N
        jButton5.setText("Add Product");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel12.setText("Bill Price :");

        jLabel13.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/add (1)_1.png"))); // NOI18N
        jButton1.setText("Save");
        jButton1.setPreferredSize(new java.awt.Dimension(73, 35));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jCheckBox1.setText("Bill Print");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField3))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(jTextField5)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(29, 29, 29))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(623, 623, 623)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(408, 408, 408))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        AddStock ad = new AddStock();
        ad.setVisible(true);
        ad.setProductInvoice(this);

        loadStrock(jTextField1.getText());

//        String size = jFormattedTextField5.getText();
//        String mesusureType = jComboBox2.getSelectedItem().toString();
//        String MesureValue = jFormattedTextField1.getText();
//        String buying_price = jFormattedTextField3.getText();
//        String selling_price = jFormattedTextField2.getText();
//
//        String[] pid = jLabel10.getText().split(" ");
//        String[] pname = jLabel3.getText().split(" ");
//        String[] cname = jLabel4.getText().split(" ");
//
//        if (jLabel10.getText().equals("Product Id : ")) {
//            JOptionPane.showMessageDialog(this, "Please Select Product ....", "Warning", JOptionPane.QUESTION_MESSAGE);
//        } else if (jComboBox1.getSelectedItem().toString().equals("Select Supplier")) {
//            JOptionPane.showMessageDialog(this, "Please Select Supplier ....", "Warning", JOptionPane.QUESTION_MESSAGE);
//        } else if (jComboBox2.getSelectedItem().toString().equals("Type")) {
//
//            JOptionPane.showMessageDialog(this, "Please Enater the type ....", "Warning", JOptionPane.QUESTION_MESSAGE);
//
//        } else if (mesusureType.isBlank()) {
//
//            JOptionPane.showMessageDialog(this, "Please Enater Mesure Typee ....", "Warning", JOptionPane.QUESTION_MESSAGE);
//
//        } else if (jFormattedTextField2.getText().isBlank()) {
//
//            JOptionPane.showMessageDialog(this, "Please Enater Buying Price ....", "Warning", JOptionPane.QUESTION_MESSAGE);
//        } else if (jFormattedTextField3.getText().isBlank()) {
//
//            JOptionPane.showMessageDialog(this, "Please Enater Selling Price ....", "Warning", JOptionPane.QUESTION_MESSAGE);
//
//        } else {
//
//            try {
//                ResultSet cheakRs = MySQL.search("SELECT * FROM `supplier` INNER JOIN "
//                    + "`supplier_has_type` ON `supplier`.`id`=`supplier_has_type`.`supplier_id` "
//                    + "INNER JOIN `product` ON `supplier_has_type`.`product_id`=`product`.`id` "
//                    + "WHERE `supplier`.`full_name`='" + jComboBox1.getSelectedItem().toString() + "' AND  "
//                    + "`product`.`id`='" + pid[3] + "'");
//
//                if (cheakRs.next()) {
//                    grnItem gr = new grnItem();
//
//                    gr.setProductId(pid[3]);
//                    gr.setProductName(pname[3]);
//                    gr.setCategory(cname[2]);
//
//                    gr.setSize(size);
//                    gr.setMesureType(mesusureType);
//                    gr.setMesureValue(MesureValue);
//                    gr.setBuyingPrice(Double.valueOf(buying_price));
//                    gr.setSellingPrice(Double.valueOf(selling_price));
//
//                    if (grnItemMap.get(pid[3]) == null) {
//                        grnItemMap.put(pid[3], gr);
//                        grnReset();
//                        loadGRNItems();
//                    } else {
//
//                        grnItem found = grnItemMap.get(pid[3]);
//
//                        if (found.getBuyingPrice() == Double.valueOf(buying_price)
//                            && found.getSellingPrice() == Double.valueOf(selling_price)
//                            && found.getMesureType() == mesusureType) {
//
//                            found.setMesureValue(String.valueOf(Double.valueOf(found.getMesureValue()) + Double.valueOf(MesureValue)));
//                            grnReset();
//                            loadGRNItems();
//                        } else {
//
//                            JOptionPane.showMessageDialog(this, "GRN item already exists with diffrent dates and prices", "Error", JOptionPane.ERROR_MESSAGE);
//
//                        }
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(this, "Please Cheack Supplier and Product Conectivity ....", "Warning", JOptionPane.QUESTION_MESSAGE);
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel6PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabel6PropertyChange

    }//GEN-LAST:event_jLabel6PropertyChange

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
//        cal();

    }//GEN-LAST:event_jTable2MouseClicked
    boolean focus;
    private void jTable2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable2FocusGained
        if (focus) {
//            cal();
        }
    }//GEN-LAST:event_jTable2FocusGained

    private void jTable2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable2FocusLost
        if (!focus) {
            focus = true;
        }
    }//GEN-LAST:event_jTable2FocusLost

    private void jTable2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable2PropertyChange
//        cal();
    }//GEN-LAST:event_jTable2PropertyChange

    private void jComboBox8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox8ItemStateChanged

    }//GEN-LAST:event_jComboBox8ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String iid = jLabel1.getText();
        String total = jLabel8.getText();
        String discount = jFormattedTextField1.getText();
        String pType = jComboBox8.getSelectedItem().toString();
        String billprice = jLabel13.getText();

        if (jTable2.getRowCount() == 0) {

            JOptionPane.showMessageDialog(this, "Please Added iNVOICE item...", "Warning", JOptionPane.QUESTION_MESSAGE);
        } else if (discount.isBlank()) {

            JOptionPane.showMessageDialog(this, "Please Enter Bill Discount...", "Warning", JOptionPane.QUESTION_MESSAGE);
        } else if (jComboBox8.getSelectedItem().toString().equals("Payment Type")) {

            JOptionPane.showMessageDialog(this, "Please Select Payment type...", "Warning", JOptionPane.QUESTION_MESSAGE);
        } else {
            try {
                Date currentDate = new Date();

                // Create a SimpleDateFormat instance with the desired format
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                // Format the date
                String formattedDate = sdf.format(currentDate);

                MySQL.iud("INSERT INTO `invoice`(`id`,`date`,`bill_price`,`discount`,`total_price`,`cashier_id`,`Payment_id`) "
                        + "VALUES('" + iid + "','" + sdf.format(currentDate) + "','" + billprice + "','" + discount + "','" + total + "','" + SignIn.ID + "','" + paymentmap.get(pType) + "')");

//
                for (invoiceitem i : invoiceItemMap.values()) {

                    ResultSet invoiceRs = MySQL.search("SELECT * FROM  `product` INNER JOIN  "
                            + "`stock` ON  `product`.`id`=`stock`.`product_id`   INNER JOIN  "
                            + "`mesurse_type` ON `stock`.`mesurse_type_id`=`mesurse_type`.`id`  WHERE "
                            + "`stock`.`id`= '" + i.getSid() + "' AND "
                            + "`mesurse_type`.`mesurse_type`='" + i.getMesureType() + "' AND"
                            + "`product`.`product_name`='" + i.getProductname() + "' "
                    );

                    String sid = "";
                    String mid = "";

                    if (invoiceRs.next()) {
                        //existing stock

                        sid = invoiceRs.getString("stock.id");
                        System.out.println("ubhdfuefewiufbeifeife-=--" + iid);
                        MySQL.iud("INSERT INTO `invoice_item`(`stock_id`,`size_of_product`,`mesurse_type_id`,`selling_price`,`total`,`invoice_id`) VALUES ('" + invoiceRs.getString("stock.id") + "','" + i.getSizeOfProduct() + "','" + invoiceRs.getString("mesurse_type_id") + "','" + i.getSelling_Price() + "','" + i.getTotal() + "','" + iid + "');");

                        if (i.getMesureType().equals("KG")) {
//                            String currentQty = invoiceRs.getString("stock.size_of_product");

                            double sof = Double.valueOf(invoiceRs.getString("stock.size_of_product")) * Double.valueOf(1000) - Double.valueOf(i.getSizeOfProduct()) * Double.valueOf(1000);

                            MySQL.iud("UPDATE `stock` SET `size_of_product` = '" + sof / 1000 + "' WHERE `id` = '" + sid + "';");

                        } else {

                            String currentQty = invoiceRs.getString("stock.size_of_product");

                            String updatedQuantity = String.valueOf(Integer.parseInt(currentQty) - Integer.parseInt(i.getSizeOfProduct()));

                            MySQL.iud("UPDATE `stock` SET `size_of_product` = '" + updatedQuantity + "' WHERE `id` = '" + sid + "'");

                        }
                    } else {
                        System.out.println("wfniiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
                    }
                }
                if (jCheckBox1.isSelected()) {
                    HashMap<String, Object> parametes = new HashMap<>();
                    //            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                    parametes.put("Parameter3", jLabel1.getText());
                    parametes.put("Parameter2", SignIn.ID);
                    parametes.put("Parameter1", sdf.format(currentDate));
                    parametes.put("Parameter4", jLabel8.getText());
                    parametes.put("Parameter5", jComboBox8.getSelectedItem().toString());
                    parametes.put("Parameter6", jFormattedTextField1.getText());
                    parametes.put("Parameter7", jLabel13.getText());

                    JRTableModelDataSource datasourse = new JRTableModelDataSource(jTable2.getModel());
                    //            JasperViewer.viewReport(JasperFillManager.fillReport("src/com/food/report/shopgrn.jasper", paramerters, datasourse));
                    JasperViewer.viewReport(JasperFillManager.fillReport("src/com/food/report/shopInvoice.jasper", parametes, datasourse));
                    invoiceReset();
                    saveReset();

                } else {
                    invoiceReset();
                    saveReset();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
//
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jFormattedTextField1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField1PropertyChange
        if (!jFormattedTextField1.getText().isBlank()) {
//            System.out.println(jFormattedTextField1.getText());

            String billPrice = String.valueOf(Double.valueOf(jLabel8.getText()) - Double.valueOf(jLabel8.getText()) * Double.valueOf(jFormattedTextField1.getText()) / 100);
            jLabel13.setText(billPrice);
        }
    }//GEN-LAST:event_jFormattedTextField1PropertyChange

    private void jFormattedTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFormattedTextField1MouseClicked
        if (!jFormattedTextField1.getText().isBlank()) {
//            System.out.println(jFormattedTextField1.getText());

            String billPrice = String.valueOf(Double.valueOf(jLabel8.getText()) - Double.valueOf(jLabel8.getText()) * Double.valueOf(jFormattedTextField1.getText()) / 100);
            jLabel13.setText(billPrice);
        }
    }//GEN-LAST:event_jFormattedTextField1MouseClicked

    private void jFormattedTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField1KeyReleased
        DecimalFormat DF = new DecimalFormat("0.00");
        if (!jFormattedTextField1.getText().isBlank()) {
            if (Double.valueOf(jFormattedTextField1.getText()) < 100) {

                String billPrice = String.valueOf(Double.valueOf(jLabel8.getText()) - Double.valueOf(jLabel8.getText()) * Double.valueOf(jFormattedTextField1.getText()) / 100);
                jLabel13.setText(billPrice);

            } else {

                jFormattedTextField1.setText("");
                jLabel13.setText("");
            }
        }
    }//GEN-LAST:event_jFormattedTextField1KeyReleased

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if (!jTextField1.getText().isBlank()) {
            loadStrock(jTextField1.getText());
        } else {
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField2.setText("");
            jTextField5.setText("");
            jLabel4.setText("");
        }


    }//GEN-LAST:event_jTextField1KeyReleased

    private void jLabel2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabel2PropertyChange

        loadStrock(jLabel2.getText());
    }//GEN-LAST:event_jLabel2PropertyChange

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        String sid = jTextField1.getText();

        String pnanme = jTextField3.getText();
        String size = jTextField4.getText();
        String mesurevalue = jLabel4.getText();
        String sof = jTextField2.getText();
        String selligPrice = jTextField5.getText();

        if (sid.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please enter Stock Id or Add ....", "Warning", JOptionPane.QUESTION_MESSAGE);

        } else if (!Pattern.compile("\\d+(\\.\\d+)?").matcher(sid).matches()) {
            JOptionPane.showMessageDialog(this, "Please Enter The Valid Stock Id", "warning", JOptionPane.QUESTION_MESSAGE);

        } else if (sof.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please enter size of product ....", "Warning", JOptionPane.QUESTION_MESSAGE);

        } else if (!Pattern.compile("\\d+(\\.\\d+)?").matcher(sof).matches()) {
            JOptionPane.showMessageDialog(this, "Please Enter The Valid Size of Product", "warning", JOptionPane.QUESTION_MESSAGE);
        } else if (jTextField3.getText().isBlank() && jTextField4.getText().isBlank() && jTextField5.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Please Enter The Valid product", "warning", JOptionPane.QUESTION_MESSAGE);

        } else {
            try {

                ResultSet checkStockRs = MySQL.search("SELECT * FROM  `product` INNER JOIN  "
                        + "`stock` ON  `product`.`id`=`stock`.`product_id`   INNER JOIN  "
                        + "`mesurse_type` ON `stock`.`mesurse_type_id`=`mesurse_type`.`id`  WHERE "
                        + "`stock`.`id`= '" + sid + "' AND "
                        + "`product`.`product_name`='" + pnanme + "' AND"
                        + "`mesurse_type`.`mesurse_type`='" + mesurevalue + "' "
                );

                boolean v = true;
                if (checkStockRs.next()) {
                    String sizeOfProduct = checkStockRs.getString("stock.size_of_product");
                    invoiceitem cheack = invoiceItemMap.get(sid);
                    if (mesurevalue.equals("KG")) {

                        if (cheack != null) {

                            double mValue = Double.valueOf(cheack.getSizeOfProduct()) * Double.valueOf(1000) + Double.valueOf(sof) * Double.valueOf(1000);

                            if (Double.parseDouble(sizeOfProduct) * Double.valueOf(1000) >= mValue) {
                                v = true;

                            } else {

                                invoiceReset();
                                v = false;

                            }
                        } else {

                            if (Double.valueOf(sizeOfProduct) * Double.valueOf(1000) >= Double.valueOf(sof) * Double.valueOf(1000)) {
                                v = true;

                            } else {

                                invoiceReset();
                                v = false;

                            }
                        }
                    } else {
//efefef
                        try {
                            int intValue = Integer.parseInt(sof);

                            if (cheack != null) {

                                int mValue = Integer.valueOf(cheack.getSizeOfProduct()) + Integer.valueOf(sof);

                                if (Integer.valueOf(sizeOfProduct) >= mValue) {

                                    v = true;

                                } else {

                                    invoiceReset();
                                    v = false;

                                }
                            } else {

                                if (Integer.valueOf(sizeOfProduct) >= Integer.valueOf(sof)) {
                                    v = true;

                                } else {

                                    invoiceReset();
                                    v = false;

                                }
                            }
                        } catch (NumberFormatException e) {
                            invoiceReset();
                            v = false;
                            System.out.println(sof + " is not a valid integer.");
                        }
//efefefef
                    }

                }

                if (v) {

                    invoiceitem i = new invoiceitem();

                    i.setSid(sid);
                    i.setProductname(pnanme);
                    i.setSize(size);
                    i.setMesureType(mesurevalue);
                    i.setSizeOfProduct(sof);
                    i.setSelling_Price(Double.valueOf(selligPrice));

                    if (invoiceItemMap.get(sid) == null) {
                        invoiceItemMap.put(sid, i);
                        invoiceReset();
                        loadINVOICEItems();

                    } else {

                        invoiceitem found = invoiceItemMap.get(sid);

                        if (found.getProductname().equals(pnanme)
                                && found.getSize().equals(size)
                                && found.getSelling_Price().equals(Double.valueOf(selligPrice))
                                && found.getMesureType().equals(mesurevalue)) {

                            found.setSizeOfProduct(String.valueOf(Double.valueOf(found.getSizeOfProduct()) + Double.valueOf(sof)));

                            invoiceReset();
                            loadINVOICEItems();
                        } else {
//                        System.out.println(found.getSize());
//                        System.out.println(found.getBuyingPrice());
//                        System.out.println(found.getSellingPrice());
//                        System.out.println(found.getMesureType());
//
//                        System.out.println(size);
//                        System.out.println(buying_price);
//                            System.out.println(found.getSelling_Price());
//                            System.out.println(Double.valueOf(selligPrice));
//
//                            System.out.println(found.getSize().equals(size));
//                            System.out.println(found.getSelling_Price() == Double.valueOf(selligPrice));
//                            System.out.println(found.getMesureType().equals(mesurevalue));
                            JOptionPane.showMessageDialog(this, "INVOICE item already exists with diffrent  prices", "Error", JOptionPane.ERROR_MESSAGE);

                        }
                    }
                } else {
                    v = false;
                    JOptionPane.showMessageDialog(this, "Invalid Mesure value ....", "Warning", JOptionPane.QUESTION_MESSAGE);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTextField1PropertyChange
        if (!jTextField1.getText().isBlank()) {
            loadStrock(jTextField1.getText());
        } else {
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField2.setText("");
            jTextField5.setText("");
            jLabel4.setText("");
        }

    }//GEN-LAST:event_jTextField1PropertyChange

    private void jTextField1HierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jTextField1HierarchyChanged
        if (!jTextField1.getText().isBlank()) {
            loadStrock(jTextField1.getText());
        } else {
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField2.setText("");
            jTextField5.setText("");
            jLabel4.setText("");
        }


    }//GEN-LAST:event_jTextField1HierarchyChanged

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            HashMap<String, Object> parametes = new HashMap<>();
            //            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            parametes.put("Parameter3", jLabel1.getText());
            parametes.put("Parameter2", SignIn.ID);
            parametes.put("Parameter1", "2034");
            parametes.put("Parameter4", jLabel8.getText());
            parametes.put("Parameter5", jFormattedTextField1.getText());
            parametes.put("Parameter6", jComboBox8.getSelectedItem().toString());
            parametes.put("Parameter7", jLabel13.getText());

            JRTableModelDataSource datasourse = new JRTableModelDataSource(jTable2.getModel());
            //            JasperViewer.viewReport(JasperFillManager.fillReport("src/com/food/report/shopgrn.jasper", paramerters, datasourse));
            JasperViewer.viewReport(JasperFillManager.fillReport("src/com/food/report/shopInvoice.jasper", parametes, datasourse));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            java.util.logging.Logger.getLogger(Invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Invoice().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
