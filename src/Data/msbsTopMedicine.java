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
public class msbsTopMedicine {
    private int code;
    private int quantity;

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
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
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public msbsTopMedicine(){
        this.code=0;
        this.quantity=0;
    }
    public msbsTopMedicine(int pCode,int pQuantity){
        this.code=pCode;
        this.quantity=pQuantity;
    }
 public static Vector<msbsTopMedicine> sort(Vector<msbsTopMedicine> pass){
        for(int i=0;i<pass.size();i++){
            for(int j=i;j<=pass.size()-1;j++){
                if(pass.get(j).getQuantity()>pass.get(i).getQuantity()){
                    msbsTopMedicine temp = new msbsTopMedicine();
                    temp = pass.get(j) ;
                    pass.setElementAt(pass.elementAt(i), j);
                    pass.setElementAt(temp, i);
                }
            }
        }
        return pass;
    }


}
