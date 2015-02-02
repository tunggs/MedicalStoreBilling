/*
 * msbsMedicine.java
 *
 * Created on November 3, 2009, 9:03 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Data;

import java.util.Date;

/**
 *
 * @author hoppd_b00375
 */
public class msbsMedicine {
    
    /** Creates a new instance of msbsMedicine */

    // intance of Medicine's Code:
    private int medicineCode;

    // set medicineCode by parameter funcion:
    public void setMedicineCode(int pMedicineCode){
        this.medicineCode = pMedicineCode;
    }

    // get medicineCode funcion:
    public int getMedicineCode(){
        return this.medicineCode;
    }

    // intance of Medicine's Name:
    private String medicineName;

    // set medicineName by parameter function:
    public void setMedicineName(String pMedicineName){
        this.medicineName = pMedicineName;
    }

    // get medicineName function:
    public String getMedicineName(){
        return this.medicineName;
    }

    // intance of MedicineTypeCode:
    private int medicineTypeCode;

    // set medicineTypeCode by paramerter function:
    public void setMedicineTypeCode(int pMedicineTypeCode){
        this.medicineTypeCode = pMedicineTypeCode;
    }

    // get medicineTypeCode function:
    public int getMedicineTypeCode(){
        return this.medicineTypeCode;
    }

    // intance of medicine's supplierCode
    private int supplierCode;

    // set supplierCode by paramerter function:
    public void setSupplierCode(int pSupplierCode){
        this.supplierCode = pSupplierCode;
    }

    // get supplierCode function:
    public int getSupplierCode(){
        return this.supplierCode;
    }

    // intance of medicine's measureCode
    private int measureCode;

    // set medicine's measureCode byparemeter function:
    public void setMeasureCode(int pMeasureCode){
        this.measureCode = pMeasureCode;
    }

    // get medicine's measureCode function:
    public int getMeasureCode(){
        return this.measureCode;
    }

    // intance medicine's price per unit:
    private double pricePerUnit;

    // set medicine price per unit by parameter function:
    public void setPricePerUnit(double pPricePerUnit){
        this.pricePerUnit = pPricePerUnit;
    }

    // get medicine's price per unit:
    public double getPricePerUnit(){
        return this.pricePerUnit;
    }

    // intance of medicine's available amount in stock:
    private int availableAmount;

    // set medicine's available amount by parameter function:
    public void setAvailableAmount(int pAvailableAmount){
        this.availableAmount = pAvailableAmount;
    }

    // get medicine's available amount function:
    public int getAvailableAmount(){
        return this.availableAmount;
    }

    // intance of medicine's register number:
    private String registerNumber;

    // set medicine's register number by parameter function:
    public void setRegisterNumber(String pRegisterNumber){
        this.registerNumber = pRegisterNumber;
    }

    //get medicine's register number function:

    public String getRegisterNumber(){
        return this.registerNumber;
    }

    // intance of medicine's origin:
    private String medicineOrigin;

    // set medicine's origin by parameter function:
    public void setMedicineOrigin(String pOrigin){
        this.medicineOrigin = pOrigin;
    }

    // get medicine's Origin:
    public String getMedicineOrigine(){
        return this.medicineOrigin;
    }

    // intance of medicine's designation:
    private String designation;

    // set medicine's designation by parameter function:
    public void setDesignation(String pDesignation){
        this.designation = pDesignation;
    }
    
    // get medicine's designation function: 
    public String getDesignation(){
        return this.designation;
    }

    // intance of medicine's  Notdesignation:
    private String notDesignation;

    // set medicine's Notdesignation by parameter function:
    public void setNotDesignation(String pNotDesignation){
        this.notDesignation = pNotDesignation;
    }

    // get medicine's Notdesignation function:
    public String getNotDesignation(){
        return this.notDesignation;
    }

    // intance of medicine's dosage:
    private String dosage;

    // set medicine's dosage by parameter
    public void setDosage(String pDosage){
        this.dosage = pDosage;
    }

    // get medicine's dosage function:
    public String getDosage(){
        return this.dosage;
    }

    // intance of medicine expiredString:
    private Date  expiredDate;

    // set medicine expiredDate by parameter function:
    public void setExpiredDate(Date pExpiredDate){
        this.expiredDate = pExpiredDate;
    }

    // get medicine expiredDate function:
    public Date getExpiredDate(){
        return this.expiredDate;
    }

    // intance of medicine user Guide:
    private String userGuide;

    //set userGuide by parameter function:
    public void setUserGuide(String pUserGuide){
        this.userGuide = pUserGuide;
    }

    // get userGuide function:
    public String getUserGuide(){
        return this.userGuide;
    }


    public msbsMedicine(int pMedicineCode,String pMedicineName,int pMedicineTypeCode,int pSupplierCode,int pMeasureCode,double pPrice,int pAvailableAmount,
                    String pRegisterNo,String pOrigin,String pDesignation,String pNotDes,String pDosage,Date pExpired,String pGuide) {
        this.medicineCode = pMeasureCode;
        this.medicineName =pMedicineName;
        this.medicineTypeCode = pMedicineTypeCode;
        this.supplierCode = pSupplierCode;
        this.measureCode = pMeasureCode;
        this.pricePerUnit = pPrice;
        this.availableAmount = pAvailableAmount;
        this.registerNumber = pRegisterNo;
        this.medicineOrigin = pOrigin;
        this.designation = pDesignation;
        this.dosage = pDosage;
        this.expiredDate = pExpired;
        this.userGuide = pGuide;
    }

    public msbsMedicine() {
        this.medicineCode = 0;
        this.medicineName =null;
        this.medicineTypeCode = 0;
        this.supplierCode = 0;
        this.measureCode = 0;
        this.pricePerUnit = 0.0;
        this.availableAmount = 0;
        this.registerNumber = null;
        this.medicineOrigin = null;
        this.designation = null;
        this.dosage = null;
        this.expiredDate =null ;
        this.userGuide = null;
    }

}
