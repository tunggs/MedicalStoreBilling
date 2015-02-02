/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UpdateUserForm.java
 *
 * Created on Nov 8, 2009, 3:56:19 PM
 */
package GUI;

import Business.OrderManage;
import Business.UserManage;
import Data.msbsOrders;
import Data.msbsUser;
import java.text.DecimalFormat;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Administrator
 */
public class UpdateUserForm extends javax.swing.JDialog {

    /** Creates new form UpdateUserForm */
    /** Creates new form UpdateUserForm */
    public UpdateUserForm(java.awt.Frame parent, boolean modal, msbsUser pUser) {
        super(parent, modal);
        this.tempUser = pUser;
        userMn = new UserManage();
        initComponents();
        txtUserName1.setText(tempUser.getNameLogin());
        txtFullName1.setText(tempUser.getFullName());
        txtAddress1.setText(tempUser.getUserAddress());
        txtPhone1.setText(tempUser.getUserPhone());
        txtEmail1.setText(tempUser.getUserEmail());
        model = new DefaultComboBoxModel(new String[]{"Manager", "Seller", "Accountant"});
        cbUserType.setModel(model);
        OrdMn = new OrderManage();
        if (tempUser.getUserTypeCode() == 1) {
            cbUserType.setSelectedIndex(0);
            jTabbedPane1.remove(jPanel2);
            
        } else if (tempUser.getUserTypeCode() == 2) {
            cbUserType.setSelectedIndex(1);
            
        } else if (tempUser.getUserTypeCode() == 3) {
            cbUserType.setSelectedIndex(2);
            jTabbedPane1.remove(jPanel2);
        }
        if (tempUser.getUserActive() == 1) {
            rbtnActive_YES.setSelected(true);
        } else if (tempUser.getUserActive() == 0) {
            rbtnActive_NO.setSelected(true);
        }
        if (modal == false) {
            txtAddress1.setEditable(false);
            txtEmail1.setEditable(false);
            txtFullName1.setEditable(false);
            txtPhone1.setEditable(false);
            txtUserName1.setEditable(false);
            cbUserType.setEnabled(false);
            rbtnActive_NO.setEnabled(false);
            rbtnActive_YES.setEnabled(false);
            btnUpdate.setVisible(false);
        }
        getAllBill();
    }

    public void getAllBill() {
        df = new DecimalFormat(".#");
        allOrders = OrdMn.getAllOrder();

        OrderTableModel = new DefaultTableModel();
        OrderTableModel.addColumn("Order Code");
        OrderTableModel.addColumn("Delivered Date");
        OrderTableModel.addColumn("Date Order");
        OrderTableModel.addColumn("Total Cost");
        OrderTableModel.addColumn("Paid Money");
        OrderTableModel.addColumn("Status");

        totalBill = 0;
        totalMoney = 0.0;
        for (msbsOrders od : allOrders) {

            if (od.getUserCode() == this.tempUser.getUserCode()) {
                Vector newData = new Vector();
                String status;
                if (od.getStatus() == 0) {
                    status = "Waiting...";
                } else if (od.getStatus() == 1) {
                    status = "Paying";
                } else {
                    status = "Done!";
                }

                newData.addElement(od.getOrderCode());
                newData.addElement(od.getExpiredTime());
                newData.addElement(od.getOrderDate());
                newData.addElement(df.format(od.getTotal()));
                newData.addElement(df.format(od.getPaidMoney()));
                newData.addElement(status);
                OrderTableModel.addRow(newData);
                totalMoney = totalMoney + od.getPaidMoney();
                totalBill++;
            }
        }
        tblBillList.setModel(OrderTableModel);

        tableColumn = tblBillList.getColumnModel().getColumn(0);
        tableColumn.setPreferredWidth(0);
        tableColumn.setMinWidth(0);
        tableColumn.setMaxWidth(0);
        
        lblTotalMoney.setText(df.format(totalMoney));
        lblTotalBill.setText(String.valueOf(totalBill));

    }

    public boolean checkUser() {

        String txtEmailp = txtEmail1.getText().trim();
        String txtNamep = txtFullName1.getText().trim();
        String txtPhonep = txtPhone1.getText().trim();

        Pattern emailcheck = Pattern.compile(".+@.+\\.[a-z]+");
        Pattern namecheck = Pattern.compile("^[a-zA-Z\\s]+");
        Pattern phonecheck = Pattern.compile("[0-9]{9,11}");

        Matcher m = emailcheck.matcher(txtEmailp);
        Matcher n = namecheck.matcher(txtNamep);
        Matcher p = phonecheck.matcher(txtPhonep);

        boolean email = m.matches();
        boolean name = n.matches();
        boolean phone = p.matches();

        if (txtFullName1.getText().trim().equals("") || txtFullName1.getText().trim().equals(null)) {
            JOptionPane.showMessageDialog(this, "FullName is not null !!!");
            txtFullName1.requestFocus();
            return false;
        } else if (name == false) {
            JOptionPane.showMessageDialog(this, "FullName is not valid !!!" + "\n" + " Name can't contained number!", "WRANING", JOptionPane.WARNING_MESSAGE);
            txtFullName1.requestFocus();
            return false;
        } else if (txtPhone1.getText().trim().equals("") || txtPhone1.getText().trim().equals(null)) {
            JOptionPane.showMessageDialog(this, "Phone is not null !!! ");
            txtPhone1.requestFocus();
            return false;
        } else if (phone == false) {
            JOptionPane.showMessageDialog(this, "Phone is not valid !!! " + "\n" + "Phone can't contained text!" + "\n" + "Example : xxxxxxxxx or xxxxxxxxxxx", "WRANING", JOptionPane.WARNING_MESSAGE);
            txtPhone1.requestFocus();
            return false;
        } else if (txtEmail1.getText().trim().equals("") || txtEmail1.getText().trim().equals(null)) {
            
        } else if (email == false) {
            JOptionPane.showMessageDialog(this, "Email is not valid !!!" + "\n" + "Example : emxample@email.com", "WRANING", JOptionPane.WARNING_MESSAGE);
            txtEmail1.requestFocus();
            return false;
        } else if (txtAddress1.getText().trim().equals("") || txtAddress1.getText().trim().equals(null)) {
            JOptionPane.showMessageDialog(this, "Address is not null !!!");
            txtAddress1.requestFocus();
            return false;
        }
        return true;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUserName1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtFullName1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbUserType = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txtPhone1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEmail1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAddress1 = new javax.swing.JTextArea();
        btnUpdate = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        rbtnActive_YES = new javax.swing.JRadioButton();
        rbtnActive_NO = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBillList = new javax.swing.JTable();
        btnViewDetails = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblTotalBill = new javax.swing.JLabel();
        lblTotalMoney = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnClose2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("View details or Update User infomation");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Users Update", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        jLabel1.setText("Username ");

        txtUserName1.setEnabled(false);

        jLabel3.setText("Full Name  ");

        jLabel4.setText("User Type ");

        jLabel5.setText("Phone        ");

        jLabel6.setText("Email         ");

        jLabel7.setText("Address  ");

        txtAddress1.setColumns(20);
        txtAddress1.setRows(5);
        jScrollPane1.setViewportView(txtAddress1);

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Update.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jLabel2.setText("Active");

        buttonGroup1.add(rbtnActive_YES);
        rbtnActive_YES.setSelected(true);
        rbtnActive_YES.setText("Yes");

        buttonGroup1.add(rbtnActive_NO);
        rbtnActive_NO.setText("No");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/icon_profile.png"))); // NOI18N

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/exit.png"))); // NOI18N
        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rbtnActive_YES)
                                .addGap(37, 37, 37)
                                .addComponent(rbtnActive_NO))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtPhone1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtFullName1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtUserName1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                                .addComponent(txtEmail1)
                                .addComponent(cbUserType, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(93, 93, 93))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUserName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFullName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPhone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(cbUserType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(rbtnActive_YES)
                            .addComponent(rbtnActive_NO))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(jLabel7)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnClose)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("User Profile", new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/up.png")), jPanel1); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bill List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        tblBillList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Delivered Date", "Date Order", "Total Money", "Paid Money", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblBillList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblBillListMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblBillList);

        btnViewDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/edit.png"))); // NOI18N
        btnViewDetails.setText("Bill Details");
        btnViewDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDetailsActionPerformed(evt);
            }
        });

        jLabel8.setText("Total Payoff: ");

        jLabel10.setText("Total Bill : ");

        lblTotalBill.setText("0");

        lblTotalMoney.setText("0");

        jLabel9.setText("$");

        btnClose2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/exit.png"))); // NOI18N
        btnClose2.setText("Close");
        btnClose2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClose2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotalBill)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTotalMoney)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
                .addComponent(btnViewDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClose2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lblTotalBill))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lblTotalMoney)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnClose2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnViewDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jTabbedPane1.addTab("Bill List", new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/up.png")), jPanel2); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        int code = 0;
        String fullName = null;
        String Email = null;
        String Phone = null;
        String Address = null;
        int active = 0;
        code = tempUser.getUserCode();
        fullName = txtFullName1.getText();
        Email = txtEmail1.getText();
        Phone = txtPhone1.getText();
        Address = txtAddress1.getText();
        if (rbtnActive_YES.isSelected()) {
            active = 1;
        } else if (rbtnActive_NO.isSelected()) {
            active = 0;
        }
        if (checkUser()) {
            if (userMn.updateUser(code, fullName, Address, Phone, Email, active)) {
                JOptionPane.showMessageDialog(rootPane, "Upadate successful!!");

                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Check your infomation to Update", "UPDATE FAILED", JOptionPane.ERROR_MESSAGE);
            }
        }
}//GEN-LAST:event_btnUpdateActionPerformed

    private void btnViewDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetailsActionPerformed
        // TODO add your handling code here:

        ViewOrderDetailForm addNew = new ViewOrderDetailForm(this.frmParent, true, OrdMn.getOrdersByOrderCode(String.valueOf(tblBillList.getValueAt(tblBillList.getSelectedRow(), 0))), "");
        addNew.setLocationRelativeTo(this);
        addNew.setVisible(true);
}//GEN-LAST:event_btnViewDetailsActionPerformed

    private void tblBillListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillListMousePressed
        // TODO add your handling code here:
        tblBillList.removeEditor();
        if (evt.getClickCount() > 1) {
            ViewOrderDetailForm addNew = new ViewOrderDetailForm(this.frmParent, true, OrdMn.getOrdersByOrderCode(String.valueOf(tblBillList.getValueAt(tblBillList.getSelectedRow(), 0))), "");
            addNew.setLocationRelativeTo(this);
            addNew.setVisible(true);
        }
    }//GEN-LAST:event_tblBillListMousePressed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnClose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClose2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_btnClose2ActionPerformed
    /**
     * @param args the command line arguments
     */
    DefaultComboBoxModel model;
    private Vector<msbsOrders> allOrders;
    private TableColumn tableColumn;
    private DefaultTableModel OrderTableModel;
    private OrderManage OrdMn;
    private DecimalFormat df;
    private double totalMoney;
    private int totalBill;
    private msbsUser tempUser;
    private UserManage userMn;
    private JFrame frmParent;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnClose2;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnViewDetails;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbUserType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblTotalBill;
    private javax.swing.JLabel lblTotalMoney;
    private javax.swing.JRadioButton rbtnActive_NO;
    private javax.swing.JRadioButton rbtnActive_YES;
    private javax.swing.JTable tblBillList;
    private javax.swing.JTextArea txtAddress1;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JTextField txtFullName1;
    private javax.swing.JTextField txtPhone1;
    private javax.swing.JTextField txtUserName1;
    // End of variables declaration//GEN-END:variables
}
