/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Access.UserAccess;
import Data.msbsUser;
import java.sql.SQLException;
import java.util.Vector;
/**
 *
 * @author Administrator
 */
public class UserManage {

    private UserAccess userAcc = null;

    public UserManage(){
        userAcc = new UserAccess();
    }

    public msbsUser isLogin(String UserName, String Password) throws SQLException{
        Vector<msbsUser> allUser = new Vector<msbsUser>();
        allUser = userAcc.getAllUser();
        msbsUser user = new msbsUser();
        
        for(int i = 0; i < allUser.size(); i++){
            user = (msbsUser)allUser.get(i);

           if (user.getNameLogin().equals(UserName) && user.getPassword().equals(Password)){
                if(userAcc.getUserTypeName(user.getUserTypeCode()) != null){
                    return user;
                }else{
                    return null;
                }
           }
        }


        return null;
    }
    public Vector<msbsUser> getAllUser(){
        Vector<msbsUser> allUser = new Vector<msbsUser>();
        if(userAcc.getAllUser() != null){
            allUser = userAcc.getAllUser();
            return allUser;
        }
        return null;
    }
    public msbsUser getUserByCode(int code){
        msbsUser user = new msbsUser();
        if(userAcc.getUserByCode(code) != null){
            user = userAcc.getUserByCode(code);
            return user;
        }
        return null;
    }
    public String getUserTypeName(int typeCode){
        String userTypeName = null;
        if(userAcc.getUserTypeName(typeCode) != null){
            userTypeName = userAcc.getUserTypeName(typeCode);
            return userTypeName;
        }
        return null;
    }
    public int getCodeByName(String name){
        Vector<msbsUser> allUser = userAcc.getAllUser();
        msbsUser user = new msbsUser();
        for(int i = 0; i < allUser.size(); i++){
            user = (msbsUser)allUser.get(i);
            if(user.getNameLogin().equals(name)){
                return user.getUserCode();
            }
        }
        return -1;
    }
    public boolean changePass(int userCode,String pass){
        if(userAcc.changePass(userCode, pass)){
            return true;
        }
        return false;
    }

    public boolean insertUser(String userName,String userPassword,String fullName,int userTypeCode,String userAddress,String userPhone,String userEmail,int userActive){

        if(userAcc.insertUser(userName,userPassword, fullName, userTypeCode, userAddress, userPhone, userEmail,userActive)){
            return true;
        }
        return false;
    }

    public boolean updateUser(int userCode,String fullName,String userAddress,String userPhone,String userEmail,int active){
        if(userAcc.updateUser(userCode, fullName, userAddress, userPhone, userEmail,active)){
            return true;
        }
        return false;
    }

    public boolean deleteUser(int userCode){
        if(userAcc.deleteUser(userCode)){
            return true;
        }
        return false;
    }

}
