/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Data;

/**
 *
 * @author Administrator
 */
public class msbsOrderDetails {

    // intance of orderCode in OrderDetails:
    private String orderCode;

    // set orderCode function:
    public void setOrderCode(String pOderCode){
        this.orderCode = pOderCode;
    }

    // get orderCode function:
    public String getOrderCode(){
        return this.orderCode;
    }

     // instance of Customer's Code
    private int medicineCode;
    // set customerCode by parameter
    public void setMedicineCode(int pMedicineCode){
        this.medicineCode = pMedicineCode;
    }
    // get customerCode function
    public int getMedicineCode(){
        return this.medicineCode;
    }

    // intance price per medicine in oder:
    private double price;

    // set price function:
    public void setPrice(double pPrice){
        this.price = pPrice;
    }

    // get medicine's price per unit:
    public double getPrice(){
        return this.price;
    }   

     // intance quantity:
    private int quantity;

    // set quantity function:
    public void setQuantity(int pQuantity){
        this.quantity = pQuantity;
    }

    // get quantity function:
    public int getQuantity(){
        return this.quantity;
    }
    public msbsOrderDetails(String pOrderCode,int pMedCode,double pPrice,int pQuantity){
        this.orderCode = pOrderCode;
        this.medicineCode = pMedCode;
        this.price = pPrice;
        this.quantity = pQuantity;
    }

    public msbsOrderDetails(){
        this.orderCode = null;
        this.medicineCode = 0;
        this.price = 0.0;
        this.quantity = 0;
    }

   
}
