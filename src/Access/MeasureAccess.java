/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Data.msbsMeasure;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class MeasureAccess {

    private ConnectDB con = null;
    private ResultSet rs = null;

    public MeasureAccess() {
        try {
            con = new ConnectDB();
            con.open();
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public Vector<msbsMeasure> getAllMeasure() {
        Vector<msbsMeasure> allMeasure = new Vector<msbsMeasure>();
        try {
            con.open();
            String sql = "spGetAllMeasure";
            ResultSet rs = con.executeQuery(sql);
            while (rs.next()) {
                msbsMeasure newMeasure = new msbsMeasure();

                newMeasure.setMeasureCode(rs.getInt(1));
                newMeasure.setMeasureName(rs.getString(2));
                allMeasure.add(newMeasure);
            }
            con.close();
            return allMeasure;
        } catch (SQLException ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (Exception ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public msbsMeasure getMeasureByCode(int code) {
        msbsMeasure newMeasure = null;
        Vector getMeasureListPara = new Vector();
        getMeasureListPara.add(code);
        try {
            con.open();
            String sql = "spGetMeasureByCode";
            ResultSet rs = con.executeQuery(sql, getMeasureListPara);
            while (rs.next()) {
                newMeasure = new msbsMeasure();
                newMeasure.setMeasureCode(rs.getInt(1));
                newMeasure.setMeasureName(rs.getString(2));

                return newMeasure;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (Exception ex) {
            Logger.getLogger(UserAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean insertMeasure(String MeaureName) {
        try {
            con.open();
            Vector insertParaList = new Vector();

            insertParaList.addElement(MeaureName);

            con.executeUpdate("spInsertMeasure", insertParaList);
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

    public boolean updateMeasure(int MeasureCode, String MeasureName) {
        try {
            con.open();
            Vector updateParaList = new Vector();
            updateParaList.addElement(MeasureCode);
            updateParaList.addElement(MeasureName);

            con.executeUpdate("spUpdateMeasure", updateParaList);
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

    public boolean deleteMeasure(int MeasureCode) {
        try {
            con.open();
            Vector deleteParaList = new Vector();

            deleteParaList.addElement(MeasureCode);
            con.executeUpdate("spDeleteMeasure", deleteParaList);
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


