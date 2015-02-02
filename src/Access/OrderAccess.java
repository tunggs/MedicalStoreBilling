/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Data.msbsOrders;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author V-sign
 */
public class OrderAccess {

    private ConnectDB con = null;
    private ResultSet rs = null;

    public OrderAccess() {
        try {
            con = new ConnectDB();
            con.open();
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public Vector<msbsOrders> getAllOrders() {
        Vector<msbsOrders> allOrders = new Vector<msbsOrders>();
        try {
            con.open();
            String sql = "spGetAllOrders";
            ResultSet rs = con.executeQuery(sql);


            while (rs.next()) {
                msbsOrders newOrder = new msbsOrders();

                newOrder.setOrderCode(rs.getString(1));
                newOrder.setCustomerCode(rs.getString(2));
                newOrder.setUserCode(rs.getInt(3));
                newOrder.setStatus(rs.getInt(4));
                newOrder.setTotal(rs.getFloat(5));
                newOrder.setPaidMoney(rs.getFloat(6));
                newOrder.setTax(rs.getInt(7));
                newOrder.setDiscount(rs.getInt(8));
                newOrder.setOrderDate(rs.getDate(9));
                newOrder.setAddressToDeliver(rs.getString(10));
                newOrder.setExpiredTime(rs.getDate(11));

                allOrders.add(newOrder);

            }
            con.close();
            return allOrders;
        } catch (SQLException ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }

    }

    public msbsOrders searchOrdersByOrderCode(String orderCode) {
        msbsOrders newOrder = new msbsOrders();
        Vector getOrderListPara = new Vector();
        getOrderListPara.add(orderCode);
        try {
            con.open();
            String sql = "spSearchOrdersByOrderCode";
            ResultSet rs = con.executeQuery(sql, getOrderListPara);
            while (rs.next()) {
                newOrder = new msbsOrders();
                newOrder.setOrderCode(rs.getString(1));
                newOrder.setCustomerCode(rs.getString(2));
                newOrder.setUserCode(rs.getInt(3));
                newOrder.setStatus(rs.getInt(4));
                newOrder.setTotal(rs.getFloat(5));
                newOrder.setPaidMoney(rs.getFloat(6));
                newOrder.setTax(rs.getInt(7));
                newOrder.setDiscount(rs.getInt(8));
                newOrder.setOrderDate(rs.getDate(9));
                newOrder.setAddressToDeliver(rs.getString(10));
                newOrder.setExpiredTime(rs.getDate(11));
                return newOrder;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            Logger.getLogger(MedicineAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;

        }
        return null;
    }

    public msbsOrders searchOrdersByCustomerCode(int code) {
        msbsOrders newOrder = null;
        Vector getOrderListPara = new Vector();
        getOrderListPara.add(code);
        try {
            con.open();
            String sql = "searchOrdersByCustomerCode";
            ResultSet rs = con.executeQuery(sql, getOrderListPara);
            while (rs.next()) {
                newOrder = new msbsOrders();
                newOrder.setOrderCode(rs.getString(1));
                newOrder.setCustomerCode(rs.getString(2));
                newOrder.setUserCode(rs.getInt(3));
                newOrder.setStatus(rs.getInt(4));
                newOrder.setTotal(rs.getFloat(5));
                newOrder.setPaidMoney(rs.getFloat(6));
                newOrder.setTax(rs.getInt(7));
                newOrder.setDiscount(rs.getInt(8));
                newOrder.setOrderDate(rs.getDate(9));
                newOrder.setAddressToDeliver(rs.getString(10));
                newOrder.setExpiredTime(rs.getDate(11));
                return newOrder;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            Logger.getLogger(MedicineAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    public Vector<msbsOrders> searchOrdersByStatus(int status) {
        Vector<msbsOrders> allOrderByStatus = new Vector<msbsOrders>();
        Vector getOrderListPara = new Vector();
        getOrderListPara.add(status);
        try {
            con.open();
            String sql = "spSearchOrdersByStatus";
            ResultSet rs = con.executeQuery(sql, getOrderListPara);
            while (rs.next()) {
                msbsOrders newOrder = new msbsOrders();
                newOrder.setOrderCode(rs.getString(1));
                newOrder.setCustomerCode(rs.getString(2));
                newOrder.setUserCode(rs.getInt(3));
                newOrder.setStatus(rs.getInt(4));
                newOrder.setTotal(rs.getFloat(5));
                newOrder.setPaidMoney(rs.getFloat(6));
                newOrder.setTax(rs.getInt(7));
                newOrder.setDiscount(rs.getInt(8));
                newOrder.setOrderDate(rs.getDate(9));
                newOrder.setAddressToDeliver(rs.getString(10));
                newOrder.setExpiredTime(rs.getDate(11));
                allOrderByStatus.add(newOrder);
            }
            con.close();
            return allOrderByStatus;
        } catch (SQLException ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            Logger.getLogger(MedicineAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
    }

    public msbsOrders searchOrdersByDateOrder(Date dateFrom, Date dateTo) {
        msbsOrders newOrder = null;
        Vector getOrderListPara = new Vector();
        getOrderListPara.addElement(dateFrom);
        getOrderListPara.addElement(dateTo);
        try {
            con.open();
            String sql = "searchOrdersByDateOrder";
            ResultSet rs = con.executeQuery(sql, getOrderListPara);
            while (rs.next()) {
                newOrder = new msbsOrders();
                newOrder.setOrderCode(rs.getString(1));
                newOrder.setCustomerCode(rs.getString(2));
                newOrder.setUserCode(rs.getInt(3));
                newOrder.setStatus(rs.getInt(4));
                newOrder.setTotal(rs.getFloat(5));
                newOrder.setTax(rs.getInt(6));
                newOrder.setDiscount(rs.getInt(7));
                newOrder.setOrderDate(rs.getDate(8));
                newOrder.setAddressToDeliver(rs.getString(9));
                newOrder.setExpiredTime(rs.getDate(10));
                return newOrder;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            Logger.getLogger(MedicineAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    public msbsOrders searchOrdersByExpiredTime(Date expiredTime) {
        msbsOrders newOrder = null;
        Vector getOrderListPara = new Vector();
        getOrderListPara.add(expiredTime);
        try {
            con.open();
            String sql = "searchOrdersByExpiredTime";
            ResultSet rs = con.executeQuery(sql, getOrderListPara);
            while (rs.next()) {
                newOrder = new msbsOrders();
                newOrder.setOrderCode(rs.getString(1));
                newOrder.setCustomerCode(rs.getString(2));
                newOrder.setUserCode(rs.getInt(3));
                newOrder.setStatus(rs.getInt(4));
                newOrder.setTotal(rs.getFloat(5));
                newOrder.setTax(rs.getInt(6));
                newOrder.setDiscount(rs.getInt(7));
                newOrder.setOrderDate(rs.getDate(8));
                newOrder.setAddressToDeliver(rs.getString(9));
                newOrder.setExpiredTime(rs.getDate(10));
                return newOrder;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            Logger.getLogger(MedicineAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean insertOrder(String orderCode, String CustomerCode, int UserCode, int Status, double Total, double paidMoney, int Tax, int Discount, Date OrderDate,
            String Address, Date ExpiredTime) {
        try {
            con.open();
            Vector insertOrderListPara = new Vector();
            insertOrderListPara.addElement(orderCode);
            insertOrderListPara.addElement(CustomerCode);
            insertOrderListPara.addElement(UserCode);
            insertOrderListPara.addElement(Status);
            insertOrderListPara.addElement(Total);
            insertOrderListPara.addElement(paidMoney);
            insertOrderListPara.addElement(Tax);
            insertOrderListPara.addElement(Discount);
            insertOrderListPara.addElement(OrderDate);
            insertOrderListPara.addElement(Address);
            insertOrderListPara.addElement(ExpiredTime);

            con.executeUpdate("spInsertOrders", insertOrderListPara);
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateOrderStatus(String OrderCode) {
        try {
            con.open();
            Vector updateOrderListPara = new Vector();
            updateOrderListPara.addElement(OrderCode);
            con.executeUpdate("spUpdateOrderStatus", updateOrderListPara);
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateOrderPaidMoney(String OrderCode, double PaidMoney) {
        try {
            con.open();
            Vector updateOrderListPara = new Vector();

            updateOrderListPara.addElement(OrderCode);
            updateOrderListPara.addElement(PaidMoney);
            con.executeUpdate("spUpdateOrderPaidMoney", updateOrderListPara);
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }

    }

    public boolean updateOrderComplete(String OrderCode, double PaidMoney, Date ExpiredDate) {
        try {
            con.open();
            Vector updateOrderListPara = new Vector();
            updateOrderListPara.addElement(OrderCode);
            updateOrderListPara.addElement(PaidMoney);
            updateOrderListPara.addElement(ExpiredDate);
            con.executeUpdate("spUpdateOrderComplete", updateOrderListPara);
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }

    }

    public boolean updateOrder(String OrderCode, double Total, double paidMoney, Date ExpiredTime) {
        try {
            con.open();
            Vector updateOrderListPara = new Vector();

            updateOrderListPara.addElement(OrderCode);
            updateOrderListPara.addElement(Total);
            updateOrderListPara.addElement(paidMoney);
            updateOrderListPara.addElement(ExpiredTime);
            con.executeUpdate("spUpdateOrders", updateOrderListPara);
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteOrder(String OrderCode) {
        try {
            con.open();
            Vector deleteOrderListPara = new Vector();

            deleteOrderListPara.addElement(OrderCode);
            con.executeUpdate("spDeleteOrderDetails", deleteOrderListPara);
            con.executeUpdate("spDeleteOrders", deleteOrderListPara);
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }

    }
}
