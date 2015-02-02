/*
 * msbsOrders.java
 *
 * Created on November 3, 2009, 9:04 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Data;

import java.sql.Date;

/**
 *
 * @author hoppd_b00375
 */
public class msbsOrders {
    
    /** Creates a new instance of msbsOrders */
    
    // intance of orderCode:
    private String orderCode;

    // set orderCode function:
    public void setOrderCode(String pOderCode){
        this.orderCode = pOderCode;
    }

    // get orderCode function:
    public String getOrderCode(){
        return this.orderCode;
    }

    // intance of customerCode in Order:
    private String customerCode;

    // set customerCode function:
    public void setCustomerCode(String pCtCode){
        this.customerCode = pCtCode;
    }

    // get customerCode function:
    public String getCustomerCode(){
        return this.customerCode;
    }

    // intance of userCode in Order:
    private int userCode;
    // set userCode by parameter
    public void setUserCode(int pUserCode){
        this.userCode = pUserCode;
    }
    // get userCode function
    public int getUserCode(){
        return this.userCode;
    }

    // intance of Order's status:
    private int status;

    // set Order's status function:
    public void setStatus(int pStatus){
        this.status = pStatus;
    }

    // get Order's status:
    public int getStatus(){
        return this.status;
    }

    //intance of order's total money
    private double total;

    //set total function:
    public void setTotal(double pTotal){
        this.total = pTotal;
    }

    // get total function:
    public double getTotal(){
        return this.total;
    }

    private double paidMoney;
    /**
     * @return the paidMoney
     */
    public double getPaidMoney() {
        return paidMoney;
    }

    /**
     * @param paidMoney the paidMoney to set
     */
    public void setPaidMoney(double paidMoney) {
        this.paidMoney = paidMoney;
    }

    // intace date of order:
    private Date orderDate;

    // set orderDate function:
    public void setOrderDate(Date pOrderDate){
        this.orderDate = pOrderDate;
    }

    // get orderDate function:
    public Date getOrderDate(){
        return this.orderDate;
    }

    //intance Address to Deliver medicine:
    private String addressToDeliver;

    // set address :
    public void setAddressToDeliver(String pAddress){
        this.addressToDeliver = pAddress;
    }

    // get address:
    public String getAddressToDeliver(){
        return this.addressToDeliver;
    }

    // intance of expired time to Deliver medicine:
    private Date expiredTime;

    //set expiredTime:
    public void setExpiredTime(Date pExpired){
        this.expiredTime = pExpired;
    }

    //get expiredTime:
    public Date getExpiredTime(){
        return this.expiredTime;
    }

    private int tax;

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    private int discount;
    /**
     * @return the discount
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(int discount) {
        this.discount = discount;
    }


    public msbsOrders(String orderCode, String customerCode, int userCode, int status, double total,double paidMoney,
                       Date orderDate, String addressToDeliver,Date expiredTime,int tax,int discount) {
        this.orderCode = orderCode;
        this.customerCode = customerCode;
        this.userCode = userCode;
        this.status = status;
        this.total = total;
        this.paidMoney = paidMoney;
        this.orderDate = orderDate;
        this.addressToDeliver = addressToDeliver;
        this.expiredTime = expiredTime;
        this.tax=tax;
        this.discount=discount;
    }

    public msbsOrders() {
        this.orderCode = null;
        this.customerCode = null;
        this.userCode = 0;
        this.status = 0;
        this.total = 0.0;
        this.paidMoney=0.0;
        this.orderDate = null;
        this.addressToDeliver = null;
        this.expiredTime = null;
        this.tax=0;
        this.discount=0;
    }

    
}
