/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DistributorModule.java
 *
 * Created on Nov 7, 2009, 11:01:06 PM
 */

package GUI;

import Access.SupplierAccess;
import Business.MedicineManage;
import Business.SupplierManage;
import Data.msbsMedicine;
import Data.msbsSupplier;
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
public class DistributorModule extends javax.swing.JPanel {

    /** Creates new form DistributorModule */
    public DistributorModule() throws UnsupportedLookAndFeelException {

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
        supplier = new msbsSupplier();
        supMn = new SupplierManage();
        allSup = new Vector<msbsSupplier>();
        tableColumn = new TableColumn();
        allMed = new Vector<msbsMedicine>();
        medMn = new MedicineManage();
        initComponents();
        getAllSupplier();
    }
    public void getAllSupplier(){
        allSup = new Vector<msbsSupplier>();
        allSup = supMn.getAllSupplier();
        String supName;
        
        if (txtProviderName.getText().trim().equals(null) || txtProviderName.getText().trim().equals("")) {
            supName = "";
        } else {
            supName = txtProviderName.getText().trim().toLowerCase();
        }
        
        modelTableDistributorList = new DefaultTableModel();

        modelTableDistributorList.addColumn("Dist code");
        modelTableDistributorList.addColumn("Name");
        modelTableDistributorList.addColumn("Phone");
        modelTableDistributorList.addColumn("Email");
        modelTableDistributorList.addColumn("Address");
       
        totalDistributor = 0;
        for(int i = 0; i < allSup.size(); i++){
            supplier = (msbsSupplier)allSup.get(i);
            Vector newData = new Vector();
            if (supplier.getSupplierName().toLowerCase().matches(".*" + supName.toLowerCase() + ".*")) {
                  newData.addElement(supplier.getSupplierCode());
                newData.addElement(supplier.getSupplierName());
                newData.addElement(supplier.getSupplierPhone());
                newData.addElement(supplier.getSupplierEmail());
                newData.addElement(supplier.getSupplierAddress());
                modelTableDistributorList.addRow(newData);
                totalDistributor++;
            }
        }
        tblSupplier.setModel(modelTableDistributorList);
        tableColumn = tblSupplier.getColumnModel().getColumn(0);
        tableColumn.setPreferredWidth(0);
        tableColumn.setMinWidth(0);
        tableColumn.setMaxWidth(0);
        lblTotalDis.setText(String.valueOf(totalDistributor));
    }

    public msbsSupplier getCodeSelectedRow(){
        int index = tblSupplier.getSelectedRow();
        int supCode = (int)Integer.valueOf(String.valueOf(tblSupplier.getValueAt(index,0)));
        supplier = supMn.getSupplierByCode(supCode);
        if(supplier != null){
            return supplier;
        }
        return null ;
    }
    public void fillMedicine(){
        String medName;
        if (txtMedName.getText().trim().equals(null) || txtMedName.getText().trim().equals("")) {
            medName = "";
        } else {
            medName = txtMedName.getText().trim().toLowerCase();
        }
        tblSupplier.removeEditor();
        tblMedicineList.removeEditor();
        modelTableMedicineList = new DefaultTableModel();
        allMed = new Vector<msbsMedicine>();
        modelTableMedicineList.addColumn("code");
        modelTableMedicineList.addColumn("Medicine Name");
        modelTableMedicineList.addColumn("Stock");
        modelTableMedicineList.addColumn("Price Per Unit");

        totalMedicine=0;
        allMed = medMn.getMedicineBySupplier(getCodeSelectedRow().getSupplierCode());
        if(allMed!=null){
            for (msbsMedicine med : allMed) {
                Vector data = new Vector();
                if(med.getMedicineName().toLowerCase().matches(".*"+medName.toLowerCase()+".*")){
                    data.add(med.getMedicineCode());
                    data.add(med.getMedicineName());
                    data.add(med.getAvailableAmount());
                    data.add(med.getPricePerUnit());
                    modelTableMedicineList.addRow(data);
                    totalMedicine++;
                }
                
            }
        }
        tblMedicineList.setModel(modelTableMedicineList);
        tableColumn = tblMedicineList.getColumnModel().getColumn(0);
        tableColumn.setPreferredWidth(0);
        tableColumn.setMinWidth(0);
        tableColumn.setMaxWidth(0);
        lblTotalMedicine.setText(String.valueOf(totalMedicine));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnAddSup = new javax.swing.JButton();
        btnDeleteSup = new javax.swing.JButton();
        btnUpdateSup = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblTotalDis = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblTotalMedicine = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSupplier = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMedicineList = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtProviderName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMedName = new javax.swing.JTextField();

        btnAddSup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Add.png"))); // NOI18N
        btnAddSup.setText("Add");
        btnAddSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSupActionPerformed(evt);
            }
        });

        btnDeleteSup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Delete.png"))); // NOI18N
        btnDeleteSup.setText("Delete");
        btnDeleteSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSupActionPerformed(evt);
            }
        });

        btnUpdateSup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Update.png"))); // NOI18N
        btnUpdateSup.setText("Edit");
        btnUpdateSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateSupActionPerformed(evt);
            }
        });

        jLabel2.setText("Total Distributor: ");

        lblTotalDis.setText("total");

        jLabel4.setText("Total Medicine of Distributor:");

        lblTotalMedicine.setText("total");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Distributor List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        tblSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Phone", "Email", "Address"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tblSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSupplierMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSupplierMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblSupplier);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Medicine by Distributor List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        tblMedicineList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine Name", "Available Amount", "Price Per Unit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblMedicineList.getTableHeader().setReorderingAllowed(false);
        tblMedicineList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblMedicineListMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblMedicineList);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filter Distributor & Medicine", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        jLabel3.setText("Provider name:");

        txtProviderName.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtProviderNameCaretUpdate(evt);
            }
        });

        jLabel5.setText("Medicine name:");

        txtMedName.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMedNameCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtProviderName, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtMedName, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(txtMedName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3)
                .addComponent(txtProviderName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalDis)
                .addGap(82, 82, 82)
                .addComponent(btnAddSup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdateSup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDeleteSup, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalMedicine)
                .addContainerGap(184, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTotalDis)
                    .addComponent(jLabel4)
                    .addComponent(lblTotalMedicine)
                    .addComponent(btnAddSup, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdateSup, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteSup, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSupActionPerformed
        // TODO add your handling code here:

        AddDistributorForm addNew = new AddDistributorForm(this.frmParent,true);
        addNew.setLocationRelativeTo(this);
        addNew.setVisible(true);
        getAllSupplier();

}//GEN-LAST:event_btnAddSupActionPerformed

    private void btnUpdateSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateSupActionPerformed
        // TODO add your handling code here:
        if(tblSupplier.getSelectedRowCount()==1){
            msbsSupplier editSup = getCodeSelectedRow();
            UpdateDistributorForm updateSupForm = new UpdateDistributorForm(this.frmParent,true,editSup);
            updateSupForm.setLocationRelativeTo(this);
            updateSupForm.setVisible(true);
            getAllSupplier();
        }else if(tblSupplier.getSelectedRowCount()>1){
            JOptionPane.showMessageDialog(this,"Do not select more one Provider", "WARNING", JOptionPane.WARNING_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this,"Please Select a Provider", "WARNING", JOptionPane.WARNING_MESSAGE);
        }
        
}//GEN-LAST:event_btnUpdateSupActionPerformed

    private void btnDeleteSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSupActionPerformed
        // TODO add your handling code here:
        msbsSupplier tempSup = null;
        if(tblSupplier.getSelectedRowCount()==1){
            tempSup = getCodeSelectedRow();
            int result;
            result = JOptionPane.showConfirmDialog(frmParent, "Are you sure delete this User? ");
            if(result == JOptionPane.YES_OPTION){
                if(supMn.deleteSupplier(tempSup.getSupplierCode())){
                    JOptionPane.showMessageDialog(frmParent, "Delete successfull! ");
                }else{
                    JOptionPane.showMessageDialog(this, "This Provider has one or more Medicine", "CANNOT DELETE", JOptionPane.ERROR_MESSAGE);
                }

                getAllSupplier();
            }else if(result == JOptionPane.NO_OPTION){
                getAllSupplier();
            }
        }else if(tblSupplier.getSelectedRowCount()>1){
            JOptionPane.showMessageDialog(this,"Do not select more one Provider", "WARNING", JOptionPane.WARNING_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this,"Please Select a Provider", "WARNING", JOptionPane.WARNING_MESSAGE);
        }
}//GEN-LAST:event_btnDeleteSupActionPerformed

    private void tblSupplierMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSupplierMousePressed
        // TODO add your handling code here:
        tblSupplier.removeEditor();
        if(evt.getClickCount()==1){
            fillMedicine();
        }else if(evt.getClickCount()>1){
            msbsSupplier editSup = getCodeSelectedRow();
            UpdateDistributorForm updateSupForm = new UpdateDistributorForm(this.frmParent,false,editSup);
            updateSupForm.setLocationRelativeTo(this);
            updateSupForm.setVisible(true);
        }
        
    }//GEN-LAST:event_tblSupplierMousePressed

    private void tblSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSupplierMouseClicked
        // TODO add your handling code here:
         

    }//GEN-LAST:event_tblSupplierMouseClicked

    private void tblMedicineListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMedicineListMousePressed
        // TODO add your handling code here:
        tblMedicineList.removeEditor();
    }//GEN-LAST:event_tblMedicineListMousePressed

    private void txtProviderNameCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtProviderNameCaretUpdate
        // TODO add your handling code here:
        getAllSupplier();
    }//GEN-LAST:event_txtProviderNameCaretUpdate

    private void txtMedNameCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMedNameCaretUpdate
        // TODO add your handling code here:
        fillMedicine();
    }//GEN-LAST:event_txtMedNameCaretUpdate
    private msbsSupplier supplier = null;
    private SupplierManage supMn = null;
    private Vector<msbsSupplier> allSup = null;
    private DefaultTableModel modelTableDistributorList;
    private DefaultTableModel modelTableMedicineList;
    private JFrame frmParent;
    private TableColumn tableColumn;
    private Vector<msbsMedicine> allMed;
    private MedicineManage medMn ;
    private int totalMedicine;
    private int totalDistributor;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddSup;
    private javax.swing.JButton btnDeleteSup;
    private javax.swing.JButton btnUpdateSup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTotalDis;
    private javax.swing.JLabel lblTotalMedicine;
    private javax.swing.JTable tblMedicineList;
    private javax.swing.JTable tblSupplier;
    private javax.swing.JTextField txtMedName;
    private javax.swing.JTextField txtProviderName;
    // End of variables declaration//GEN-END:variables

}
