/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Access.MedicineAccess;
import Access.SupplierAccess;
import Data.msbsMedicine;
import Data.msbsSupplier;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class SupplierManage {
    private SupplierAccess supAcc;
    private Vector<msbsMedicine> allMed;
    private MedicineAccess medAcc;
    public SupplierManage(){
        supAcc  = new SupplierAccess();
        allMed = new Vector<msbsMedicine>();
        medAcc = new MedicineAccess();
    }

    public Vector<msbsSupplier> getAllSupplier(){
        Vector<msbsSupplier> allSupplier = new Vector<msbsSupplier>();
        if(supAcc.getAllSupplier() != null){
            allSupplier = supAcc.getAllSupplier();
            return allSupplier;
        }

        return null;
    }
    public msbsSupplier getSupplierByCode(int code){
        msbsSupplier supplier = new msbsSupplier();
        if(supAcc.getSupplierByCode(code) != null){
            supplier = supAcc.getSupplierByCode(code);
            return supplier;
        }
        return null;
    }
    public int getCodeByName(String name){
        Vector<msbsSupplier> allSup = supAcc.getAllSupplier();
        msbsSupplier supplier = new msbsSupplier();
        for(int i = 0; i < allSup.size(); i++){
            supplier = (msbsSupplier)allSup.get(i);
            if(supplier.getSupplierName().equals(name)){
                return supplier.getSupplierCode();
            }
        }
        return -1;
    }
    public boolean insertSupplier(String supName,String supFullName,String supAddress,String supPhone,String supFax, String supEmail,String supWebsite){
        if(supAcc.insertSupplier(supName, supFullName, supAddress, supPhone, supFax, supEmail, supWebsite)){
            return true;
        }
        return false;
    }

    public boolean updateSupplier(int supCode,String supName,String supFullName,String supAddress,String supPhone,String supFax, String supEmail,String supWebsite){
        if(supAcc.updateSupplier(supCode, supName, supFullName, supAddress, supPhone, supFax, supEmail, supWebsite)){
            return true;
        }
        return false;
    }

    public boolean deleteSupplier(int supCode){
        allMed  = medAcc.getAllMedicine();
        for(int i=0;i<allMed.size();i++){
            msbsMedicine temp = new msbsMedicine();
            temp = (msbsMedicine)allMed.get(i);
            if(temp.getSupplierCode() == supCode){
                return false;
            }
        }
        if(supAcc.deleteSupplier(supCode)){
            return true;
        }
        return false;
    }
}
