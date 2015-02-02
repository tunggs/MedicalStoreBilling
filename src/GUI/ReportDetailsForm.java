/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RepostDetails.java
 *
 * Created on Nov 13, 2009, 3:06:21 PM
 */

package GUI;
import Business.ReportDetailsManage;
import Business.CustomerManage;
import Business.OrderManage;
import Data.msbsCustomer;
import Data.msbsOrders;
import Data.msbsReportDetails;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Administrator
 */
public class ReportDetailsForm extends javax.swing.JDialog {
    String month;
    String year;
    String Code;


    /** Creates new form RepostDetails */
    public ReportDetailsForm(java.awt.Frame parent, boolean modal,String RepCode) {
        super(parent, modal);
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
        
        reportDetailsMn = new ReportDetailsManage();
        this.Code = RepCode;
        getAllOrder();
        double totalMoney =  0;
        for(int i = 0; i<tblReportDetail.getRowCount(); i++){
            if(!String.valueOf(tblReportDetail.getValueAt(i,3)).equals("Delay...")){
                double money = Double.valueOf(String.valueOf(tblReportDetail.getValueAt(i,3)));
                totalMoney = totalMoney + money;
            }
        }
        txtTotalPrice.setText(String.valueOf(new DecimalFormat(".#").format(totalMoney)));

    }

    public void getAllOrder(){
        String name=null;
        if(txtSearch.getText().equals(null)||txtSearch.getText().equals("")){
            name = "";
        }else{
            name = txtSearch.getText().trim();
        }
        Vector<msbsReportDetails> reportDetails = new Vector<msbsReportDetails>();
        reportDetails = reportDetailsMn.getReportDetailsByCode(Code);

        CustomerManage CustomerMn = new CustomerManage();
        msbsCustomer customer = new msbsCustomer();
        allOrders = OrdMn.getAllOrder();
        OrderTableModel = new DefaultTableModel();
        OrderTableModel.addColumn("Order Code");
        OrderTableModel.addColumn("Date PayOff");
        OrderTableModel.addColumn("Customer");
        OrderTableModel.addColumn("Payed Money");
        OrderTableModel.addColumn("Status");


        for (int i = 0; i < reportDetails.size(); i++) {
            msbsReportDetails ReportD = new msbsReportDetails();
            ReportD = (msbsReportDetails) reportDetails.get(i);
            Orders = OrdMn.getOrdersByOrderCode(ReportD.getOrderCode());
            DecimalFormat df = new DecimalFormat(".#");
            Date currentD = new Date(System.currentTimeMillis());
            if(Orders.getStatus()==2 && CustomerMn.getCustomerByCode(Orders.getCustomerCode()).getCustomerName().toLowerCase().matches(".*"+name.toLowerCase()+".*")){
                Vector newData = new Vector();
                customer = CustomerMn.getCustomerByCode(Orders.getCustomerCode());
                newData.addElement(Orders.getOrderCode());
                newData.addElement(Orders.getExpiredTime());
                newData.addElement(customer.getCustomerName());
                newData.addElement(df.format(Orders.getPaidMoney()));
                newData.addElement("Done!");

                OrderTableModel.addRow(newData);
            }else if(Orders.getStatus()==1 && Orders.getExpiredTime().before(currentD) && CustomerMn.getCustomerByCode(Orders.getCustomerCode()).getCustomerName().toLowerCase().matches(".*"+name.toLowerCase()+".*")){
                Vector newData = new Vector();
                customer = CustomerMn.getCustomerByCode(Orders.getCustomerCode());
                newData.addElement(Orders.getOrderCode());
                newData.addElement(Orders.getExpiredTime());
                newData.addElement(customer.getCustomerName());
                newData.addElement(df.format(Orders.getPaidMoney()));
                newData.addElement("Delay...");

                OrderTableModel.addRow(newData);
            }

        }  
        tblReportDetail.setModel(OrderTableModel);
        TableColumn tableColumn = tblReportDetail.getColumnModel().getColumn(0);
        tableColumn.setPreferredWidth(0);
        tableColumn.setMinWidth(0);
        tableColumn.setMaxWidth(0);
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
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblReportDetail = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtTotalPrice = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Report Details");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Search Order Owner", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        jLabel1.setText("Name");

        txtSearch.setToolTipText("Enter name of Customer who you want to view Order");

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Filter.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(51, 51, 51)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSearch)
                .addContainerGap(185, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/edit.png"))); // NOI18N
        jButton1.setText("View Order Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "ReportDetails List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        tblReportDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date PayOff", "Customer", "Paid Money", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblReportDetail.getTableHeader().setReorderingAllowed(false);
        tblReportDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblReportDetailMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblReportDetail);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Total PaidMoney");

        txtTotalPrice.setEditable(false);

        jLabel3.setText("$");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel2)
                    .addComponent(txtTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(tblReportDetail.getSelectedRowCount()!=1){
            JOptionPane.showMessageDialog(this.frmParent,"Please select one Order to view details!","Warning",2);
        }else{
            int index = tblReportDetail.getSelectedRow();
            String orderCode = String.valueOf(tblReportDetail.getValueAt(index, 0)).trim();
            msbsOrders ord = new msbsOrders();
            ord = OrdMn.getOrdersByOrderCode(orderCode);

            ViewOrderDetailForm addNew = new ViewOrderDetailForm(this.frmParent,true,ord,"");
            addNew.setLocationRelativeTo(this);
            addNew.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblReportDetailMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblReportDetailMousePressed
        // TODO add your handling code here:
        if(evt.getClickCount()>1){
            int index = tblReportDetail.getSelectedRow();
            String orderCode = String.valueOf(tblReportDetail.getValueAt(index, 0)).trim();
            msbsOrders ord = new msbsOrders();
            ord = OrdMn.getOrdersByOrderCode(orderCode);
            tblReportDetail.removeEditor();
            ViewOrderDetailForm addNew = new ViewOrderDetailForm(this.frmParent,true,ord,"");
            addNew.setLocationRelativeTo(this);
            addNew.setVisible(true);
        }else{
            tblReportDetail.removeEditor();
        }
    }//GEN-LAST:event_tblReportDetailMousePressed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        getAllOrder();
    }//GEN-LAST:event_btnSearchActionPerformed

    /**
    * @param args the command line arguments
    */
    private msbsOrders OCode;
    private ReportDetailsManage reportDetailsMn;
    static int count=0;
    private boolean first=false;
    private OrderManage OrdMn;
    private CustomerManage CustMn;
    private msbsOrders Orders;
    private Vector<msbsOrders> allOrders;
    private DefaultTableModel OrderTableModel;
    private JFrame frmParent;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblReportDetail;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTotalPrice;
    // End of variables declaration//GEN-END:variables

}
