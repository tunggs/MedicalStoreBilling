/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Data.msbsOrderDetails;
import Data.msbsOrders;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class OrderDetailsAccess {

    private ConnectDB con = null;
    private ResultSet rs = null;

    public OrderDetailsAccess() {
        try {
            con = new ConnectDB();
            con.open();
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public Vector<msbsOrderDetails> getAllOrderDetailsByOrderCode(String code) {
        Vector<msbsOrderDetails> allOrderDtails = new Vector<msbsOrderDetails>();
        try {
            con.open();
            String sql = "spGetAllOrderDetailsByOrderCode";
            Vector paramList = new Vector();
            paramList.add(code);
            ResultSet rs = con.executeQuery(sql, paramList);
            while (rs.next()) {
                msbsOrderDetails newOrderDetails = new msbsOrderDetails();
                newOrderDetails.setOrderCode(rs.getString(1));
                newOrderDetails.setMedicineCode(rs.getInt(2));
                newOrderDetails.setPrice(rs.getFloat(3));
                newOrderDetails.setQuantity(rs.getInt(4));

                allOrderDtails.add(newOrderDetails);

            }
            con.close();
            return allOrderDtails;
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
    public Vector<msbsOrderDetails> getTopMedicine() {
        Vector<msbsOrderDetails> top10Medicine = new Vector<msbsOrderDetails>();
        try {
            con.open();
            String sql = "spGetTopMedicine";
            ResultSet rs = con.executeQuery(sql);
            
            while (rs.next()) {
                msbsOrderDetails od = new msbsOrderDetails();
                od.setMedicineCode(rs.getInt(1));
                od.setQuantity(rs.getInt(2));
                top10Medicine.add(od);

            }
            con.close();
            return top10Medicine;
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

    public boolean insertOrderDetails(String orderCode, int medicineCode, double cost, int quantity) {
        try {
            con.open();
            Vector insertOrderDetailsListPara = new Vector();

            insertOrderDetailsListPara.addElement(orderCode);
            insertOrderDetailsListPara.addElement(medicineCode);
            insertOrderDetailsListPara.addElement(cost);
            insertOrderDetailsListPara.addElement(quantity);

            con.executeUpdate("spInsertOrderDetails", insertOrderDetailsListPara);
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

    public boolean updateQuantity(String orderCode, int medicineCode, double cost, int quantity) {
        try {
            con.open();
            Vector updateMedicineQuantityListPara = new Vector();

            updateMedicineQuantityListPara.addElement(orderCode);
            updateMedicineQuantityListPara.addElement(medicineCode);
            updateMedicineQuantityListPara.addElement(cost);
            updateMedicineQuantityListPara.addElement(quantity);

            con.executeUpdate("spUpdateMedicineQuantity", updateMedicineQuantityListPara);
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

    public boolean deleteOrderDetailByOrderCode(String OrderCode) {
        try {
            con.open();
            Vector deleteOrderListPara = new Vector();

            deleteOrderListPara.addElement(OrderCode);
            con.executeUpdate("spDeleteOrderDetails", deleteOrderListPara);
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteMedicine(String OrderCode, int medicineCode) {
        try {
            con.open();
            Vector deleteMedicineOrderDetailsListPara = new Vector();

            deleteMedicineOrderDetailsListPara.addElement(OrderCode);
            deleteMedicineOrderDetailsListPara.addElement(medicineCode);
            con.executeUpdate("spDeleteMedicineOrderDetails", deleteMedicineOrderDetailsListPara);
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
