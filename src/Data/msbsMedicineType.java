/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Data;

/**
 *
 * @author Administrator
 */
public class msbsMedicineType {

    // intance for Type of medicine:
    private int medicineTypeCode;

    // set medicineTypeCode:
    public void setMedicineTypeCode(int pTypeCode){
        this.medicineTypeCode = pTypeCode;
    }

    // get medicineTypeCode:
    public int getMedicineTypeCode(){
        return this.medicineTypeCode;
    }

    private String medicineTypeName;

    // set name of medicine type:
    public void setMedicineTypeName(String pName){
        this.medicineTypeName = pName;
    }

    //get name of medicine type:
    public String getMedicineTypeName(){
        return this.medicineTypeName;
    }

    public msbsMedicineType(int pCode,String pName){
        this.medicineTypeCode = pCode;
        this.medicineTypeName = pName;
    }
    public msbsMedicineType(){
        this.medicineTypeCode = 0;
        this.medicineTypeName = null;
    }
}
