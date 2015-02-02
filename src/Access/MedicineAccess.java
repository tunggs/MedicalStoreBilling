/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Data.msbsMedicine;
import Data.msbsOrderDetails;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Dictionary;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class MedicineAccess {

    private ConnectDB con = null;
    private ResultSet rs = null;

    public MedicineAccess() {
        try {
            con = new ConnectDB();
            con.open();
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public Vector<msbsMedicine> getAllMedicine() {
        Vector<msbsMedicine> allMedicine = new Vector<msbsMedicine>();
        try {
            con.open();
            String sql = "spGetAllMedicine";
            ResultSet rs = con.executeQuery(sql);


            while (rs.next()) {
                msbsMedicine newMedicine = new msbsMedicine();

                newMedicine.setMedicineCode(rs.getInt(1));
                newMedicine.setMedicineName(rs.getString(2));
                newMedicine.setMedicineTypeCode(rs.getInt(3));
                newMedicine.setSupplierCode(rs.getInt(4));
                newMedicine.setMeasureCode(rs.getInt(5));
                newMedicine.setPricePerUnit(rs.getFloat(6));
                newMedicine.setAvailableAmount(rs.getInt(7));
                newMedicine.setRegisterNumber(rs.getString(8));
                newMedicine.setMedicineOrigin(rs.getString(9));
                newMedicine.setDesignation(rs.getString(10));
                newMedicine.setNotDesignation(rs.getString(11));
                newMedicine.setDosage(rs.getString(12));
                newMedicine.setExpiredDate(rs.getDate(13));
                newMedicine.setUserGuide(rs.getString(14));
                allMedicine.add(newMedicine);

            }
            con.close();
            return allMedicine;
        } catch (SQLException ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }

    }

    public msbsMedicine getMedicineByCode(int code) {
        msbsMedicine newMedicine = null;
        Vector getMedicineListPara = new Vector();
        getMedicineListPara.add(code);
        try {
            con.open();
            String sql = "spSearchMedicineByCode";
            ResultSet rs = con.executeQuery(sql, getMedicineListPara);
            while (rs.next()) {
                newMedicine = new msbsMedicine();
                newMedicine.setMedicineCode(rs.getInt(1));
                newMedicine.setMedicineName(rs.getString(2));
                newMedicine.setMedicineTypeCode(rs.getInt(3));
                newMedicine.setSupplierCode(rs.getInt(4));
                newMedicine.setMeasureCode(rs.getInt(5));
                newMedicine.setPricePerUnit(rs.getDouble(6));
                newMedicine.setAvailableAmount(rs.getInt(7));
                newMedicine.setRegisterNumber(rs.getString(8));
                newMedicine.setMedicineOrigin(rs.getString(9));
                newMedicine.setDesignation(rs.getString(10));
                newMedicine.setNotDesignation(rs.getString(11));
                newMedicine.setDosage(rs.getString(12));
                newMedicine.setExpiredDate(rs.getDate(13));
                newMedicine.setUserGuide(rs.getString(14));

                return newMedicine;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            Logger.getLogger(MedicineAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
        return null;

    }

    public boolean updateMedicineQuantityLeft(int Medcode, int quantity) {
        try {
            con.open();
            Vector updateMedicinePara = new Vector();
            updateMedicinePara.addElement(Medcode);
            updateMedicinePara.addElement(quantity);
            con.executeUpdate("spUpdateMedicineQuantityLeft", updateMedicinePara);
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }

    }

    public boolean insertMedicine(String medicineName, int medicineTypeCode, int supplierCode, int measureCode, double pricePerUnit, int avaiableMount, String registerNumber,
            String origin, String designation, String notDesiignation, String dosage, Date expiredDate, String userGuide) {
        try {
            con.open();
            Vector insertParaList = new Vector();

            insertParaList.addElement(medicineName);
            insertParaList.addElement(medicineTypeCode);
            insertParaList.addElement(supplierCode);
            insertParaList.addElement(measureCode);
            insertParaList.addElement(pricePerUnit);
            insertParaList.addElement(avaiableMount);
            insertParaList.addElement(registerNumber);
            insertParaList.addElement(origin);
            insertParaList.addElement(designation);
            insertParaList.addElement(notDesiignation);
            insertParaList.addElement(dosage);
            insertParaList.addElement(expiredDate);
            insertParaList.addElement(userGuide);

            con.executeUpdate("spInsertMedicine", insertParaList);
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }

    }

    public boolean updateMedicine(int code, String medicineName, int medicineTypeCode, int supplierCode, int measureCode, double pricePerUnit, int avaiableMount, String registerNumber, String origin, String designation, String notDesiignation, String dosage, Date expiredDate, String userGuide) {
        try {
            con.open();
            Vector updateParaList = new Vector();

            updateParaList.addElement(code);
            updateParaList.addElement(medicineName);
            updateParaList.addElement(medicineTypeCode);
            updateParaList.addElement(supplierCode);
            updateParaList.addElement(measureCode);
            updateParaList.addElement(pricePerUnit);
            updateParaList.addElement(avaiableMount);
            updateParaList.addElement(registerNumber);
            updateParaList.addElement(origin);
            updateParaList.addElement(designation);
            updateParaList.addElement(notDesiignation);
            updateParaList.addElement(dosage);
            updateParaList.addElement(expiredDate);
            updateParaList.addElement(userGuide);
            con.executeUpdate("spUpdateMedicine", updateParaList);
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }

    }

    public boolean deleteMedicine(int medicineCode) {
        try {
            con.open();
            Vector deleteParaList = new Vector();

            deleteParaList.addElement(medicineCode);
            con.executeUpdate("spDeleteMedicine", deleteParaList);
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }

    }
}
