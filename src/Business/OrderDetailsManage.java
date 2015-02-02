/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Access.OrderDetailsAccess;
import Data.msbsOrderDetails;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class OrderDetailsManage {
    private OrderDetailsAccess orderDetailAcc;
    public OrderDetailsManage(){
        orderDetailAcc = new OrderDetailsAccess();

    }

    public Vector<msbsOrderDetails> getAllOrderDetailsByOrderCode(String code){
        Vector<msbsOrderDetails> allOrderDetails = new Vector<msbsOrderDetails>();

        if(orderDetailAcc.getAllOrderDetailsByOrderCode(code) != null){
            allOrderDetails = orderDetailAcc.getAllOrderDetailsByOrderCode(code);
            return allOrderDetails;
        }

        return null;
    }
    public Vector<msbsOrderDetails> getTopMedicine(){
        Vector<msbsOrderDetails> allOrderDetails = new Vector<msbsOrderDetails>();

        if(orderDetailAcc.getTopMedicine() != null){
            allOrderDetails = orderDetailAcc.getTopMedicine();
            return allOrderDetails;
        }

        return null;
    }

    public boolean insertOrderDetails(String orderCode,int medicineCode,double cost,int quantity){
        if(orderDetailAcc.insertOrderDetails(orderCode, medicineCode,cost, quantity)){
            return true;
        }

        return false;
    }

    public boolean deleteMedicine(String OrderCode,int medicineCode){
        if(orderDetailAcc.deleteMedicine(OrderCode, medicineCode)){
            return true;
        }
        return false;
    }
    public boolean updateQuantity(String orderCode,int medicineCode,double cost,int quantity){
        if(orderDetailAcc.updateQuantity(orderCode, medicineCode, cost, quantity)){
            return true;
        }

        return false;
    }

     public boolean deleteOrderDetailByOrderCode(String OrderCode){
        if(orderDetailAcc.deleteOrderDetailByOrderCode(OrderCode)){
            return true;
        }
        return false;
    }
}
