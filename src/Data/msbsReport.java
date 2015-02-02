/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Data;
import java.sql.Date;

/**
 *
 * @author Administrator
 */
public class msbsReport {
    private String payedReportCode;

    /**
     * @return the payedReportCode
     */
    public String getPayedReportCode() {
        return payedReportCode;
    }

    /**
     * @param payedReportCode the payedReportCode to set
     */
    public void setPayedReportCode(String payedReportCode) {
        this.payedReportCode = payedReportCode;
    }


    private String reportName;
    
     /**
     * @return the reportName
     */
    public String getReportName() {
        return reportName;
    }

    /**
     * @param reportName the reportName to set
     */
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }


    private Date dateCreated;

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    // create contructer 
    public msbsReport() {

    }

    public msbsReport(String payedReportCode, String reportName, Date dateCreated) {
        this.payedReportCode = payedReportCode;
        this.reportName = reportName;
        this.dateCreated = dateCreated;
    }

   
}
