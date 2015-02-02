/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Data.msbsReportDetails;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ReportDetailsAccess {

    private ConnectDB con = null;
    private ResultSet rs = null;

    public ReportDetailsAccess() {
        try {
            con = new ConnectDB();
            con.open();
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public boolean insertReportDetails(String pCode, String orderCode) {
        try {
            con.open();
            Vector insertParaList = new Vector();

            insertParaList.addElement(pCode);
            insertParaList.addElement(orderCode);

            con.executeUpdate("spInsertReportDetails", insertParaList);
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

    public Vector<msbsReportDetails> getReportDetailByCode(String code) {
        Vector<msbsReportDetails> allReportDetails = null;
        Vector getOrderListPara = new Vector();
        allReportDetails = new Vector<msbsReportDetails>();
        getOrderListPara.add(code);
        try {
            con.open();
            String sql = "spGetReportDetailByCode";
            ResultSet rs = con.executeQuery(sql, getOrderListPara);
            while (rs.next()) {
                msbsReportDetails newReportD = new msbsReportDetails();

                newReportD.setPaypedReportCode(rs.getString(1));
                newReportD.setOrderCode(rs.getString(2));
                allReportDetails.add(newReportD);
            }
            con.close();
            return allReportDetails;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            Logger.getLogger(MedicineAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }

    }

    public boolean deleteReportDetailsByCode(String payedReportCode) {
        try {
            con.open();
            Vector deleteReportListPara = new Vector();
            deleteReportListPara.addElement(payedReportCode);
            con.executeUpdate("spDeleteReportDetailsByCode", deleteReportListPara);
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
