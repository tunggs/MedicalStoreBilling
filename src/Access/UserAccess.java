/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Data.msbsTopCustomer;
import Data.msbsUser;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class UserAccess {

    private ConnectDB con = null;
    private ResultSet rs = null;

    public UserAccess() {
        try {
            con = new ConnectDB();
            con.open();
        } catch (Exception ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public msbsUser getUserByCode(int code) {
        msbsUser user = null;
        Vector getUserListPara = new Vector();
        getUserListPara.add(code);
        try {
            con.open();
            String sql = "spGetUserByCode";
            ResultSet rs = con.executeQuery(sql, getUserListPara);
            while (rs.next()) {
                user = new msbsUser();
                user.setUserCode(rs.getInt(1));
                user.setNameLogin(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setFullName(rs.getString(4));
                user.setUserTypeCode(rs.getInt(5));
                user.setUserAddress(rs.getString(6));
                user.setUserPhone(rs.getString(7));
                user.setUserEmail(rs.getString(8));
                user.setUserActive(rs.getInt(9));

                return user;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    public Vector<msbsUser> getAllUser() {
        Vector<msbsUser> users = new Vector<msbsUser>();

        try {
            con.open();
            String sql = "spGetAllUser";
            ResultSet rs = con.executeQuery(sql);
            while (rs.next()) {
                msbsUser objUser = new msbsUser();
                objUser.setUserCode(rs.getInt(1));
                objUser.setNameLogin(rs.getString(2));
                objUser.setPassword(rs.getString(3));
                objUser.setFullName(rs.getString(4));
                objUser.setUserTypeCode(rs.getInt(5));
                objUser.setUserAddress(rs.getString(6));
                objUser.setUserPhone(rs.getString(7));
                objUser.setUserEmail(rs.getString(8));
                objUser.setUserActive(rs.getInt(9));

                users.addElement(objUser);
            }
            con.close();
            return users;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }


    }
    
    public String getUserTypeName(int userTypeCode) {
        String typeName = null;
        try {
            con.open();
            String sql = "spGetAllUserType";
            ResultSet rs = con.executeQuery(sql);
            while (rs.next()) {
                if (rs.getInt(1) == userTypeCode) {
                    typeName = rs.getString(2);
                    return typeName;
                }

            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean changePass(int userCode, String pass) {
        try {
            con.open();
            Vector chgPassListPara = new Vector();
            chgPassListPara.addElement(userCode);
            chgPassListPara.addElement(pass);
            con.executeUpdate("spChangePassword", chgPassListPara);
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

    public boolean updateUser(int userCode, String fullName, String userAddress, String userPhone, String userEmail, int userActive) {
        try {
            con.open();
            Vector updateParaList = new Vector();

            updateParaList.addElement(userCode);
            updateParaList.addElement(fullName);
            updateParaList.addElement(userAddress);
            updateParaList.addElement(userPhone);
            updateParaList.addElement(userEmail);
            updateParaList.addElement(userActive);
            con.executeUpdate("spUpdateUser", updateParaList);
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

    public boolean insertUser(String userName, String userPassword, String fullName, int userTypeCode, String userAddress, String userPhone, String userEmail, int userActive) {
        try {
            con.open();
            Vector insertParaList = new Vector();

            insertParaList.addElement(userName);
            insertParaList.addElement(userPassword);
            insertParaList.addElement(fullName);
            insertParaList.addElement(userTypeCode);
            insertParaList.addElement(userAddress);
            insertParaList.addElement(userPhone);
            insertParaList.addElement(userEmail);
            insertParaList.addElement(userActive);

            con.executeUpdate("spInsertUser", insertParaList);
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

    public boolean deleteUser(int userCode) {
        try {
            con.open();
            Vector deleteParaList = new Vector();

            deleteParaList.addElement(userCode);
            con.executeUpdate("spDeleteUser", deleteParaList);
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
