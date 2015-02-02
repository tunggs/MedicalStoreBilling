/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;
import Access.ReportDetailsAccess;
import Data.msbsReportDetails;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class ReportDetailsManage {

    private ReportDetailsAccess ReportDetailsAcc;

    public ReportDetailsManage() {
        ReportDetailsAcc = new ReportDetailsAccess();
    }

    public boolean insertReportDetails(String payedReportCode, String oCode) {
        if (ReportDetailsAcc.insertReportDetails(payedReportCode, oCode)) {
            return true;
        }
        return false;
    }

     public Vector<msbsReportDetails> getReportDetailsByCode(String code){
        Vector<msbsReportDetails> allReportDetails = new Vector<msbsReportDetails>();
        if(ReportDetailsAcc.getReportDetailByCode(code) != null){
            allReportDetails = ReportDetailsAcc.getReportDetailByCode(code);

            return allReportDetails;
        }
        return null;
    }

     public boolean deleteReportDetailsByCode(String code) {
        if (ReportDetailsAcc.deleteReportDetailsByCode(code)) {
            return true;
        }
        return false;
    }
}
