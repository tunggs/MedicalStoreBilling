/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Access.MedicineTypeAccess;
import Data.msbsMedicineType;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class MedicineTypeManage {
    private MedicineTypeAccess medTypeAcc;

    public MedicineTypeManage(){
        medTypeAcc = new MedicineTypeAccess();
    }

    public Vector<msbsMedicineType> getAllType(){
        Vector<msbsMedicineType> allMedType = new Vector<msbsMedicineType>();
        if(medTypeAcc.getAllMedicineType() != null){
            allMedType = medTypeAcc.getAllMedicineType();
            return allMedType;
        }

        return null;

    }

    public int getCodeByName(String name){
        Vector<msbsMedicineType> allMedType = medTypeAcc.getAllMedicineType();
        msbsMedicineType medType = new msbsMedicineType();
        for(int i = 0; i < allMedType.size(); i++){
            medType = (msbsMedicineType)allMedType.get(i);
            if(medType.getMedicineTypeName().equals(name)){
                return medType.getMedicineTypeCode();
            }
        }

        return -1;
    }

    public msbsMedicineType getTypeByCode(int code){
        msbsMedicineType medType = new msbsMedicineType();
        if(medTypeAcc.getMedicineTypeByCode(code) != null ){
            medType = medTypeAcc.getMedicineTypeByCode(code);
            return medType;
        }

        return null;
    }

    public boolean insertMedicineType(String name){
        if(medTypeAcc.insertMedicineType(name)){
            return true;
        }

        return false;
    }

    public boolean updateMedicineType(int code,String name){
        if(medTypeAcc.updateMedicineType(code,name)){
            return true;
        }

        return false;
    }
    public boolean deleteMedicineType(int code){
        if(medTypeAcc.deleteMedicineType(code)){
            return true;
        }

        return false;
    }
}
