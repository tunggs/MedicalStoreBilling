/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Data.msbsSupplier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class SupplierAccess {

    private ConnectDB con = null;
    private ResultSet rs = null;

    public SupplierAccess() {
        try {
            con = new ConnectDB();
            con.open();
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public Vector<msbsSupplier> getAllSupplier() {
        Vector<msbsSupplier> allSupplier = new Vector<msbsSupplier>();
        try {
            con.open();
            String sql = "spGetAllSupplier";
            ResultSet rs = con.executeQuery(sql);

            while (rs.next()) {
                msbsSupplier newSup = new msbsSupplier();
                newSup.setSupplierCode(rs.getInt(1));
                newSup.setSupplierName(rs.getString(2));
                newSup.setSupplierFullName(rs.getString(3));
                newSup.setSupplierAddress(rs.getString(4));
                newSup.setSupplierPhone(rs.getString(5));
                newSup.setSupplierFax(rs.getString(6));
                newSup.setSupplierEmail(rs.getString(7));
                newSup.setSupplierWebsite(rs.getString(8));

                allSupplier.add(newSup);

            }
            con.close();
            return allSupplier;
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

    public msbsSupplier getSupplierByCode(int code) {
        msbsSupplier supplier = null;
        Vector getSupListPara = new Vector();
        getSupListPara.add(code);
        try {
            con.open();
            String sql = "spSearchSupplierByCode";
            ResultSet rs = con.executeQuery(sql, getSupListPara);
            while (rs.next()) {
                supplier = new msbsSupplier();
                supplier.setSupplierCode(rs.getInt(1));
                supplier.setSupplierName(rs.getString(2));
                supplier.setSupplierFullName(rs.getString(3));
                supplier.setSupplierAddress(rs.getString(4));
                supplier.setSupplierPhone(rs.getString(5));
                supplier.setSupplierFax(rs.getString(6));
                supplier.setSupplierEmail(rs.getString(7));
                supplier.setSupplierWebsite(rs.getString(8));

                return supplier;
            }
        } catch (Exception ex) {
            Logger.getLogger(UserAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return null;
    }

    public boolean insertSupplier(String supName, String supFullName, String supAddress, String supPhone, String supFax, String supEmail, String supWebsite) {
        Vector insertSupParamList = new Vector();
        try {
            insertSupParamList.add(supName);
            insertSupParamList.add(supFullName);
            insertSupParamList.add(supAddress);
            insertSupParamList.add(supPhone);
            insertSupParamList.add(supFax);
            insertSupParamList.add(supEmail);
            insertSupParamList.add(supWebsite);
            con.open();

            con.executeUpdate("spInsertSupplier", insertSupParamList);
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

    public boolean updateSupplier(int supCode, String supName, String supFullName, String supAddress, String supPhone, String supFax, String supEmail, String supWebsite) {
        Vector updateSupParamList = new Vector();
        try {
            updateSupParamList.add(supCode);
            updateSupParamList.add(supName);
            updateSupParamList.add(supFullName);
            updateSupParamList.add(supAddress);
            updateSupParamList.add(supPhone);
            updateSupParamList.add(supFax);
            updateSupParamList.add(supEmail);
            updateSupParamList.add(supWebsite);
            con.open();

            con.executeUpdate("spUpdateSupplier", updateSupParamList);
            con.close();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteSupplier(int supCode) {
        Vector deleteSupParamList = new Vector();
        try {

            deleteSupParamList.add(supCode);
            con.open();
            con.executeUpdate("spDeleteSupplier", deleteSupParamList);
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return false;
    }
}
