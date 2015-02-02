/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Data;

import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class msbsTopCustomer {
    private String code;
    private double quantity;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    public msbsTopCustomer(){
        this.code=null;
        this.quantity=0;
    }
    public msbsTopCustomer(String pCode,double pQuantity){
        this.code=pCode;
        this.quantity=pQuantity;
    }
 public static Vector<msbsTopCustomer> sort(Vector<msbsTopCustomer> pass){
        for(int i=0;i<pass.size();i++){
            for(int j=i;j<=pass.size()-1;j++){
                if(pass.get(j).getQuantity()>pass.get(i).getQuantity()){
                    msbsTopCustomer temp = new msbsTopCustomer();
                    temp = pass.get(j) ;
                    pass.setElementAt(pass.elementAt(i), j);
                    pass.setElementAt(temp, i);
                }
            }
        }
        return pass;
    }


}
