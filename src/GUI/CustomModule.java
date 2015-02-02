/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CustomModule.java
 *
 * Created on Nov 4, 2009, 4:30:01 PM
 */

package GUI;

import Access.CustomerAccess;
import Business.CustomerManage;
import Data.msbsCustomer;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Administrator
 */
public class CustomModule extends javax.swing.JPanel {

    /** Creates new form CustomModule */
    public CustomModule() throws UnsupportedLookAndFeelException {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        tableColumn = new TableColumn();
        custMn  = new CustomerManage();
        customer = new msbsCustomer();
        allCustomer = new Vector<msbsCustomer>();
        modelCustomerList = new DefaultTableModel();
        modelDealerList = new DefaultTableModel();
        modelDistributorList = new DefaultTableModel();
        modelRetailerList = new DefaultTableModel();
        initComponents();
        getAllCustomer("");
    }

    // This function is fill all Customer from Database to 4 table: Customer List, Dealer List,Retailer List,Distributor List
    public void getAllCustomer(String filerName){
        allCustomer = new Vector<msbsCustomer>();
        modelCustomerList = new DefaultTableModel();
        modelDealerList = new DefaultTableModel();
        modelDistributorList = new DefaultTableModel();
        modelRetailerList = new DefaultTableModel();
        custCount = 0;
        dealerCount = 0;
        retailerCount = 0;
        distributorCount = 0;
        // return all Customer from Database
        allCustomer = custMn.getAllCustomer();
        // Create Column for  tables: Customer List
        modelCustomerList.addColumn("custCode");
        modelCustomerList.addColumn("Name ");
        modelCustomerList.addColumn("Type");
        modelCustomerList.addColumn("Phone");
        modelCustomerList.addColumn("Fax");
        modelCustomerList.addColumn("Email");
        modelCustomerList.addColumn("Address");
        modelCustomerList.addColumn("Priority");
        // Create Column for  tables: Dealer List
        modelDealerList.addColumn("custCode");
        modelDealerList.addColumn("Name ");
        modelDealerList.addColumn("Type");
        modelDealerList.addColumn("Phone");
        modelDealerList.addColumn("Fax");
        modelDealerList.addColumn("Email");
        modelDealerList.addColumn("Address");
        modelDealerList.addColumn("Priority");
        // Create Column for  tables: Retailer List
        modelRetailerList.addColumn("custCode");
        modelRetailerList.addColumn("Name ");
        modelRetailerList.addColumn("Type");
        modelRetailerList.addColumn("Phone");
        modelRetailerList.addColumn("Fax");
        modelRetailerList.addColumn("Email");
        modelRetailerList.addColumn("Address");
        modelRetailerList.addColumn("Priority");
        // Create Column for  tables: Distributor List
        modelDistributorList.addColumn("custCode");
        modelDistributorList.addColumn("Name ");
        modelDistributorList.addColumn("Type");
        modelDistributorList.addColumn("Phone");
        modelDistributorList.addColumn("Fax");
        modelDistributorList.addColumn("Email");
        modelDistributorList.addColumn("Address");
        modelDistributorList.addColumn("Priority");


        for(int i = 0; i < allCustomer.size(); i++){
            customer = (msbsCustomer)allCustomer.get(i);
            // Filter all customer that are Customer
            if (customer.getCustomerType().equals("Customer")&&customer.getCustomerName().toLowerCase().matches(".*"+filerName+".*")) {
                Vector newData = new Vector();
                newData.addElement(customer.getCustomerCode());
                newData.addElement(customer.getCustomerName());
                newData.addElement(customer.getCustomerType());
                newData.addElement(customer.getCustomerPhone());
                newData.addElement(customer.getCustomerFax());
                newData.addElement(customer.getCustomerEmail());
                newData.addElement(customer.getCustomerAddress());
                newData.addElement(customer.getCustomerPriority());
                modelCustomerList.addRow(newData);
                custCount++;
            }
            // Filter all customer that are Dealer
            if (customer.getCustomerType().equals("Dealer")&&customer.getCustomerName().toLowerCase().matches(".*"+filerName+".*")) {
                Vector newData = new Vector();
                newData.addElement(customer.getCustomerCode());
                newData.addElement(customer.getCustomerName());
                newData.addElement(customer.getCustomerType());
                newData.addElement(customer.getCustomerPhone());
                newData.addElement(customer.getCustomerFax());
                newData.addElement(customer.getCustomerEmail());
                newData.addElement(customer.getCustomerAddress());
                newData.addElement(customer.getCustomerPriority());
                modelDealerList.addRow(newData);
                dealerCount++;
            }
            // Filter all customer that are Retailer
            if (customer.getCustomerType().equals("Retailer")&&customer.getCustomerName().toLowerCase().matches(".*"+filerName+".*")) {
                Vector newData = new Vector();
                newData.addElement(customer.getCustomerCode());
                newData.addElement(customer.getCustomerName());
                newData.addElement(customer.getCustomerType());
                newData.addElement(customer.getCustomerPhone());
                newData.addElement(customer.getCustomerFax());
                newData.addElement(customer.getCustomerEmail());
                newData.addElement(customer.getCustomerAddress());
                newData.addElement(customer.getCustomerPriority());
                modelRetailerList.addRow(newData);
                retailerCount++;
            }
            // Filter all customer that are Distributor
            if (customer.getCustomerType().equals("Distributor")&&customer.getCustomerName().toLowerCase().matches(".*"+filerName+".*")) {
                Vector newData = new Vector();
                newData.addElement(customer.getCustomerCode());
                newData.addElement(customer.getCustomerName());
                newData.addElement(customer.getCustomerType());
                newData.addElement(customer.getCustomerPhone());
                newData.addElement(customer.getCustomerFax());
                newData.addElement(customer.getCustomerEmail());
                newData.addElement(customer.getCustomerAddress());
                newData.addElement(customer.getCustomerPriority());
                modelDistributorList.addRow(newData);
                distributorCount++;
            }

        }
        //Set model for table Customer List:
        tblCustomerList.setModel(modelCustomerList);
        tblCustomerList.removeEditor();
        tableColumn = tblCustomerList.getColumnModel().getColumn(0);
        tableColumn.setPreferredWidth(0);
        tableColumn.setMinWidth(0);
        tableColumn.setMaxWidth(0);
        lblAmountCust.setText(String.valueOf(custCount));
        //Set model for table Dealer List:
        tblDealerList.setModel(modelDealerList);
        tblDealerList.removeEditor();
        tableColumn = tblDealerList.getColumnModel().getColumn(0);
        tableColumn.setPreferredWidth(0);
        tableColumn.setMinWidth(0);
        tableColumn.setMaxWidth(0);
        lblAmountDealer.setText(String.valueOf(dealerCount));
        //Set model for table Retailer List:
        tblRetailerList.setModel(modelRetailerList);
        tblRetailerList.removeEditor();
        tableColumn = tblRetailerList.getColumnModel().getColumn(0);
        tableColumn.setPreferredWidth(0);
        tableColumn.setMinWidth(0);
        tableColumn.setMaxWidth(0);
        lblAmountRetailer.setText(String.valueOf(retailerCount));
        
        
    }

    


    public msbsCustomer getCodeSelectedRow(JTable table){
        String custCode = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
        customer = custMn.getCustomerByCode(custCode);
        if(customer != null){
            return customer;
        }
        return null ;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtSeachCustomer = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomerList = new javax.swing.JTable();
        btnDeleteCustomer = new javax.swing.JButton();
        btnEditCustomer = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblAmountCust = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnAddDealer = new javax.swing.JButton();
        btnEditDealer = new javax.swing.JButton();
        btnDeleteDealer = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDealerList = new javax.swing.JTable();
        txtSeachDealer = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblAmountDealer = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnAddRetailer = new javax.swing.JButton();
        btnEditRetailer = new javax.swing.JButton();
        btnDeleteRetailer = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblRetailerList = new javax.swing.JTable();
        txtSeachRetailer = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblAmountRetailer = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(814, 445));

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(800, 400));

        jLabel2.setText("Filter Name:");

        txtSeachCustomer.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSeachCustomerCaretUpdate(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Customers List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 153))); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(788, 290));

        tblCustomerList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Type", "Phone", "Fax", "Email", "Address", "Relationship"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblCustomerList.setToolTipText("Double click to view all Order that was sale by this customer");
        tblCustomerList.getTableHeader().setReorderingAllowed(false);
        tblCustomerList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblCustomerListMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblCustomerList);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
        );

        btnDeleteCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Delete.png"))); // NOI18N
        btnDeleteCustomer.setText("Delete");
        btnDeleteCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCustomerActionPerformed(evt);
            }
        });

        btnEditCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Update.png"))); // NOI18N
        btnEditCustomer.setText("View Detail");
        btnEditCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCustomerActionPerformed(evt);
            }
        });

        jLabel1.setText("Total Customer :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)
                        .addGap(6, 6, 6)
                        .addComponent(lblAmountCust)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 495, Short.MAX_VALUE)
                        .addComponent(btnEditCustomer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtSeachCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSeachCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblAmountCust))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel1)))
                        .addContainerGap(70, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDeleteCustomer)
                            .addComponent(btnEditCustomer))
                        .addContainerGap())))
        );

        jTabbedPane1.addTab("Customer", new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Customer_Icon.png")), jPanel2); // NOI18N

        btnAddDealer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Add.png"))); // NOI18N
        btnAddDealer.setText("Add");
        btnAddDealer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDealerActionPerformed(evt);
            }
        });

        btnEditDealer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Update.png"))); // NOI18N
        btnEditDealer.setText("View Detail");
        btnEditDealer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditDealerActionPerformed(evt);
            }
        });

        btnDeleteDealer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Delete.png"))); // NOI18N
        btnDeleteDealer.setText("Delete");
        btnDeleteDealer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteDealerActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Dealers List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 153))); // NOI18N
        jPanel7.setPreferredSize(new java.awt.Dimension(788, 290));

        tblDealerList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Type", "Phone", "Fax", "Email", "Address", "Relationship"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDealerList.setToolTipText("Double click to view all Order that was sale by this Dealer");
        tblDealerList.getTableHeader().setReorderingAllowed(false);
        tblDealerList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDealerListMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblDealerList);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
        );

        txtSeachDealer.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSeachDealerCaretUpdate(evt);
            }
        });

        jLabel3.setText("Filter Name:");

        jLabel6.setText("Total Dealer :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel6)
                        .addGap(6, 6, 6)
                        .addComponent(lblAmountDealer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 429, Short.MAX_VALUE)
                        .addComponent(btnAddDealer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditDealer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteDealer, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtSeachDealer, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSeachDealer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(1, 1, 1)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblAmountDealer))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel6)))
                        .addContainerGap(68, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDeleteDealer)
                            .addComponent(btnEditDealer)
                            .addComponent(btnAddDealer))
                        .addContainerGap())))
        );

        jTabbedPane1.addTab("Dealer", new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/delaer.png")), jPanel3); // NOI18N

        btnAddRetailer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Add.png"))); // NOI18N
        btnAddRetailer.setText("Add");
        btnAddRetailer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRetailerActionPerformed(evt);
            }
        });

        btnEditRetailer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Update.png"))); // NOI18N
        btnEditRetailer.setText("View Detail");
        btnEditRetailer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditRetailerActionPerformed(evt);
            }
        });

        btnDeleteRetailer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Delete.png"))); // NOI18N
        btnDeleteRetailer.setText("Delete");
        btnDeleteRetailer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRetailerActionPerformed(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Retailers List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 153))); // NOI18N
        jPanel8.setPreferredSize(new java.awt.Dimension(788, 290));

        tblRetailerList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Type", "Phone", "Fax", "Email", "Address", "Relationship"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblRetailerList.setToolTipText("Double click to view all Order that was sale by this Retailer");
        tblRetailerList.getTableHeader().setReorderingAllowed(false);
        tblRetailerList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblRetailerListMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblRetailerList);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
        );

        txtSeachRetailer.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSeachRetailerCaretUpdate(evt);
            }
        });

        jLabel4.setText("Filter Name: ");

        jLabel7.setText("Total Retailer: ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel7)
                        .addGap(6, 6, 6)
                        .addComponent(lblAmountRetailer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 423, Short.MAX_VALUE)
                        .addComponent(btnAddRetailer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditRetailer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteRetailer, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtSeachRetailer, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSeachRetailer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblAmountRetailer))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel7)))
                        .addContainerGap(68, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDeleteRetailer)
                            .addComponent(btnEditRetailer)
                            .addComponent(btnAddRetailer))
                        .addContainerGap())))
        );

        jTabbedPane1.addTab("Retailer", new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/retailerIcon.png")), jPanel5); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCustomerActionPerformed
        // TODO add your handling code here:
        if(tblCustomerList.getSelectedRowCount()==1){
            UpdateCustomForm updateCust = new UpdateCustomForm(frmParent, true, getCodeSelectedRow(tblCustomerList));
            updateCust.setLocationRelativeTo(this);
            updateCust.setVisible(true);
            getAllCustomer("");
        }else if(tblCustomerList.getSelectedRowCount()>1){
            JOptionPane.showMessageDialog(this, "Do not select more one Customer to Edit Profile", "WARNING", JOptionPane.WARNING_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Please Select a Customer to Edit Profile", "WARNING", JOptionPane.WARNING_MESSAGE);
        }

}//GEN-LAST:event_btnEditCustomerActionPerformed

    private void btnDeleteCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCustomerActionPerformed
        // TODO add your handling code here:
        
        msbsCustomer tempCust = null;
        if(tblCustomerList.getSelectedRowCount()==1){
            tempCust = getCodeSelectedRow(tblCustomerList);
            int result;
            if(tempCust != null){
                result = JOptionPane.showConfirmDialog(frmParent, "Are you sure delete this Customer ? ");
                if(result == JOptionPane.YES_OPTION){
                    if(custMn.deleteCustomer(tempCust.getCustomerCode())){
                        JOptionPane.showMessageDialog(frmParent, "Delete successfull! ");
                    }else{
                        JOptionPane.showMessageDialog(this, "This customer can not delete", "ERROR WHEN DELETE", JOptionPane.ERROR_MESSAGE);
                    }
                    getAllCustomer("");
                }else if(result == JOptionPane.NO_OPTION){
                    getAllCustomer("");
                }
            }
        }else if(tblCustomerList.getSelectedRowCount()>1){
            JOptionPane.showMessageDialog(this, "Do not select more one Customer to Delete", "WARNING", JOptionPane.WARNING_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Please Select a Customer to Delete", "WARNING", JOptionPane.WARNING_MESSAGE);
        }
        
}//GEN-LAST:event_btnDeleteCustomerActionPerformed

    private void tblCustomerListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerListMousePressed
        // TODO add your handling code here:
        tblCustomerList.removeEditor();
        if(evt.getClickCount()==2){
            UpdateCustomForm updateCust = new UpdateCustomForm(frmParent, true, getCodeSelectedRow(tblCustomerList));
            updateCust.setLocationRelativeTo(this);
            updateCust.setVisible(true);
            getAllCustomer("");
        }
}//GEN-LAST:event_tblCustomerListMousePressed

    private void btnAddDealerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDealerActionPerformed
        // TODO add your handling code here:
        AddCustomForm addNew = new AddCustomForm(this.frmParent,true,"Dealer");
        addNew.setLocationRelativeTo(this);
        addNew.setVisible(true);
        getAllCustomer("");
}//GEN-LAST:event_btnAddDealerActionPerformed

    private void btnEditDealerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditDealerActionPerformed
        // TODO add your handling code here:
        if(tblDealerList.getSelectedRowCount()==1){
            UpdateCustomForm updateCust = new UpdateCustomForm(frmParent, true, getCodeSelectedRow(tblDealerList));
            updateCust.setLocationRelativeTo(this);
            updateCust.setVisible(true);
            getAllCustomer("");
        }else if(tblDealerList.getSelectedRowCount()>1){
            JOptionPane.showMessageDialog(this, "Do not select more one Dealer to Edit Profile", "WARNING", JOptionPane.WARNING_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Please Select a Dealer to Edit Profile", "WARNING", JOptionPane.WARNING_MESSAGE);
        }
}//GEN-LAST:event_btnEditDealerActionPerformed

    private void btnDeleteDealerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteDealerActionPerformed
        // TODO add your handling code here:

        msbsCustomer tempCust = null;
        if(tblDealerList.getSelectedRowCount()==1){
            tempCust = getCodeSelectedRow(tblDealerList);
            int result;
            if(tempCust != null){
                result = JOptionPane.showConfirmDialog(frmParent, "Are you sure delete this Dealer ? ");
                if(result == JOptionPane.YES_OPTION){
                    if(custMn.deleteCustomer(tempCust.getCustomerCode())){
                        JOptionPane.showMessageDialog(frmParent, "Delete successfull! ");
                    }else{
                        JOptionPane.showMessageDialog(this, "This Dealer can not delete", "ERROR WHEN DELETE", JOptionPane.ERROR_MESSAGE);
                    }
                    getAllCustomer("");
                }else if(result == JOptionPane.NO_OPTION){
                    getAllCustomer("");
                }
            }
        }else if(tblDealerList.getSelectedRowCount()>1){
            JOptionPane.showMessageDialog(this, "Do not select more one Dealer to Delete", "WARNING", JOptionPane.WARNING_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Please Select a Dealer to Delete", "WARNING", JOptionPane.WARNING_MESSAGE);
        }

}//GEN-LAST:event_btnDeleteDealerActionPerformed

    private void tblDealerListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDealerListMousePressed
        // TODO add your handling code here:
        tblDealerList.removeEditor();
        if(evt.getClickCount()==2){
            UpdateCustomForm updateCust = new UpdateCustomForm(frmParent, true, getCodeSelectedRow(tblDealerList));
            updateCust.setLocationRelativeTo(this);
            updateCust.setVisible(true);
            getAllCustomer("");
        }
}//GEN-LAST:event_tblDealerListMousePressed

    private void btnAddRetailerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRetailerActionPerformed
        // TODO add your handling code here:
        AddCustomForm addNew = new AddCustomForm(this.frmParent,true,"Retailer");
        addNew.setLocationRelativeTo(this);
        addNew.setVisible(true);
        getAllCustomer("");
}//GEN-LAST:event_btnAddRetailerActionPerformed

    private void btnEditRetailerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditRetailerActionPerformed
        // TODO add your handling code here:
        if(tblRetailerList.getSelectedRowCount()==1){
            UpdateCustomForm updateCust = new UpdateCustomForm(frmParent, true, getCodeSelectedRow(tblRetailerList));
            updateCust.setLocationRelativeTo(this);
            updateCust.setVisible(true);
            getAllCustomer("");
        }else if(tblRetailerList.getSelectedRowCount()>1){
            JOptionPane.showMessageDialog(this, "Do not select more one Retailer to Edit Profile", "WARNING", JOptionPane.WARNING_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Please Select a Retailer to Edit Profile", "WARNING", JOptionPane.WARNING_MESSAGE);
        }
}//GEN-LAST:event_btnEditRetailerActionPerformed

    private void btnDeleteRetailerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRetailerActionPerformed
        // TODO add your handling code here:

        msbsCustomer tempCust = null;
        if(tblRetailerList.getSelectedRowCount()==1){
            tempCust = getCodeSelectedRow(tblRetailerList);
            int result;
            if(tempCust != null){
                result = JOptionPane.showConfirmDialog(frmParent, "Are you sure delete this Retailer ? ");
                if(result == JOptionPane.YES_OPTION){
                    if(custMn.deleteCustomer(tempCust.getCustomerCode())){
                        JOptionPane.showMessageDialog(frmParent, "Delete successfull! ");
                    }else{
                        JOptionPane.showMessageDialog(this, "This Retailer can not delete", "ERROR WHEN DELETE", JOptionPane.ERROR_MESSAGE);
                    }
                    getAllCustomer("");
                }else if(result == JOptionPane.NO_OPTION){
                    getAllCustomer("");
                }
            }
        }else if(tblRetailerList.getSelectedRowCount()>1){
            JOptionPane.showMessageDialog(this, "Do not select more one Retailer to Delete", "WARNING", JOptionPane.WARNING_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Please Select a Retailer to Delete", "WARNING", JOptionPane.WARNING_MESSAGE);
        }

}//GEN-LAST:event_btnDeleteRetailerActionPerformed

    private void tblRetailerListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRetailerListMousePressed
        // TODO add your handling code here:
        tblRetailerList.removeEditor();
        if(evt.getClickCount()==2){
            UpdateCustomForm updateCust = new UpdateCustomForm(frmParent, true, getCodeSelectedRow(tblRetailerList));
            updateCust.setLocationRelativeTo(this);
            updateCust.setVisible(true);
            getAllCustomer("");
        }
}//GEN-LAST:event_tblRetailerListMousePressed

    private void txtSeachCustomerCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSeachCustomerCaretUpdate
        // TODO add your handling code here:
        String filerName = "";
        if(txtSeachCustomer.getText().trim().equals("")||txtSeachCustomer.getText().trim().equals(null)){
            filerName = "";
            getAllCustomer(filerName);
        }else{
            filerName = txtSeachCustomer.getText().trim();
            getAllCustomer(filerName);
        }
}//GEN-LAST:event_txtSeachCustomerCaretUpdate

    private void txtSeachDealerCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSeachDealerCaretUpdate
        // TODO add your handling code here:
        // Filter Dealer with Dealer's name
        String filerName = "";
        if(txtSeachDealer.getText().trim().equals("")||txtSeachDealer.getText().trim().equals(null)){
            filerName = "";
            getAllCustomer(filerName);
        }else{
            filerName = txtSeachDealer.getText().trim();
            getAllCustomer(filerName);
        }
    }//GEN-LAST:event_txtSeachDealerCaretUpdate

    private void txtSeachRetailerCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSeachRetailerCaretUpdate
        // TODO add your handling code here:
        // Filter Retailer with Retailer's name
        String filerName = "";
        if(txtSeachRetailer.getText().trim().equals("")||txtSeachRetailer.getText().trim().equals(null)){
            filerName = "";
            getAllCustomer(filerName);
        }else{
            filerName = txtSeachRetailer.getText().trim();
            getAllCustomer(filerName);
        }
    }//GEN-LAST:event_txtSeachRetailerCaretUpdate
    private int custCount;
    private int dealerCount;
    private int retailerCount;
    private int distributorCount;
    private TableColumn tableColumn;
    private CustomerManage custMn;
    private msbsCustomer customer;
    private Vector<msbsCustomer> allCustomer;
    private DefaultTableModel modelCustomerList;
    private DefaultTableModel modelDealerList;
    private DefaultTableModel modelRetailerList;
    private DefaultTableModel modelDistributorList;
    private JFrame frmParent;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddDealer;
    private javax.swing.JButton btnAddRetailer;
    private javax.swing.JButton btnDeleteCustomer;
    private javax.swing.JButton btnDeleteDealer;
    private javax.swing.JButton btnDeleteRetailer;
    private javax.swing.JButton btnEditCustomer;
    private javax.swing.JButton btnEditDealer;
    private javax.swing.JButton btnEditRetailer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAmountCust;
    private javax.swing.JLabel lblAmountDealer;
    private javax.swing.JLabel lblAmountRetailer;
    private javax.swing.JTable tblCustomerList;
    private javax.swing.JTable tblDealerList;
    private javax.swing.JTable tblRetailerList;
    private javax.swing.JTextField txtSeachCustomer;
    private javax.swing.JTextField txtSeachDealer;
    private javax.swing.JTextField txtSeachRetailer;
    // End of variables declaration//GEN-END:variables

}
