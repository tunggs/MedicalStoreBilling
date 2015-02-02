/*
 * Provider.java
 *
 * Created on November 3, 2009, 8:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Data;

/**
 *
 * @author hoppd_b00375
 */
public class msbsSupplier {
    
    /** Creates a new instance of Provider */

    // instance of supplierCode
     private int supplierCode;
    // set supplierCode by parameter
    public void setSupplierCode(int pSupplierCode){
        this.supplierCode = pSupplierCode;
    }
    // get supplierCode function
    public int getSupplierCode(){
        return this.supplierCode;
    }

    // instance of Supplier Name
    private String supplierName;
    // set Suplier Name by parameter
    public void setSupplierName(String pSupplierName){
        this.supplierName = pSupplierName;
    }

    //get supplierName of user function
    public String getSupplierName(){
        return this.supplierName;
    }

    private String supplierFullName;
    // set Suplier Name by parameter
    public void setSupplierFullName(String pSupplierFullName){
        this.supplierFullName = pSupplierFullName;
    }

    //get supplierName of user function
    public String getSupplierFullName(){
        return this.supplierFullName;
    }
    // instance of Supplier's Address
    private String supplierAddres;
    // set Supplier's Address by parameter
    public void setSupplierAddress(String pSupplierAddress){
        this.supplierAddres = pSupplierAddress;
    }

    //get Supplier Address of user function
    public String getSupplierAddress(){
        return this.supplierAddres;
    }

    // instance of Supplier's Phone
    private String supplierPhone;

    // set supplierPhone by parameter
    public void setSupplierPhone(String pSupplierPhone){
        this.supplierPhone = pSupplierPhone;
    }

    // get supplierPhone function:
    public  String getSupplierPhone(){
        return this.supplierPhone;
    }

    // instance of Supplier's Fax
    private String supplierFax ;
    // set Supplier Fax of User by parameter
    public void setSupplierFax(String pSupplierFax){
        this.supplierFax = pSupplierFax;
    }

    //get  Supplier Fax of user function
    public String getSupplierFax(){
        return this.supplierFax;
    }

    // intance of Supplier's Email:
    private String supplierEmail;

    // set  Supplier's Email by parameter:

    public void setSupplierEmail(String pSupplierEmail){
        this.supplierEmail = pSupplierEmail;
    }

    //get  Supplier's Email function :
    public String getSupplierEmail(){
        return this.supplierEmail;
    }

    //intance of supplierWebsite:
    private String supplierWebsite;

    // set Supplier's Website by parameter:
    public void setSupplierWebsite(String pSupplierWebsite){
        this.supplierWebsite = pSupplierWebsite;
    }

    //get supplierWebsite function

    public String getSupplierWebsite(){
        return this.supplierWebsite;
    }



    public msbsSupplier(int psupplierCode,String psupplierName,String pPassword,String psupplierPhone,String psupplierFax,
                    String psupplierEmail,String psupplierWebsite) {

        this.supplierCode = psupplierCode;
        this.supplierName = psupplierName;
        this.supplierAddres = pPassword;
        this.supplierPhone = psupplierPhone;
        this.supplierFax = psupplierFax;
        this.supplierEmail = psupplierEmail;
        this.supplierWebsite = psupplierWebsite;
      
    }

    public msbsSupplier() {
        this.supplierCode = 0;
        this.supplierName = null;
        this.supplierAddres = null;
        this.supplierPhone = null;
        this.supplierFax = null;
        this.supplierEmail = null;
        this.supplierWebsite = null;
       
    }
   
    
}
