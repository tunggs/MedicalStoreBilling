/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UpdateCustomForm.java
 *
 * Created on Nov 10, 2009, 9:43:48 AM
 */

package GUI;

import Business.CustomerManage;
import Business.OrderManage;
import Data.msbsCustomer;
import Data.msbsOrders;
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
public class UpdateCustomForm extends javax.swing.JDialog {

    /** Creates new form UpdateCustomForm */
    public UpdateCustomForm(java.awt.Frame parent, boolean modal,msbsCustomer editCust) {
        super(parent, modal);
        initComponents();
        custMn = new CustomerManage();
        tempCust = new msbsCustomer();
        this.tempCust = editCust;
        model = new DefaultComboBoxModel(new String[] {"Customer","Dealer","Retailer"});
        cbTypeC.setModel(model);
        cbxCustPriorityModel = new DefaultComboBoxModel(new String[] {"General","Family","VIP"});
        cbxCustPriority.setModel(cbxCustPriorityModel);


        txtNameC.setText(tempCust.getCustomerName());
        txtAddressC.setText(tempCust.getCustomerAddress());
        txtEmailC.setText(tempCust.getCustomerEmail());
        txtFaxC.setText(tempCust.getCustomerFax());
        txtPhoneC.setText(tempCust.getCustomerPhone());
        cbxCustPriority.setSelectedItem(tempCust.getCustomerPriority());
        cbTypeC.setSelectedItem(tempCust.getCustomerType());

        tableColumn = new TableColumn();
        OrdMn = new OrderManage();

        allOrders = new Vector<msbsOrders>();
        custMn = new CustomerManage();
        if(modal==false){
            txtAddressC.setEditable(false);
            txtEmailC.setEditable(false);
            txtFaxC.setEditable(false);
            txtNameC.setEditable(false);
            txtPhoneC.setEditable(false);
            cbTypeC.setEnabled(false);
            cbxCustPriority.setEnabled(false);
            btnUpdateCustomer.setVisible(false);
        }
        getAllBill();

        
    }

    public void getAllBill(){
        df = new DecimalFormat(".#");
        allOrders = OrdMn.getAllOrder();            

        OrderTableModel = new DefaultTableModel();
        OrderTableModel.addColumn("Order Code");
        OrderTableModel.addColumn("Delivered Date");
        OrderTableModel.addColumn("Date Order");
        OrderTableModel.addColumn("Total Cost");
        OrderTableModel.addColumn("Paid Money");
        OrderTableModel.addColumn("Status");
 
        totalBill=0;
        totalMoney=0.0;
        for (msbsOrders od : allOrders) {
            
            if(od.getCustomerCode().equals(this.tempCust.getCustomerCode())){
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

    public boolean CheckFormCustomser(){
        String txtEmailc = txtEmailC.getText().trim();
        String txtPhonec = txtPhoneC.getText().trim();
        String txtNamec = txtNameC.getText().trim();
        String Fax = txtFaxC.getText().trim();

        Pattern emailcheck = Pattern.compile(".+@.+\\.[a-z]+");
        Pattern phonecheck = Pattern.compile("[0-9]{9,11}");
        Pattern namecheck = Pattern.compile("^[a-zA-Z\\s]+");

        Matcher m = emailcheck.matcher(txtEmailc);
        Matcher p = phonecheck.matcher(txtPhonec);
        Matcher n = namecheck.matcher(txtNamec);
        Matcher f = phonecheck.matcher(Fax);

        boolean email = m.matches();
        boolean phone = p.matches();
        boolean name = n.matches();
        boolean fax =  f.matches();

        if(txtNameC.getText().trim().equals("") || txtNameC.getText().trim().equals(null)){
            JOptionPane.showMessageDialog(this, "Name not null !!!"+"\n"+"Name can't contained number!","WARNING",JOptionPane.WARNING_MESSAGE);
            txtNameC.requestFocus();
            return false;
        }else if(name == false){
            JOptionPane.showMessageDialog(this, "Name is not valid !!!");
            txtNameC.requestFocus();
            return false;
        }
        if(txtEmailC.getText().trim().equals("")||txtEmailC.getText().trim().equals(null)){

        }else if(email == false){
           JOptionPane.showMessageDialog(this, "Email is not valid !!!"+"\n"+"Example : example@email.com","WARNING",JOptionPane.WARNING_MESSAGE);
           txtEmailC.requestFocus();
           return false;
        }
        if(cbTypeC.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(this, "Must select user type !!!");
            cbTypeC.requestFocus();
            return false;
        }
        if(cbxCustPriority.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(this, "Must select relationship !!!");
            cbxCustPriority.requestFocus();
            return false;
        }
        if(txtPhoneC.getText().trim().equals("")||txtPhoneC.getText().trim().equals(null)){
            JOptionPane.showMessageDialog(this, "You must enter the Phone: ");
            txtPhoneC.requestFocus();
            return false;
        }else if(phone == false){
            JOptionPane.showMessageDialog(this, "Not Invailid Phone !!!"+"\n"+"Phone can't contained text!"+"\n"+"Example : xxxxxxxxx or xxxxxxxxxxx","WARNING",JOptionPane.WARNING_MESSAGE);
            txtPhoneC.requestFocus();
            return false;
        }
        if(txtAddressC.getText().trim().equals("") || txtAddressC.getText().trim().equals(null)){
            JOptionPane.showMessageDialog(this, "Address not null !!!");
            txtAddressC.requestFocus();
            return false;
        }
        if(txtFaxC.getText().trim().equals("") || txtFaxC.getText().trim().equals(null)){

        }else if(fax==false){
            JOptionPane.showMessageDialog(this, "Not Invailid Fax !!!"+"\n"+"Fax can't contained text!"+"\n"+"Example : xxxxxxxxx or xxxxxxxxxxx","WARNING",JOptionPane.WARNING_MESSAGE);
            txtFaxC.requestFocus();
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNameC = new javax.swing.JTextField();
        txtPhoneC = new javax.swing.JTextField();
        cbTypeC = new javax.swing.JComboBox();
        txtFaxC = new javax.swing.JTextField();
        txtEmailC = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAddressC = new javax.swing.JTextArea();
        btnUpdateCustomer = new javax.swing.JButton();
        cbxCustPriority = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        btnClose2 = new javax.swing.JButton();
        billListPane = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBillList = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        lblTotalMoney = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnViewDetails = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        lblTotalBill = new javax.swing.JLabel();
        btnClose3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("View Details and Update Infomation for Custom");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Update Customer", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        jLabel1.setText("Name ");

        jLabel2.setText("Type ");

        jLabel3.setText("Phone ");

        jLabel4.setText("Fax ");

        jLabel5.setText("Email ");

        jLabel6.setText("Relationship ");

        jLabel7.setText("Address ");

        cbTypeC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtAddressC.setColumns(20);
        txtAddressC.setRows(5);
        jScrollPane1.setViewportView(txtAddressC);

        btnUpdateCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Update.png"))); // NOI18N
        btnUpdateCustomer.setText("Update");
        btnUpdateCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCustomerActionPerformed(evt);
            }
        });

        cbxCustPriority.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/icon_profile.png"))); // NOI18N

        btnClose2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/exit.png"))); // NOI18N
        btnClose2.setText("Close");
        btnClose2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClose2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(25, 25, 25))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel6))
                                        .addGap(18, 18, 18)))
                                .addGap(4, 4, 4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                            .addComponent(txtEmailC, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                            .addComponent(txtFaxC, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNameC, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                                .addComponent(cbTypeC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxCustPriority, 0, 156, Short.MAX_VALUE)
                                .addComponent(txtPhoneC, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(btnUpdateCustomer)
                        .addGap(18, 18, 18)
                        .addComponent(btnClose2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addGap(67, 67, 67))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNameC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbTypeC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxCustPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPhoneC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFaxC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmailC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnUpdateCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnClose2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(jLabel7)))))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Customer Profile", new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/up.png")), jPanel2); // NOI18N

        billListPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bill List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

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

        jLabel8.setText("Total Payoff: ");

        lblTotalMoney.setText("0");

        jLabel9.setText("$");

        btnViewDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/edit.png"))); // NOI18N
        btnViewDetails.setText("Bill Details");
        btnViewDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDetailsActionPerformed(evt);
            }
        });

        jLabel10.setText("Total Bill : ");

        lblTotalBill.setText("0");

        btnClose3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/exit.png"))); // NOI18N
        btnClose3.setText("Close");
        btnClose3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClose3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout billListPaneLayout = new javax.swing.GroupLayout(billListPane);
        billListPane.setLayout(billListPaneLayout);
        billListPaneLayout.setHorizontalGroup(
            billListPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
            .addGroup(billListPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(billListPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(billListPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(billListPaneLayout.createSequentialGroup()
                        .addComponent(lblTotalMoney)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addComponent(lblTotalBill))
                .addGap(176, 176, 176)
                .addComponent(btnViewDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClose3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        billListPaneLayout.setVerticalGroup(
            billListPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billListPaneLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(billListPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(billListPaneLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(billListPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnViewDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClose3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(billListPaneLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(billListPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lblTotalBill))
                        .addGap(8, 8, 8)
                        .addGroup(billListPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lblTotalMoney)
                            .addComponent(jLabel9))
                        .addContainerGap())))
        );

        jTabbedPane2.addTab("Bill List", new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/up.png")), billListPane); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCustomerActionPerformed
        // TODO add your handling code here:

        String name = txtNameC.getText().trim();
        String type = null;
        type = String.valueOf(cbTypeC.getSelectedItem());
        String priority = String.valueOf(cbxCustPriority.getSelectedItem());
        String phone = txtPhoneC.getText().trim();
        String fax = txtFaxC.getText().trim();
        String email = txtEmailC.getText().trim();
        
        String address = txtAddressC.getText().trim();
        String code = tempCust.getCustomerCode();
        if(CheckFormCustomser()){
            if(custMn.updateCustomer(code,name, type, phone, fax, email, address, priority)){
                JOptionPane.showMessageDialog(rootPane,"Update Customer successful");
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this, "Please Check your infomation to update! ","UPDATE FAILED",JOptionPane.ERROR_MESSAGE);
            }
        }
}//GEN-LAST:event_btnUpdateCustomerActionPerformed

    private void btnViewDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetailsActionPerformed
        // TODO add your handling code here:
              
        ViewOrderDetailForm addNew = new ViewOrderDetailForm(this.frmParent,true,OrdMn.getOrdersByOrderCode(String.valueOf(tblBillList.getValueAt(tblBillList.getSelectedRow(), 0))),"");
        addNew.setLocationRelativeTo(this);
        addNew.setVisible(true);
}//GEN-LAST:event_btnViewDetailsActionPerformed

    private void tblBillListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillListMousePressed
        // TODO add your handling code here:
        tblBillList.removeEditor();
        if(evt.getClickCount()>1){
             ViewOrderDetailForm addNew = new ViewOrderDetailForm(this.frmParent,true,OrdMn.getOrdersByOrderCode(String.valueOf(tblBillList.getValueAt(tblBillList.getSelectedRow(), 0))),"");
            addNew.setLocationRelativeTo(this);
            addNew.setVisible(true);
        }
    }//GEN-LAST:event_tblBillListMousePressed

    private void btnClose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClose2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_btnClose2ActionPerformed

    private void btnClose3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClose3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnClose3ActionPerformed

    /**
    * @param args the command line arguments
    */
    private Vector<msbsOrders> allOrders;
    private TableColumn tableColumn;
    private DefaultTableModel OrderTableModel;
    private OrderManage OrdMn;
    private DecimalFormat df;
    private double totalMoney;
    private int totalBill;
    private CustomerManage custMn;
    private msbsCustomer tempCust;
    private DefaultComboBoxModel model;
    private DefaultComboBoxModel cbxCustPriorityModel;
    private JFrame frmParent;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel billListPane;
    private javax.swing.JButton btnClose2;
    private javax.swing.JButton btnClose3;
    private javax.swing.JButton btnUpdateCustomer;
    private javax.swing.JButton btnViewDetails;
    private javax.swing.JComboBox cbTypeC;
    private javax.swing.JComboBox cbxCustPriority;
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
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblTotalBill;
    private javax.swing.JLabel lblTotalMoney;
    private javax.swing.JTable tblBillList;
    private javax.swing.JTextArea txtAddressC;
    private javax.swing.JTextField txtEmailC;
    private javax.swing.JTextField txtFaxC;
    private javax.swing.JTextField txtNameC;
    private javax.swing.JTextField txtPhoneC;
    // End of variables declaration//GEN-END:variables

}
