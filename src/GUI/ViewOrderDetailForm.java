/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ViewOrderDetailForm.java
 *
 * Created on Nov 12, 2009, 8:15:47 AM
 */

package GUI;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Business.CustomerManage;
import Business.OrderManage;
import Business.OrderDetailsManage;
import Business.MedicineManage;
import Data.msbsCustomer;
import Data.msbsOrders;
import Data.msbsOrderDetails;
import Data.msbsMedicine;
import java.awt.Point;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class ViewOrderDetailForm extends javax.swing.JDialog {

    /** Creates new form ViewOrderDetailForm */
    public ViewOrderDetailForm(java.awt.Frame parent, boolean modal, msbsOrders updateOrder,String status) {
        super(parent, modal);
        initComponents();
        ODEclicked=false;
        txtCode.setVisible(false);
        OrdCode = updateOrder.getOrderCode();
		ordDeMn = new OrderDetailsManage();
        med = new msbsMedicine();
        this.orders = updateOrder;
        condition = -1;
        Date ex = orders.getExpiredTime();
        String Eday = String.valueOf(ex);
        String[] Earray = Eday.split("-");
        String getE = Earray[2];

        Date current = new Date(System.currentTimeMillis());
        String Cday = String.valueOf(current);
        String[] Carray = Cday.split("-");
        String getC = Carray[2];

        boolean fl = getC.equals(getE);
        
        if(status.equals("Waiting...")){
            lbPay.setVisible(false);
            lbMoney.setVisible(false);
            txtPaidMoney.setVisible(false);
            btnSetPay.setVisible(false);
            condition = 0;
        }else if(status.equals("WARNING!!!")){
            lbPay.setVisible(false);
            lbMoney.setVisible(false);
            txtPaidMoney.setVisible(false);
            btnSetPay.setVisible(false);
            condition = 4;
        }else if(status.equals("Paying")){
            btnAdd.setVisible(false);
            btnDelete.setVisible(false);
            btnStart.setText("Done");
            ExpiredDate.setEnabled(false);
            condition = 1;
        }else if(status.equals("Transfer Delay")){
            lbPay.setVisible(false);
            lbMoney.setVisible(false);
            txtPaidMoney.setVisible(false);
            btnSetPay.setVisible(false);
            btnUpdate.setVisible(false);
            ExpiredDate.setEnabled(false);
            condition = 2;
        }else if(status.equals("Delay")){
            btnAdd.setVisible(false);
            btnDelete.setVisible(false);
            btnSetPay.setVisible(false);
            btnUpdate.setVisible(false);
            btnStart.setText("Done");
            ExpiredDate.setEnabled(false);
            condition = 3;
        }else if(status.equals("") || status.equals("Done!")){
            btnAdd.setVisible(false);
            btnDelete.setVisible(false);
            lbPay.setVisible(false);
            lbMoney.setVisible(false);
            txtPaidMoney.setVisible(false);
            btnSetPay.setVisible(false);
            btnStart.setVisible(false);
            ExpiredDate.setEnabled(false);
            btnUpdate.setVisible(false);
        }
        ordDetails = new msbsOrderDetails();
        allOrderDetails = new Vector<msbsOrderDetails>();
        med  =new msbsMedicine();
        medMn = new MedicineManage();
        Custom = new msbsCustomer();
        ordMn = new OrderManage();
        newOrderDE  = new Vector<msbsOrderDetails>();
        backUpList  = new Vector<msbsOrderDetails>();
        newOrderDE = ordDeMn.getAllOrderDetailsByOrderCode(orders.getOrderCode());
        backUpList =  ordDeMn.getAllOrderDetailsByOrderCode(orders.getOrderCode());


        String CustomerCode = orders.getCustomerCode();
        Custom = new CustomerManage().getCustomerByCode(CustomerCode);
        String CustName = Custom.getCustomerName();
        String CustPhone = Custom.getCustomerPhone();
        String address = Custom.getCustomerAddress();
        txtCustName.setText(CustName);
        txtPhone.setText(CustPhone);
        txtAddress.setText(address);
        DateChooser.setDate(orders.getOrderDate());
        ExpiredDate.setDate(orders.getExpiredTime());
        txtCode.setText(String.valueOf(orders.getOrderCode()));
        txtTax.setText(String.valueOf(orders.getTax()));
        txtDiscount.setText(String.valueOf(orders.getDiscount()));
        txtPaidMoney.setText(String.valueOf(orders.getPaidMoney()));
        getAllOrderDetails();
        CountCost();
        CountTotal();
    }
    public void getAllOrderDetails(){
        allOrderDetails = ordDeMn.getAllOrderDetailsByOrderCode(OrdCode);
        OrderTableModel = new DefaultTableModel();
        OrderTableModel.addColumn("Medicine");
        OrderTableModel.addColumn("Price");
        OrderTableModel.addColumn("Quantity");
        refreshDrugList();
    }

    public void CountCost(){
        int quantity;
        double price;
        Cost=0;
        Total=0;
        for(int i = 0; i<newOrderDE.size(); i++){
            msbsOrderDetails temp = (msbsOrderDetails)newOrderDE.get(i);
            quantity = temp.getQuantity();
            price = temp.getPrice();
            Cost = Cost + (quantity*price);
        }
            txtCost.setText(String.valueOf(Cost));
    }

       public void CountTotal(){
        int quantity;
        double price;
        Cost=0;
        Total=0;
        int tax = Integer.valueOf(String.valueOf(txtTax.getText()));
        int discount = Integer.valueOf(String.valueOf(txtDiscount.getText()));
        for(int i = 0; i<newOrderDE.size(); i++){
            msbsOrderDetails temp = (msbsOrderDetails)newOrderDE.get(i);
            quantity = temp.getQuantity();
            price = temp.getPrice();
            Cost = Cost + (quantity*price);
        }
        Cost = Cost + (Cost*tax/100);
        DecimalFormat df = new DecimalFormat(".#");
        Total = Cost - (Cost*discount/100) ;
        txtTotal.setText(df.format(Total));
    }

    public void AddMedicine(){
        newOrderDE = SelectMedicineForm.newOrderDETemp;
        ordDeMn.deleteOrderDetailByOrderCode(OrdCode);
        for(msbsOrderDetails od : newOrderDE){
            ordDeMn.insertOrderDetails(od.getOrderCode(), od.getMedicineCode(), od.getPrice(), od.getQuantity());
        }
    }
    public void refreshDrugList(){
        OrderTableModel = new DefaultTableModel();
        OrderTableModel.addColumn("Medicine");
        OrderTableModel.addColumn("Price");
        OrderTableModel.addColumn("Quantity");
        for(int i =0;i<newOrderDE.size();i++){
                msbsOrderDetails ode = (msbsOrderDetails)newOrderDE.get(i);
                msbsMedicine oldMed = medMn.getMedicineByCode(ode.getMedicineCode());
                Vector data = new Vector();
                data.add(oldMed.getMedicineName());
                data.add(ode.getPrice());
                data.add(ode.getQuantity());
                data.add(oldMed.getMedicineOrigine());
                data.add(oldMed.getDesignation());
                data.add(oldMed.getDosage());
                OrderTableModel.addRow(data);

            }
            tblOrder.setModel(OrderTableModel);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCustName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        DateChooser = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        ExpiredDate = new com.toedter.calendar.JDateChooser();
        txtCode = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();
        txtTax = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtCost = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jlTotal = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        lbPay = new javax.swing.JLabel();
        txtPaidMoney = new javax.swing.JTextField();
        lbMoney = new javax.swing.JLabel();
        btnSetPay = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnStart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("View Order Detail");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Customer Informations", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        jLabel1.setText("Customer Name");

        txtCustName.setEditable(false);

        jLabel2.setText("Phone");

        txtPhone.setEditable(false);

        jLabel3.setText("Address");

        txtAddress.setEditable(false);

        jLabel4.setText("Order Date");

        DateChooser.setEnabled(false);

        jLabel13.setText("Expired Date");

        txtCode.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                        .addGap(42, 42, 42)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtAddress)
                    .addComponent(txtCustName, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel13))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ExpiredDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)))
                    .addComponent(txtCode))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCustName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(DateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ExpiredDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Drugs List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Add.png"))); // NOI18N
        btnAdd.setText("Add");
        btnAdd.setToolTipText("Add medicine to drugs list");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Medicine", "Price", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOrder.getTableHeader().setReorderingAllowed(false);
        tblOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblOrderMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblOrder);
        tblOrder.getColumnModel().getColumn(0).setResizable(false);
        tblOrder.getColumnModel().getColumn(1).setResizable(false);
        tblOrder.getColumnModel().getColumn(2).setResizable(false);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setToolTipText("Delete medicine from drugs list");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        txtTax.setColumns(2);
        txtTax.setEditable(false);

        jLabel8.setText("Tax");

        jLabel16.setText("%");

        jLabel11.setText("Discount ");

        txtDiscount.setEditable(false);

        jLabel17.setText("%");

        txtCost.setEditable(false);

        jLabel14.setText("$");

        jLabel7.setText("Order Cost");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTax, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCost, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addGap(47, 47, 47))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnDelete)
                    .addComponent(txtTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel16)
                    .addComponent(jLabel11)
                    .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txtCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("Total");

        txtTotal.setEditable(false);

        jLabel15.setText("$");

        lbPay.setText("Payed");

        txtPaidMoney.setEditable(false);

        lbMoney.setText("$");

        btnSetPay.setText("Set Pay");
        btnSetPay.setToolTipText("Click here to pay order cost");
        btnSetPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetPayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(302, 302, 302)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbPay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPaidMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbMoney)
                .addGap(18, 18, 18)
                .addComponent(btnSetPay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 187, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addGap(41, 41, 41))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPay)
                    .addComponent(txtPaidMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMoney)
                    .addComponent(btnSetPay)
                    .addComponent(jLabel15)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel5.setText(" Order Detail");

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Update.png"))); // NOI18N
        btnUpdate.setText("Save");
        btnUpdate.setToolTipText("");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/exit.png"))); // NOI18N
        btnCancel.setText("Close");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/login.png"))); // NOI18N
        btnStart.setText("Start Transfer");
        btnStart.setToolTipText("");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, 0, 593, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnStart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 261, Short.MAX_VALUE)
                .addComponent(btnUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancel)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel)
                    .addComponent(btnStart))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void SaveOrder(){
        if(condition==0 || condition==4){
            btnUpdate.setToolTipText("Save Order informations");
            Date exDate = new Date(ExpiredDate.getDate().getTime());
            Total = Double.valueOf(String.valueOf(txtTotal.getText()));
            if(ordMn.updateOrder(OrdCode,Total,0,exDate)){
                ordDeMn.deleteOrderDetailByOrderCode(OrdCode);
                for(msbsOrderDetails od : newOrderDE){
                        ordDeMn.insertOrderDetails(od.getOrderCode(), od.getMedicineCode(), od.getPrice(), od.getQuantity());
                    }
                JOptionPane.showMessageDialog(frmParent,"Saved!");
                getAllOrderDetails();
                this.dispose();
             }else{
                JOptionPane.showMessageDialog(frmParent,"Error!");
             }
         }else if(condition==1){
            btnUpdate.setToolTipText("Save PaidMoney");
            double paidM = Double.valueOf(String.valueOf(txtPaidMoney.getText()));
            Date currentDay = new Date(System.currentTimeMillis());
            if(paidM<orders.getPaidMoney()){
                JOptionPane.showMessageDialog(frmParent,"PaidMoney of this time cannot less than the last!");
            }else{
                if(ordMn.updateOrderPaidMoney(OrdCode, paidM)){
                    JOptionPane.showMessageDialog(frmParent,"PaidMoney saved!");
                }else{
                    JOptionPane.showMessageDialog(frmParent,"Error!");
                }
            }
         }
    }
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        ODEclicked=true;
        SaveOrder();
        this.dispose();
}//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
       
        SelectMedicineForm addNew;
        try {
            addNew = new SelectMedicineForm(this.frmParent, true,OrdCode,newOrderDE);
            addNew.setLocationRelativeTo(this);
            addNew.setVisible(true);

            if(SelectMedicineForm.isDone==true){
                AddMedicine();
                refreshDrugList();
                CountCost();
                CountTotal();
            }else{
                newOrderDE = ordDeMn.getAllOrderDetailsByOrderCode(orders.getOrderCode());
            }
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(AddOrderForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    
}//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if(tblOrder.getSelectedRowCount()>1){
            JOptionPane.showMessageDialog(this,"Please select One row one time!","Warning",2);
        }else{
            int index = tblOrder.getSelectedRow();
            int medCode = medMn.getCodeByName(String.valueOf(tblOrder.getValueAt(index, 0)));
            for(int i =0;i<newOrderDE.size();i++){
                if(medCode == ((msbsOrderDetails)newOrderDE.get(i)).getMedicineCode()){
                    newOrderDE.remove(i);
                }
            }
            ordDeMn.deleteOrderDetailByOrderCode(OrdCode);
            for(msbsOrderDetails od : newOrderDE){
                ordDeMn.insertOrderDetails(od.getOrderCode(), od.getMedicineCode(), od.getPrice(), od.getQuantity());
            }
            refreshDrugList();
            CountCost();
            CountTotal();
        }
}//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        ordDeMn.deleteOrderDetailByOrderCode(OrdCode);
        for(msbsOrderDetails od : backUpList){
            ordDeMn.insertOrderDetails(od.getOrderCode(), od.getMedicineCode(), od.getPrice(), od.getQuantity());
        }
        this.dispose();
}//GEN-LAST:event_btnCancelActionPerformed

    private void tblOrderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrderMouseReleased
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            if(orders.getStatus()==1){
                tblOrder.removeEditor();
            }else{
                Point click = new Point(evt.getX(), evt.getY());
                int col = tblOrder.columnAtPoint(click);
                if(col != 2){
                    JOptionPane.showMessageDialog(this,"This field cannot be edited!","Warning",2);
                    tblOrder.removeEditor();
                }else{
                    int rowClick;
                    rowClick = tblOrder.rowAtPoint(click);
                    tblOrder.editCellAt(rowClick, 2);
                    int mCode2 = medMn.getCodeByName(String.valueOf(tblOrder.getValueAt(rowClick, 0)));
                    SetQuantityForm setQ;
                    setQ = new SetQuantityForm(this.frmParent, true,mCode2);
                    setQ.setLocationRelativeTo(this);
                    setQ.setVisible(true);
                    if(SetQuantityForm.isClick==true){
                        int newQuan = SetQuantityForm.newQuantity;
                        msbsOrderDetails temp = (msbsOrderDetails)newOrderDE.get(rowClick);
                        temp.setQuantity(newQuan);
                        newOrderDE.set(rowClick, temp);
                    }
                    refreshDrugList();
                    CountTotal();
                }
            }
        }
    }//GEN-LAST:event_tblOrderMouseReleased

    private void btnSetPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetPayActionPerformed
        // TODO add your handling code here:\
        oldMoney = Double.valueOf(String.valueOf(txtPaidMoney.getText()));
        allCost = Double.valueOf(String.valueOf(txtTotal.getText()));
        SetPayForm setPay;
        setPay = new SetPayForm(this.frmParent, true);
        setPay.setLocationRelativeTo(this);
        setPay.setVisible(true);
        if(SetPayForm.fla==true){
            double newMoney = SetPayForm.money;
            txtPaidMoney.setText(String.valueOf(newMoney));
        }
        if(SetPayForm.isFull==true){
            btnUpdate.setVisible(false);
        }else{
            btnUpdate.setVisible(true);
        }
    }//GEN-LAST:event_btnSetPayActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        // TODO add your handling code here:
        ODEclicked=true;
        if(condition==0 || condition==2 || condition==4){
            btnStart.setToolTipText("Begin deliver merchandises to Customer");
            SaveOrder();
            if(condition==2){
                ExpiredDate.setDate(new Date(System.currentTimeMillis()));
            }
            Date exDate = new Date(ExpiredDate.getDate().getTime());
            ordMn.updateOrder(OrdCode,Total,0,exDate);
            for(int i = 0; i<newOrderDE.size(); i++){
                msbsOrderDetails temp = (msbsOrderDetails)newOrderDE.get(i);
                int medCode = temp.getMedicineCode();
                int quantity = temp.getQuantity();
                if(!medMn.updateMedQuantityLeft(medCode, quantity)){
                    JOptionPane.showMessageDialog(frmParent,"Error!");
                }
            }
            if(ordMn.updateOrderStatus(OrdCode)){
                JOptionPane.showMessageDialog(this,"This Order is waiting for pay off!");
            }else{
                JOptionPane.showMessageDialog(frmParent,"Error!");
            }
            this.dispose();
        }else if(condition==1 || condition==3){
            btnStart.setToolTipText("Click here if payment completed");
            Total = Double.valueOf(String.valueOf(txtTotal.getText()));
            double paid;
            if(condition==3){
                paid=Total;
            }else{
                paid = Double.valueOf(String.valueOf(txtPaidMoney.getText()));
            }
            Date currentDay = new Date(System.currentTimeMillis());
            if(paid != Total){
                JOptionPane.showMessageDialog(frmParent,"Paid money is not equal with Order's total cost. Can't be set Done!","Warning",2);
            }else{

                if(ordMn.updateOrderComplete(OrdCode,Total, currentDay)){
                    if(condition==3){
                    JOptionPane.showMessageDialog(frmParent,"Total money saved is "+Total+" $"+"\n"+"Order Completed!");
                    }else{
                        JOptionPane.showMessageDialog(frmParent,"Order Completed!");
                    }
                }
                this.dispose();
            }
        }
    }//GEN-LAST:event_btnStartActionPerformed

    /**
    * @param args the command line arguments
    */
    private int condition;
    static Vector<msbsOrderDetails> ViewOrdDetailsList;
    static double oldMoney;
    static String OrdCode;
    private double Total=0;
    private double Cost=0;
	private OrderDetailsManage ordDeMn;
    private OrderManage ordMn;
    private MedicineManage medMn;
    private msbsOrders orders;
    private msbsMedicine med;
    private msbsOrderDetails ordDetails;
    private msbsCustomer Custom;
    private Vector<msbsOrderDetails> allOrderDetails;
    private DefaultTableModel OrderTableModel;
    private JFrame frmParent;
    static double allCost;
    static int checkDone;
    private Vector<msbsOrderDetails> newOrderDE ;
    static Vector<msbsOrderDetails> backUpList ;
    static boolean ODEclicked;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateChooser;
    private com.toedter.calendar.JDateChooser ExpiredDate;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSetPay;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlTotal;
    private javax.swing.JLabel lbMoney;
    private javax.swing.JLabel lbPay;
    private javax.swing.JTable tblOrder;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtCost;
    private javax.swing.JTextField txtCustName;
    private javax.swing.JTextField txtDiscount;
    private javax.swing.JTextField txtPaidMoney;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtTax;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

}
