/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import java.sql.Date;
import Data.msbsReport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ReportAccess {

    private ConnectDB con = null;
    private ResultSet rs = null;

    public ReportAccess() {
        try {
            con = new ConnectDB();
            con.open();
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public Vector<msbsReport> getAllReport() {
        Vector<msbsReport> allReports = new Vector<msbsReport>();
        try {
            con.open();
            String sql = "spGetAllPaypedReport";
            ResultSet rs = con.executeQuery(sql);


            while (rs.next()) {
                msbsReport newPr = new msbsReport();

                newPr.setPayedReportCode(rs.getString(1));
                newPr.setReportName(rs.getString(2));
                newPr.setDateCreated(rs.getDate(3));
                allReports.add(newPr);

            }
            con.close();
            return allReports;
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

    public msbsReport getReportByPayedReportCode(String code) {
        msbsReport newPr = null;
        Vector getOrderListPara = new Vector();
        getOrderListPara.add(code);
        try {
            con.open();
            String sql = "spSearchPaypedReportByCode";
            ResultSet rs = con.executeQuery(sql, getOrderListPara);
            while (rs.next()) {
                newPr = new msbsReport();
                newPr.setPayedReportCode(rs.getString(1));
                newPr.setReportName(rs.getString(2));
                newPr.setDateCreated(rs.getDate(3));

                return newPr;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean insertReport(String payedReportCode, String reportName, Date dateCreated) {
        try {
            con.open();
            Vector insertRepostListPara = new Vector();

            insertRepostListPara.addElement(payedReportCode);
            insertRepostListPara.addElement(reportName);
            insertRepostListPara.addElement(dateCreated);

            con.executeUpdate("spInsertPaypedReport", insertRepostListPara);
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

    public boolean UpdateReportDate(String payedReportCode, Date newDate) {
        try {
            con.open();
            Vector insertRepostListPara = new Vector();

            insertRepostListPara.addElement(payedReportCode);
            insertRepostListPara.addElement(newDate);

            con.executeUpdate("spUpdatePayedReport", insertRepostListPara);
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
