/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.food.gui;

import static com.food.gui.AddGrn.grnItemMap;
import static com.food.gui.AddGrn.suppliermap;
import com.food.model.MySQL;
import com.food.model.grnItem;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.print.DocFlavor;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ABC
 */
public class AddGrn extends javax.swing.JFrame {

    public static HashMap<String, Integer> suppliermap = new HashMap();
    public static HashMap<String, Integer> productmMap = new HashMap();

    public static HashMap<String, grnItem> grnItemMap = new HashMap<>();

    /**
     * Creates new form AddG
     */
    public AddGrn() {
        initComponents();
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        uniqeId();
        SignIn.getID();
        loadsupllirCombo();
        loadProductCombo();
        
          DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable3.getColumnCount(); i++) {
            jTable3.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

    }
    String id;

    public Map<String, grnItem> getGrnItemMap() {
        return grnItemMap;
    }

    public JComboBox getjComboBox1() {
        return jComboBox1;

    }

    //mobile
    public JLabel getjLabel2() {
        return jLabel2;

    }

    //Nic
    public JLabel getjLabel1() {
        return jLabel1;

    }

    //Id
    public JLabel getjLabel10() {
        return jLabel10;

    }

    //Product
    public JLabel getjLabel3() {
        return jLabel3;

    }

    public JComboBox getjComboBox3() {
        return jComboBox3;

    }

    //Category
    public JLabel getjLabel4() {
        return jLabel4;

    }

    public void uniqeId() {

        long uniqueID = System.currentTimeMillis();

        jLabel5.setText("GRN" + String.valueOf(uniqueID));
    }

    public void loadsupllirCombo() {
        try {

            ResultSet mesurseRs = MySQL.search("SELECT * FROM `supplier`");

            Vector<String> vector = new Vector<>();
            vector.add("Select Supplier");

            while (mesurseRs.next()) {
                vector.add(mesurseRs.getString("full_name"));
                suppliermap.put(mesurseRs.getString("full_name"), mesurseRs.getInt("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public void loadProductCombo(int supplierId) {
        try {

            ResultSet productRs = MySQL.search("SELECT * FROM `supplier` INNER JOIN `supplier_has_type` ON `supplier`.`id`=`supplier_has_type`.`supplier_id` INNER JOIN `product` ON `supplier_has_type`.`product_id`=`product`.`id` WHERE `supplier`.`id`='" + supplierId + "'");

            Vector<String> vector = new Vector<>();
            vector.add("Select Product");

            while (productRs.next()) {
                vector.add(productRs.getString("product_name"));
                productmMap.put(productRs.getString("product_name"), productRs.getInt("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox3.setModel(model);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public void loadProductCombo() {
        try {

            ResultSet productRs = MySQL.search("SELECT * FROM `product` ");

            Vector<String> vector = new Vector<>();
            vector.add("Select Product");

            while (productRs.next()) {
                vector.add(productRs.getString("product_name"));
                productmMap.put(productRs.getString("product_name"), productRs.getInt("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox3.setModel(model);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    private void loadMesureCo(String pid) {
        try {
            ResultSet mesurseRs = MySQL.search("SELECT `mesurse_type` FROM `product_has_mesurse_type` "
                    + "INNER JOIN `mesurse_type` ON `mesurse_type`.`id` = `product_has_mesurse_type`.`mesurse_type_id`"
                    + " WHERE  `product_has_mesurse_type`.`product_id`='" + pid + "'");

            Vector vector = new Vector<>();
            vector.add("Type");

            while (mesurseRs.next()) {
                vector.add(mesurseRs.getString("mesurse_type"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(dcm);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadSupplierDeils(int supplierId) {

        try {

            ResultSet SupplierDeilsRs = MySQL.search("SELECT * FROM  `supplier` INNER JOIN"
                    + " `supplier_has_type` ON `supplier`.`id`=`supplier_has_type`.`supplier_id`"
                    + " INNER JOIN `product` ON `product`.`id`=`supplier_has_type`.`product_id` "
                    + "INNER JOIN `category` ON `category`.`id`=`product`.`category_id` "
                    + "WHERE `supplier`.`id`='" + supplierId + "'");

            if (SupplierDeilsRs.next()) {

                jLabel1.setText("Mobile  : " + SupplierDeilsRs.getString("supplier.mobile"));
                jLabel2.setText("Nic  : " + SupplierDeilsRs.getString("supplier.nic"));
            } else {
                jLabel1.setText("Mobile  : ");
                jLabel2.setText("Nic  : ");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadproductDeils(String productId) {

        try {

            ResultSet ProdutDeilsRs = MySQL.search("SELECT * FROM    `product` "
                    + "INNER JOIN  `category` ON `product`.`category_id`=`category`.`id` "
                    + "WHERE `product`.`product_name`='" + productId + "'");

            if (ProdutDeilsRs.next()) {

                jLabel10.setText(ProdutDeilsRs.getString("product.id"));
                jLabel4.setText(ProdutDeilsRs.getString("category.category_name"));
            } else {
                jLabel10.setText("");
                jLabel4.setText("");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
// public void loadbrandcombo() {
//
//        try {
//
//            ResultSet rs = MySQL.search("SELECT * FROM `category`");
//            ArrayList<String> cars = new ArrayList<String>();
//            while (rs.next()) {
//
//                cars.add(rs.getString("category_name"));
//
//            }
//
//            JComboBox C = new JComboBox(cars.toArray());
//            jTable3.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(C));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    private void loadGRNItems() {
        double total = 0;
        DecimalFormat df = new DecimalFormat("0.000");
        DecimalFormat pf = new DecimalFormat("0.00");
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        model.setRowCount(0);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        for (grnItem gr : grnItemMap.values()) {
            Vector<String> vector = new Vector<>();

            vector.add(gr.getProductId());
            vector.add(gr.getProductName());
            vector.add(gr.getCategory());
            vector.add(gr.getSize());
            vector.add(gr.getMesureType());
            vector.add(String.valueOf(gr.getMesureValue()));
            vector.add(String.valueOf(pf.format(gr.getBuyingPrice())));
            vector.add(String.valueOf(pf.format(gr.getSellingPrice())));

            if (gr.getMesureType().equals("KG") || gr.getMesureType().equals("G")) {
                double itemTotal = (gr.getBuyingPrice() / Double.valueOf(gr.getSize())) * Double.valueOf(gr.getMesureValue());
                total += itemTotal;
                vector.add(String.valueOf(pf.format(itemTotal)));

            } else {
                double itemTotal = Double.valueOf(gr.getMesureValue()) * gr.getBuyingPrice();
                total += itemTotal;
                vector.add(String.valueOf(itemTotal));

            }

            model.addRow(vector);
        }
//
//        jTable3.setModel(model);
        jLabel12.setText(String.valueOf(pf.format(total)));
    }

    public void grnReset() {
        jLabel10.setText("");
        jLabel3.setText("");

        jLabel4.setText("");
        jTextField1.setText("");
        jComboBox2.setSelectedIndex(-1);
        jComboBox3.setSelectedIndex(0);
        jTextField2.setText("");
        jFormattedTextField3.setText("");
        jFormattedTextField2.setText("");
        jTextField2.setText("");

//        jLabel1.setText("Mobile :");
//        jLabel2.setText("Nic :");
    }

    public void saveReset() {
        uniqeId();
        jLabel10.setText("");
        jLabel3.setText("");

        jLabel4.setText("");
        jTextField1.setText("");
        jComboBox2.setSelectedIndex(-1);
        jTextField2.setText("");
        jFormattedTextField2.setText("");
        jTextField1.setText("");
        jFormattedTextField3.setText("");
        jComboBox1.setSelectedIndex(-1);
        jLabel1.setText("Mobile :");
        jLabel2.setText("Nic :");
        jLabel12.setText("");
        jFormattedTextField4.setText("");
        jLabel11.setText("");
        jCheckBox1.setSelected(false);
        DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
        dtm.setRowCount(0);
        grnItemMap.clear();
    }

//    private void loadSupplierDeils(String text) {
//
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "GRN", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dubai Medium", 1, 18), new java.awt.Color(0, 122, 255)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dubai Medium", 1, 18), new java.awt.Color(0, 122, 255))); // NOI18N
        jPanel1.setToolTipText("");

        jComboBox1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                jComboBox1HierarchyChanged(evt);
            }
        });
        jComboBox1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox1FocusLost(evt);
            }
        });
        jComboBox1.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                jComboBox1AncestorMoved(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });
        jComboBox1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jComboBox1InputMethodTextChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox1PropertyChange(evt);
            }
        });
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jComboBox1KeyTyped(evt);
            }
        });

        jTable3.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Product ID", "Product Name", "Category", "Unit Product", "Mesure Type", "Mesure Value", "Buying Price", "Selling Price", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setSelectionBackground(new java.awt.Color(0, 122, 255));
        jTable3.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable3);

        jButton2.setFont(new java.awt.Font("Dubai Medium", 2, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/add (1)_1.png"))); // NOI18N
        jButton2.setText("Add Product");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Dubai Medium", 2, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/add (1)_1.png"))); // NOI18N
        jButton3.setText("Add Supplier ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dubai Medium", 2, 14)); // NOI18N
        jLabel3.setText("Product : ");
        jLabel3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jLabel3KeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N

        jComboBox2.setFont(new java.awt.Font("Dubai Medium", 2, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Product" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox2MouseClicked(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField2.setSelectedTextColor(new java.awt.Color(251, 255, 255));
        jFormattedTextField2.setSelectionColor(new java.awt.Color(0, 122, 255));
        jFormattedTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField2ActionPerformed(evt);
            }
        });

        jFormattedTextField3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField3.setSelectedTextColor(new java.awt.Color(251, 255, 255));
        jFormattedTextField3.setSelectionColor(new java.awt.Color(0, 122, 255));
        jFormattedTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField3ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dubai Medium", 2, 14)); // NOI18N
        jLabel6.setText("Size");

        jLabel7.setFont(new java.awt.Font("Dubai Medium", 2, 14)); // NOI18N
        jLabel7.setText("Selling Price :");

        jLabel8.setFont(new java.awt.Font("Dubai Medium", 2, 14)); // NOI18N
        jLabel8.setText("Buying Price :");

        jButton4.setFont(new java.awt.Font("Dubai Medium", 2, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/add (1).png"))); // NOI18N
        jButton4.setText("Add Grn");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dubai Medium", 0, 12)); // NOI18N
        jLabel10.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                jLabel10HierarchyChanged(evt);
            }
        });
        jLabel10.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jLabel10AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jLabel10.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabel10PropertyChange(evt);
            }
        });
        jLabel10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jLabel10KeyReleased(evt);
            }
        });

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(38, 44, 244), 3, true));

        jLabel9.setFont(new java.awt.Font("Dubai Medium", 2, 14)); // NOI18N
        jLabel9.setText("Total :");

        jLabel12.setFont(new java.awt.Font("Dubai Medium", 2, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Dubai Medium", 2, 14)); // NOI18N
        jLabel13.setText("Amount :");

        jFormattedTextField4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextField4KeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Dubai Medium", 2, 14)); // NOI18N
        jLabel14.setText("Balence : ");

        jCheckBox1.setFont(new java.awt.Font("Dubai Medium", 2, 14)); // NOI18N
        jCheckBox1.setText("Bill Print");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jButton6.setText("jButton6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/food/icon/add (1)_1.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6)
                            .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(13, 13, 13)))
                .addContainerGap())
        );

        jTextField1.setSelectedTextColor(new java.awt.Color(251, 255, 255));
        jTextField1.setSelectionColor(new java.awt.Color(0, 122, 255));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setSelectedTextColor(new java.awt.Color(251, 255, 255));
        jTextField2.setSelectionColor(new java.awt.Color(0, 122, 255));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        jLabel16.setText("Mobile :");

        jLabel17.setText("Nic :");

        jLabel18.setFont(new java.awt.Font("Dubai Medium", 2, 14)); // NOI18N
        jLabel18.setText("Category :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFormattedTextField3))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(30, 30, 30)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(11, 11, 11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jFormattedTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jFormattedTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)))
                .addGap(15, 15, 15)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {

            String grnNumber = jLabel5.getText();

            String employeeEmail = jLabel3.getText();
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            DecimalFormat df = new DecimalFormat("0.000");
            String paidAmount = jFormattedTextField4.getText();
            String total = jLabel12.getText();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if (paidAmount.isBlank()) {
                JOptionPane.showMessageDialog(this, "Please Enater the paid amount ....", "Warning", JOptionPane.QUESTION_MESSAGE);
            } else {
                ResultSet supplierRs = MySQL.search("SELECT * FROM supplier WHERE full_name='" + jComboBox1.getSelectedItem().toString() + "'");
                supplierRs.next();

                MySQL.iud("INSERT INTO `grn` VALUES('" + grnNumber + "','" + date + "','" + Double.valueOf(paidAmount) + "','" + Double.valueOf(total) + "',"
                        + "'" + supplierRs.getString("id") + "','" + SignIn.ID + "')");

                for (grnItem gr : grnItemMap.values()) {

                    ResultSet resultSet = MySQL.search("SELECT * FROM `stock` INNER JOIN  "
                            + "`mesurse_type` ON `stock`.`mesurse_type_id`=`mesurse_type`.`id` WHERE "
                            + "`stock`.`product_id`= '" + gr.getProductId() + "' AND "
                            + "`mesurse_type`.`mesurse_type`='" + gr.getMesureType() + "' AND"
                            + "`buying_price`='" + gr.getBuyingPrice() + "' AND"
                            + "`selling_price` = '" + gr.getSellingPrice() + "'");

                    String sid = "";
                    String mid = "";

                    if (resultSet.next()) {
                        //existing stock

                        sid = resultSet.getString("stock.id");

                        if (gr.getMesureType().equals("KG") || gr.getMesureType().equals("G")) {
                            String currentQty = resultSet.getString("stock.size_of_product");

                            Double updatedQuantity = Double.parseDouble(currentQty) + Double.parseDouble(gr.getMesureValue());
                            String upqty = df.format(updatedQuantity);
                            System.out.println("upqty"+upqty);
                            MySQL.iud("UPDATE `stock` SET `size_of_product` = '" + updatedQuantity + "' WHERE `id` = '" + sid + "'");

                            System.out.println("updatedQuantity KG G:::::" + updatedQuantity);
                        } else {
                            String currentQty = resultSet.getString("stock.size_of_product");

                            String updatedQuantity = String.valueOf(Integer.parseInt(currentQty) + Integer.parseInt(gr.getMesureValue()));

                            MySQL.iud("UPDATE `stock` SET `size_of_product` = '" + updatedQuantity + "' WHERE `id` = '" + sid + "'");

                        }
                    } else {
                        ResultSet mesureRs = MySQL.search("SELECT * FROM  `mesurse_type`  WHERE `mesurse_type`='" + gr.getMesureType() + "'");
                        if (mesureRs.next()) {
                            mid = mesureRs.getString("id");
                        }
                        MySQL.iud("INSERT INTO `stock`(`product_id`,`unit_per_product`,`mesurse_type_id`,`size_of_product`,`buying_price`,`selling_price`) "
                                + "VALUES('" + gr.getProductId() + "','" + gr.getSize() + "','" + mid + "','" + gr.getMesureValue() + "','" + gr.getBuyingPrice() + "', "
                                + "'" + gr.getSellingPrice() + "')");

                        ResultSet resultSet2 = MySQL.search("SELECT * FROM `stock`  WHERE "
                                + "`stock`.`product_id`= '" + gr.getProductId() + "' AND "
                                + "`stock`.`unit_per_product`= '" + gr.getSize() + "' AND "
                                + "`stock`.`size_of_product`= '" + gr.getMesureValue() + "' AND "
                                + "`stock`.`mesurse_type_id`='" + mid + "' AND"
                                + "`stock`.`buying_price`='" + gr.getBuyingPrice() + "' AND"
                                + "`stock`.`selling_price` = '" + gr.getSellingPrice() + "'");

                        if (resultSet2.next()) {
                            sid = resultSet2.getString("id");

                        }

                    }
                    ResultSet mesureRs = MySQL.search("SELECT * FROM  `mesurse_type`  WHERE `mesurse_type`='" + gr.getMesureType() + "'");
                    if (mesureRs.next()) {
                        mid = mesureRs.getString("id");
                        System.out.println("mid" + mid);
                    }
                    System.out.println("stockId" + sid);
                    MySQL.iud("INSERT INTO `grn_item`(`unit_per_product`,`mesurse_type_id`,`size_of_product`,`stock_id`,`grn_id`) "
                            + "VALUES('" + gr.getSize() + "','" + mid + "','" + gr.getMesureValue() + "','" + sid + "','" + grnNumber + "')");
                }
                JOptionPane.showMessageDialog(this, "Grn Added ....", "Sucsess", JOptionPane.INFORMATION_MESSAGE);
                if (jCheckBox1.isSelected()) {
                    HashMap<String, Object> parametes = new HashMap<>();
                    //            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                    parametes.put("Parameter7", date);
                    parametes.put("Parameter3", jComboBox1.getSelectedItem().toString());
                    parametes.put("Parameter2", jLabel5.getText().trim());
                    parametes.put("Parameter1", jLabel12.getText().trim());
                    parametes.put("Parameter4", jFormattedTextField4.getText().trim());
                    parametes.put("Parameter5", jLabel11.getText().trim());

                    try {
                        JRTableModelDataSource datasourse = new JRTableModelDataSource(jTable3.getModel());
                        //            JasperViewer.viewReport(JasperFillManager.fillReport("src/com/food/report/shopgrn.jasper", paramerters, datasourse));
                        JasperViewer.viewReport(JasperFillManager.fillReport("src/com/food/report/shopgrn.jasper", parametes, datasourse));
                        grnReset();
                        saveReset();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                grnReset();
                saveReset();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        HashMap<String, Object> parametes = new HashMap<>();
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        parametes.put("Parameter7", date);
        parametes.put("Parameter3", jComboBox1.getSelectedItem().toString());
        parametes.put("Parameter2", jLabel5.getText().trim());
        parametes.put("Parameter1", jLabel12.getText().trim());
        parametes.put("Parameter4", jFormattedTextField4.getText().trim());
        parametes.put("Parameter5", jLabel11.getText().trim());

        try {
            JRTableModelDataSource datasourse = new JRTableModelDataSource(jTable3.getModel());
            //            JasperViewer.viewReport(JasperFillManager.fillReport("src/com/food/report/shopgrn.jasper", paramerters, datasourse));
            JasperViewer.viewReport(JasperFillManager.fillReport("src/com/food/report/shopgrn.jasper", parametes, datasourse));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jFormattedTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField4KeyReleased
DecimalFormat df = new DecimalFormat("0.00");
        String payment = jFormattedTextField4.getText();

        if (payment.isEmpty()) {
            payment = "0";
        } else if (!payment.matches("^(0|[1-9]\\d*)?(\\.\\d+)?(?<=\\d)$")) {

            jLabel11.setText("INVALID");
            jLabel11.setForeground(Color.RED);
        } else {
            String total = jLabel12.getText();

            // Split the sentence based on space
            double balance = Double.parseDouble(total) - Double.parseDouble(payment);
            jLabel11.setText(String.valueOf(df.format(balance)));
            loadGRNItems();
        }
        if (jFormattedTextField4.getText().isBlank()) {
            jFormattedTextField4.setText("");
        }
    }//GEN-LAST:event_jFormattedTextField4KeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
//        String size = jFormattedTextField5.getText();
        String size = jTextField1.getText();
        String mesusureType = jComboBox2.getSelectedItem().toString();
//        String MesureValue = jFormattedTextField1.getText();
        String MesureValue = jTextField2.getText();
        String buying_price = jFormattedTextField3.getText();
        String selling_price = jFormattedTextField2.getText();

        String pid = jLabel10.getText();
        String pname = jComboBox3.getSelectedItem().toString();
        String cname = jLabel4.getText();

        if (jLabel10.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Please Select Product ....", "Warning", JOptionPane.QUESTION_MESSAGE);
        } else if (jComboBox1.getSelectedItem().toString().equals("Select Supplier")) {
            JOptionPane.showMessageDialog(this, "Please Select Supplier ....", "Warning", JOptionPane.QUESTION_MESSAGE);
        } else if (jComboBox2.getSelectedItem().toString().equals("Type")) {

            JOptionPane.showMessageDialog(this, "Please Enater the type ....", "Warning", JOptionPane.QUESTION_MESSAGE);
        } else if (jComboBox3.getSelectedItem().toString().equals("Select Product")) {

            JOptionPane.showMessageDialog(this, "Please Select Product ....", "Warning", JOptionPane.QUESTION_MESSAGE);

        } else if (mesusureType.isBlank()) {

            JOptionPane.showMessageDialog(this, "Please Enater Mesure Typee ....", "Warning", JOptionPane.QUESTION_MESSAGE);

        } else if (size.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please Enter Size", "Warning", JOptionPane.QUESTION_MESSAGE);
        } else if (!Pattern.compile("\\d+(\\.\\d+)?").matcher(size).matches()) {
            JOptionPane.showMessageDialog(this, "Please Enter The Valid Size", "warning", JOptionPane.QUESTION_MESSAGE);

        } else if (MesureValue.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please Enter Size Of Product", "Warning", JOptionPane.QUESTION_MESSAGE);
        } else if (!Pattern.compile("\\d+(\\.\\d+)?").matcher(MesureValue).matches()) {
            JOptionPane.showMessageDialog(this, "Please Enter The Valid Size Of Product", "warning", JOptionPane.QUESTION_MESSAGE);

        } else if (jFormattedTextField2.getText().isBlank()) {

            JOptionPane.showMessageDialog(this, "Please Enater Buying Price ....", "Warning", JOptionPane.QUESTION_MESSAGE);
        } else if (jFormattedTextField3.getText().isBlank()) {

            JOptionPane.showMessageDialog(this, "Please Enater Selling Price ....", "Warning", JOptionPane.QUESTION_MESSAGE);

        } else if (Double.valueOf(buying_price) > Double.valueOf(selling_price)) {

            JOptionPane.showMessageDialog(this, "Please Enater valid selling price and buying price ....", "Warning", JOptionPane.QUESTION_MESSAGE);

        } else {

            try {
                ResultSet cheakRs = MySQL.search("SELECT * FROM `supplier` INNER JOIN "
                        + "`supplier_has_type` ON `supplier`.`id`=`supplier_has_type`.`supplier_id` "
                        + "INNER JOIN `product` ON `supplier_has_type`.`product_id`=`product`.`id` "
                        + "WHERE `supplier`.`full_name`='" + jComboBox1.getSelectedItem().toString() + "' AND  "
                        + "`product`.`id`='" + pid + "'");

                if (cheakRs.next()) {
                    grnItem gr = new grnItem();

                    gr.setProductId(pid);
                    gr.setProductName(pname);
                    gr.setCategory(cname);

                    gr.setSize(size);
                    gr.setMesureType(mesusureType);
                    gr.setMesureValue(MesureValue);
                    gr.setBuyingPrice(Double.valueOf(buying_price));
                    gr.setSellingPrice(Double.valueOf(selling_price));

                    if (grnItemMap.get(pid) == null) {
                        grnItemMap.put(pid, gr);
                        grnReset();
                        loadGRNItems();

                    } else {

                        grnItem found = grnItemMap.get(pid);

                        if (found.getSize().equals(size)
                                && found.getBuyingPrice() == Double.valueOf(buying_price)
                                && found.getSellingPrice() == Double.valueOf(selling_price)
                                && found.getMesureType().equals(mesusureType)) {

                            found.setMesureValue(String.valueOf(Double.valueOf(found.getMesureValue()) + Double.valueOf(MesureValue)));

                            grnReset();
                            loadGRNItems();
                        } else {
                            System.out.println(found.getSize());
                            System.out.println(found.getBuyingPrice());
                            System.out.println(found.getSellingPrice());
                            System.out.println(found.getMesureType());

                            System.out.println(size);
                            System.out.println(buying_price);
                            System.out.println(selling_price);
                            System.out.println(mesusureType);

                            System.out.println(found.getBuyingPrice() == Double.valueOf(buying_price));
                            System.out.println(found.getSellingPrice() == Double.valueOf(selling_price));
                            System.out.println(found.getMesureType() == mesusureType);
                            JOptionPane.showMessageDialog(this, "GRN item already exists with diffrent dates and prices", "Error", JOptionPane.ERROR_MESSAGE);

                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please Cheack Supplier and Product Conectivity ....", "Warning", JOptionPane.QUESTION_MESSAGE);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jFormattedTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField3ActionPerformed
        jFormattedTextField2.grabFocus();
    }//GEN-LAST:event_jFormattedTextField3ActionPerformed

    private void jFormattedTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField2ActionPerformed
        jButton4.grabFocus();
    }//GEN-LAST:event_jFormattedTextField2ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        jTextField2.grabFocus();
        //        jFormattedTextField5.requestFocusInWindow();
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox2MouseClicked

    }//GEN-LAST:event_jComboBox2MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        AddFood af = new AddFood();
        af.setVisible(true);
        af.setGrn(this);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AddStock ad = new AddStock();
        ad.setVisible(true);
        ad.setProduct(this);

        //        String nic = jTextField2.getText();
        //        String fullname = jTextField3.getText();
        //        String address = jTextField4.getText();
        //        String mobile = jTextField5.getText();
        //        String age = jTextField6.getText();
        //        String username = jTextField7.getText();
        //        String password = String.valueOf(jPasswordField1.getPassword());
        //        String cashiertype = String.valueOf(jComboBox2.getSelectedItem());
        //
        //        if (nic.isBlank() || nic.equals("Nic  :")) {
        //
        //            JOptionPane.showMessageDialog(this, "Nic Number is required...", "Warning", JOptionPane.QUESTION_MESSAGE);
        //            jTextField2.setBorder(new LineBorder(Color.red, 1));
        //
        //        } else if (fullname.isBlank() || fullname.equals("Full Name  :")) {
        //
        //            jTextField2.setBorder(BorderFactory.createEmptyBorder());
        //            JOptionPane.showMessageDialog(this, "Full Name is required..", "Warning", JOptionPane.QUESTION_MESSAGE);
        //            jTextField3.setBorder(new LineBorder(Color.red, 1));
        //
        //        } else if (address.isBlank() || address.equals("Address  :")) {
        //
        //            jTextField3.setBorder(BorderFactory.createEmptyBorder());
        //            JOptionPane.showMessageDialog(this, "Address is required...", "Warning", JOptionPane.QUESTION_MESSAGE);
        //            jTextField4.setBorder(new LineBorder(Color.red, 1));
        //
        //        } else if (mobile.isBlank() || mobile.equals("Mobile  :")) {
        //
        //            jTextField4.setBorder(BorderFactory.createEmptyBorder());
        //            JOptionPane.showMessageDialog(this, "Mobile is required...", "Warning", JOptionPane.QUESTION_MESSAGE);
        //            jTextField5.setBorder(new LineBorder(Color.red, 1));
        //
        //        } else if (!Pattern.compile("^(0|[+]94)(11|3(1|3)|7(1|2|3|4|5|6|7|8))([0-9]{7})$").matcher(mobile).matches()) {
        //
        //            jTextField5.setBorder(BorderFactory.createEmptyBorder());
        //            JOptionPane.showMessageDialog(this, "Please Enter The Valid Mobile Number", "warning", JOptionPane.QUESTION_MESSAGE);
        //            jTextField5.setBorder(new LineBorder(Color.red, 1));
        //
        //        } else if (age.isBlank() || age.equals("Age :")) {
        //
        //            jTextField5.setBorder(BorderFactory.createEmptyBorder());
        //            JOptionPane.showMessageDialog(this, "Age is required...", "Warning", JOptionPane.QUESTION_MESSAGE);
        //            jTextField6.setBorder(new LineBorder(Color.red, 1));
        //
        //        } else if (username.isBlank() || username.equals("User Name  :")) {
        //
        //            jTextField6.setBorder(BorderFactory.createEmptyBorder());
        //            JOptionPane.showMessageDialog(this, "Username is required...", "Warning", JOptionPane.QUESTION_MESSAGE);
        //            jTextField7.setBorder(new LineBorder(Color.red, 1));
        //
        //        } else if (password.isBlank() || password.equals("Mobile  :")) {
        //
        //            jTextField7.setBorder(BorderFactory.createEmptyBorder());
        //            JOptionPane.showMessageDialog(this, "Passworld is required...", "Warning", JOptionPane.QUESTION_MESSAGE);
        //            jPasswordField1.setBorder(new LineBorder(Color.red, 1));
        //
        //        } else if (cashiertype.equals("Select Cashier Type")) {
        //
        //            jPasswordField1.setBorder(BorderFactory.createEmptyBorder());
        //            JOptionPane.showMessageDialog(this, "Select Cashier Your Type..", "Warning", JOptionPane.QUESTION_MESSAGE);
        //
        //        } else if (!jRadioButton1.isSelected() & !jRadioButton2.isSelected()) {
        //            JOptionPane.showMessageDialog(this, "Select a gender", "Warning", JOptionPane.WARNING_MESSAGE);
        //
        //        } else {
        //
        //            int genderButton;
        //
        //            if (jRadioButton1.isSelected()) {
        //                genderButton = 1;
        //            } else {
        //                genderButton = 2;
        //            }
        //
        //            int countryId = cashiertyperymap.get(cashiertype);
        //            try {
        //                ResultSet cashierRs = MySQL.search("SELECT * FROM `cashier` WHERE `nic`='" + nic + "' OR `Mobile`='" + mobile + "' OR `passworld`='" + password + "'");
        //
        //                boolean canUpdate = false;
        //
        //                if (cashierRs.next()) {
        //                    JOptionPane.showMessageDialog(this, "Cashier is Already Exist...", "Warning", JOptionPane.QUESTION_MESSAGE);
        //
        //                } else {
        //                    canUpdate = true;
        //                }
        //
        //                if (canUpdate) {
        //                    MySQL.iud("INSERT INTO "
        //                        + "`cashier` (`nic`,`full name`,`Address`,`Mobile`,`age`,"
        //                        + "`username`,`passworld`,`cashiertype_id`,`gender_id`,`Status_id`)"
        //                        + "VALUES('" + nic + "','" + fullname + "','" + address + "'"
        //                        + ",'" + mobile + "','" + age + "','" + username + "','" + password + "',"
        //                        + "'" + cashiertyperymap.get(cashiertype) + "','" + genderButton + "','1')");
        //
        //                    JOptionPane.showMessageDialog(this, "Cashier Registration Successfull!", "Sucsess", JOptionPane.INFORMATION_MESSAGE);
        //                    grnReset
        //                    loadCashier("");
        //                }
        //
        //            } catch (Exception e) {
        //
        //                e.printStackTrace();
        //
        //            }
        //            //
        //        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        int sr = jTable3.getSelectedRow();
        if (sr == -1) {

            JOptionPane.showMessageDialog(this, "please select a Row.", "Warning", JOptionPane.WARNING_MESSAGE);

        } else {
            if (evt.getClickCount() == 2) {
                System.out.println(jTable3.getValueAt(sr, 0).toString());
                grnItemMap.remove(jTable3.getValueAt(sr, 0).toString());
                loadGRNItems();
            }
        }
    }//GEN-LAST:event_jTable3MouseClicked

    private void jComboBox1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyTyped

    }//GEN-LAST:event_jComboBox1KeyTyped

    private void jComboBox1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyReleased

    }//GEN-LAST:event_jComboBox1KeyReleased

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed

    }//GEN-LAST:event_jComboBox1KeyPressed

    private void jComboBox1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox1PropertyChange
        //        loadSupplierDeils(jComboBox1.getSelectedItem().toString());
    }//GEN-LAST:event_jComboBox1PropertyChange

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jComboBox1InputMethodTextChanged

    }//GEN-LAST:event_jComboBox1InputMethodTextChanged

    private void jComboBox1AncestorMoved(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jComboBox1AncestorMoved

    }//GEN-LAST:event_jComboBox1AncestorMoved

    private void jComboBox1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1FocusLost

    private void jComboBox1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox1FocusGained

    }//GEN-LAST:event_jComboBox1FocusGained

    private void jComboBox1HierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jComboBox1HierarchyChanged

    }//GEN-LAST:event_jComboBox1HierarchyChanged

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        //  if (!jComboBox1.getSelectedItem().toString().equals("null")) {
        //            System.out.println("m11111" + jComboBox1.getSelectedItem().toString());
        //        }
        if (evt.getStateChange() == 1) {

            if (!jComboBox1.getSelectedItem().toString().equals("Select Supplier")) {

                loadSupplierDeils(suppliermap.get(jComboBox1.getSelectedItem().toString()));
                loadProductCombo(suppliermap.get(jComboBox1.getSelectedItem().toString()));
                System.out.println(jComboBox1.getSelectedItem().toString());

            } else if (jComboBox1.getSelectedItem().toString().equals("Select Supplier")) {

                jLabel1.setText("");
                jLabel2.setText("");
                jLabel4.setText("");
                jLabel10.setText("");
                loadProductCombo();

            }

        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jLabel10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel10KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel10KeyReleased

    private void jLabel10PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabel10PropertyChange

        if (!jLabel10.getText().isBlank()) {
            //             System.out.println(jLabel10.getText());

            String s = jLabel10.getText();

            // Split the sentence based on space
            String[] words = s.split(" ");

            loadMesureCo(jLabel10.getText());
            System.out.println("ddddddddddddddddd" + s);
        }
    }//GEN-LAST:event_jLabel10PropertyChange

    private void jLabel10AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel10AncestorAdded

    }//GEN-LAST:event_jLabel10AncestorAdded

    private void jLabel10HierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jLabel10HierarchyChanged

    }//GEN-LAST:event_jLabel10HierarchyChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged

    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        jComboBox2.grabFocus();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        jFormattedTextField3.grabFocus();
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        if (evt.getStateChange() == 1) {

            if (!jComboBox3.getSelectedItem().toString().equals("Select Product")) {

                loadproductDeils(jComboBox3.getSelectedItem().toString());
                System.out.println("repat" + productmMap.get(jComboBox3.getSelectedItem().toString()));
            } else if (jComboBox3.getSelectedItem().toString().equals("Select Product")) {

                jLabel10.setText("");
                jLabel4.setText("");
            }

        }
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jLabel3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel3KeyReleased

    }//GEN-LAST:event_jLabel3KeyReleased

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
            java.util.logging.Logger.getLogger(AddGrn.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddGrn.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddGrn.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddGrn.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddGrn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

}
