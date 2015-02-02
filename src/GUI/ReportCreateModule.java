/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReportCreateModule.java
 *
 * Created on Nov 13, 2009, 2:25:00 PM
 */
package GUI;

import Business.OrderManage;
import Business.ReportDetailsManage;
import Business.ReportManage;
import Data.msbsOrders;
import Data.msbsReport;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class ReportCreateModule extends javax.swing.JPanel {

    String time = null;
    String month;
    String year;

    /** Creates new form ReportCreateModule */
    public ReportCreateModule() throws UnsupportedLookAndFeelException {
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

        month = String.valueOf(cbMonth.getSelectedItem());
        year = String.valueOf(ycYear.getValue());
        allReport = new Vector<msbsReport>();
        reportMn = new ReportManage();
        report = new msbsReport();
        OrdMn = new OrderManage();
        Orders = new msbsOrders();
        ReportDetailsMn = new ReportDetailsManage();


        getAllReport();
    }

    public void getAllReport() {

        allReport = reportMn.getAllReport();
        reportTableModel = new DefaultTableModel();
        reportTableModel.addColumn("Report Name");
        reportTableModel.addColumn("Date Create");
        for (int i = 0; i < allReport.size(); i++) {
            report = (msbsReport) allReport.get(i);
            Vector newData = new Vector();
            newData.add(report.getReportName());
            newData.add(report.getDateCreated());
            reportTableModel.addRow(newData);

        }
        tblReport.setModel(reportTableModel);
    }

    public void addReport() {
        month = String.valueOf(cbMonth.getSelectedItem());
        year = String.valueOf(ycYear.getValue());
        time = String.valueOf(month);
        time += "_";
        time += String.valueOf(year);
        Date todayD = new Date(System.currentTimeMillis());

        Calendar currentTime = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        this.ReportCode = "";
        this.ReportCode += format.format(currentTime.getTime());
        this.reportName = "Report_" + time;
        

        allReport = reportMn.getAllReport();
        boolean check = false;
        for (int i = 0; i < allReport.size(); i++) {
            msbsReport Report = new msbsReport();
            Report = (msbsReport) allReport.get(i);
            if (this.reportName.equals(Report.getReportName())) {
                check = true;
                break;
            } else {
                check = false;
            }
        }
        if (check == true) {
            
            ReportDetailsMn.deleteReportDetailsByCode(reportMn.getCodeByName(this.reportName));
            addReportDetails();
            reportMn.updateReport(reportMn.getCodeByName(this.reportName), todayD);
            JOptionPane.showMessageDialog(this, "Updated!");
            
        } else if (check == false) {
            reportMn.insertReport(this.ReportCode, this.reportName, todayD);
            addReportDetails();
            JOptionPane.showMessageDialog(this, "Created!");
        }

    }

    public void addReportDetails() {
        String[] arrReportName = this.reportName.split("_");
        String monthToCompare = arrReportName[1];
        String yearToCompare = arrReportName[2];
        if (monthToCompare.equals("January")) {
            monthCompare = "1";
        } else if (monthToCompare.equals("February")) {
            monthCompare = "2";
        } else if (monthToCompare.equals("March")) {
            monthCompare = "3";
        } else if (monthToCompare.equals("April")) {
            monthCompare = "4";
        } else if (monthToCompare.equals("May")) {
            monthCompare = "5";
        } else if (monthToCompare.equals("June")) {
            monthCompare = "6";
        } else if (monthToCompare.equals("July")) {
            monthCompare = "7";
        } else if (monthToCompare.equals("August")) {
            monthCompare = "8";
        } else if (monthToCompare.equals("September")) {
            monthCompare = "9";
        } else if (monthToCompare.equals("October")) {
            monthCompare = "10";
        } else if (monthToCompare.equals("November")) {
            monthCompare = "11";
        } else if (monthToCompare.equals("December")) {
            monthCompare = "12";
        }
        allOrders = OrdMn.getAllOrder();

        for (int i = 0; i < allOrders.size(); i++) {
            Orders = (msbsOrders) allOrders.get(i);
            Date ex = Orders.getExpiredTime();
            String Eday = String.valueOf(ex);
            String[] Earray = Eday.split("-");
            String getE = Earray[2];
            Date current = new Date(System.currentTimeMillis());
            String Cday = String.valueOf(current);
            String[] Carray = Cday.split("-");
            String getC = Carray[2];
            boolean fl = getC.equals(getE);
            String status;
            if (Orders.getStatus() == 1 && Orders.getExpiredTime().before(new Date(System.currentTimeMillis()))) {
                if(fl==false){
                    status = "Paying";
                }else{
                    status="Waiting...";
                }
            } else {
                status = "Done!";
            }
            String[] arrET = String.valueOf(Orders.getExpiredTime()).split("-");
            if ((yearToCompare.equals(arrET[0]) && monthCompare.equals(arrET[1]))
                 && status.equals("Done!")
                 || (yearToCompare.equals(arrET[0]) && monthCompare.equals(arrET[1]))
                 &&  status.equals("Paying") ) {
                ReportDetailsMn.insertReportDetails(reportMn.getCodeByName(this.reportName), Orders.getOrderCode());
            }
        }
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
        tblReport = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btCreate = new javax.swing.JButton();
        cbMonth = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        ycYear = new com.toedter.calendar.JYearChooser();
        btnViewGraph = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Report List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        tblReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Report Name", "Date Created"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblReportMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblReport);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/edit.png"))); // NOI18N
        jButton1.setText("Report Details");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btCreate.setText("Create");
        btCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCreateActionPerformed(evt);
            }
        });

        cbMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Month...", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Choose month and year to create report ");

        btnViewGraph.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/graph.png"))); // NOI18N
        btnViewGraph.setText("View Report Graph");
        btnViewGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewGraphActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ycYear, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btCreate)))
                        .addGap(324, 324, 324)
                        .addComponent(btnViewGraph)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(btnViewGraph))
                    .addComponent(cbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ycYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(tblReport.getSelectedRowCount()!=1){
            JOptionPane.showMessageDialog(this.frmParent,"Please select one Report to view details!","Warning",2);
        }else{
            int index = tblReport.getSelectedRow();
            String ReportName = String.valueOf(tblReport.getValueAt(index, 0)).trim();
            String RepCode = reportMn.getCodeByName(ReportName);

            ReportDetailsForm addNew = new ReportDetailsForm(this.frmParent, true, RepCode);
            addNew.setLocationRelativeTo(this);
            addNew.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCreateActionPerformed
        // TODO add your handling code here:
        int reMonth = cbMonth.getSelectedIndex();
        int reYear = ycYear.getValue();
        Date current = new Date(System.currentTimeMillis());
        String Cday = String.valueOf(current);
        String[] Carray = Cday.split("-");
        int currentMonth = Integer.valueOf(Carray[1]);
        int currentYear = Integer.valueOf(Carray[0]);
        if (cbMonth.getSelectedIndex() != 0) {
            if(currentYear<reYear || (currentYear==reYear && currentMonth<reMonth)){
                JOptionPane.showMessageDialog(this, "Cannot create Report for future!");
            }else{
                addReport();
                getAllReport();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please choose Month");
        }
}//GEN-LAST:event_btCreateActionPerformed

    private void tblReportMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblReportMousePressed
        // TODO add your handling code here:
        if(evt.getClickCount()>1){
            int index = tblReport.getSelectedRow();
            String ReportName = String.valueOf(tblReport.getValueAt(index, 0)).trim();
            String RepCode = reportMn.getCodeByName(ReportName);
            tblReport.removeEditor();
            ReportDetailsForm addNew = new ReportDetailsForm(this.frmParent, true, RepCode);
            addNew.setLocationRelativeTo(this);
            addNew.setVisible(true);
        }else{
            tblReport.removeEditor();
        }
    }//GEN-LAST:event_tblReportMousePressed

    private void btnViewGraphActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewGraphActionPerformed
        // TODO add your handling code here:
        if(tblReport.getSelectedRowCount()!=1){
            JOptionPane.showMessageDialog(this.frmParent,"Please select one Report to view details!","Warning",2);
        }else{
            int index = tblReport.getSelectedRow();
            String ReportName = String.valueOf(tblReport.getValueAt(index, 0)).trim();
            String[] Rtime = ReportName.split("_");
            String Ryear = Rtime[2];
            String Rmonth = Rtime[1];
            String RepCode = reportMn.getCodeByName(ReportName);

            GraphModule addNew = new GraphModule(this.frmParent, true,RepCode,ReportName,Rmonth,Ryear);
            addNew.setLocationRelativeTo(this);
            addNew.setVisible(true);
        }

}//GEN-LAST:event_btnViewGraphActionPerformed
    private OrderManage OrdMn;
    private Vector<msbsOrders> allOrders;
    private String monthCompare;
    private String ReportCode;
    private msbsReport report;
    private DefaultTableModel reportTableModel;
    private Vector<msbsReport> allReport;
    private ReportManage reportMn;
    private JFrame frmParent;
    private msbsOrders Orders;
    private String reportName;
    private ReportDetailsManage ReportDetailsMn;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCreate;
    private javax.swing.JButton btnViewGraph;
    private javax.swing.JComboBox cbMonth;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblReport;
    private com.toedter.calendar.JYearChooser ycYear;
    // End of variables declaration//GEN-END:variables
}
