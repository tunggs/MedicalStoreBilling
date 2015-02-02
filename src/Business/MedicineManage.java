/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Access.MedicineAccess;
import Data.msbsMedicine;
import java.sql.Date;
import java.util.Vector;
/**
 *
 * @author Administrator
 */
public class MedicineManage {
    private MedicineAccess medAcc;

    public MedicineManage(){
        medAcc = new MedicineAccess();
    }

    public Vector<msbsMedicine> getAllMedicine(){
        Vector<msbsMedicine> allMed = new Vector<msbsMedicine>();

        if(medAcc.getAllMedicine() != null){
            allMed = medAcc.getAllMedicine();

            return allMed;
        }

        return null;
    }

    public msbsMedicine getMedicineByCode(int code){
        msbsMedicine medicine = new msbsMedicine();
        if(medAcc.getMedicineByCode(code) != null){
            medicine = medAcc.getMedicineByCode(code);
            return medicine;
        }


        return null;
    }

    public int getCodeByName(String name){
        Vector<msbsMedicine> allMed = medAcc.getAllMedicine();
        msbsMedicine medicine = new msbsMedicine();
        for(int i = 0; i < allMed.size(); i++){
            medicine = (msbsMedicine)allMed.get(i);
            if(medicine.getMedicineName().equals(name)){
                return medicine.getMedicineCode();
            }
        }
        return -1;
    }

    public Vector<msbsMedicine> getMedicineByType(int code){
        Vector<msbsMedicine> allMed = new Vector<msbsMedicine>();
        Vector<msbsMedicine> allMedByType = new Vector<msbsMedicine>();
        allMed = medAcc.getAllMedicine();
        msbsMedicine medicine = new msbsMedicine();
        for(int i = 0; i < allMed.size(); i++){
            medicine = (msbsMedicine)allMed.get(i);
            if(medicine.getMedicineTypeCode() == code){
                allMedByType.addElement(medicine);
            }
        }
        if(allMedByType.size() != 0){
            return allMedByType;
        }
        return null;

    }
    public Vector<msbsMedicine> getMedicineMeasure(int code){
        Vector<msbsMedicine> allMed = new Vector<msbsMedicine>();
        Vector<msbsMedicine> allMedByMeasure = new Vector<msbsMedicine>();
        allMed = medAcc.getAllMedicine();
        msbsMedicine medicine = new msbsMedicine();
        for(int i = 0; i < allMed.size(); i++){
            medicine = (msbsMedicine)allMed.get(i);
            if(medicine.getMeasureCode() == code){
                allMedByMeasure.addElement(medicine);
            }
        }
        if(allMedByMeasure.size() != 0){
            return allMedByMeasure;
        }
        return null;

    }

    public Vector<msbsMedicine> getMedicineBySupplier(int code){
        Vector<msbsMedicine> allMed = new Vector<msbsMedicine>();
        Vector<msbsMedicine> allMedBySupplier = new Vector<msbsMedicine>();
        allMed = medAcc.getAllMedicine();
        msbsMedicine medicine = new msbsMedicine();
        for(int i = 0; i < allMed.size(); i++){
            medicine = (msbsMedicine)allMed.get(i);
            if(medicine.getSupplierCode() == code){
                allMedBySupplier.addElement(medicine);
            }
        }
        if(allMedBySupplier.size() != 0){
            return allMedBySupplier;
        }
        return null;

    }

   public boolean updateMedQuantityLeft(int medCode,int quantity){
        if(medAcc.updateMedicineQuantityLeft(medCode, quantity)){
            return true;
        }

        return false;
    }

    public boolean insertMedicine(String medicineName,int medicineTypeCode,int supplierCode,int measureCode,double pricePerUnit,int avaiableMount,String registerNumber,String origin,String designation,String notDesiignation,String dosage,Date expiredDate,String userGuide){
        if(medAcc.insertMedicine(medicineName, medicineTypeCode, supplierCode, measureCode, pricePerUnit, avaiableMount, registerNumber, origin, designation, notDesiignation, dosage, expiredDate, userGuide)){
            return true;
        }

        return false;
    }

    public boolean updateMedicine(int code,String medicineName,int medicineTypeCode,int supplierCode,int measureCode,double pricePerUnit,int avaiableMount,String registerNumber,String origin,String designation,String notDesiignation,String dosage,Date expiredDate,String userGuide){
        if(medAcc.updateMedicine(code,medicineName, medicineTypeCode, supplierCode, measureCode, pricePerUnit, avaiableMount, registerNumber, origin, designation, notDesiignation, dosage, expiredDate, userGuide)){

            return true;
        }

        return false;
    }

    public boolean deleteMedicine(int code){
        if(medAcc.deleteMedicine(code)){
            return true;
        }

        return false;
    }
    

}
