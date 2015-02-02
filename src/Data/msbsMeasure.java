/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Data;

/**
 *
 * @author Administrator
 */
public class msbsMeasure {

    // intance of Measure for medicine:
    private int measureCode;

    // set measureCode:
    public void setMeasureCode(int pCode){
        this.measureCode = pCode;
    }

    // get measureCode:
    public int getMeasureCode(){
        return this.measureCode;
    }

    private String measureName;

    //set measure's name:
    public void setMeasureName(String pName){
        this.measureName = pName;
    }

    // get measureName:
    public String getMeasureName(){
        return this.measureName;
    }


    public msbsMeasure(int pCode,String pName){
        this.measureCode = pCode;
        this.measureName = pName;
    }

    public msbsMeasure(){
        this.measureCode = 0;
        this.measureName = null;
    }
}
