/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Access.CustomerAccess;
import Data.msbsCustomer;
import Data.msbsOrders;
import GUI.OrderModule;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class CustomerManage {

    private CustomerAccess custAcc;

    public CustomerManage() {
        custAcc = new CustomerAccess();
    }

    public Vector<msbsCustomer> getAllCustomer() {
        Vector<msbsCustomer> allCustomer = new Vector<msbsCustomer>();
        if (custAcc.getAllCusomer() != null) {
            allCustomer = custAcc.getAllCusomer();
            return allCustomer;
        }

        return null;
    }

    public msbsCustomer getCustomerByCode(String code) {
        msbsCustomer customer = new msbsCustomer();

        if (custAcc.getCustomerByCode(code) != null) {
            customer = custAcc.getCustomerByCode(code);
            return customer;
        }

        return null;
    }

    public String getCodeByName(String name) {
        Vector<msbsCustomer> allCust = custAcc.getAllCusomer();
        msbsCustomer customer = new msbsCustomer();
        for (int i = 0; i < allCust.size(); i++) {
            customer = (msbsCustomer) allCust.get(i);
            if (customer.getCustomerName().equals(name)) {
                return customer.getCustomerCode();
            }
        }
        return null;
    }

    public boolean insertCustomer(String CustCode, String customerName, String customerType, String customerPhone, String customerFax, String customerEmail, String customerAddress, String customerRelationship) {
        if (custAcc.insertCustomers(CustCode, customerName, customerType, customerPhone, customerFax, customerEmail, customerAddress, customerRelationship)) {
            return true;
        }

        return false;
    }

    public boolean updateCustomer(String code, String customerName, String customerType, String customerPhone, String customerFax, String customerEmail, String customerAddress, String customerRelationship) {
        if (custAcc.updateCustomer(code, customerName, customerType, customerPhone, customerFax, customerEmail, customerAddress, customerRelationship)) {
            return true;
        }

        return false;
    }

    public boolean deleteCustomer(String code) {
        Vector<msbsOrders> allOrder = new Vector<msbsOrders>();
        OrderManage orderMn = new OrderManage();
        allOrder = orderMn.getAllOrder();
        for (int i = 0; i < allOrder.size(); i++) {
            msbsOrders temp = new msbsOrders();
            temp = (msbsOrders) allOrder.get(i);
            if (temp.getCustomerCode().equals(code)) {
                return false;
            }
        }
        if (custAcc.deleteCustomer(code)) {
            return true;
        }


        return false;
    }
}
