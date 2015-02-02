/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReportModule.java
 *
 * Created on Nov 19, 2009, 4:23:56 PM
 */

package GUI;

import Business.CustomerManage;
import Business.MedicineManage;
import Business.MedicineTypeManage;
import Business.OrderManage;
import Business.ReportDetailsManage;
import Business.ReportManage;
import Business.SupplierManage;
import Business.UserManage;
import Data.msbsCustomer;
import Data.msbsMedicine;
import Data.msbsOrders;
import Data.msbsReport;
import Data.msbsReportDetails;
import Data.msbsUser;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Administrator
 */
public class ReportModule extends javax.swing.JPanel {

    /** Creates new form ReportModule */
    public ReportModule() {
        allCust = new Vector<msbsCustomer>();
        allOrder = new Vector<msbsOrders>();
        custMn = new CustomerManage();
        ordMn = new OrderManage();
        tableColumn = new TableColumn();

        current = new Date(System.currentTimeMillis());
        sim = new SimpleDateFormat("yyyy-MM");
        today = sim.format(current);
       
        
        initComponents();
        getAllReport();
        getAllTardyBillsList();
        getOutStock();
        getExpiryDate();
    }
public void getAllReport() {
        Vector<msbsReport> allReport = new Vector<msbsReport>();
        allReport = new ReportManage().getAllReport();
        DefaultTableModel reportTableModel = new DefaultTableModel();
        reportTableModel.addColumn("Code");
        reportTableModel.addColumn("No");
        reportTableModel.addColumn("Report Name");
        reportTableModel.addColumn("Date Create");

        for (int i = 0; i < allReport.size(); i++) {
            msbsReport report = (msbsReport) allReport.get(i);
            String[] arrReportName = report.getReportName().split("_");
            String yearToCompare = arrReportName[2];
            if(yearToCompare.equals(String.valueOf(jYearChooser1.getYear()))){
                Vector newData = new Vector();
                newData.add(report.getPayedReportCode());
                newData.add(i + 1);
                newData.add(report.getReportName());
                newData.add(report.getDateCreated());
                reportTableModel.addRow(newData);
            }


        }
        tblPurchasal.setModel(reportTableModel);
        tblPurchasal.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblPurchasal.getColumnModel().getColumn(0).setMinWidth(0);
        tblPurchasal.getColumnModel().getColumn(0).setMaxWidth(0);
    }
public void getBillList(String Code){
        Vector<msbsReportDetails> reportDetails = new Vector<msbsReportDetails>();
        reportDetails = new ReportDetailsManage().getReportDetailsByCode(Code);
        DefaultTableModel OrderTableModel = new DefaultTableModel();
        OrderTableModel.addColumn("Order Code");
        OrderTableModel.addColumn("Date PayOff");
        OrderTableModel.addColumn("Customer");
        OrderTableModel.addColumn("Seller");
        OrderTableModel.addColumn("Payed Money");
        OrderTableModel.addColumn("Status");
        msbsOrders Orders = new msbsOrders();
        int totalBills = 0;
        double totalMoneys = 0.0;
        for (int i = 0; i < reportDetails.size(); i++) {
            msbsReportDetails ReportD = new msbsReportDetails();
            ReportD = (msbsReportDetails) reportDetails.get(i);
            Orders = ordMn.getOrdersByOrderCode(ReportD.getOrderCode());
            DecimalFormat df = new DecimalFormat(".#");
            Date currentD = new Date(System.currentTimeMillis());
            if(Orders.getStatus()==2){
                Vector newData = new Vector();
                newData.addElement(Orders.getOrderCode());
                newData.addElement(Orders.getExpiredTime());
                String CustName;
                CustName = new CustomerManage().getCustomerByCode(Orders.getCustomerCode()).getCustomerName();
                if(CustName.equals("unknown")){
                    newData.add("Guest");
                }else{
                    newData.add(CustName);
                }
                
                newData.addElement(new UserManage().getUserByCode(Orders.getUserCode()).getFullName());
                newData.addElement(df.format(Orders.getPaidMoney()));
                newData.add("Done");
                totalBills++;
                totalMoneys+=Orders.getPaidMoney();
                OrderTableModel.addRow(newData);
                tblBillList.setModel(OrderTableModel);
            }else if(Orders.getStatus()==1 && Orders.getExpiredTime().before(currentD)){
                Vector newData = new Vector();
                newData.addElement(Orders.getOrderCode());
                newData.addElement(Orders.getExpiredTime());
                String CustName;
                CustName = new CustomerManage().getCustomerByCode(Orders.getCustomerCode()).getCustomerName();
                if(CustName.equals("unknown")){
                    newData.add("Guest");
                }else{
                    newData.add(CustName);
                }
                
                newData.addElement(new UserManage().getUserByCode(Orders.getUserCode()).getFullName());
                newData.addElement(Orders.getPaidMoney());
                newData.add("Delay");
                totalBills++;
                totalMoneys+=Orders.getPaidMoney();
                OrderTableModel.addRow(newData);
                tblBillList.setModel(OrderTableModel);
            }

        }
        totalBillList.setText(String.valueOf(totalBills));
        lblTotalMoneys.setText(new DecimalFormat(".#").format(totalMoneys)+" $");
        //tblBillList.setModel(OrderTableModel);
        tableColumn = tblBillList.getColumnModel().getColumn(0);
        tableColumn.setPreferredWidth(0);
        tableColumn.setMinWidth(0);
        tableColumn.setMaxWidth(0);
    }


    public void getOutStock() {
        int totalOutMed;
        
        type="";
        Vector<msbsMedicine> allMedicine = new Vector<msbsMedicine>();
        allMedicine = new MedicineManage().getAllMedicine();
        modelCompList = new DefaultTableModel();
        modelCompList.addColumn("MedCode");
        modelCompList.addColumn("proCode");
        modelCompList.addColumn("Name");
        modelCompList.addColumn("Stock");
        modelCompList.addColumn("Origin");
        modelCompList.addColumn("Category");
        modelCompList.addColumn("Provider");

        totalOutMed = 0;
        for (msbsMedicine med : allMedicine) {
            
            Vector data = new Vector();
            if (med.getAvailableAmount() < 50) {
                data.add(med.getMedicineCode());
                data.add(new SupplierManage().getSupplierByCode(med.getSupplierCode()).getSupplierCode());
                data.add(med.getMedicineName());
                data.add(med.getAvailableAmount());
                data.add(med.getMedicineOrigine());
                data.add(new MedicineTypeManage().getTypeByCode(med.getMedicineTypeCode()).getMedicineTypeName());
                data.add(new SupplierManage().getSupplierByCode(med.getSupplierCode()).getSupplierName());
                modelCompList.addRow(data);
                totalOutMed++;
            }
        }
        tblOutStock.setModel(modelCompList);
        tableColumn = tblOutStock.getColumnModel().getColumn(0);
        tableColumn.setPreferredWidth(0);
        tableColumn.setMinWidth(0);
        tableColumn.setMaxWidth(0);
        tableColumn = tblOutStock.getColumnModel().getColumn(1);
        tableColumn.setPreferredWidth(0);
        tableColumn.setMinWidth(0);
        tableColumn.setMaxWidth(0);
        lblTotalMedicine1.setText(String.valueOf(totalOutMed));




    }

    public void getExpiryDate() {
        int totalOutMed;

        type="";
        Vector<msbsMedicine> allMedicine = new Vector<msbsMedicine>();
        allMedicine = new MedicineManage().getAllMedicine();
        modelCompList = new DefaultTableModel();
        modelCompList.addColumn("MedCode");
        modelCompList.addColumn("Name");
        modelCompList.addColumn("Expired");
        modelCompList.addColumn("Stock");
        modelCompList.addColumn("Origin");
        modelCompList.addColumn("Category");
        modelCompList.addColumn("Provider");

        totalOutMed = 0;
        for (msbsMedicine med : allMedicine) {
            String expired = sim.format(med.getExpiredDate());
            Vector data = new Vector();
            if (expired.equals(today) || med.getExpiredDate().before(current)) {
                data.add(med.getMedicineCode());
                data.add(med.getMedicineName());
                data.add(med.getExpiredDate());
                data.add(med.getAvailableAmount());
                data.add(med.getMedicineOrigine());
                data.add(new MedicineTypeManage().getTypeByCode(med.getMedicineTypeCode()).getMedicineTypeName());
                data.add(new SupplierManage().getSupplierByCode(med.getSupplierCode()).getSupplierName());
                modelCompList.addRow(data);
                totalOutMed++;
            }
        }
        tblExpiryDate.setModel(modelCompList);
        tableColumn = tblExpiryDate.getColumnModel().getColumn(0);
        tableColumn.setPreferredWidth(0);
        tableColumn.setMinWidth(0);
        tableColumn.setMaxWidth(0);
        lblTotalMedicine2.setText(String.valueOf(totalOutMed));




    }

    public void getAllTardyBillsList() {
        Date current = new Date(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String today = df.format(current);
        String status;
        totalBill=0;
        allOrder = new Vector<msbsOrders>();
        allOrder = ordMn.getAllOrder();
        modelTardyList = new DefaultTableModel();
        modelTardyList.addColumn("Order Code");
        modelTardyList.addColumn("Status");
        modelTardyList.addColumn("Customer");
        modelTardyList.addColumn("Seller");
        modelTardyList.addColumn("Date Order");
        modelTardyList.addColumn("Expired Date");
        modelTardyList.addColumn("Total Cost");
        modelTardyList.addColumn("Paid money");
        for (msbsOrders od : allOrder) {
            Vector newData = new Vector();
            if (od.getStatus() == 1 && od.getExpiredTime().before(new Date(System.currentTimeMillis()))) {
               if(!df.format(od.getExpiredTime()).equals(today)){
                   status = "Delay";
                   newData.add(od.getOrderCode());
                   newData.add(status);
                   newData.add(custMn.getCustomerByCode(od.getCustomerCode()).getCustomerName());
                   newData.add(new UserManage().getUserByCode(od.getUserCode()).getFullName());
                   newData.add(od.getOrderDate());
                   newData.add(od.getExpiredTime());
                   newData.add(new DecimalFormat(".#").format(od.getTotal()));
                   newData.add(new DecimalFormat(".#").format(od.getPaidMoney()));
                   modelTardyList.addRow(newData);
                   totalTardy++;
               }
// && od.getExpiredTime().after(new Date(System.currentTimeMillis()))
            } else if (od.getStatus() == 0 && od.getExpiredTime().before(new Date(System.currentTimeMillis()))) {
                if (!df.format(od.getExpiredTime()).equals(today)) {
                    status = "Transfer Delay";
                    newData.add(od.getOrderCode());
                    newData.add(status);
                    newData.add(custMn.getCustomerByCode(od.getCustomerCode()).getCustomerName());
                    newData.add(new UserManage().getUserByCode(od.getUserCode()).getFullName());
                    newData.add(od.getOrderDate());
                    newData.add(od.getExpiredTime());
                    newData.add( new DecimalFormat(".#").format(od.getTotal()));
                    newData.add( new DecimalFormat(".#").format(od.getPaidMoney()));
                    modelTardyList.addRow(newData);
                    totalTardy++;
                }

            }
        }
        tblTardyList.setModel(modelTardyList);
        tableColumn = tblTardyList.getColumnModel().getColumn(0);
        tableColumn.setPreferredWidth(0);
        tableColumn.setMinWidth(0);
        tableColumn.setMaxWidth(0);
        lblTotalTardy.setText(String.valueOf(totalTardy));
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        outStockPane = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblOutStock = new javax.swing.JTable();
        btnImportMed = new javax.swing.JButton();
        btnUpdateMed = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        lblTotalMedicine1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblExpiryDate = new javax.swing.JTable();
        btnViewDetails = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        lblTotalMedicine2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTardyList = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        lblTotalTardy = new javax.swing.JLabel();
        btnTardyDetails = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jLabel3 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblPurchasal = new javax.swing.JTable();
        btnFilter = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblBillList = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        totalBillList = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblTotalMoneys = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 400));

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "MEDICINE OUT OFF STOCK LIST", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        tblOutStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Stock", "Origin", "Category", "Provider"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOutStock.getTableHeader().setReorderingAllowed(false);
        tblOutStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblOutStockMousePressed(evt);
            }
        });
        jScrollPane12.setViewportView(tblOutStock);

        btnImportMed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Add.png"))); // NOI18N
        btnImportMed.setText("Update");
        btnImportMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportMedActionPerformed(evt);
            }
        });

        btnUpdateMed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/edit.png"))); // NOI18N
        btnUpdateMed.setText("Details of Distributor");
        btnUpdateMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateMedActionPerformed(evt);
            }
        });

        jLabel41.setText("Total Medicine out of stock: ");

        lblTotalMedicine1.setText("0");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41)
                .addGap(40, 40, 40)
                .addComponent(lblTotalMedicine1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 312, Short.MAX_VALUE)
                .addComponent(btnImportMed)
                .addGap(18, 18, 18)
                .addComponent(btnUpdateMed)
                .addContainerGap())
            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(btnUpdateMed, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalMedicine1)
                    .addComponent(btnImportMed)))
        );

        javax.swing.GroupLayout outStockPaneLayout = new javax.swing.GroupLayout(outStockPane);
        outStockPane.setLayout(outStockPaneLayout);
        outStockPaneLayout.setHorizontalGroup(
            outStockPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        outStockPaneLayout.setVerticalGroup(
            outStockPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Out of Stock", new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/outStock.png")), outStockPane); // NOI18N

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "MEDICINE OUT OFF STOCK LIST", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        tblExpiryDate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Stock", "Origin", "Category", "Provider"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblExpiryDate.getTableHeader().setReorderingAllowed(false);
        tblExpiryDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblExpiryDateMousePressed(evt);
            }
        });
        jScrollPane13.setViewportView(tblExpiryDate);

        btnViewDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/edit.png"))); // NOI18N
        btnViewDetails.setText("Update ");
        btnViewDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDetailsActionPerformed(evt);
            }
        });

        jLabel42.setText("Total Medicine : ");

        lblTotalMedicine2.setText("0");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalMedicine2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 559, Short.MAX_VALUE)
                .addComponent(btnViewDetails)
                .addContainerGap())
            .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(btnViewDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalMedicine2)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Expiry date ", new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/outStock.png")), jPanel1); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tardy Bills List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        tblTardyList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Status", "Customer", "Seller", "Order Date", "Expired Date", "Total Cost", "Paid Money"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
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
        tblTardyList.getTableHeader().setReorderingAllowed(false);
        tblTardyList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTardyListMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblTardyList);

        jLabel6.setText("Total Tardy Bills: ");

        lblTotalTardy.setText("0");

        btnTardyDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/edit.png"))); // NOI18N
        btnTardyDetails.setText("Bills Details");
        btnTardyDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTardyDetailsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalTardy, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(529, 529, 529)
                .addComponent(btnTardyDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(lblTotalTardy))
                    .addComponent(btnTardyDetails))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Tardy Bills", new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/warningIcon..png")), jPanel2); // NOI18N

        jLabel3.setText("Choose Year: ");

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Report List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        tblPurchasal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "No", "Report Name", "Date created"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPurchasal.getTableHeader().setReorderingAllowed(false);
        tblPurchasal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPurchasalMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblPurchasal);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
        );

        btnFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Filter.png"))); // NOI18N
        btnFilter.setText("Filter");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bill List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        tblBillList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date Payoff", "Customer", "Seller", "Paid Money", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBillList.getTableHeader().setReorderingAllowed(false);
        tblBillList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBillListMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblBillListMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblBillListMouseReleased(evt);
            }
        });
        jScrollPane8.setViewportView(tblBillList);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
        );

        jLabel1.setText("Total of Bill:");

        totalBillList.setText("0");

        jLabel4.setText("Total Money :");

        lblTotalMoneys.setText("0");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/graph.png"))); // NOI18N
        jButton1.setText("View Report Graph");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(btnFilter))
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalBillList, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalMoneys, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFilter)
                        .addComponent(jButton1))
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(totalBillList)
                        .addComponent(jLabel4)
                        .addComponent(lblTotalMoneys)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Purchasal", new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/BillList.png")), jPanel3); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblTardyListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTardyListMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() > 1) {
            tblTardyList.removeEditor();

            ViewOrderDetailForm tardyDetails = new ViewOrderDetailForm(frmParent, true, ordMn.getOrdersByOrderCode(String.valueOf(tblTardyList.getValueAt(tblTardyList.getSelectedRow(), 0))),"");
            tardyDetails.setLocationRelativeTo(this);
            tardyDetails.setVisible(true);

        }
}//GEN-LAST:event_tblTardyListMousePressed

    private void btnTardyDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTardyDetailsActionPerformed
        // TODO add your handling code here:
        if (tblTardyList.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Select a bill in Bills List to view details! ");
        } else if (tblTardyList.getSelectedRowCount() > 1) {
            JOptionPane.showMessageDialog(this, "Do not select more one bill !");
        } else {
            ViewOrderDetailForm tardyDetails = new ViewOrderDetailForm(frmParent, true, ordMn.getOrdersByOrderCode(String.valueOf(tblTardyList.getValueAt(tblTardyList.getSelectedRow(), 0))),"");
            tardyDetails.setLocationRelativeTo(this);
            tardyDetails.setVisible(true);
        }
}//GEN-LAST:event_btnTardyDetailsActionPerformed

    private void tblOutStockMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOutStockMousePressed
        // TODO add your handling code here:
        tblOutStock.removeEditor();
}//GEN-LAST:event_tblOutStockMousePressed

    private void btnImportMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportMedActionPerformed
        // TODO add your handling code here:
        if(tblOutStock.getSelectedRowCount()!= 1){
            JOptionPane.showMessageDialog(this,"Please choose only one Medicine in List to import! ","WRANING",JOptionPane.WARNING_MESSAGE);
        }else{
            ViewDetailMedicineForm addNew = new ViewDetailMedicineForm(this.frmParent, true, new MedicineManage().getMedicineByCode((int) Integer.valueOf(String.valueOf(tblExpiryDate.getValueAt(tblExpiryDate.getSelectedRow(), 0)))));
            addNew.setLocationRelativeTo(this);
            addNew.setVisible(true);
        }
        getOutStock();

        //fillMedicineCatelory();
    }//GEN-LAST:event_btnImportMedActionPerformed

    private void btnUpdateMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateMedActionPerformed
        // TODO add your handling code here:
        if (tblOutStock.getSelectedRowCount() < 1) {
            JOptionPane.showMessageDialog(this, "Please choose one to view details Provider !", "WRANING", JOptionPane.WARNING_MESSAGE);
        } else if (tblOutStock.getSelectedRowCount() > 1) {
            JOptionPane.showMessageDialog(this, "Cannot choose more than one  !", "WRANING", JOptionPane.WARNING_MESSAGE);
        } else {
            UpdateDistributorForm detail = new UpdateDistributorForm(frmParent, false, new SupplierManage().getSupplierByCode((int) Integer.valueOf(String.valueOf(tblOutStock.getValueAt(tblOutStock.getSelectedRow(), 1)))));
            detail.setLocationRelativeTo(this);
            detail.setVisible(true);
        }
}//GEN-LAST:event_btnUpdateMedActionPerformed

    private void tblPurchasalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPurchasalMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()>1){
            tblPurchasal.removeEditor();
        }
        String code = String.valueOf(tblPurchasal.getValueAt(tblPurchasal.getSelectedRow(), 0));
        //JOptionPane.showMessageDialog(this, code);
        getBillList(code);
}//GEN-LAST:event_tblPurchasalMouseClicked

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        // TODO add your handling code here:
        getAllReport();
}//GEN-LAST:event_btnFilterActionPerformed

    private void tblBillListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillListMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()>1){
            tblBillList.removeEditor();
            ViewOrderDetailForm addNew = new ViewOrderDetailForm(this.frmParent, true, new OrderManage().getOrdersByOrderCode(String.valueOf(tblBillList.getValueAt(tblBillList.getSelectedRow(), 0))), "");
            addNew.setLocationRelativeTo(this);
            addNew.setVisible(true);
        }
    }//GEN-LAST:event_tblBillListMouseClicked

    private void tblBillListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillListMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblBillListMouseReleased

    private void tblBillListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillListMousePressed
        if(evt.getClickCount()>1){
            tblBillList.removeEditor();
            ViewOrderDetailForm addNew = new ViewOrderDetailForm(this.frmParent, true, new OrderManage().getOrdersByOrderCode(String.valueOf(tblBillList.getValueAt(tblBillList.getSelectedRow(), 0))), "");
            addNew.setLocationRelativeTo(this);
            addNew.setVisible(true);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tblBillListMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(tblPurchasal.getSelectedRowCount()!=1){
            JOptionPane.showMessageDialog(this.frmParent,"Please select one Report to view details!","Warning",2);
        }else{
            int index = tblPurchasal.getSelectedRow();
            String ReportName = String.valueOf(tblPurchasal.getValueAt(index, 2)).trim();
            String[] Rtime = ReportName.split("_");
            String Ryear = Rtime[2];
            String Rmonth = Rtime[1];
            String RepCode = new ReportManage().getCodeByName(ReportName);

            GraphModule addNew = new GraphModule(this.frmParent, true,RepCode,ReportName,Rmonth,Ryear);
            addNew.setLocationRelativeTo(this);
            addNew.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblExpiryDateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblExpiryDateMousePressed
        // TODO add your handling code here:
        tblExpiryDate.removeEditor();
        if(evt.getClickCount()==2){
            ViewDetailMedicineForm addNew = new ViewDetailMedicineForm(this.frmParent, false, new MedicineManage().getMedicineByCode((int) Integer.valueOf(String.valueOf(tblExpiryDate.getValueAt(tblExpiryDate.getSelectedRow(), 0)))));
         addNew.setLocationRelativeTo(this);
         addNew.setVisible(true);
        }
}//GEN-LAST:event_tblExpiryDateMousePressed

    private void btnViewDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetailsActionPerformed
        // TODO add your handling code here:
         if (tblExpiryDate.getSelectedRowCount() == 1) {
         ViewDetailMedicineForm addNew = new ViewDetailMedicineForm(this.frmParent, true, new MedicineManage().getMedicineByCode((int) Integer.valueOf(String.valueOf(tblExpiryDate.getValueAt(tblExpiryDate.getSelectedRow(), 0)))));
         addNew.setLocationRelativeTo(this);
         addNew.setVisible(true);
         getExpiryDate();
     } else if (tblExpiryDate.getSelectedRowCount() > 1) {
         JOptionPane.showMessageDialog(this, "Do not select more than one Medicine", "WARNING", JOptionPane.WARNING_MESSAGE);
     } else {
         JOptionPane.showMessageDialog(this, "Please select a Medicine", "WARNING", JOptionPane.WARNING_MESSAGE);
     }
}//GEN-LAST:event_btnViewDetailsActionPerformed
    private SimpleDateFormat sim;
    private Date current;
    private String today;
    private String custName = null;
    private String type = null;
    private int totalBill;
    private int totalTardy;
    private TableColumn tableColumn;
    private Vector<msbsOrders> allOrder;
    private Vector<msbsCustomer> allCust;
    private OrderManage ordMn;
    private CustomerManage custMn;
    private DefaultTableModel modelCompList;
    private DefaultTableModel modelTardyList;
    private msbsUser user;
    private JFrame frmParent;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnImportMed;
    private javax.swing.JButton btnTardyDetails;
    private javax.swing.JButton btnUpdateMed;
    private javax.swing.JButton btnViewDetails;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane2;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JLabel lblTotalMedicine1;
    private javax.swing.JLabel lblTotalMedicine2;
    private javax.swing.JLabel lblTotalMoneys;
    private javax.swing.JLabel lblTotalTardy;
    private javax.swing.JPanel outStockPane;
    private javax.swing.JTable tblBillList;
    private javax.swing.JTable tblExpiryDate;
    private javax.swing.JTable tblOutStock;
    private javax.swing.JTable tblPurchasal;
    private javax.swing.JTable tblTardyList;
    private javax.swing.JLabel totalBillList;
    // End of variables declaration//GEN-END:variables

}
