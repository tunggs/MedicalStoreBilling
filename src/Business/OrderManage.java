/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Access.OrderAccess;
import Data.msbsOrders;
import java.sql.Date;
import java.util.Vector;
/**
 *
 * @author V-sign
 */
public class OrderManage {
    private OrderAccess ordAcc;

    public OrderManage(){
        ordAcc = new OrderAccess();
    }

    public Vector<msbsOrders> getAllOrder(){
        Vector<msbsOrders> allOrd = new Vector<msbsOrders>();

        if(ordAcc.getAllOrders() != null){
            allOrd = ordAcc.getAllOrders();

            return allOrd;
        }

        return null;
    }

    public msbsOrders getOrdersByOrderCode(String orderCode){
        msbsOrders ord = new msbsOrders();
        if(ordAcc.searchOrdersByOrderCode(orderCode) != null){
            ord = ordAcc.searchOrdersByOrderCode(orderCode);
            return ord;
        }
        return null;
    }


    public Vector<msbsOrders> getOrdersByStatus(int status){
        Vector<msbsOrders> allOrd = new Vector<msbsOrders>();
        if(ordAcc.searchOrdersByStatus(status) != null){
            allOrd = ordAcc.searchOrdersByStatus(status);
            return allOrd;
        }
        return null;

    }

    public Vector<msbsOrders> getOrdersByTotal(float total){
        Vector<msbsOrders> allOrd = new Vector<msbsOrders>();
        Vector<msbsOrders> allOrdByTotal = new Vector<msbsOrders>();
        msbsOrders ord = new msbsOrders();
        for(int i = 0; i < allOrd.size(); i++){
            ord = (msbsOrders)allOrd.get(i);
            if(ord.getTotal() == total){
                allOrdByTotal.addElement(ord);
            }
        }
        if(allOrdByTotal.size() != 0){
            return allOrdByTotal;
        }
        return null;

    }

    public Vector<msbsOrders> getOrdersByDateOrder(Date dateOrder){
        Vector<msbsOrders> allOrd = new Vector<msbsOrders>();
        Vector<msbsOrders> allOrdByDateOrder = new Vector<msbsOrders>();
        msbsOrders ord = new msbsOrders();
        Date ordDate = ord.getOrderDate();
        for(int i = 0; i < allOrd.size(); i++){
            ord = (msbsOrders)allOrd.get(i);
            if(dateOrder.equals(ordDate)){
                allOrdByDateOrder.addElement(ord);
            }
        }
        if(allOrdByDateOrder.size() != 0){
            return allOrdByDateOrder;
        }
        return null;

    }

    public Vector<msbsOrders> getOrdersByExpiredTime(Date expiredDate){
        Vector<msbsOrders> allOrd = new Vector<msbsOrders>();
        Vector<msbsOrders> allOrdByExpiredDate = new Vector<msbsOrders>();
        msbsOrders ord = new msbsOrders();
        Date ordDate = ord.getOrderDate();
        for(int i = 0; i < allOrd.size(); i++){
            ord = (msbsOrders)allOrd.get(i);
            if(expiredDate.equals(ordDate)){
                allOrdByExpiredDate.addElement(ord);
            }
        }
        if(allOrdByExpiredDate.size() != 0){
            return allOrdByExpiredDate;
        }
        return null;

    }

    public boolean insertOrder(String orderCode,String CustomerCode,int UserCode,int Status,double Total,double paidMoney,int Tax,int Discount, Date OrderDate,
                                String Address,Date ExpiredTime){
        if(ordAcc.insertOrder(orderCode,CustomerCode,UserCode,Status,Total,paidMoney,Tax,Discount,OrderDate,Address,ExpiredTime)){
            return true;
        }

        return false;
    }

    public boolean updateOrderStatus(String OrderCode){
        if(ordAcc.updateOrderStatus(OrderCode)){
            return true;
        }

        return false;
    }

        public boolean updateOrderPaidMoney(String OrderCode,double PaidMoney){
        if(ordAcc.updateOrderPaidMoney(OrderCode,PaidMoney)){
            return true;
        }

        return false;
    }

    public boolean updateOrderComplete(String OrderCode,double PaidMoney,Date ExpiredDate){
        if(ordAcc.updateOrderComplete(OrderCode,PaidMoney,ExpiredDate)){
            return true;
        }

        return false;
    }

    public boolean updateOrder(String OrderCode,double Total,double paidMoney,Date ExpiredTime){
        if(ordAcc.updateOrder(OrderCode,Total,paidMoney,ExpiredTime)){
            return true;
        }

        return false;
    }
    
    public boolean deleteOrder(String code){
        if(ordAcc.deleteOrder(code)){
            return true;
        }

        return false;
    }
}

