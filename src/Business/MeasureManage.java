/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;
import Access.MeasureAccess;
import Data.msbsMeasure;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class MeasureManage {
    private MeasureAccess measureAcc;

    public MeasureManage(){
        measureAcc = new MeasureAccess();
    }

    public Vector<msbsMeasure> getAllMeasures(){
        Vector<msbsMeasure> allMeasure = new Vector<msbsMeasure>();
        if(measureAcc.getAllMeasure() != null){
            allMeasure = measureAcc.getAllMeasure();
            return allMeasure;
        }

        return null;
    }

    public int getCodeByName(String name){
        Vector<msbsMeasure> allMeasure = measureAcc.getAllMeasure();
        msbsMeasure measure = new msbsMeasure();
        for(int i = 0; i < allMeasure.size(); i++){
            measure = (msbsMeasure)allMeasure.get(i);
            if(measure.getMeasureName().equals(name)){
                return measure.getMeasureCode();
            }
        }

        return -1;
    }

    public msbsMeasure getMeasureByCode(int code){
        msbsMeasure measure = new msbsMeasure();
        if(measureAcc.getMeasureByCode(code) != null){
            measure = measureAcc.getMeasureByCode(code);

            return measure;
        }
        return null;
    }

    public boolean insertMeasure(String name){
        if(measureAcc.insertMeasure(name)){
            return true;
        }

        return false;
    }

    public boolean updateMeasure(int code,String name){
        if(measureAcc.updateMeasure(code,name)){
            return true;
        }

        return false;
    }

    public boolean deleteMeasure(int code){
        if(measureAcc.deleteMeasure(code)){
            return true;
        }

        return false;
    }
}
