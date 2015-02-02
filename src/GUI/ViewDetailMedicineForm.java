/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ViewDetailMedicineForm.java
 *
 * Created on Nov 8, 2009, 5:38:32 PM
 */
package GUI;

import Business.MeasureManage;
import Business.MedicineManage;
import Business.MedicineTypeManage;
import Business.SupplierManage;
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
public class ViewDetailMedicineForm extends javax.swing.JDialog {

    /** Creates new form ViewDetailMedicineForm */
    public ViewDetailMedicineForm(java.awt.Frame parent, boolean modal, msbsMedicine updateMe) {
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
        this.medicine = updateMe;
        allSup = supMn.getAllSupplier();
        allMesure = measureMn.getAllMeasures();
        allCategory = medTypeMn.getAllType();

        Vector modelData = new Vector();
        for (int i = 0; i < allMesure.size(); i++) {
            msbsMeasure ms = new msbsMeasure();
            ms = (msbsMeasure) allMesure.get(i);
            modelData.add(ms.getMeasureName());
        }
        measureModel = new DefaultComboBoxModel(modelData);
        cbMeasureCode.setModel(measureModel);

        modelData = new Vector();
        for (int i = 0; i < allSup.size(); i++) {
            msbsSupplier sup = new msbsSupplier();
            sup = (msbsSupplier) allSup.get(i);
            modelData.add(sup.getSupplierName());
        }

        supplierModel = new DefaultComboBoxModel(modelData);
        cbxSupplierCode.setModel(supplierModel);

        modelData = new Vector();
        for (int i = 0; i < allCategory.size(); i++) {
            msbsMedicineType type = new msbsMedicineType();
            type = (msbsMedicineType) allCategory.get(i);
            modelData.add(type.getMedicineTypeName());
        }

        categoryModel = new DefaultComboBoxModel(modelData);
        cbMedicineTypecode.setModel(categoryModel);


        //--------------------------------
        txtMedicineName.setText(medicine.getMedicineName());
        txtAvaiableMount.setText(String.valueOf(medicine.getAvailableAmount()));
        txtDesignation.setText(medicine.getDesignation());
        txtNotDesignation.setText(medicine.getNotDesignation());
        txtPriceperUnit.setText(String.valueOf(medicine.getPricePerUnit()));
        txtRegNo.setText(medicine.getRegisterNumber());
        txtUserGuide.setText(medicine.getUserGuide());
        txtDosage.setText(medicine.getDosage());

        for (int i = 0; i < allMesure.size(); i++) {
            msbsMeasure ms = new msbsMeasure();
            ms = (msbsMeasure) allMesure.get(i);
            if (medicine.getMeasureCode() == ms.getMeasureCode()) {
                measureModel.setSelectedItem(ms.getMeasureName());
            }
        }

        for (int i = 0; i < allSup.size(); i++) {
            msbsSupplier sup = new msbsSupplier();
            sup = (msbsSupplier) allSup.get(i);
            if (medicine.getSupplierCode() == sup.getSupplierCode()) {
                supplierModel.setSelectedItem(sup.getSupplierName());
            }
        }

        for (int i = 0; i < allCategory.size(); i++) {
            msbsMedicineType type = new msbsMedicineType();
            type = (msbsMedicineType) allCategory.get(i);
            if (medicine.getMedicineTypeCode() == type.getMedicineTypeCode()) {
                categoryModel.setSelectedItem(type.getMedicineTypeName());
            }
        }

        if (medicine.getMedicineOrigine().equals("Domestic")) {
            rbtnDomestic.setSelected(true);
        } else {
            rbtnForeign.setSelected(true);
        }
        jDateChooser1.setDate(medicine.getExpiredDate());

        if (modal == false) {
            txtAvaiableMount.setEditable(false);
            txtDesignation.setEditable(false);
            txtMedicineName.setEditable(false);
            txtNotDesignation.setEditable(false);
            txtPriceperUnit.setEditable(false);
            txtRegNo.setEditable(false);
            txtUserGuide.setEditable(false);
            txtDosage.setEditable(false);
            cbMeasureCode.setEnabled(false);
            cbMedicineTypecode.setEnabled(false);
            cbxSupplierCode.setEnabled(false);
            rbtnDomestic.setEnabled(false);
            rbtnForeign.setEnabled(false);
            jDateChooser1.setEnabled(false);
            btnUpdate.setVisible(false);
        }


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
        if (txtMedicineName.getText().trim().equals("") || txtMedicineName.getText().trim().equals(null)) {
            JOptionPane.showMessageDialog(this, "Name is not null !!!");
            txtMedicineName.requestFocus();
            return false;
        }  else if(expiredDate==null){
            JOptionPane.showMessageDialog(this, "Must select Self-Time !!! ");
            return false;
        } else if(expiredDate.before(today)){
            JOptionPane.showMessageDialog(this, "Self-Time doesn't less than today or is today!!! ");
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
            JOptionPane.showMessageDialog(this, "Available Amount is not null");
            txtAvaiableMount.requestFocus();
            return false;
        } else if (amountResult==false) {
            JOptionPane.showMessageDialog(this, "Invalid available Amount !!! ");
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
        cbxSupplierCode = new javax.swing.JComboBox();
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
        btnUpdate = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDosage = new javax.swing.JTextArea();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        btnClose = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("View details or Update medicine infomation");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Medicine View Detail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 153))); // NOI18N

        jLabel1.setText("Category");

        jLabel2.setText("Packaging");

        jLabel5.setText("Priceper Unit ");

        jLabel3.setText("Indication");

        jLabel13.setText("Self-Time");

        jLabel7.setText("Origin ");

        jLabel9.setText("Users Guide ");

        jLabel4.setText("Medicine Name ");

        jLabel11.setText("Distributor ");

        jLabel8.setText("Register Number");

        jLabel10.setText("Contrain");

        jLabel12.setText("Dosage ");

        jLabel6.setText("Avaiable Mount ");

        txtNotDesignation.setColumns(20);
        txtNotDesignation.setRows(5);
        jScrollPane1.setViewportView(txtNotDesignation);

        buttonGroup1.add(rbtnDomestic);
        rbtnDomestic.setText("Domestic");

        buttonGroup1.add(rbtnForeign);
        rbtnForeign.setText("Foreign ");

        txtDesignation.setColumns(20);
        txtDesignation.setRows(5);
        jScrollPane2.setViewportView(txtDesignation);

        txtUserGuide.setColumns(20);
        txtUserGuide.setRows(5);
        jScrollPane3.setViewportView(txtUserGuide);

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Update.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
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

        jLabel14.setText("Indication");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel13))
                                .addGap(48, 48, 48)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                    .addComponent(txtMedicineName, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPriceperUnit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                                    .addComponent(cbMeasureCode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(71, 71, 71)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel11))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                        .addGap(8, 8, 8))
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAvaiableMount, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                    .addComponent(cbMedicineTypecode, 0, 230, Short.MAX_VALUE)
                                    .addComponent(cbxSupplierCode, 0, 230, Short.MAX_VALUE)
                                    .addComponent(txtRegNo, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)))
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(57, 57, 57))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(122, 122, 122)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addGap(122, 122, 122)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(rbtnDomestic)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(rbtnForeign)))))
                                        .addGap(71, 71, 71)))
                                .addComponent(jLabel12)
                                .addGap(44, 44, 44)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(524, 524, 524)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(btnClose)))
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
                            .addComponent(txtMedicineName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbMeasureCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPriceperUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbMedicineTypecode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxSupplierCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addComponent(txtAvaiableMount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtRegNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(rbtnDomestic)
                            .addComponent(rbtnForeign))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(40, 40, 40))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addGap(16, 16, 16)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(jLabel9))
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:

        int code = 0;
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
            code = medicine.getMedicineCode();
            name = txtMedicineName.getText().trim();
            price = Double.valueOf(txtPriceperUnit.getText().trim());
            availAmount = (int) Integer.valueOf(txtAvaiableMount.getText().trim());
            regNo = txtRegNo.getText().trim();
            des = txtDesignation.getText();
            unDes = txtNotDesignation.getText();
            dosage = txtDosage.getText();
            userGuide = txtUserGuide.getText();
            exDate = new Date(jDateChooser1.getDate().getTime());

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
                if (String.valueOf(cbxSupplierCode.getSelectedItem()).equals(sup.getSupplierName())) {
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

            if (medMn.updateMedicine(code, name, typeCode, supCode, measureCode, price, availAmount, regNo, origin, des, unDes, dosage, exDate, userGuide)) {
                JOptionPane.showMessageDialog(rootPane, "Update successful! ");
            } else {
                JOptionPane.showMessageDialog(this, "Please check your infomation to Update", "UPDATE FAILED", JOptionPane.ERROR_MESSAGE);
            }
            this.dispose();
        }
}//GEN-LAST:event_btnUpdateActionPerformed

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
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbMeasureCode;
    private javax.swing.JComboBox cbMedicineTypecode;
    private javax.swing.JComboBox cbxSupplierCode;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
