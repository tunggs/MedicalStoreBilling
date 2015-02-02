/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.sql.Date;
import Access.ReportAccess;
import Data.msbsReport;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class ReportManage {

    private ReportAccess ReportAcc;

    public ReportManage() {
        ReportAcc = new ReportAccess();
    }

    public Vector<msbsReport> getAllReport() {
        Vector<msbsReport> allReport = new Vector<msbsReport>();
        if (ReportAcc.getAllReport() != null) {
            allReport = ReportAcc.getAllReport();
            return allReport;
        }
        return null;
    }

    public msbsReport getReportByPayedReportCode(String code) {
        msbsReport report = new msbsReport();
        if (ReportAcc.getReportByPayedReportCode(code) != null) {
            report = ReportAcc.getReportByPayedReportCode(code);
            return report;
        }
        return null;
    }

    public String getCodeByName(String name) {
        Vector<msbsReport> allReport = ReportAcc.getAllReport();
        msbsReport report = new msbsReport();
        for (int i = 0; i < allReport.size(); i++) {
            report = (msbsReport) allReport.get(i);
            if (report.getReportName().equals(name)) {
                return report.getPayedReportCode();
            }
        }
        return null;
    }

    public boolean insertReport(String payCode, String name, Date dateCreate) {
        if (ReportAcc.insertReport(payCode, name, dateCreate)) {
            return true;
        }
        return false;
    }

    public boolean  updateReport(String payCode,Date newDate){
        if(ReportAcc.UpdateReportDate(payCode, newDate)){
            return true;
        }
        return false;
    }

}
