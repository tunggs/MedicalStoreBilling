/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MedicineModule.java
 *
 * Created on Nov 8, 2009, 12:44:58 AM
 */
package GUI;

import Business.CustomerManage;
import Business.MeasureManage;
import Business.MedicineManage;
import Business.MedicineTypeManage;
import Business.SupplierManage;
import Data.msbsMeasure;
import Data.msbsMedicine;
import Data.msbsMedicineType;
import Data.msbsSupplier;
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
public class MedicineModule extends javax.swing.JPanel {

    /** Creates new form MedicineModule */
    public MedicineModule(boolean modal) throws UnsupportedLookAndFeelException {
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


        medMn = new MedicineManage();
        medTypeMn = new MedicineTypeManage();
        measureMn = new MeasureManage();
        medicine = new msbsMedicine();
        medType = new msbsMedicineType();
        measure = new msbsMeasure();
        allMedicine = new Vector<msbsMedicine>();
        allMedType = new Vector<msbsMedicineType>();
        allMeasure = new Vector<msbsMeasure>();

        allSup = new Vector<msbsSupplier>();
        allCategory = new Vector<msbsMedicineType>();
        supMn = new SupplierManage();
        measureMn = new MeasureManage();
        medTypeMn = new MedicineTypeManage();
        custMn = new CustomerManage();

        allSup = supMn.getAllSupplier();
        allCategory = medTypeMn.getAllType();
        tableColumn = new TableColumn();

//        totalMedicine = 0;
//        totalCatelory = 0;
//        totalMedicineByCatelory = 0;



        Vector modelData = new Vector();

        modelData = new Vector();
        modelData.add("All Distributor");
        for (int i = 0; i < allSup.size(); i++) {
            msbsSupplier sup = new msbsSupplier();
            sup = (msbsSupplier) allSup.get(i);
            modelData.add(sup.getSupplierName());
        }

        supplierModel = new DefaultComboBoxModel(modelData);
        cbxSupplier.setModel(supplierModel);


        modelData = new Vector();
        modelData.add("All Category");
        for (int i = 0; i < allCategory.size(); i++) {
            msbsMedicineType type = new msbsMedicineType();
            type = (msbsMedicineType) allCategory.get(i);
            modelData.add(type.getMedicineTypeName());
        }

        categoryModel = new DefaultComboBoxModel(modelData);
        cbxMedicineType.setModel(categoryModel);
        

        
        getAllCategory();
        getAllMeasure();
        advSearch();

    }

    public void getAllCategory() {

        totalCatelory = 0;

        allMedType = medTypeMn.getAllType();
        medTypeTableModel = new DefaultTableModel();
        medTypeTableModel.addColumn("Code");
        medTypeTableModel.addColumn("Category Name");
        medTypeTableModel.addColumn("Amount Medicine of Category");
        Vector<msbsMedicine> tempAllMed = new Vector<msbsMedicine>();
        tempAllMed = medMn.getAllMedicine();

        for (int i = 0; i < allMedType.size(); i++) {
            int count = 0;
            medType = (msbsMedicineType) allMedType.get(i);
            for (msbsMedicine me : tempAllMed) {
                if (me.getMedicineTypeCode() == medType.getMedicineTypeCode()) {
                    count++;
                }
            }

            Vector newData = new Vector();
            newData.add(medType.getMedicineTypeCode());
            newData.add(medType.getMedicineTypeName());
            newData.add(count);
            medTypeTableModel.addRow(newData);
            totalCatelory++;

        }
        tblCategory.setModel(medTypeTableModel);
        tableColumn = tblCategory.getColumnModel().getColumn(0);
        tableColumn.setPreferredWidth(0);
        tableColumn.setMinWidth(0);
        tableColumn.setMaxWidth(0);
        lblTotalCatelory.setText(String.valueOf(totalCatelory));

    }

    public void getAllMeasure() {

        allMeasure = measureMn.getAllMeasures();
        measureTableModel = new DefaultTableModel();
        measureTableModel.addColumn("Code");
        measureTableModel.addColumn("No");
        measureTableModel.addColumn("Measure Type");
        totalMeasure=0;
        for (int i = 0; i < allMeasure.size(); i++) {
            measure = (msbsMeasure) allMeasure.get(i);
            Vector newData = new Vector();
            newData.add(measure.getMeasureCode());
            newData.add(i + 1);
            newData.add(measure.getMeasureName());
            totalMeasure++;
            measureTableModel.addRow(newData);

        }
        tblMeasure.setModel(measureTableModel);
        tableColumn = tblMeasure.getColumnModel().getColumn(0);
        tableColumn.setPreferredWidth(0);
        tableColumn.setMinWidth(0);
        tableColumn.setMaxWidth(0);
        lblTotalCatelory1.setText(String.valueOf(totalMeasure));

    }


    

    public void advSearch() {
        String name = null;
        String origin = null;
        double priceFrom = 0.0;
        double priceTo = 0.0;
        int medType = 0;
        int medSup = 0;

        totalMedicine=0;
        if (txtAdvSearchMedicine.getText().trim().equals(null) || txtAdvSearchMedicine.getText().trim().equals("")) {
            name = "";
        } else {
            name = txtAdvSearchMedicine.getText().trim().toLowerCase();
        }
        if (rbtnAdvSearch_Domestic.isSelected()) {
            origin = "domestic";
        } else if (rbtnAdvSearch_Foreign.isSelected()) {
            origin = "foreign";
        } else {
            origin = "";
        }
        if (txtAdvSearchPriceFrom.getText().trim().equals(null) || txtAdvSearchPriceFrom.getText().trim().equals("")) {
            priceFrom = 0.0;
        } else {
            priceFrom = Double.valueOf(txtAdvSearchPriceFrom.getText().trim());
        }
        if (txtAdvSearchPriceTo.getText().trim().equals(null) || txtAdvSearchPriceTo.getText().trim().equals("")) {
            priceTo = Double.MAX_VALUE;
        } else {
            priceTo = Double.valueOf(txtAdvSearchPriceTo.getText().trim());
        }

        if (String.valueOf(cbxMedicineType.getSelectedItem()).equals(null) || String.valueOf(cbxMedicineType.getSelectedItem()).equals("")) {
            medType = -1;
        } else {
            medType = medTypeMn.getCodeByName(String.valueOf(cbxMedicineType.getSelectedItem()));
        }
        if (String.valueOf(cbxSupplier.getSelectedItem()).equals(null) || String.valueOf(cbxSupplier.getSelectedItem()).equals("")) {
            medSup = -1;
        } else {
            medSup = supMn.getCodeByName(String.valueOf(cbxSupplier.getSelectedItem()));
        }
        Vector<msbsMedicine> searchResult = new Vector<msbsMedicine>();

        searchResult = advSearchMedicine(name, origin, priceFrom, priceTo, medType, medSup);
        if (searchResult == null) {
            JOptionPane.showMessageDialog(this, "There are not any Medicine", "ERROR", JOptionPane.INFORMATION_MESSAGE);
        } else {
            medicineTableModel = new DefaultTableModel();
            medicineTableModel.addColumn("Code");
            medicineTableModel.addColumn("Name");
            medicineTableModel.addColumn("Stock");
            medicineTableModel.addColumn("Price");
            medicineTableModel.addColumn("Packaging");
            medicineTableModel.addColumn("Origin");
            medicineTableModel.addColumn("Category");
            medicineTableModel.addColumn("Measure");
            medicineTableModel.addColumn("Indication");
            msbsMedicineType medicineType = new msbsMedicineType();
            for (int i = 0; i < searchResult.size(); i++) {
                medicine = (msbsMedicine) searchResult.get(i);
                Vector newData = new Vector();
                newData.add(medicine.getMedicineCode());
                newData.add(medicine.getMedicineName());
                newData.add(medicine.getAvailableAmount());
                newData.add(medicine.getPricePerUnit());
                newData.add(measureMn.getMeasureByCode(medicine.getMeasureCode()).getMeasureName());
                newData.add(medicine.getMedicineOrigine());
                medicineType = medTypeMn.getTypeByCode(medicine.getMedicineTypeCode());
                newData.add(medicineType.getMedicineTypeName());
                measure = measureMn.getMeasureByCode(medicine.getMeasureCode());
                newData.add(measure.getMeasureName());
                newData.add(medicine.getDesignation());
                totalMedicine++;
                medicineTableModel.addRow(newData);


            }
            tblMedicineList.setModel(medicineTableModel);
            tableColumn = tblMedicineList.getColumnModel().getColumn(0);
            tableColumn.setPreferredWidth(0);
            tableColumn.setMinWidth(0);
            tableColumn.setMaxWidth(0);
            lblTotalMedicine.setText(String.valueOf(totalMedicine));
        }

    }

    public Vector<msbsMedicine> advSearchMedicine(String name, String origin, double from, double to, int typeCode, int supCode) {
        Vector<msbsMedicine> allSearch = new Vector<msbsMedicine>();
        allMedicine=medMn.getAllMedicine();
        if (typeCode != -1) {
            if (supCode != -1) {
                for (int i = 0; i < allMedicine.size(); i++) {
                    msbsMedicine tempMed = new msbsMedicine();
                    tempMed = (msbsMedicine) allMedicine.get(i);
                    if ((tempMed.getMedicineName().toLowerCase().matches(".*" + name + ".*")) && (tempMed.getMedicineOrigine().toLowerCase().matches(".*" + origin.toLowerCase() + ".*")) && (tempMed.getPricePerUnit() >= from && tempMed.getPricePerUnit() <= to) && (tempMed.getMedicineTypeCode() == typeCode) && (tempMed.getSupplierCode() == supCode)) {
                        allSearch.add(tempMed);
                    }
                }
                return allSearch;
            } else {
                for (int i = 0; i < allMedicine.size(); i++) {
                    msbsMedicine tempMed = new msbsMedicine();
                    tempMed = (msbsMedicine) allMedicine.get(i);
                    if ((tempMed.getMedicineName().toLowerCase().matches(".*" + name + ".*")) && (tempMed.getMedicineOrigine().toLowerCase().matches(".*" + origin.toLowerCase() + ".*")) && (tempMed.getPricePerUnit() >= from && tempMed.getPricePerUnit() <= to) && (tempMed.getMedicineTypeCode() == typeCode)) {
                        allSearch.add(tempMed);
                    }
                }
                return allSearch;
            }
        } else {
            if (supCode != -1) {
                for (int i = 0; i < allMedicine.size(); i++) {
                    msbsMedicine tempMed = new msbsMedicine();
                    tempMed = (msbsMedicine) allMedicine.get(i);
                    if ((tempMed.getMedicineName().toLowerCase().matches(".*" + name + ".*")) && (tempMed.getMedicineOrigine().toLowerCase().matches(".*" + origin.toLowerCase() + ".*")) && (tempMed.getPricePerUnit() >= from && tempMed.getPricePerUnit() <= to) && (tempMed.getSupplierCode() == supCode)) {
                        allSearch.add(tempMed);
                    }
                }
                return allSearch;
            } else {
                for (int i = 0; i < allMedicine.size(); i++) {
                    msbsMedicine tempMed = new msbsMedicine();
                    tempMed = (msbsMedicine) allMedicine.get(i);
                    if ((tempMed.getMedicineName().toLowerCase().matches(".*" + name + ".*")) && (tempMed.getMedicineOrigine().toLowerCase().matches(".*" + origin.toLowerCase() + ".*")) && (tempMed.getPricePerUnit() >= from && tempMed.getPricePerUnit() <= to)) {
                        allSearch.add(tempMed);
                    }
                }
                return allSearch;
            }
        }
    }

    public msbsMedicine getCodeSelectedRow() {
        int index = tblMedicineList.getSelectedRow();
        String medName = String.valueOf(tblMedicineList.getValueAt(index, 0)).trim();
        int medCode = medMn.getCodeByName(medName);

        medicine = medMn.getMedicineByCode(medCode);
        if (medicine != null) {
            return medicine;
        }
        return null;
    }

    public void fillMedicineCatelory() {
        medicineListTableModel = new DefaultTableModel();
        medicineListTableModel.addColumn("Code");
        medicineListTableModel.addColumn("Medicine Name");
        medicineListTableModel.addColumn("Stock");
        medicineListTableModel.addColumn("Price");
        medicineListTableModel.addColumn("Packaging");
        medicineListTableModel.addColumn("Origin");
        medicineListTableModel.addColumn("Dosage");

        allMedicine= new Vector<msbsMedicine>();
        totalMedicineByCatelory = 0;
        int typeCode = (int) Integer.valueOf(String.valueOf(tblCategory.getValueAt(tblCategory.getSelectedRow(), 0)));
        allMedicine = medMn.getMedicineByType(typeCode);
        if (allMedicine != null) {
            for (msbsMedicine me : allMedicine) {
                //if(me.getMedicineTypeCode()==typeCode){
                Vector data = new Vector();
                data.add(me.getMedicineCode());
                data.add(me.getMedicineName());
                data.add(me.getAvailableAmount());
                data.add(me.getPricePerUnit());
                data.add(measureMn.getMeasureByCode(me.getMeasureCode()).getMeasureName());
                data.add(me.getMedicineOrigine());
                data.add(me.getDosage());
                medicineListTableModel.addRow(data);
                totalMedicineByCatelory++;
            // }
            }
        }
        tblMedicineListByCatelogy.setModel(medicineListTableModel);
        tableColumn = tblMedicineListByCatelogy.getColumnModel().getColumn(0);
        tableColumn.setPreferredWidth(0);
        tableColumn.setMinWidth(0);
        tableColumn.setMaxWidth(0);
        lblTotalMedicineByCatelory.setText(String.valueOf(totalMedicineByCatelory));
    }


    public void fillMedicineMeasure() {
        medicineListTableModel = new DefaultTableModel();
        medicineListTableModel.addColumn("Code");
        medicineListTableModel.addColumn("Medicine Name");
        medicineListTableModel.addColumn("Stock");
        medicineListTableModel.addColumn("Price");
        medicineListTableModel.addColumn("Origin");
        medicineListTableModel.addColumn("Dosage");


        int totalMedicineByMeasure = 0;
        int measureCode = (int) Integer.valueOf(String.valueOf(tblMeasure.getValueAt(tblMeasure.getSelectedRow(), 0)));
        allMedicine = medMn.getMedicineMeasure(measureCode);
        if (allMedicine != null) {
            for (msbsMedicine me : allMedicine) {
                //if(me.getMedicineTypeCode()==typeCode){
                Vector data = new Vector();
                data.add(me.getMedicineCode());
                data.add(me.getMedicineName());
                data.add(me.getAvailableAmount());
                data.add(me.getPricePerUnit());
                data.add(me.getMedicineOrigine());
                data.add(me.getDosage());
                medicineListTableModel.addRow(data);
                totalMedicineByMeasure++;
            // }
            }
        }
        tblMedicineListByMeasure.setModel(medicineListTableModel);
        tableColumn = tblMedicineListByMeasure.getColumnModel().getColumn(0);
        tableColumn.setPreferredWidth(0);
        tableColumn.setMinWidth(0);
        tableColumn.setMaxWidth(0);
        lblTotalMedicineByCatelory1.setText(String.valueOf(totalMedicineByMeasure));
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
        jDialog1 = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtPriceperUnit = new javax.swing.JTextField();
        txtExpiredDate = new javax.swing.JTextField();
        txtMedicineName = new javax.swing.JTextField();
        txtAvaiableMount = new javax.swing.JTextField();
        txtRegisterNumber = new javax.swing.JTextField();
        txtDossage = new javax.swing.JTextField();
        btUpdate = new javax.swing.JButton();
        cbMedicineTypeCode = new javax.swing.JComboBox();
        cbMeasureCode = new javax.swing.JComboBox();
        rbtDomestic = new javax.swing.JRadioButton();
        rbtForeign = new javax.swing.JRadioButton();
        cbSupplierCode = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDesignation = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNotDesignation = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtUserGuide = new javax.swing.JTextArea();
        jDialog2 = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        cbMedicineTypeCode1 = new javax.swing.JComboBox();
        cbMeasureCode1 = new javax.swing.JComboBox();
        txtPriceperUnit1 = new javax.swing.JTextField();
        rbtDemestic1 = new javax.swing.JRadioButton();
        rbtForeign1 = new javax.swing.JRadioButton();
        txtExpriedDate1 = new javax.swing.JTextField();
        txtMedicineName1 = new javax.swing.JTextField();
        cbSupplierCode1 = new javax.swing.JComboBox();
        txtAvaiableMount1 = new javax.swing.JTextField();
        txtRegisterNumber1 = new javax.swing.JTextField();
        txtDosage1 = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtDesignation1 = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtNotDesignation1 = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtUserGuide1 = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        btAdd1 = new javax.swing.JButton();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTextField4 = new javax.swing.JTextField();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        medicineManager = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMedicineList = new javax.swing.JTable();
        btnAddMedicine = new javax.swing.JButton();
        btnDeleteMedicine = new javax.swing.JButton();
        btnUpdateMedicine = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        lblTotalMedicine = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtAdvSearchPriceFrom = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtAdvSearchPriceTo = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtAdvSearchMedicine = new javax.swing.JTextField();
        rbtnAdvSearch_Domestic = new javax.swing.JRadioButton();
        rbtnAdvSearch_Foreign = new javax.swing.JRadioButton();
        rbtnBoth = new javax.swing.JRadioButton();
        jLabel40 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbxMedicineType = new javax.swing.JComboBox();
        cbxSupplier = new javax.swing.JComboBox();
        categoryPane = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSearchMedicineTypeName = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblCategory = new javax.swing.JTable();
        btnAddCategory = new javax.swing.JButton();
        btnDeleteCategory = new javax.swing.JButton();
        btnUpdateCategory = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblMedicineListByCatelogy = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        lblTotalCatelory = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lblTotalMedicineByCatelory = new javax.swing.JLabel();
        measurePane = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        txtMeasureSearch = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblMeasure = new javax.swing.JTable();
        btnAddMeasure = new javax.swing.JButton();
        btnDeleteMeasure = new javax.swing.JButton();
        btnUpdateMeasure = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblMedicineListByMeasure = new javax.swing.JTable();
        jLabel44 = new javax.swing.JLabel();
        lblTotalCatelory1 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        lblTotalMedicineByCatelory1 = new javax.swing.JLabel();

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "View Detail Medicine", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        jLabel9.setText("Medicine Name :");

        jLabel10.setText("Medicine Type Code :");

        jLabel11.setText("Supplier Code :");

        jLabel12.setText("Measure Code :");

        jLabel13.setText("Avaiable Mount :");

        jLabel14.setText("Priceper Unit :");

        jLabel15.setText("Register Number :");

        jLabel16.setText("Origin :");

        jLabel17.setText("NotDesignation :");

        jLabel18.setText("Designation :");

        jLabel19.setText("Dosage :");

        jLabel20.setText("Expired Date :");

        jLabel21.setText("User Guide :");

        btUpdate.setText("Update");

        buttonGroup1.add(rbtDomestic);
        rbtDomestic.setText("Domestic");

        rbtForeign.setText("Foreign");

        txtDesignation.setColumns(20);
        txtDesignation.setRows(5);
        jScrollPane3.setViewportView(txtDesignation);

        txtNotDesignation.setColumns(20);
        txtNotDesignation.setRows(5);
        jScrollPane2.setViewportView(txtNotDesignation);

        txtUserGuide.setColumns(20);
        txtUserGuide.setRows(5);
        jScrollPane4.setViewportView(txtUserGuide);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel10)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbMeasureCode, javax.swing.GroupLayout.Alignment.LEADING, 0, 205, Short.MAX_VALUE)
                                    .addComponent(txtPriceperUnit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                                    .addComponent(cbMedicineTypeCode, javax.swing.GroupLayout.Alignment.LEADING, 0, 205, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(rbtDomestic)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rbtForeign))
                                    .addComponent(txtExpiredDate, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel20)
                            .addComponent(jLabel12)
                            .addComponent(jLabel16)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel18)
                                .addComponent(jLabel14)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addComponent(jLabel9))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(jLabel11)))
                                .addGap(8, 8, 8))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtMedicineName, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(cbSupplierCode, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtRegisterNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                            .addComponent(txtAvaiableMount, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel19)
                        .addGap(27, 27, 27)
                        .addComponent(txtDossage, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)))
                .addGap(22, 22, 22))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtMedicineName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cbMedicineTypeCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(cbMeasureCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSupplierCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtPriceperUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rbtDomestic)
                                .addComponent(rbtForeign)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtExpiredDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAvaiableMount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRegisterNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtDossage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(btUpdate)
                        .addContainerGap())
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Add Mediacine", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        jLabel22.setText("Medicine Type Code :");

        jLabel23.setText("Measure Code :");

        jLabel24.setText("Priceper Unit :");

        jLabel25.setText("Designation :");

        jLabel26.setText("Orgin :");

        jLabel27.setText("Expired Date :");

        jLabel28.setText("User Guide :");

        jLabel29.setText("Medicine Name :");

        jLabel31.setText("Avaiable Mount :");

        jLabel32.setText("Register Number :");

        jLabel33.setText("NotDesignation :");

        jLabel34.setText("Dosage :");

        buttonGroup1.add(rbtDemestic1);
        rbtDemestic1.setText("Domestic");

        buttonGroup1.add(rbtForeign1);
        rbtForeign1.setText("Foreign");

        txtDesignation1.setColumns(20);
        txtDesignation1.setRows(5);
        jScrollPane5.setViewportView(txtDesignation1);

        jScrollPane8.setViewportView(jScrollPane5);

        txtNotDesignation1.setColumns(20);
        txtNotDesignation1.setRows(5);
        jScrollPane7.setViewportView(txtNotDesignation1);

        txtUserGuide1.setColumns(20);
        txtUserGuide1.setRows(5);
        jScrollPane6.setViewportView(txtUserGuide1);

        jLabel8.setText("Suplier Code :");

        btAdd1.setText("Add");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel25)
                        .addComponent(jLabel24)))
                .addGap(37, 37, 37)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(rbtDemestic1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(rbtForeign1))
                    .addComponent(txtExpriedDate1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(txtPriceperUnit1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(cbMeasureCode1, 0, 206, Short.MAX_VALUE)
                    .addComponent(cbMedicineTypeCode1, 0, 206, Short.MAX_VALUE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addComponent(jLabel30))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(68, 68, 68)
                                        .addComponent(jLabel34)))
                                .addGap(30, 30, 30))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel31)
                                    .addComponent(jLabel32)
                                    .addComponent(jLabel33))
                                .addGap(29, 29, 29)))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDosage1)
                            .addComponent(jScrollPane7)
                            .addComponent(txtRegisterNumber1)
                            .addComponent(txtAvaiableMount1)
                            .addComponent(cbSupplierCode1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMedicineName1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cbMedicineTypeCode1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMedicineName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbMeasureCode1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel30)
                        .addComponent(cbSupplierCode1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtPriceperUnit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAvaiableMount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRegisterNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbtDemestic1)
                            .addComponent(jLabel26)
                            .addComponent(rbtForeign1))))
                .addGap(29, 29, 29)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtExpriedDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel34)
                                .addComponent(txtDosage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btAdd1))
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTextField4.setText("jTextField4");

        jTabbedPane2.setPreferredSize(new java.awt.Dimension(815, 400));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "MEDICINE DISPLAY", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        tblMedicineList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Stock", "Price", "Packaging", "Origin", "Indication", "User Guide"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tblMedicineList.getTableHeader().setReorderingAllowed(false);
        tblMedicineList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMedicineListMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblMedicineListMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblMedicineList);

        btnAddMedicine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Add.png"))); // NOI18N
        btnAddMedicine.setText("Add");
        btnAddMedicine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMedicineActionPerformed(evt);
            }
        });

        btnDeleteMedicine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Delete.png"))); // NOI18N
        btnDeleteMedicine.setText("Delete");
        btnDeleteMedicine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteMedicineActionPerformed(evt);
            }
        });

        btnUpdateMedicine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/edit.png"))); // NOI18N
        btnUpdateMedicine.setText("View Detail");
        btnUpdateMedicine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateMedicineActionPerformed(evt);
            }
        });

        jLabel38.setText("Total Medicine: ");

        lblTotalMedicine.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTotalMedicine)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 384, Short.MAX_VALUE)
                .addComponent(btnAddMedicine, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(btnUpdateMedicine)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDeleteMedicine))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(lblTotalMedicine)
                    .addComponent(btnAddMedicine)
                    .addComponent(btnDeleteMedicine)
                    .addComponent(btnUpdateMedicine, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Medicine Filter", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Price", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 255))); // NOI18N

        jLabel3.setText("From");

        txtAdvSearchPriceFrom.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAdvSearchPriceFromCaretUpdate(evt);
            }
        });

        jLabel5.setText("To");

        txtAdvSearchPriceTo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAdvSearchPriceToCaretUpdate(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel36.setText("$");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel37.setText("$");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtAdvSearchPriceTo)
                    .addComponent(txtAdvSearchPriceFrom, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtAdvSearchPriceFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtAdvSearchPriceTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Name and Origin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 51, 153))); // NOI18N

        jLabel4.setText("Name");

        txtAdvSearchMedicine.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAdvSearchMedicineCaretUpdate(evt);
            }
        });

        buttonGroup1.add(rbtnAdvSearch_Domestic);
        rbtnAdvSearch_Domestic.setText("Domestic ");
        rbtnAdvSearch_Domestic.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnAdvSearch_DomesticStateChanged(evt);
            }
        });

        buttonGroup1.add(rbtnAdvSearch_Foreign);
        rbtnAdvSearch_Foreign.setText("Foreign");
        rbtnAdvSearch_Foreign.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnAdvSearch_ForeignStateChanged(evt);
            }
        });

        buttonGroup1.add(rbtnBoth);
        rbtnBoth.setText("Both");
        rbtnBoth.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtnBothItemStateChanged(evt);
            }
        });

        jLabel40.setText("Origin");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(rbtnAdvSearch_Domestic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtnAdvSearch_Foreign)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(rbtnBoth))
                    .addComponent(txtAdvSearchMedicine, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                .addGap(111, 111, 111))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtAdvSearchMedicine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnAdvSearch_Domestic)
                    .addComponent(rbtnAdvSearch_Foreign)
                    .addComponent(jLabel40)
                    .addComponent(rbtnBoth)))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Medicine Category and Distributor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 51, 153))); // NOI18N

        jLabel6.setText("Catelory");

        jLabel7.setText("Provider");

        cbxMedicineType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMedicineTypeItemStateChanged(evt);
            }
        });

        cbxSupplier.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSupplierItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxSupplier, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxMedicineType, 0, 173, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxMedicineType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, 0, 76, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout medicineManagerLayout = new javax.swing.GroupLayout(medicineManager);
        medicineManager.setLayout(medicineManagerLayout);
        medicineManagerLayout.setHorizontalGroup(
            medicineManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        medicineManagerLayout.setVerticalGroup(
            medicineManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medicineManagerLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Medicine Management", new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/tabMedicineIcon.png")), medicineManager); // NOI18N

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Category Filter", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        jLabel1.setText("Filter by Name:");

        txtSearchMedicineTypeName.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchMedicineTypeNameCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearchMedicineTypeName, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(446, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearchMedicineTypeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Category List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        tblCategory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine Type Name", "Medicine Amount in stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCategory.getTableHeader().setReorderingAllowed(false);
        tblCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoryMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblCategoryMousePressed(evt);
            }
        });
        jScrollPane9.setViewportView(tblCategory);
        tblCategory.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblCategory.getColumnModel().getColumn(1).setPreferredWidth(5);

        btnAddCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Add.png"))); // NOI18N
        btnAddCategory.setText("Add");
        btnAddCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCategoryActionPerformed(evt);
            }
        });

        btnDeleteCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Delete.png"))); // NOI18N
        btnDeleteCategory.setText("Delete");
        btnDeleteCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCategoryActionPerformed(evt);
            }
        });

        btnUpdateCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Update.png"))); // NOI18N
        btnUpdateCategory.setText("Change Name");
        btnUpdateCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCategoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAddCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdateCategory, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteCategory))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddCategory)
                    .addComponent(btnDeleteCategory)
                    .addComponent(btnUpdateCategory)))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Medicine List By Category", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        tblMedicineListByCatelogy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Available Amount", "Price Per Unit", "Origin"
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
        tblMedicineListByCatelogy.getTableHeader().setReorderingAllowed(false);
        tblMedicineListByCatelogy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblMedicineListByCatelogyMousePressed(evt);
            }
        });
        jScrollPane11.setViewportView(tblMedicineListByCatelogy);
        tblMedicineListByCatelogy.getColumnModel().getColumn(0).setResizable(false);
        tblMedicineListByCatelogy.getColumnModel().getColumn(1).setResizable(false);
        tblMedicineListByCatelogy.getColumnModel().getColumn(1).setPreferredWidth(2);
        tblMedicineListByCatelogy.getColumnModel().getColumn(2).setResizable(false);
        tblMedicineListByCatelogy.getColumnModel().getColumn(2).setPreferredWidth(5);
        tblMedicineListByCatelogy.getColumnModel().getColumn(3).setResizable(false);

        jLabel2.setText("Total Catelory: ");

        lblTotalCatelory.setText("0");

        jLabel39.setText("Medicine: ");

        lblTotalMedicineByCatelory.setText("0");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalCatelory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotalMedicineByCatelory)
                        .addGap(21, 21, 21))
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTotalCatelory)
                    .addComponent(jLabel39)
                    .addComponent(lblTotalMedicineByCatelory)))
        );

        javax.swing.GroupLayout categoryPaneLayout = new javax.swing.GroupLayout(categoryPane);
        categoryPane.setLayout(categoryPaneLayout);
        categoryPaneLayout.setHorizontalGroup(
            categoryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoryPaneLayout.createSequentialGroup()
                .addGroup(categoryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(categoryPaneLayout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel16, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        categoryPaneLayout.setVerticalGroup(
            categoryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoryPaneLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(categoryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Category Management", new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/tabMedicineIcon.png")), categoryPane); // NOI18N

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Filter Packaging", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        jLabel35.setText("Filter by Name: ");

        txtMeasureSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMeasureSearchCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel35)
                .addGap(31, 31, 31)
                .addComponent(txtMeasureSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(406, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtMeasureSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Packaging List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        tblMeasure.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Measure Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class
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
        tblMeasure.getTableHeader().setReorderingAllowed(false);
        tblMeasure.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMeasureMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblMeasureMousePressed(evt);
            }
        });
        jScrollPane10.setViewportView(tblMeasure);

        btnAddMeasure.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Add.png"))); // NOI18N
        btnAddMeasure.setText("Add");
        btnAddMeasure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMeasureActionPerformed(evt);
            }
        });

        btnDeleteMeasure.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Delete.png"))); // NOI18N
        btnDeleteMeasure.setText("Delete");
        btnDeleteMeasure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteMeasureActionPerformed(evt);
            }
        });

        btnUpdateMeasure.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Update.png"))); // NOI18N
        btnUpdateMeasure.setText("Change Name");
        btnUpdateMeasure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateMeasureActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddMeasure)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdateMeasure, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(btnDeleteMeasure)
                .addContainerGap())
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddMeasure)
                    .addComponent(btnDeleteMeasure)
                    .addComponent(btnUpdateMeasure)))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Medicine List By Pakaging Type", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        tblMedicineListByMeasure.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine Name", "Stock", "Price", "Origin", "Dosage"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tblMedicineListByMeasure.getTableHeader().setReorderingAllowed(false);
        tblMedicineListByMeasure.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblMedicineListByMeasureMousePressed(evt);
            }
        });
        jScrollPane13.setViewportView(tblMedicineListByMeasure);
        tblMedicineListByMeasure.getColumnModel().getColumn(0).setResizable(false);
        tblMedicineListByMeasure.getColumnModel().getColumn(1).setResizable(false);
        tblMedicineListByMeasure.getColumnModel().getColumn(1).setPreferredWidth(2);
        tblMedicineListByMeasure.getColumnModel().getColumn(2).setResizable(false);
        tblMedicineListByMeasure.getColumnModel().getColumn(2).setPreferredWidth(5);
        tblMedicineListByMeasure.getColumnModel().getColumn(3).setResizable(false);
        tblMedicineListByMeasure.getColumnModel().getColumn(4).setResizable(false);

        jLabel44.setText("Total Packaging: ");

        lblTotalCatelory1.setText("0");

        jLabel45.setText("Medicine: ");

        lblTotalMedicineByCatelory1.setText("0");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalCatelory1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 257, Short.MAX_VALUE)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalMedicineByCatelory1)
                .addGap(36, 36, 36))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(lblTotalCatelory1)
                    .addComponent(jLabel45)
                    .addComponent(lblTotalMedicineByCatelory1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout measurePaneLayout = new javax.swing.GroupLayout(measurePane);
        measurePane.setLayout(measurePaneLayout);
        measurePaneLayout.setHorizontalGroup(
            measurePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(measurePaneLayout.createSequentialGroup()
                .addGroup(measurePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(measurePaneLayout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel19, 0, 454, Short.MAX_VALUE))
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        measurePaneLayout.setVerticalGroup(
            measurePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(measurePaneLayout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(measurePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(measurePaneLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(measurePaneLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(101, 101, 101))
        );

        jTabbedPane2.addTab("Packaging Management", new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/tabMedicineIcon.png")), measurePane); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddMedicineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMedicineActionPerformed
        // TODO add your handling code here:

        AddNewMedicineForm addNew = new AddNewMedicineForm(this.frmParent, true);
        addNew.setLocationRelativeTo(this);
        addNew.setVisible(true);
        advSearch();

}//GEN-LAST:event_btnAddMedicineActionPerformed

    private void btnAddCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCategoryActionPerformed
        // TODO add your handling code here:
        String categoryName = null;
        categoryName = JOptionPane.showInputDialog("Enter Category Name: ");
        if (categoryName != null) {
            for( msbsMedicineType medtype : allCategory){
                if (medtype.getMedicineTypeName().toLowerCase().equals(categoryName.toLowerCase())){
                    JOptionPane.showMessageDialog(this,"The \""+categoryName+"\" Category existed !","WARNING",JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            if (medTypeMn.insertMedicineType(categoryName)) {
                JOptionPane.showMessageDialog(frmParent, "New Category has been added");
                getAllCategory();
            }
        }


}//GEN-LAST:event_btnAddCategoryActionPerformed

 private void btnDeleteCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCategoryActionPerformed
     // TODO add your handling code here:

     if (tblCategory.getSelectedRowCount() == 1) {
         int index = tblCategory.getSelectedRow();
         int code = (int)Integer.valueOf(String.valueOf(tblCategory.getValueAt(index, 0)));
         int result = JOptionPane.showConfirmDialog(this, "Are your sure delete this Category? ");
         if (result == JOptionPane.YES_OPTION) {
             if (medTypeMn.deleteMedicineType(code)) {
                 JOptionPane.showMessageDialog(frmParent, "Delete Successful!");
                 getAllCategory();
             } else {
                 JOptionPane.showMessageDialog(this, "This Category has one or more Medicine !", "CANNOT_DELETE", JOptionPane.ERROR_MESSAGE);
             }
         }
     } else if (tblCategory.getSelectedRowCount() > 1) {
         JOptionPane.showMessageDialog(this, "Do not select more than one Category", "WARNING", JOptionPane.WARNING_MESSAGE);
     } else {
         JOptionPane.showMessageDialog(this, "Please select a Category", "WARNING", JOptionPane.WARNING_MESSAGE);
     }
}//GEN-LAST:event_btnDeleteCategoryActionPerformed

 private void btnUpdateCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCategoryActionPerformed
     // TODO add your handling code here:
     if (tblCategory.getSelectedRowCount() == 1) {
         int index = tblCategory.getSelectedRow();
         int code = medTypeMn.getTypeByCode((int)Integer.valueOf(String.valueOf(tblCategory.getValueAt(index, 0)))).getMedicineTypeCode();
         String name = JOptionPane.showInputDialog("Enter new Name of Category! ");
         if (name != null) {
             if (medTypeMn.updateMedicineType(code, name)) {
                 JOptionPane.showMessageDialog(frmParent, "Update succesfull ");
                 getAllCategory();

             }else{
                 JOptionPane.showMessageDialog(frmParent, "Update failed ");
             }
         }
     } else if (tblCategory.getSelectedRowCount() > 1) {
         JOptionPane.showMessageDialog(this, "Do not select more than one Category", "WARNING", JOptionPane.WARNING_MESSAGE);
     } else {
         JOptionPane.showMessageDialog(this, "Please select a Category", "WARNING", JOptionPane.WARNING_MESSAGE);
     }


 }//GEN-LAST:event_btnUpdateCategoryActionPerformed

 private void btnAddMeasureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMeasureActionPerformed
     // TODO add your handling code here:

     String measureName = null;
     measureName = JOptionPane.showInputDialog("Enter Measure Type: ");
     if (measureName != null) {
         for(msbsMeasure mea:allMeasure){
             if(mea.getMeasureName().toLowerCase().equals(measureName.toLowerCase())){
                 JOptionPane.showMessageDialog(this,"The \""+measureName+"\" packaging type existed !","WARNING",JOptionPane.WARNING_MESSAGE);
                 return;
             }
         }
         if (measureMn.insertMeasure(measureName)) {
             JOptionPane.showMessageDialog(frmParent, "New Measure has been added");
             getAllMeasure();
         }
     }
 }//GEN-LAST:event_btnAddMeasureActionPerformed

 private void btnDeleteMeasureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMeasureActionPerformed
     // TODO add your handling code here:
     if (tblMeasure.getSelectedRowCount() == 1) {
         int index = tblMeasure.getSelectedRow();
         int code = (int)Integer.valueOf(String.valueOf(tblMeasure.getValueAt(index, 0)));
         int result = JOptionPane.showConfirmDialog(this, "Are your sure delete this Packaging type ? ");
         if (result == JOptionPane.YES_OPTION) {
             if (measureMn.deleteMeasure(code)) {
                 JOptionPane.showMessageDialog(frmParent, "Delete Successful!");
                 getAllMeasure();
             } else {
                 JOptionPane.showMessageDialog(this, "This Packaging has one or more Medicine !", "CANNOT_DELETE", JOptionPane.ERROR_MESSAGE);
             }
         }
     } else if (tblCategory.getSelectedRowCount() > 1) {
         JOptionPane.showMessageDialog(this, "Do not select more than one Packaging type ", "WARNING", JOptionPane.WARNING_MESSAGE);
     } else {
         JOptionPane.showMessageDialog(this, "Please select a Packaging type ", "WARNING", JOptionPane.WARNING_MESSAGE);
     }

 }//GEN-LAST:event_btnDeleteMeasureActionPerformed

 private void btnUpdateMeasureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateMeasureActionPerformed
     // TODO add your handling code here:

     if (tblMeasure.getSelectedRowCount() == 1) {
         int index = tblMeasure.getSelectedRow();
         int code = measureMn.getMeasureByCode((int)Integer.valueOf(String.valueOf(tblMeasure.getValueAt(index, 0)))).getMeasureCode();
         String name = JOptionPane.showInputDialog("Enter Packaging type : ");
         if (name != null) {
             if (measureMn.updateMeasure(code, name)) {
                 JOptionPane.showMessageDialog(frmParent, "Update succesfull ");
                 getAllMeasure();

             }else{
                 JOptionPane.showMessageDialog(frmParent, "Update failed ");
             }
         }
     } else if (tblCategory.getSelectedRowCount() > 1) {
         JOptionPane.showMessageDialog(this, "Do not select more than one Packaging type", "WARNING", JOptionPane.WARNING_MESSAGE);
     } else {
         JOptionPane.showMessageDialog(this, "Please select a Packaging type", "WARNING", JOptionPane.WARNING_MESSAGE);
     }
 }//GEN-LAST:event_btnUpdateMeasureActionPerformed

 private void btnDeleteMedicineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMedicineActionPerformed
     // TODO add your handling code here:

     if (tblMedicineList.getSelectedRowCount() == 1) {
         int index = tblMedicineList.getSelectedRow();
         int code = (int) Integer.valueOf(String.valueOf(tblMedicineList.getValueAt(index, 0)));
         int result = JOptionPane.showConfirmDialog(this, "Are you sure delete this Medicine? ");
         if (result == JOptionPane.YES_OPTION) {
             if (medMn.deleteMedicine(code)) {
                 JOptionPane.showMessageDialog(this, "Delete Successful!");
                 advSearch();
             } else {
                 JOptionPane.showMessageDialog(this, "This Medicine was sold !", "CANNOT_DELETE", JOptionPane.ERROR_MESSAGE);
             }
         }
     } else if (tblMedicineList.getSelectedRowCount() > 1) {
         JOptionPane.showMessageDialog(this, "Do not select more than one Medicine", "WARNING", JOptionPane.WARNING_MESSAGE);
     } else {
         JOptionPane.showMessageDialog(this, "Please select a Medicine", "WARNING", JOptionPane.WARNING_MESSAGE);
     }


 }//GEN-LAST:event_btnDeleteMedicineActionPerformed

 private void btnUpdateMedicineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateMedicineActionPerformed
     // TODO add your handling code here:
     if (tblMedicineList.getSelectedRowCount() == 1) {
         ViewDetailMedicineForm addNew = new ViewDetailMedicineForm(this.frmParent, true, medMn.getMedicineByCode((int) Integer.valueOf(String.valueOf(tblMedicineList.getValueAt(tblMedicineList.getSelectedRow(), 0)))));
         addNew.setLocationRelativeTo(this);
         addNew.setVisible(true);
         advSearch();
     } else if (tblMedicineList.getSelectedRowCount() > 1) {
         JOptionPane.showMessageDialog(this, "Do not select more than one Medicine", "WARNING", JOptionPane.WARNING_MESSAGE);
     } else {
         JOptionPane.showMessageDialog(this, "Please select a Medicine", "WARNING", JOptionPane.WARNING_MESSAGE);
     }

 }//GEN-LAST:event_btnUpdateMedicineActionPerformed

 private void tblCategoryMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoryMousePressed
     // TODO add your handling code here:
     
     tblCategory.removeEditor();
    
 }//GEN-LAST:event_tblCategoryMousePressed

 private void tblCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoryMouseClicked
     // TODO add your handling code here:
     tblMedicineListByCatelogy.removeEditor();
     tblCategory.removeEditor();
     fillMedicineCatelory();

 }//GEN-LAST:event_tblCategoryMouseClicked

 private void txtAdvSearchMedicineCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAdvSearchMedicineCaretUpdate
     // TODO add your handling code here:
     advSearch();
 }//GEN-LAST:event_txtAdvSearchMedicineCaretUpdate

 private void rbtnAdvSearch_DomesticStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnAdvSearch_DomesticStateChanged
     // TODO add your handling code here:
     advSearch();
 }//GEN-LAST:event_rbtnAdvSearch_DomesticStateChanged

 private void rbtnAdvSearch_ForeignStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnAdvSearch_ForeignStateChanged
     // TODO add your handling code here:
     advSearch();
 }//GEN-LAST:event_rbtnAdvSearch_ForeignStateChanged

 private void txtAdvSearchPriceFromCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAdvSearchPriceFromCaretUpdate
     // TODO add your handling code here:
     advSearch();
 }//GEN-LAST:event_txtAdvSearchPriceFromCaretUpdate

 private void txtAdvSearchPriceToCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAdvSearchPriceToCaretUpdate
     // TODO add your handling code here:
     advSearch();
 }//GEN-LAST:event_txtAdvSearchPriceToCaretUpdate

 private void cbxMedicineTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMedicineTypeItemStateChanged
     // TODO add your handling code here:
     advSearch();
 }//GEN-LAST:event_cbxMedicineTypeItemStateChanged

 private void cbxSupplierItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSupplierItemStateChanged
     // TODO add your handling code here:
     advSearch();
 }//GEN-LAST:event_cbxSupplierItemStateChanged

 private void txtSearchMedicineTypeNameCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchMedicineTypeNameCaretUpdate
     // TODO add your handling code here:
     String name = txtSearchMedicineTypeName.getText().trim();
     allMedType = medTypeMn.getAllType();
     medTypeTableModel = new DefaultTableModel();
     medTypeTableModel.addColumn("Code");
     medTypeTableModel.addColumn("Category Name");
     medTypeTableModel.addColumn("Amount Medicine of Category");
     Vector<msbsMedicine> tempAllMed = new Vector<msbsMedicine>();
     tempAllMed = medMn.getAllMedicine();
     for (int i = 0; i < allMedType.size(); i++) {
         medType = (msbsMedicineType) allMedType.get(i);
         Vector newData = new Vector();
         if (medType.getMedicineTypeName().toLowerCase().matches(".*" + name + ".*")) {
             int count = 0;
             for (msbsMedicine me : tempAllMed) {
                 if (me.getMedicineTypeCode() == medType.getMedicineTypeCode()) {
                     count++;
                 }
             }
             newData.add(medType.getMedicineTypeCode());
             newData.add(medType.getMedicineTypeName());
             newData.add(count);
             medTypeTableModel.addRow(newData);
         }
     }
     tblCategory.setModel(medTypeTableModel);
     tableColumn = tblCategory.getColumnModel().getColumn(0);
     tableColumn.setPreferredWidth(0);
     tableColumn.setMinWidth(0);
     tableColumn.setMaxWidth(0);
 }//GEN-LAST:event_txtSearchMedicineTypeNameCaretUpdate

 private void txtMeasureSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMeasureSearchCaretUpdate
     // TODO add your handling code here:
     // TODO add your handling code here:
     String search = txtMeasureSearch.getText().trim();
     allMeasure = measureMn.getAllMeasures();
     measureTableModel = new DefaultTableModel();
     measureTableModel.addColumn("Code");
     measureTableModel.addColumn("No");
     measureTableModel.addColumn("Measure Type");
     for (int i = 0; i < allMeasure.size(); i++) {
         measure = (msbsMeasure) allMeasure.get(i);
         Vector newData = new Vector();
         if (measure.getMeasureName().toLowerCase().matches(".*" + search + ".*")) {
             newData.add(i + 1);
             newData.add(measure.getMeasureCode());
             newData.add(measure.getMeasureName());
             measureTableModel.addRow(newData);
         }


     }
     tblMeasure.setModel(measureTableModel);
     tableColumn = tblMeasure.getColumnModel().getColumn(0);
     tableColumn.setPreferredWidth(0);
     tableColumn.setMinWidth(0);
     tableColumn.setMaxWidth(0);
 }//GEN-LAST:event_txtMeasureSearchCaretUpdate

 private void tblMedicineListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMedicineListMousePressed
     // TODO add your handling code here:
     tblMedicineList.removeEditor();
     
}//GEN-LAST:event_tblMedicineListMousePressed

 private void tblMedicineListByCatelogyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMedicineListByCatelogyMousePressed
     // TODO add your handling code here:
     tblMedicineListByCatelogy.removeEditor();
     if (evt.getClickCount() > 1) {
         ViewDetailMedicineForm addNew = new ViewDetailMedicineForm(this.frmParent, false, medMn.getMedicineByCode((int) Integer.valueOf(String.valueOf(tblMedicineListByCatelogy.getValueAt(tblMedicineListByCatelogy.getSelectedRow(), 0)))));
         addNew.setLocationRelativeTo(this);
         addNew.setVisible(true);

     }
     fillMedicineCatelory();
 }//GEN-LAST:event_tblMedicineListByCatelogyMousePressed

 private void rbtnBothItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtnBothItemStateChanged
     // TODO add your handling code here:
     advSearch();
 }//GEN-LAST:event_rbtnBothItemStateChanged

 private void tblMeasureMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMeasureMousePressed
     // TODO add your handling code here:
     if(evt.getClickCount()>1){
          tblCategory.removeEditor();
     }
 }//GEN-LAST:event_tblMeasureMousePressed

 private void tblMedicineListByMeasureMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMedicineListByMeasureMousePressed
     // TODO add your handling code here:
     if(evt.getClickCount()>1){
         tblMeasure.removeEditor();
         ViewDetailMedicineForm addNew = new ViewDetailMedicineForm(this.frmParent, false, medMn.getMedicineByCode((int) Integer.valueOf(String.valueOf(tblMedicineListByMeasure.getValueAt(tblMedicineListByMeasure.getSelectedRow(), 0)))));
         addNew.setLocationRelativeTo(this);
         addNew.setVisible(true);


     }
}//GEN-LAST:event_tblMedicineListByMeasureMousePressed

 private void tblMeasureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMeasureMouseClicked
     // TODO add your handling code here:
     tblMeasure.removeEditor();
     fillMedicineMeasure();
 }//GEN-LAST:event_tblMeasureMouseClicked

 private void tblMedicineListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMedicineListMouseClicked
     // TODO add your handling code here:
     tblMedicineList.removeEditor();
     //JOptionPane.showMessageDialog(this,(int) Integer.valueOf(String.valueOf(tblMedicineList.getValueAt(tblMedicineList.getSelectedRow(), 0))));
     if (evt.getClickCount() > 1) {
         ViewDetailMedicineForm addNew = new ViewDetailMedicineForm(this.frmParent, true, medMn.getMedicineByCode((int) Integer.valueOf(String.valueOf(tblMedicineList.getValueAt(tblMedicineList.getSelectedRow(), 0)))));
         addNew.setLocationRelativeTo(this);
         addNew.setVisible(true);
         advSearch();
     }
}//GEN-LAST:event_tblMedicineListMouseClicked
    private int totalMeasure;
    private int totalMedicine;
    private int totalMedicine2;
    private int totalCatelory;
    private int totalMedicineByCatelory;
    private TableColumn tableColumn;
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
    private JFrame frmParent;
    private DefaultTableModel medicineTableModel;
    private DefaultTableModel medicineModel;
    private DefaultTableModel medTypeTableModel;
    private DefaultTableModel measureTableModel;
    private DefaultTableModel medicineListTableModel;
    private CustomerManage custMn;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd1;
    private javax.swing.JButton btUpdate;
    private javax.swing.JButton btnAddCategory;
    private javax.swing.JButton btnAddMeasure;
    private javax.swing.JButton btnAddMedicine;
    private javax.swing.JButton btnDeleteCategory;
    private javax.swing.JButton btnDeleteMeasure;
    private javax.swing.JButton btnDeleteMedicine;
    private javax.swing.JButton btnUpdateCategory;
    private javax.swing.JButton btnUpdateMeasure;
    private javax.swing.JButton btnUpdateMedicine;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JPanel categoryPane;
    private javax.swing.JComboBox cbMeasureCode;
    private javax.swing.JComboBox cbMeasureCode1;
    private javax.swing.JComboBox cbMedicineTypeCode;
    private javax.swing.JComboBox cbMedicineTypeCode1;
    private javax.swing.JComboBox cbSupplierCode;
    private javax.swing.JComboBox cbSupplierCode1;
    private javax.swing.JComboBox cbxMedicineType;
    private javax.swing.JComboBox cbxSupplier;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel lblTotalCatelory;
    private javax.swing.JLabel lblTotalCatelory1;
    private javax.swing.JLabel lblTotalMedicine;
    private javax.swing.JLabel lblTotalMedicineByCatelory;
    private javax.swing.JLabel lblTotalMedicineByCatelory1;
    private javax.swing.JPanel measurePane;
    private javax.swing.JPanel medicineManager;
    private javax.swing.JRadioButton rbtDemestic1;
    private javax.swing.JRadioButton rbtDomestic;
    private javax.swing.JRadioButton rbtForeign;
    private javax.swing.JRadioButton rbtForeign1;
    private javax.swing.JRadioButton rbtnAdvSearch_Domestic;
    private javax.swing.JRadioButton rbtnAdvSearch_Foreign;
    private javax.swing.JRadioButton rbtnBoth;
    private javax.swing.JTable tblCategory;
    private javax.swing.JTable tblMeasure;
    private javax.swing.JTable tblMedicineList;
    private javax.swing.JTable tblMedicineListByCatelogy;
    private javax.swing.JTable tblMedicineListByMeasure;
    private javax.swing.JTextField txtAdvSearchMedicine;
    private javax.swing.JTextField txtAdvSearchPriceFrom;
    private javax.swing.JTextField txtAdvSearchPriceTo;
    private javax.swing.JTextField txtAvaiableMount;
    private javax.swing.JTextField txtAvaiableMount1;
    private javax.swing.JTextArea txtDesignation;
    private javax.swing.JTextArea txtDesignation1;
    private javax.swing.JTextField txtDosage1;
    private javax.swing.JTextField txtDossage;
    private javax.swing.JTextField txtExpiredDate;
    private javax.swing.JTextField txtExpriedDate1;
    private javax.swing.JTextField txtMeasureSearch;
    private javax.swing.JTextField txtMedicineName;
    private javax.swing.JTextField txtMedicineName1;
    private javax.swing.JTextArea txtNotDesignation;
    private javax.swing.JTextArea txtNotDesignation1;
    private javax.swing.JTextField txtPriceperUnit;
    private javax.swing.JTextField txtPriceperUnit1;
    private javax.swing.JTextField txtRegisterNumber;
    private javax.swing.JTextField txtRegisterNumber1;
    private javax.swing.JTextField txtSearchMedicineTypeName;
    private javax.swing.JTextArea txtUserGuide;
    private javax.swing.JTextArea txtUserGuide1;
    // End of variables declaration//GEN-END:variables
}
