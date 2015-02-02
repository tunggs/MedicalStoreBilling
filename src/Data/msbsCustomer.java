/*
 * msbsCustomer.java
 *
 * Created on November 3, 2009, 8:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Data;

/**
 *
 * @author hoppd_b00375
 */
public class msbsCustomer {
    
    /** Creates a new instance of msbsCustomer */

    // instance of Customer's Code
    private String customerCode;
    // set customerCode by parameter
    public void setCustomerCode(String pcustomerCode){
        this.customerCode = pcustomerCode;
    }
    // get customerCode function
    public String getCustomerCode(){
        return this.customerCode;
    }

    // instance of name login
    private String customerName;
    // set Customer's Name by parameter
    public void setCustomerName(String pcustomerName){
        this.customerName = pcustomerName;
    }

    //get customerName of user function
    public String getCustomerName(){
        return this.customerName;
    }

    // instance of CustomerType
    private String customerType;
    // set CustomerType by parameter
    public void setCustomerType(String pCustomerType){
        this.customerType = pCustomerType;
    }

    //get CustomerType  function
    public String getCustomerType(){
        return this.customerType;
    }

    // instance of  customerPhone
    private String customerPhone;

    // set customerPhone by parameter
    public void setCustomerPhone(String pcustomerPhone){
        this.customerPhone = pcustomerPhone;
    }

    // get customerPhone function:
    public  String getCustomerPhone(){
        return this.customerPhone;
    }

    // instance of CustomerFax
    private String customerFax ;
    // set CustomerFax of User by parameter
    public void setCustomerFax(String pcustomerFax){
        this.customerFax = pcustomerFax;
    }

    //get  CustomerFaxof user function
    public String getCustomerFax(){
        return this.customerFax;
    }

    // intance of customerEmail:
    private String customerEmail;

    // set customerEmail  by parameter:

    public void setCustomerEmail(String pcustomerEmail){
        this.customerEmail = pcustomerEmail;
    }

    //get customerEmail of user function :
    public String getCustomerEmail(){
        return this.customerEmail;
    }

    //intance of customerAddress:
    private String customerAddress;

    // set customerAddress by parameter:
    public void setCustomerAddress(String pcustomerAddress){
        this.customerAddress = pcustomerAddress;
    }

    //get customerAddress function

    public String getCustomerAddress(){
        return this.customerAddress;
    }

    // intance of customerPriority:
    private String customerPriority;

    // set customerPriority by parameter:
    public void setCustomerPriority(String pcustomerPriority){
        this.customerPriority = pcustomerPriority;
    }

    // get customerPriority function
    public String getCustomerPriority(){
        return this.customerPriority;
    }



    public msbsCustomer(String pcustomerCode,String pcustomerName,String pPassword,String pcustomerPhone,String pcustomerFax,
                    String pcustomerEmail,String pcustomerAddress,String pcustomerPriority) {

        this.customerCode = pcustomerCode;
        this.customerName = pcustomerName;
        this.customerType = pPassword;
        this.customerPhone = pcustomerPhone;
        this.customerFax = pcustomerFax;
        this.customerEmail = pcustomerEmail;
        this.customerAddress = pcustomerAddress;
        this.customerPriority = pcustomerPriority;

    }

    public msbsCustomer() {
        this.customerCode = null;
        this.customerName = null;
        this.customerType = null;
        this.customerPhone = null;
        this.customerFax = null;
        this.customerEmail = null;
        this.customerAddress = null;
        this.customerPriority = null;

    }
}
