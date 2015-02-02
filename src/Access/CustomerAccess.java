/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Data.msbsCustomer;
import Data.msbsTopCustomer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class CustomerAccess {

    private ConnectDB con = null;
    private ResultSet rs = null;

    public CustomerAccess() {
        try {
            con = new ConnectDB();
            con.open();
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public Vector<msbsCustomer> getAllCusomer() {
        Vector<msbsCustomer> allCustomer = new Vector<msbsCustomer>();
        try {
            con.open();
            String sql = "spGetAllCustomer";
            ResultSet rs = con.executeQuery(sql);

            while (rs.next()) {
                msbsCustomer newCuts = new msbsCustomer();
                newCuts.setCustomerCode(rs.getString(1));
                newCuts.setCustomerName(rs.getString(2));
                newCuts.setCustomerType(rs.getString(3));
                newCuts.setCustomerPhone(rs.getString(4));
                newCuts.setCustomerFax(rs.getString(5));
                newCuts.setCustomerEmail(rs.getString(6));
                newCuts.setCustomerAddress(rs.getString(7));
                newCuts.setCustomerPriority(rs.getString(8));
                allCustomer.add(newCuts);

            }
            con.close();
            return allCustomer;
        } catch (SQLException ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (Exception ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    public Vector<msbsTopCustomer> getTopCustomer() {
        Vector<msbsTopCustomer> topCustomer = new Vector<msbsTopCustomer>();
        try {
            con.open();
            String sql = "spGetTopCustomer";
            ResultSet rs = con.executeQuery(sql);

            while (rs.next()) {
                msbsTopCustomer topPer = new msbsTopCustomer();
                topPer.setCode(rs.getString(1));
                topPer.setQuantity(rs.getInt(2));
                topCustomer.add(topPer);

            }
            con.close();
            return topCustomer;
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
    public msbsCustomer getCustomerByCode(String code) {
        msbsCustomer newCuts = null;
        Vector getUserListPara = new Vector();
        getUserListPara.add(code);
        try {
            con.open();
            String sql = "spSearchCustomerByCode";
            ResultSet rs = con.executeQuery(sql, getUserListPara);
            while (rs.next()) {
                newCuts = new msbsCustomer();
                newCuts.setCustomerCode(rs.getString(1));
                newCuts.setCustomerName(rs.getString(2));
                newCuts.setCustomerType(rs.getString(3));
                newCuts.setCustomerPhone(rs.getString(4));
                newCuts.setCustomerFax(rs.getString(5));
                newCuts.setCustomerEmail(rs.getString(6));
                newCuts.setCustomerAddress(rs.getString(7));
                newCuts.setCustomerPriority(rs.getString(8));

                return newCuts;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierAccess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (Exception ex) {
            Logger.getLogger(UserAccess.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean insertCustomers(String CustCode, String customerName, String customerType, String customerPhone, String customerFax, String customerEmail, String customerAddress, String customerRelationship) {
        try {
            con.open();
            Vector insertParaList = new Vector();
            insertParaList.addElement(CustCode);
            insertParaList.addElement(customerName);
            insertParaList.addElement(customerType);
            insertParaList.addElement(customerPhone);
            insertParaList.addElement(customerFax);
            insertParaList.addElement(customerEmail);
            insertParaList.addElement(customerAddress);
            insertParaList.addElement(customerRelationship);

            con.executeUpdate("spInsertCustomer", insertParaList);
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean updateCustomer(String code, String customerName, String customerType, String customerPhone, String customerFax, String customerEmail, String customerAddress, String customerRelationship) {
        try {
            con.open();
            Vector updateParaList = new Vector();
            updateParaList.addElement(code);
            updateParaList.addElement(customerName);
            updateParaList.addElement(customerType);
            updateParaList.addElement(customerPhone);
            updateParaList.addElement(customerFax);
            updateParaList.addElement(customerEmail);
            updateParaList.addElement(customerAddress);
            updateParaList.addElement(customerRelationship);
            con.executeUpdate("spUpdateCustomer", updateParaList);
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean deleteCustomer(String customerCode) {
        try {
            con.open();
            Vector deleteParaList = new Vector();

            deleteParaList.addElement(customerCode);
            con.executeUpdate("spDeleteCustomer", deleteParaList);
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
}
