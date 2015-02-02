/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HomeModule.java
 *
 * Created on Nov 19, 2009, 4:06:01 PM
 */
package GUI;

import Access.CustomerAccess;
import Access.UserAccess;
import Business.CustomerManage;
import Business.MedicineManage;
import Business.MedicineTypeManage;
import Business.OrderDetailsManage;
import Business.OrderManage;
import Business.SupplierManage;
import Business.UserManage;
import Data.msbsCustomer;
import Data.msbsMedicine;
import Data.msbsMedicineType;
import Data.msbsOrderDetails;
import Data.msbsOrders;
import Data.msbsTopCustomer;
import Data.msbsUser;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class HomeModule extends javax.swing.JPanel {

    /** Creates new form HomeModule */
    public HomeModule() {
        initComponents();
      
        sim = new SimpleDateFormat("yyyy-MM");
        
        allOrder = new Vector<msbsOrders>();
        allOrder = new OrderManage().getAllOrder();
        getTodayOrder();
        

    }


    public void getTopCustomer() {
        DecimalFormat df2 = new DecimalFormat(".#");
        String type;
        String month = String.valueOf(jMonthChooser1.getMonth()+1);
        String year = String.valueOf(jYearChooser1.getYear());
        Vector<msbsCustomer> topCustomer = new Vector<msbsCustomer>();
        topCustomer = new CustomerAccess().getAllCusomer();
        DefaultTableModel modelCompList = new DefaultTableModel();
        modelCompList.addColumn("cust Code");
        modelCompList.addColumn("Customer Name");
        modelCompList.addColumn("Type");
        modelCompList.addColumn("Purchased ($)");
        DecimalFormat df = new DecimalFormat(".#");
        if (String.valueOf(cbxFilterCust.getSelectedItem()).equals("Customer")) {
            type = "Customer";
        } else if (String.valueOf(cbxFilterCust.getSelectedItem()).equals("Dealer")) {
            type = "Dealer";
        } else if (String.valueOf(cbxFilterCust.getSelectedItem()).equals("Retailer")) {
            type = "Retailer";
        } else {
            type = "";
        }

        Vector<msbsTopCustomer> data = new Vector<msbsTopCustomer>();
        if (topCustomer != null) {
            for (int i = 0; i < topCustomer.size(); i++) {
                msbsCustomer person = new msbsCustomer();
                person = topCustomer.get(i);
                if (person != null) {
                    if (new CustomerManage().getCustomerByCode(person.getCustomerCode()).getCustomerType().toLowerCase().matches(".*" + type.toLowerCase() + ".*")) {
                        Vector<msbsOrders> allOrder = new Vector<msbsOrders>();
                        allOrder = new OrderManage().getAllOrder();
                        double totalPurchased = 0.0;
                        for (msbsOrders od : allOrder) {
                            if (od.getCustomerCode().equals(person.getCustomerCode()) && sim.format(od.getOrderDate()).equals(year + "-" + month)) {
                                totalPurchased += od.getPaidMoney();
                            }
                        }
                        if (totalPurchased > 0) {
                            data.add(new msbsTopCustomer(person.getCustomerCode(), totalPurchased));
                        }
                    }
                }
            }
            Vector<msbsTopCustomer> result = new Vector<msbsTopCustomer>();
            result = msbsTopCustomer.sort(data);
            result.setSize(10);
            for (msbsTopCustomer t : result) {
                if (t != null) {
                    Vector a = new Vector();
                    a.add(t.getCode());
                    String name;
                    name= new CustomerManage().getCustomerByCode(t.getCode()).getCustomerName();
                    if(name.equals("unknown")){
                        a.add("Guest");
                    }else{
                        a.add(new CustomerManage().getCustomerByCode(t.getCode()).getCustomerName());
                    }
                    a.add(new CustomerManage().getCustomerByCode(t.getCode()).getCustomerType());
                    a.add(df2.format(t.getQuantity()));
                    modelCompList.addRow(a);

                }
            }
            tblTopCust.setModel(modelCompList);
            tblTopCust.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblTopCust.getColumnModel().getColumn(0).setMinWidth(0);
            tblTopCust.getColumnModel().getColumn(0).setMaxWidth(0);

        }
    }

    public void getTodayOrder() {
        DecimalFormat df2 = new DecimalFormat(".#");
        
        int totalOrder = 0;
        double totalCost = 0;
        double totalPayoff = 0;
        DefaultTableModel modelCompList = new DefaultTableModel();
        modelCompList.addColumn("Order Code");
        modelCompList.addColumn("Status");
        modelCompList.addColumn("Customer");
        modelCompList.addColumn("Type");
        modelCompList.addColumn("Seller");
        modelCompList.addColumn("Total");
        modelCompList.addColumn("Paid Money");
        modelCompList.addColumn("Expired Time");
        msbsOrders Orders = new msbsOrders();
        Date current = new Date(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String today = df.format(current);

        //Orders.getStatus()!=2 &&
        for (int i = 0; i < allOrder.size(); i++) {
            Orders = (msbsOrders) allOrder.get(i);
            if (df.format(Orders.getOrderDate()).equals(today)) {
                Vector newData = new Vector();
                String status = "";
                if (Orders.getStatus() == 0) {
                    status = "Waiting...";
                } else if (Orders.getStatus() == 1 && Orders.getExpiredTime().before(new Date(System.currentTimeMillis()))) {
                    status = "Delay";
                } else if (Orders.getStatus() == 2) {
                    status = "Done";
                } else {
                    status = "Paying";
                }
                newData.add(Orders.getOrderCode());
                newData.add(status);
                String CustName;
                CustName = new CustomerManage().getCustomerByCode(Orders.getCustomerCode()).getCustomerName();
                if(CustName.equals("unknown")){
                    newData.add("Guest");
                }else{
                    newData.add(CustName);
                }
                
                newData.add(new CustomerManage().getCustomerByCode(Orders.getCustomerCode()).getCustomerType());
                newData.add(new UserManage().getUserByCode(Orders.getUserCode()).getFullName());
                newData.add((float) Orders.getTotal());
                newData.add((float) Orders.getPaidMoney());
                newData.add(Orders.getExpiredTime());
                modelCompList.addRow(newData);
                totalOrder++;
                totalCost = totalCost + Orders.getTotal();
                totalPayoff = totalPayoff + Orders.getPaidMoney();
            }
        }
        
        tblOrder.setModel(modelCompList);

        tblOrder.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblOrder.getColumnModel().getColumn(0).setMinWidth(0);
        tblOrder.getColumnModel().getColumn(0).setMaxWidth(0);
        lblTotalOrder.setText(String.valueOf(totalOrder));
        lblTotalCost.setText(df2.format(totalCost)+" $");
        lblTotalPayoff.setText(df2.format(totalPayoff)+" $");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();
        btnDetails = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblTotalCost = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblTotalPayoff = new javax.swing.JLabel();
        lblTotalOrder = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTopCust = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        cbxFilterCust = new javax.swing.JComboBox();
        btnViewDetailsCust = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jButton1 = new javax.swing.JButton();

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Order List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N
        jPanel14.setPreferredSize(new java.awt.Dimension(799, 314));

        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Status", "Customer", "Total", "Paid Money", "Date Order", "ExpriedTime"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOrder.setRowHeight(20);
        tblOrder.getTableHeader().setReorderingAllowed(false);
        tblOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblOrderMousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(tblOrder);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
        );

        btnDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/edit.png"))); // NOI18N
        btnDetails.setText("Order Details");
        btnDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsActionPerformed(evt);
            }
        });

        jLabel14.setText("Total Order: ");

        jLabel15.setText("Total  Cost: ");

        lblTotalCost.setText("0");

        jLabel16.setText("Total Payofft: ");

        lblTotalPayoff.setText("0");

        lblTotalOrder.setText("0");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Refresh.ico-32x32.png"))); // NOI18N
        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotalPayoff, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(lblTotalCost)
                    .addComponent(jLabel16)
                    .addComponent(lblTotalPayoff)
                    .addComponent(lblTotalOrder)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDetails))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Today Order", new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/today_icon.png")), jPanel13); // NOI18N

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Top Customer List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        tblTopCust.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer Name", "Type", "Purchased"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblTopCust.getTableHeader().setReorderingAllowed(false);
        tblTopCust.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTopCustMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(tblTopCust);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
        );

        jLabel9.setText(" Customer Type:");

        cbxFilterCust.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All Type", "Customer", "Dealer", "Retailer" }));

        btnViewDetailsCust.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/edit.png"))); // NOI18N
        btnViewDetailsCust.setText("View Details Customer");
        btnViewDetailsCust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDetailsCustActionPerformed(evt);
            }
        });

        jLabel1.setText("Choose Month of Year: ");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Filter.png"))); // NOI18N
        jButton1.setText("Show");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(624, Short.MAX_VALUE)
                .addComponent(btnViewDetailsCust)
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxFilterCust, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxFilterCust, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnViewDetailsCust, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Top Customer ", new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/topIcon.png")), jPanel9); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblOrderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrderMousePressed
        //TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            tblOrder.removeEditor();
            ViewOrderDetailForm addNew = new ViewOrderDetailForm(this.frmParent, true, new OrderManage().getOrdersByOrderCode(String.valueOf(tblOrder.getValueAt(tblOrder.getSelectedRow(), 0))), "");
            addNew.setLocationRelativeTo(this);
            addNew.setVisible(true);
            getTodayOrder();

        } else {
            tblOrder.removeEditor();
        }
}//GEN-LAST:event_tblOrderMousePressed

    private void btnDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsActionPerformed
        // TODO add your handling code here:
        if (tblOrder.getSelectedRowCount() < 1) {
            JOptionPane.showMessageDialog(this, "Please choose one Order !", "WRANING", JOptionPane.WARNING_MESSAGE);
        } else if (tblOrder.getSelectedRowCount() > 1) {
            JOptionPane.showMessageDialog(this, "Cannot choose more one Order", "WRANING", JOptionPane.WARNING_MESSAGE);
        } else {
            ViewOrderDetailForm addNew = new ViewOrderDetailForm(this.frmParent, true, new OrderManage().getOrdersByOrderCode(String.valueOf(tblOrder.getValueAt(tblOrder.getSelectedRow(), 0))), "");
            addNew.setLocationRelativeTo(this);
            addNew.setVisible(true);
        }
    }//GEN-LAST:event_btnDetailsActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        getTodayOrder();
}//GEN-LAST:event_jButton2ActionPerformed

    private void tblTopCustMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTopCustMousePressed
        // TODO add your handling code here:
        tblTopCust.removeEditor();
        if (evt.getClickCount() > 1) {
            tblTopCust.removeEditor();
            UpdateCustomForm view = new UpdateCustomForm(frmParent, false, new CustomerManage().getCustomerByCode(String.valueOf(tblTopCust.getValueAt(tblTopCust.getSelectedRow(), 0))));
            view.setLocationRelativeTo(this);
            view.setVisible(true);

        } else {
            tblTopCust.removeEditor();
        }
}//GEN-LAST:event_tblTopCustMousePressed

    private void btnViewDetailsCustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetailsCustActionPerformed
        // TODO add your handling code here:
        if (tblTopCust.getSelectedRowCount() < 1) {
            JOptionPane.showMessageDialog(this, "Please choose one Customer !", "WRANING", JOptionPane.WARNING_MESSAGE);
        } else if (tblTopCust.getSelectedRowCount() == 1) {
            UpdateCustomForm view = new UpdateCustomForm(frmParent, false, new CustomerManage().getCustomerByCode(String.valueOf(tblTopCust.getValueAt(tblTopCust.getSelectedRow(), 0))));
            view.setLocationRelativeTo(this);
            view.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Cannot choose more one Customer", "WRANING", JOptionPane.WARNING_MESSAGE);
        }
}//GEN-LAST:event_btnViewDetailsCustActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        getTopCustomer();
    }//GEN-LAST:event_jButton1ActionPerformed
    private SimpleDateFormat sim;
    private JFrame frmParent;
    private Vector<msbsOrders> allOrder;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetails;
    private javax.swing.JButton btnViewDetailsCust;
    private javax.swing.JComboBox cbxFilterCust;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel9;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane3;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JLabel lblTotalCost;
    private javax.swing.JLabel lblTotalOrder;
    private javax.swing.JLabel lblTotalPayoff;
    private javax.swing.JTable tblOrder;
    private javax.swing.JTable tblTopCust;
    // End of variables declaration//GEN-END:variables
}
