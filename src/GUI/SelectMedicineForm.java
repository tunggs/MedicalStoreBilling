/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SelectMedicineForm.java
 *
 * Created on Nov 12, 2009, 7:57:05 AM
 */

package GUI;

import Business.MeasureManage;
import Business.MedicineManage;
import Business.MedicineTypeManage;
import Business.SupplierManage;
import Data.msbsMeasure;
import Data.msbsMedicine;
import Data.msbsMedicineType;
import Data.msbsOrderDetails;
import Data.msbsSupplier;
import java.awt.Point;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
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
public class SelectMedicineForm extends javax.swing.JDialog {

    /** Creates new form SelectMedicineForm */
    public SelectMedicineForm(java.awt.Frame parent, boolean modal,String code,Vector<msbsOrderDetails> oldOrdDetailsList) throws UnsupportedLookAndFeelException {
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
        this.oCode = code;
        isDone=false;
        medMn = new MedicineManage();
        medTypeMn = new MedicineTypeManage();
        measureMn = new MeasureManage();
        medicine = new msbsMedicine();
        medType = new msbsMedicineType();
        measure = new msbsMeasure();
        allMedicine = new Vector<msbsMedicine>();
        allMedType = new Vector<msbsMedicineType>();
        allMeasure = new Vector<msbsMeasure>();
        tableColumn = new TableColumn();
        newOrderDETemp = new Vector<msbsOrderDetails>();
        newOrderDETemp = oldOrdDetailsList;
        refreshDrugList();
        getAllMedicine();
    }

     public void  getAllMedicine(){
        allMedicine = medMn.getAllMedicine();
        medicineTableModel = new DefaultTableModel();
        medicineTableModel.addColumn("Code");
        medicineTableModel.addColumn("Medicine Name");
        medicineTableModel.addColumn("Price");
        medicineTableModel.addColumn("Stock");

        for(int i = 0;i<allMedicine.size();i++){
            medicine = (msbsMedicine)allMedicine.get(i);
            if(medicine.getAvailableAmount()>0){
                Vector newData = new Vector();
                newData.add(medicine.getMedicineCode());
                newData.add(medicine.getMedicineName());
                newData.add(medicine.getPricePerUnit());
                newData.add(medicine.getAvailableAmount());
                medicineTableModel.addRow(newData);
            }


        }
        tblSelectMedicine.setModel(medicineTableModel);
        tableColumn = tblSelectMedicine.getColumnModel().getColumn(0);
        tableColumn.setPreferredWidth(0);
        tableColumn.setMinWidth(0);
        tableColumn.setMaxWidth(0);

    }

    public void seachMedicine(){
        String txtSearchMed = txtSearch.getText().trim().toLowerCase();
        allMedicine = medMn.getAllMedicine();
        medicineTableModel = new DefaultTableModel();
        medicineTableModel.addColumn("Code");
        medicineTableModel.addColumn("Medicine Name");
        medicineTableModel.addColumn("Price");
        medicineTableModel.addColumn("Stock");

        for(int i = 0;i<allMedicine.size();i++){
            medicine = (msbsMedicine)allMedicine.get(i);
            Vector newData = new Vector();
            if(medicine.getMedicineName().toLowerCase().matches(".*"+txtSearchMed+".*")){
                newData.add(medicine.getMedicineCode());
                newData.add(medicine.getMedicineName());
                newData.add(medicine.getPricePerUnit());
                newData.add(medicine.getAvailableAmount());

                medicineTableModel.addRow(newData);
            }
        }
        tblSelectMedicine.setModel(medicineTableModel);
        tableColumn = tblSelectMedicine.getColumnModel().getColumn(0);
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

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSelectMedicine = new javax.swing.JTable();
        btnRemove = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCart = new javax.swing.JTable();
        btnDone = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Select Medicine");
        setAlwaysOnTop(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Search ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        jLabel1.setText("Name  ");

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
                .addGap(53, 53, 53)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        tblSelectMedicine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Medicine Name", "Price per Unit", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
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
        tblSelectMedicine.getTableHeader().setReorderingAllowed(false);
        tblSelectMedicine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSelectMedicineMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblSelectMedicine);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
        );

        btnRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Delete.png"))); // NOI18N
        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 18));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/addCart.png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Shopping Cart", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        tblCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Medicine Name", "Price per Unit", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
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
        tblCart.getTableHeader().setReorderingAllowed(false);
        tblCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblCartMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblCart);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
        );

        btnDone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/login.png"))); // NOI18N
        btnDone.setText("Done");
        btnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoneActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24));
        jLabel2.setText("Shopping Cart");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jLabel2)
                        .addGap(139, 139, 139))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(892, Short.MAX_VALUE)
                .addComponent(btnDone)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemove)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(btnDone, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        seachMedicine();
}//GEN-LAST:event_btnSearchActionPerformed

    public void refreshDrugList(){
        CartTableModel = new DefaultTableModel();
        CartTableModel.addColumn("Medicine");
        CartTableModel.addColumn("Price");
        CartTableModel.addColumn("Quantity");
        for(int i =0;i<newOrderDETemp.size();i++){
                msbsOrderDetails ode = (msbsOrderDetails)newOrderDETemp.get(i);
                msbsMedicine oldMed = medMn.getMedicineByCode(ode.getMedicineCode());
                Vector data = new Vector();
                data.add(oldMed.getMedicineName());
                data.add(ode.getPrice());
                data.add(ode.getQuantity());
                CartTableModel.addRow(data);

            }
            tblCart.setModel(CartTableModel);
    }

    private void AddMed(){
        int index = tblSelectMedicine.getSelectedRow();
        if(tblSelectMedicine.getSelectedRowCount()!= 1){
            JOptionPane.showMessageDialog(this,"Select one medicine to Add to Cart!");
        }else{
            medCode = Integer.valueOf(String.valueOf(tblSelectMedicine.getValueAt(index, 0)));
            msbsOrderDetails tempOrderDe = new msbsOrderDetails();
            msbsMedicine selectedMed = new msbsMedicine();
            if(medCode!=-1){
                selectedMed = medMn.getMedicineByCode(medCode);
                if(newOrderDETemp.isEmpty()){
                    tempOrderDe.setOrderCode(oCode);
                    tempOrderDe.setMedicineCode(medCode);
                    tempOrderDe.setQuantity(1);
                    tempOrderDe.setPrice(selectedMed.getPricePerUnit());
                    newOrderDETemp.add(tempOrderDe);
                }else{
                    boolean flag=false;
                    int indexed=0;
                    for(int i=0;i<newOrderDETemp.size();i++){
                        msbsOrderDetails od = new msbsOrderDetails();
                        od = (msbsOrderDetails)newOrderDETemp.get(i);
                        if(od.getMedicineCode()!=selectedMed.getMedicineCode()){
                            tempOrderDe = new msbsOrderDetails();
                            tempOrderDe.setOrderCode(oCode);
                            tempOrderDe.setMedicineCode(selectedMed.getMedicineCode());
                            tempOrderDe.setQuantity(1);
                            tempOrderDe.setPrice(selectedMed.getPricePerUnit());
                        }else if(od.getMedicineCode()==selectedMed.getMedicineCode()){
                                int quan = od.getQuantity()+1;
                                od.setQuantity(quan);
                                od.setPrice(selectedMed.getPricePerUnit());
                                tempOrderDe = od;
                                indexed = i;
                                flag = true;
                                break;
                        }
                    }
                    if(flag==false){
                        newOrderDETemp.add(tempOrderDe);
                    }else if(flag==true){
                            newOrderDETemp.set(indexed, tempOrderDe);
                    }
                }
            }
        }
    }
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        AddMed();
        refreshDrugList();
}//GEN-LAST:event_btnAddActionPerformed

    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoneActionPerformed
        // TODO add your handling code here:
        isDone=true;
        this.dispose();
}//GEN-LAST:event_btnDoneActionPerformed

    private void tblCartMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCartMouseReleased
        // TODO add your handling code here:
        if (evt.getClickCount() > 1) {
            Point clicked = new Point(evt.getX(), evt.getY());
            int col = tblCart.columnAtPoint(clicked);
            if(col != 2){
                tblCart.removeEditor();
            }else{
                int roww;
                roww = tblCart.rowAtPoint(clicked);
                tblCart.editCellAt(roww, 2);

                int meCode = medMn.getCodeByName(String.valueOf(tblCart.getValueAt(roww, 0)));
                SetQuantityForm setQ;
                setQ = new SetQuantityForm(this.frmParent, true, meCode);
                setQ.setLocationRelativeTo(this);
                setQ.setVisible(true);
                if(SetQuantityForm.isClick==true){
                    int newQuan = SetQuantityForm.newQuantity;
                    msbsOrderDetails temp = (msbsOrderDetails)newOrderDETemp.get(roww);
                    temp.setQuantity(newQuan);
                    newOrderDETemp.set(roww, temp);
                }
                refreshDrugList();
            }
        }
    }//GEN-LAST:event_tblCartMouseReleased

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
        int index = tblCart.getSelectedRow();
        if(tblCart.getSelectedRowCount()!=1){
            JOptionPane.showMessageDialog(this,"Select one medicine to Remove!");
        }else{
            int medC = medMn.getCodeByName(String.valueOf(tblCart.getValueAt(index, 0)));
            for(int i =0;i<newOrderDETemp.size();i++){
                if(medC == ((msbsOrderDetails)newOrderDETemp.get(i)).getMedicineCode()){
                    newOrderDETemp.remove(i);
                }
            }
            refreshDrugList();
        }
}//GEN-LAST:event_btnRemoveActionPerformed

    private void tblSelectMedicineMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSelectMedicineMousePressed
        // TODO add your handling code here:
            tblSelectMedicine.removeEditor();
    }//GEN-LAST:event_tblSelectMedicineMousePressed

    /**
    * @param args the command line arguments
    */
    private Vector<msbsSupplier> allSup;
    private SupplierManage supMn;
    private MeasureManage measureMn;
    private MedicineTypeManage medTypeMn;
    private Vector<msbsMedicineType> allCategory;
    private Vector<msbsMeasure> allMesure;
    private DefaultComboBoxModel measureModel;
    private DefaultComboBoxModel supplierModel;
    private DefaultComboBoxModel categoryModel;
    private MedicineManage medMn;
    private msbsMedicine medicine;
    private msbsMeasure measure;
    private msbsMedicineType medType;
    private Vector<msbsMedicine> allMedicine;
    private Vector<msbsMeasure> allMeasure;
    private Vector<msbsMedicineType> allMedType;
    private DefaultTableModel medicineTableModel;
    private DefaultTableModel medTypeTableModel;
    private DefaultTableModel measureTableModel;
    private DefaultTableModel CartTableModel;
    static int code;
    private String oCode;
    private int medCode;
    private TableColumn tableColumn;
    static Vector<msbsOrderDetails> newOrderDETemp ;
    private JFrame frmParent;
    static boolean isDone;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDone;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCart;
    private javax.swing.JTable tblSelectMedicine;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

}