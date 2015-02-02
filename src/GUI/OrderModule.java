/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * OrderModule.java
 *
 * Created on Nov 4, 2009, 4:31:12 PM
 */

package GUI;

import Business.CustomerManage;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import Business.OrderManage;
import Business.UserManage;
import Data.msbsOrders;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
/**
 *
 * @author Administrator
 */
public class OrderModule extends javax.swing.JPanel {

    /** Creates new form OrderModule */
    public OrderModule() {
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
        initComponents();
        OrdMn = new OrderManage();
        CustMn = new CustomerManage();
        Orders = new msbsOrders();
        usMn = new UserManage();
        column = new TableColumn();
        current = new Date(System.currentTimeMillis());
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        Cday = String.valueOf(form.format(current));
        Carray = Cday.split("-");
        getC = Carray[2];
        today =String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));
        chooseDate=today;

        FilterOrder("","", chooseDate);
    }

    public void checkStatus(msbsOrders O){
        condition=0;
        checkedStatus="";
        Date ex = O.getExpiredTime();
        String Eday = String.valueOf(ex);
        String[] Earray = Eday.split("-");
        String getE = Earray[2];

        fl = getC.equals(getE);
        if(O.getStatus() == 0){
            if(O.getExpiredTime().before(new Date(System.currentTimeMillis()))){
                if(fl==true){
                   checkedStatus = "WARNING!!!"; //Expired Time = Current Time
                   condition = 1;
                }else{
                    checkedStatus = "Transfer Delay"; //Expired Time < Current Time (Order's time is over!)
                    condition = 2;
                }

            }else{
                checkedStatus = "Waiting..."; //Expired Time > Current Time
                condition = 3;
            }
        }else if(O.getStatus() == 1){
            if(O.getExpiredTime().before(new Date(System.currentTimeMillis()))){
                if(fl==true){
                    checkedStatus = "Paying"; //Expired Time = Current Time
                    condition = 4;
                }else{
                    checkedStatus = "Delay"; //Expired Time < Current Time (Order's time is over!)
                    condition = 5;
                }
            }else{
                checkedStatus = "Paying"; //Expired Time > Current Time
                condition = 4;
            }
        }else if(O.getStatus() == 2){
            checkedStatus = "Done!";
            condition = 6;
        }
    }

    public void getAllOrder(){
        allOrders = OrdMn.getAllOrder();
        OrderTableModel = new DefaultTableModel();
        OrderTableModel.addColumn("Status");
        OrderTableModel.addColumn("Order Code");
        OrderTableModel.addColumn("Seller");
        OrderTableModel.addColumn("Customer");
        OrderTableModel.addColumn("Total");
        OrderTableModel.addColumn("Date Order");
        OrderTableModel.addColumn("Expired Time");

        

        for(int i = allOrders.size()-1;i>=0;i--){
            Orders = (msbsOrders)allOrders.get(i);
            checkStatus(Orders);
            Vector newData = new Vector();
            if(condition == 1 || condition == 2 || condition == 3 || condition== 6){
                newData.add(checkedStatus);
                newData.add(Orders.getOrderCode());
                String sellerName;
                sellerName = usMn.getUserByCode(Orders.getUserCode()).getFullName();
                newData.add(sellerName);
                String CustName;
                CustName= CustMn.getCustomerByCode(Orders.getCustomerCode()).getCustomerName();
                newData.add(CustName);
                newData.add(new DecimalFormat(".#").format(Orders.getTotal()));
                newData.add(Orders.getOrderDate());
                newData.add(Orders.getExpiredTime());
                OrderTableModel.addRow(newData);
            }
        }
        tblOrder.setModel(OrderTableModel);
        tblOrder.getColumnModel().getColumn(1).setMinWidth(0);
        tblOrder.getColumnModel().getColumn(1).setPreferredWidth(0);
        tblOrder.getColumnModel().getColumn(1).setMaxWidth(0);

    }


    public void FilterOrder(String Cname,String stt,String dateOrd){
        if(Cname.equals("") && stt.equals("") && dateOrd.equals("")){
            getAllOrder();
        }else{
            getAllOrder();
            OrderTableModel = new DefaultTableModel();
            OrderTableModel.addColumn("Status");
            OrderTableModel.addColumn("Order Code");
            OrderTableModel.addColumn("Seller");
            OrderTableModel.addColumn("Customer");
            OrderTableModel.addColumn("Total");
            OrderTableModel.addColumn("Date Order");
            OrderTableModel.addColumn("Expired Time");
            TempOrders = new Vector<msbsOrders>();
            msbsOrders ord;
            msbsOrders resultOrd;
            for(int i=0; i<tblOrder.getRowCount();i++){
                if((String.valueOf(tblOrder.getValueAt(i,0)).matches(".*" + stt + ".*"))
                  && (String.valueOf(tblOrder.getValueAt(i,3)).toLowerCase().matches(".*" + Cname.toLowerCase() + ".*"))
                  && (String.valueOf(tblOrder.getValueAt(i,5)).toLowerCase().matches(".*"+dateOrd+".*"))){
                            ord = OrdMn.getOrdersByOrderCode(String.valueOf(tblOrder.getValueAt(i,1)));
                            TempOrders.add(ord);
                }
            }
            for(int z=0;z<TempOrders.size();z++){
                resultOrd = (msbsOrders)TempOrders.get(z);
                Vector newData = new Vector();
                checkStatus(resultOrd);
                newData.add(checkedStatus);
                newData.add(resultOrd.getOrderCode());
                String sellerName;
                sellerName = usMn.getUserByCode(resultOrd.getUserCode()).getFullName();
                newData.add(sellerName);
                String CuName;
                CuName= CustMn.getCustomerByCode(resultOrd.getCustomerCode()).getCustomerName();
                newData.add(CuName);
                newData.add(new DecimalFormat(".#").format(resultOrd.getTotal()));
                newData.add(resultOrd.getOrderDate());
                newData.add(resultOrd.getExpiredTime());
                OrderTableModel.addRow(newData);
            }
            tblOrder.setModel(OrderTableModel);
            tblOrder.getColumnModel().getColumn(1).setMinWidth(0);
            tblOrder.getColumnModel().getColumn(1).setPreferredWidth(0);
            tblOrder.getColumnModel().getColumn(1).setMaxWidth(0);
           // JOptionPane.showMessageDialog(frmParent,String.valueOf(tblOrder.getValueAt(0,3)));
        }
    }
    public msbsOrders getCodeSelectedRow(){
        int index = tblOrder.getSelectedRow();
        String OrdCode = String.valueOf(tblOrder.getValueAt(index, 1));
        Orders = OrdMn.getOrdersByOrderCode(OrdCode);
        if(Orders != null){
            return Orders;
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btnDetails = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbStatus = new javax.swing.JComboBox();
        dcDateOrder = new com.toedter.calendar.JDateChooser();
        cbDate = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnFilter = new javax.swing.JButton();
        btnShowAll = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        setPreferredSize(new java.awt.Dimension(661, 400));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Status", "Order Code", "Seller", "Customer", "Total", "Date Order", "ExpiredTime"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
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
        tblOrder.setRowHeight(20);
        tblOrder.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tblOrder.getTableHeader().setReorderingAllowed(false);
        tblOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblOrderMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblOrder);
        tblOrder.getColumnModel().getColumn(1).setMinWidth(0);
        tblOrder.getColumnModel().getColumn(1).setPreferredWidth(0);
        tblOrder.getColumnModel().getColumn(1).setMaxWidth(0);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Add.png"))); // NOI18N
        btnAdd.setText("Add");
        btnAdd.setToolTipText("Add new Order");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Delete.png"))); // NOI18N
        btnDel.setText("Delete");
        btnDel.setToolTipText("Delete one Order");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        btnDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/edit.png"))); // NOI18N
        btnDetails.setText("Order Details");
        btnDetails.setToolTipText("View Order Details");
        btnDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filter", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 0, 51))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(0, 51, 153));

        jLabel1.setText("Customer Name");

        jLabel5.setText("Status");

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Done", "Waiting", "Expired Today", "Transfer Delay" }));

        cbDate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Today" }));

        jLabel2.setText("Date Order ");

        jLabel3.setText("OR");

        btnFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Filter.png"))); // NOI18N
        btnFilter.setText("Filter");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearch)
                    .addComponent(dcDateOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbDate, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbStatus, 0, 141, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFilter)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(dcDateOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnFilter))))
        );

        btnShowAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/thl_icon.png"))); // NOI18N
        btnShowAll.setText("Show All");
        btnShowAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnShowAll))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnShowAll, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnDel)
                    .addComponent(btnDetails))
                .addContainerGap(26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsActionPerformed
        // TODO add your handling code here:
        int index = tblOrder.getSelectedRow();
        if(tblOrder.getSelectedRowCount()!=1){
            JOptionPane.showMessageDialog(this.frmParent,"Please select one Order to view details!","Warning",2);
        }else{
            ViewOrderDetailForm addNew = new ViewOrderDetailForm(this.frmParent,true,getCodeSelectedRow(),String.valueOf(tblOrder.getValueAt(index,0)));
            addNew.setLocationRelativeTo(this);
            addNew.setVisible(true);
            FilterOrder(cusName,status,chooseDate);
        }
}//GEN-LAST:event_btnDetailsActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        ChooseAddOrderForm choose = new ChooseAddOrderForm(this.frmParent,true);
        choose.setLocationRelativeTo(this);
        choose.setVisible(true);

        int result;
        result = ChooseAddOrderForm.choice;
        if(ChooseAddOrderForm.check==true){
            if(result==1){
                AddOrderForm addNew = new AddOrderForm(this.frmParent,true);
                addNew.setLocationRelativeTo(this);
                addNew.setVisible(true);
                FilterOrder("","", today);
            }else{
                AddOrder_NewCustomForm addNew = new AddOrder_NewCustomForm(this.frmParent,true);
                addNew.setLocationRelativeTo(this);
                addNew.setVisible(true);
                FilterOrder("","", today);
            }
        }
        if(OrdMn.getOrdersByOrderCode("0") != null){
            OrdMn.deleteOrder("0");
        }

}//GEN-LAST:event_btnAddActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        // TODO add your handling code here:
        if(tblOrder.getSelectedRowCount()!=1){
            JOptionPane.showMessageDialog(this.frmParent,"Please select one Order to Delete!","Warning",2);
        }else{
             int index = tblOrder.getSelectedRow();
             String code = String.valueOf(tblOrder.getValueAt(index, 1));
             String st = String.valueOf(tblOrder.getValueAt(index,0));
             if(st.equals("Waiting...") || st.equals("Transfer Delay") || st.equals("WARNING!!!")){
                 if(OrdMn.deleteOrder(code)){
                    JOptionPane.showMessageDialog(frmParent,"Deleted!");
                 }
             }else{
               JOptionPane.showMessageDialog(frmParent,"Order is done, cannot be deleted!","Warning",2);
             }
             FilterOrder(cusName,status,chooseDate);
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void tblOrderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrderMousePressed
        // TODO add your handling code here:
        int index = tblOrder.getSelectedRow();
        if(evt.getClickCount()==2){
            ViewOrderDetailForm addNew = new ViewOrderDetailForm(this.frmParent,true,getCodeSelectedRow(),String.valueOf(tblOrder.getValueAt(index,0)));
            addNew.setLocationRelativeTo(this);
            addNew.setVisible(true);
            FilterOrder(cusName,status,chooseDate);
            tblOrder.removeEditor();
        }else{
            tblOrder.removeEditor();
        }
    }//GEN-LAST:event_tblOrderMousePressed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        // TODO add your handling code here:
        cusName = txtSearch.getText().trim();
        String sta = String.valueOf(cbStatus.getSelectedItem());
        status="";
        if(sta.equals("All")){
            status="";
        }else if(sta.equals("Done")){
            status="Done!";
        }else if(sta.equals("Waiting")){
            status="Waiting...";
        }else if(sta.equals("Expired Today")){
            status="WARNING!!!";
        }else if(sta.equals("Transfer Delay")){
            status="Transfer Delay";
        }
        chooseDate="";
        String cDate="";
        String item = String.valueOf(cbDate.getSelectedItem());
        if(dcDateOrder.getDate() == null ){
            if(item.equals("All")){
                chooseDate="";
            }else if(item.equals("Today")){
                chooseDate=String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));
            }
        }else{
            if(item.equals("Today")){
                JOptionPane.showMessageDialog(this.frmParent,"You choose Date Order and Today for filter."+"\n"+"Result is Today only!");
                chooseDate=String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));
            }else{
                chooseDate = String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(dcDateOrder.getDate()));
            }
        }

        FilterOrder(cusName,status,chooseDate);
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowAllActionPerformed
        // TODO add your handling code here:
        getAllOrder();
        txtSearch.setText("");
        cusName="";
        cbDate.setSelectedIndex(0);
        chooseDate="";
        cbStatus.setSelectedIndex(0);
        status="";
        dcDateOrder.setDate(null);
    }//GEN-LAST:event_btnShowAllActionPerformed
    private String cusName="";
    private String status="";
    private String chooseDate="";
    private String today;
    private String checkedStatus;
    private int condition;

    private Date current;
    private boolean fl;
    private String Cday;
    private String[] Carray;
    private String getC;
    private UserManage usMn;
    private OrderManage OrdMn;
    private CustomerManage CustMn;
    private msbsOrders Orders;
    public Vector<msbsOrders> TempOrders;
    private Vector<msbsOrders> allOrders;
    private DefaultTableModel OrderTableModel;
    private JFrame frmParent;
    static String ordCode;
    private TableColumn column;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnDetails;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnShowAll;
    private javax.swing.JComboBox cbDate;
    private javax.swing.JComboBox cbStatus;
    private com.toedter.calendar.JDateChooser dcDateOrder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblOrder;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

}
