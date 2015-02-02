/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddNewMedicineForm.java
 *
 * Created on Nov 8, 2009, 10:17:21 PM
 */
package GUI;

import Business.MeasureManage;
import Business.MedicineManage;
import Business.MedicineTypeManage;
import Business.SupplierManage;
import Data.msbsCustomer;
import Data.msbsMeasure;
import Data.msbsMedicine;
import Data.msbsMedicineType;
import Data.msbsSupplier;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class AddNewMedicineForm extends javax.swing.JDialog {

    /** Creates new form AddNewMedicineForm */
    public AddNewMedicineForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        medMn = new MedicineManage();
        medicine = new msbsMedicine();
        allMedicine = new Vector<msbsMedicine>();
        allSup = new Vector<msbsSupplier>();
        allMesure = new Vector<msbsMeasure>();
        allCategory = new Vector<msbsMedicineType>();
        supMn = new SupplierManage();
        measureMn = new MeasureManage();
        medTypeMn = new MedicineTypeManage();

        allSup = supMn.getAllSupplier();
        allMesure = measureMn.getAllMeasures();
        allCategory = medTypeMn.getAllType();

        Vector modelData = new Vector();
        modelData.add("Select...");
        for (int i = 0; i < allMesure.size(); i++) {
            msbsMeasure ms = new msbsMeasure();
            ms = (msbsMeasure) allMesure.get(i);
            modelData.add(ms.getMeasureName());
        }
        measureModel = new DefaultComboBoxModel(modelData);
        cbMeasureCode.setModel(measureModel);

        modelData = new Vector();
        modelData.add("Select...");
        for (int i = 0; i < allSup.size(); i++) {
            msbsSupplier sup = new msbsSupplier();
            sup = (msbsSupplier) allSup.get(i);
            modelData.add(sup.getSupplierName());
        }

        supplierModel = new DefaultComboBoxModel(modelData);
        cbSupplierCode.setModel(supplierModel);

        modelData = new Vector();
        modelData.add("Select...");
        for (int i = 0; i < allCategory.size(); i++) {
            msbsMedicineType type = new msbsMedicineType();
            type = (msbsMedicineType) allCategory.get(i);
            modelData.add(type.getMedicineTypeName());
        }

        categoryModel = new DefaultComboBoxModel(modelData);
        cbMedicineTypecode.setModel(categoryModel);

    }

    public boolean checkMedicineForm() {
        String textPrice = txtPriceperUnit.getText().trim();
        Pattern priceCheck = Pattern.compile("([0-9]+.)[0-9]");
        Matcher mPrice = priceCheck.matcher(textPrice);
        boolean priceResult = mPrice.matches();

        String textAmount = txtAvaiableMount.getText().trim();
        Pattern amountCheck = Pattern.compile("[0-9]+");
        Matcher mAmount = amountCheck.matcher(textAmount);
        boolean amountResult = mAmount.matches();

        Calendar currentTime  = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date expiredDate = null;
        if(jDateChooser1.getDate() != null){
           expiredDate  = new Date(jDateChooser1.getDate().getTime());
        }
        
        Date today = new Date(currentTime.getTime().getTime());

        if (txtMedicineName.getText().trim().equals("") || txtMedicineName.getText().equals(null)) {
            JOptionPane.showMessageDialog(this, "Name is not null !!!");
            txtMedicineName.requestFocus();
            return false;
        } else if (cbMedicineTypecode.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Must select categoty !!! ");
            cbMedicineTypecode.requestFocus();
            return false;
        } else if (cbMeasureCode.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Must select packaging type !!! ");
            cbMeasureCode.requestFocus();
            return false;
        } else if (cbSupplierCode.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Must select Distributor !!! ");
            cbSupplierCode.requestFocus();
            return false;
        } else if(expiredDate==null){
            JOptionPane.showMessageDialog(this, "Must select Self-Time !!! ");
            return false;
        } else if(expiredDate.before(today)){
            JOptionPane.showMessageDialog(this, "Self-time doesn't less than today !!! ");
            return false;
        }else if (txtPriceperUnit.getText().trim().equals("") || txtPriceperUnit.getText().trim().equals(null)) {
            JOptionPane.showMessageDialog(this, "Price is not null !!! ");
            txtPriceperUnit.requestFocus();
            return false;
        } else if (priceResult==false) {
            JOptionPane.showMessageDialog(this, "Invalid price !!! ");
            txtPriceperUnit.requestFocus();
            return false;
        } else if (txtAvaiableMount.getText().equals("") || txtAvaiableMount.getText().equals(null)) {
            JOptionPane.showMessageDialog(this, "Avaiable is not null");
            txtAvaiableMount.requestFocus();
            return false;
        } else if (amountResult==false) {
            JOptionPane.showMessageDialog(this, "Invalid Amount !!! ");
            txtAvaiableMount.requestFocus();
            return false;
        } else if (txtRegNo.getText().trim().equals("") || txtRegNo.getText().trim().equals(null)) {
            JOptionPane.showMessageDialog(this, "Register number is not null !!!");
            txtRegNo.requestFocus();
            return false;
        } else if (txtDesignation.getText().trim().equals("") || txtDesignation.getText().trim().equals(null)) {
            JOptionPane.showMessageDialog(this, "Indication is not null !!!");
            txtDesignation.requestFocus();
            return false;
        } else if (txtNotDesignation.getText().trim().equals("") || txtNotDesignation.getText().trim().equals(null)) {
            JOptionPane.showMessageDialog(this, "Therapeutic Indication is not null !!!");
            txtNotDesignation.requestFocus();
            return false;
        } else if (txtUserGuide.getText().trim().equals("") || txtUserGuide.getText().trim().equals(null)) {
            JOptionPane.showMessageDialog(this, "UserGuide is not null !!!");
            txtUserGuide.requestFocus();
            return false;
        } else if (txtDosage.getText().trim().equals("") || txtDosage.getText().trim().equals(null)) {
            JOptionPane.showMessageDialog(this, "Dosage is not null !!!");
            txtDosage.requestFocus();
            return false;
        }else{

        return true;
        }
    }
    public void addMedicine(){
        String name = null;
        int typeCode = 0;
        int supCode = 0;
        int measureCode = 0;
        double price = 0.0;
        int availAmount = 0;
        String regNo = null;
        String origin = null;
        String des = null;
        String unDes = null;
        String dosage = null;
        Date exDate = null;
        String userGuide = null;

        if (checkMedicineForm()) {
            name = txtMedicineName.getText().trim();
            price = Double.valueOf(txtPriceperUnit.getText().trim());
            availAmount = (int) Integer.valueOf(txtAvaiableMount.getText().trim());
            regNo = txtRegNo.getText().trim();
            des = txtDesignation.getText().trim();
            unDes = txtNotDesignation.getText().trim();
            dosage = txtDosage.getText().trim();
            userGuide = txtUserGuide.getText().trim();
            exDate = new Date(jDateChooser1.getDate().getTime());
            System.out.println(price);
            for (int i = 0; i < allMesure.size(); i++) {
                msbsMeasure ms = new msbsMeasure();
                ms = (msbsMeasure) allMesure.get(i);
                if (String.valueOf(cbMeasureCode.getSelectedItem()).equals(ms.getMeasureName())) {
                    measureCode = measureMn.getCodeByName(ms.getMeasureName());
                }
            }

            for (int i = 0; i < allSup.size(); i++) {
                msbsSupplier sup = new msbsSupplier();
                sup = (msbsSupplier) allSup.get(i);
                if (String.valueOf(cbSupplierCode.getSelectedItem()).equals(sup.getSupplierName())) {
                    supCode = supMn.getCodeByName(sup.getSupplierName());
                }
            }

            for (int i = 0; i < allCategory.size(); i++) {
                msbsMedicineType type = new msbsMedicineType();
                type = (msbsMedicineType) allCategory.get(i);
                if (String.valueOf(cbMedicineTypecode.getSelectedItem()).equals(type.getMedicineTypeName())) {
                    typeCode = medTypeMn.getCodeByName(type.getMedicineTypeName());
                }
            }

            if (rbtnDomestic.isSelected()) {
                origin = "Domestic";
            } else if (rbtnForeign.isSelected()) {
                origin = "Foreign";
            }

            if (medMn.insertMedicine(name, typeCode, supCode, measureCode, price, availAmount, regNo, origin, des, unDes, dosage, exDate, userGuide)) {
                JOptionPane.showMessageDialog(rootPane, "A mdeicine has been added! ");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cannot add new Medicine to database", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                this.dispose();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbMedicineTypecode = new javax.swing.JComboBox();
        cbMeasureCode = new javax.swing.JComboBox();
        cbSupplierCode = new javax.swing.JComboBox();
        txtMedicineName = new javax.swing.JTextField();
        txtAvaiableMount = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNotDesignation = new javax.swing.JTextArea();
        txtPriceperUnit = new javax.swing.JTextField();
        rbtnDomestic = new javax.swing.JRadioButton();
        rbtnForeign = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDesignation = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtUserGuide = new javax.swing.JTextArea();
        txtRegNo = new javax.swing.JTextField();
        btnAddMedidine = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDosage = new javax.swing.JTextArea();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add new Medicine");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Medicine Add", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 153))); // NOI18N

        jLabel1.setText("Category");

        jLabel2.setText("Packaging");

        jLabel5.setText("Priceper Unit ");

        jLabel3.setText("Indication");

        jLabel13.setText("Self-Tine");

        jLabel7.setText("Origin ");

        jLabel9.setText("Users Guide ");

        jLabel4.setText("Medicine Name ");

        jLabel11.setText("Distributor");

        jLabel8.setText("Register Number");

        jLabel10.setText("ConstrainIndication");

        jLabel12.setText("Dosage ");

        jLabel6.setText("Avaiable Mount ");

        txtNotDesignation.setColumns(20);
        txtNotDesignation.setRows(5);
        jScrollPane1.setViewportView(txtNotDesignation);

        rbtnDomestic.setSelected(true);
        rbtnDomestic.setText("Domestic");

        rbtnForeign.setText("Foreign ");

        txtDesignation.setColumns(20);
        txtDesignation.setRows(5);
        jScrollPane2.setViewportView(txtDesignation);

        txtUserGuide.setColumns(20);
        txtUserGuide.setRows(5);
        jScrollPane3.setViewportView(txtUserGuide);

        btnAddMedidine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Add.png"))); // NOI18N
        btnAddMedidine.setText("Add");
        btnAddMedidine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMedidineActionPerformed(evt);
            }
        });

        txtDosage.setColumns(20);
        txtDosage.setRows(5);
        jScrollPane4.setViewportView(txtDosage);

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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel13)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(rbtnDomestic)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbtnForeign))
                            .addComponent(cbMeasureCode, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMedicineName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPriceperUnit))))
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel11)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10))
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtAvaiableMount, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbSupplierCode, javax.swing.GroupLayout.Alignment.LEADING, 0, 260, Short.MAX_VALUE)
                        .addComponent(cbMedicineTypecode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                            .addComponent(txtRegNo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAddMedidine, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnClose)))
                        .addGap(76, 76, 76)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtMedicineName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(cbMedicineTypecode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(cbSupplierCode))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtAvaiableMount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbMeasureCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(64, 64, 64)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(rbtnDomestic)
                            .addComponent(rbtnForeign))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane4)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnAddMedidine, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGap(193, 193, 193))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtPriceperUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtRegNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(120, 120, 120)
                        .addComponent(jLabel10)
                        .addGap(94, 94, 94)
                        .addComponent(jLabel12)
                        .addGap(263, 263, 263))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 846, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddMedidineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMedidineActionPerformed
        // TODO add your handling code here:

        addMedicine();


}//GEN-LAST:event_btnAddMedidineActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_btnCloseActionPerformed
    /**
     * @param args the command line arguments
     */
    private MedicineManage medMn;
    private msbsMedicine medicine;
    private Vector<msbsMedicine> allMedicine;
    private Vector<msbsSupplier> allSup;
    private SupplierManage supMn;
    private MeasureManage measureMn;
    private MedicineTypeManage medTypeMn;
    private Vector<msbsMedicineType> allCategory;
    private Vector<msbsMeasure> allMesure;
    private DefaultComboBoxModel measureModel;
    private DefaultComboBoxModel supplierModel;
    private DefaultComboBoxModel categoryModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddMedidine;
    private javax.swing.JButton btnClose;
    private javax.swing.JComboBox cbMeasureCode;
    private javax.swing.JComboBox cbMedicineTypecode;
    private javax.swing.JComboBox cbSupplierCode;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JRadioButton rbtnDomestic;
    private javax.swing.JRadioButton rbtnForeign;
    private javax.swing.JTextField txtAvaiableMount;
    private javax.swing.JTextArea txtDesignation;
    private javax.swing.JTextArea txtDosage;
    private javax.swing.JTextField txtMedicineName;
    private javax.swing.JTextArea txtNotDesignation;
    private javax.swing.JTextField txtPriceperUnit;
    private javax.swing.JTextField txtRegNo;
    private javax.swing.JTextArea txtUserGuide;
    // End of variables declaration//GEN-END:variables
}
