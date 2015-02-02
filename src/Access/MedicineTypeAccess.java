/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Data.msbsMedicineType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class MedicineTypeAccess {

    private ConnectDB con = null;
    private ResultSet rs = null;

    public MedicineTypeAccess() {
        try {
            con = new ConnectDB();
            con.open();
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public Vector<msbsMedicineType> getAllMedicineType() {
        Vector<msbsMedicineType> allMedicineType = new Vector<msbsMedicineType>();
        try {
            con.open();
            String sql = "spGetAllMedicineType";
            ResultSet rs = con.executeQuery(sql);


            while (rs.next()) {
                msbsMedicineType newMedicineType = new msbsMedicineType();

                newMedicineType.setMedicineTypeCode(rs.getInt(1));
                newMedicineType.setMedicineTypeName(rs.getString(2));
                allMedicineType.add(newMedicineType);
            }
            con.close();
            return allMedicineType;
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

    public msbsMedicineType getMedicineTypeByCode(int code) {
        msbsMedicineType newMedicineType = null;
        Vector getMedicineTypeListPara = new Vector();
        getMedicineTypeListPara.add(code);
        try {
            con.open();
            String sql = "spGetMedicineTypeByCode";
            ResultSet rs = con.executeQuery(sql, getMedicineTypeListPara);
            while (rs.next()) {
                newMedicineType = new msbsMedicineType();
                newMedicineType.setMedicineTypeCode(rs.getInt(1));
                newMedicineType.setMedicineTypeName(rs.getString(2));

                return newMedicineType;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            Logger.getLogger(UserAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean insertMedicineType(String MedicineTypeName) {
        try {
            con.open();
            Vector insertParaList = new Vector();

            insertParaList.addElement(MedicineTypeName);

            con.executeUpdate("spInsertMedicineType", insertParaList);
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

    public boolean updateMedicineType(int code, String MedicineTypeName) {
        try {
            con.open();
            Vector updateParaList = new Vector();
            updateParaList.addElement(code);
            updateParaList.addElement(MedicineTypeName);

            con.executeUpdate("spUpdateMedicineType", updateParaList);
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

    public boolean deleteMedicineType(int MedicineTypeCode) {
        try {
            con.open();
            Vector deleteParaList = new Vector();

            deleteParaList.addElement(MedicineTypeCode);
            con.executeUpdate("spDeleteMedicineType", deleteParaList);
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
