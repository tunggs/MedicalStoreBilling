/*
 * User.java
 *
 * Created on November 3, 2009, 8:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Data;

/**
 *
 * @author hoppd_b00375
 */
public class msbsUser {

    /** Creates a new instance of User */

    // instance of User's Code
    private int userCode;
    // set userCode by parameter
    public void setUserCode(int pUserCode){
        this.userCode = pUserCode;
    }
    // get userCode function
    public int getUserCode(){
        return this.userCode;
    }
    
    // instance of name login
    private String nameLogin;
    // set name Login by parameter
    public void setNameLogin(String pNameLogin){
        this.nameLogin = pNameLogin;
    }
    
    //get nameLogin of user function
    public String getNameLogin(){
        return this.nameLogin;
    }
    
    // instance of name user Password
    private String userPassword;
    // set password by parameter
    public void setPassword(String pPassword){
        this.userPassword = pPassword;
    }
    
    //get password of user function
    public String getPassword(){
        return this.userPassword;
    }

    // instance of User's fullName
    private String fullName;

    // set fullName by parameter
    public void setFullName(String pFullName){
        this.fullName = pFullName;
    }

    // get fullName function:
    public  String getFullName(){
        return this.fullName;
    }

    // instance of user  Type Code
    private int userTypeCode ;
    // set Type Code of User by parameter
    public void setUserTypeCode(int pUserTypeCode){
        this.userTypeCode = pUserTypeCode;
    }                                                                                                                                                                                                                                                                                                                                                                                                  
    
    //get  Type Code of user function
    public int getUserTypeCode(){
        return this.userTypeCode;
    }

    // intance of user's address:
    private String userAddress;

    // set address of user by parameter:

    public void setUserAddress(String pUserAddress){
        this.userAddress = pUserAddress;
    }

    //get address of user function :
    public String getUserAddress(){
        return this.userAddress;
    }

    //intance of userPhone:
    private String userPhone;

    // set Phone of user by parameter:
    public void setUserPhone(String pUserPhone){
        this.userPhone = pUserPhone;
    }

    //get userPhone function

    public String getUserPhone(){
        return this.userPhone;
    }

    // intance of userEmail:
    private String userEmail;

    // set email of user by parameter:
    public void setUserEmail(String pUserEmail){
        this.userEmail = pUserEmail;
    }

    // get userEmail function
    public String getUserEmail(){
        return this.userEmail;
    }

    // instance of userActive:
    private int userActive;

    // set userActive function:
    public void setUserActive(int pUserActive){
        this.userActive = pUserActive;
    }

    // get userActive function:

    public int getUserActive(){
        return this.userActive;
    }


    public msbsUser(int pUserCode,String pNameLogin,String pPassword,String pFullName,int pUserTypeCode,
                    String pUserAddress,String pUserPhone,String pUserEmail,int pUserActive) {

        this.userCode = pUserCode;
        this.nameLogin = pNameLogin;
        this.userPassword = pPassword;
        this.fullName = pFullName;
        this.userTypeCode = pUserTypeCode;
        this.userAddress = pUserAddress;
        this.userPhone = pUserPhone;
        this.userEmail = pUserEmail;
        this.userActive = pUserActive;
    }

    public msbsUser() {
        this.userCode = 0;
        this.nameLogin = null;
        this.userPassword = null;
        this.fullName = null;
        this.userTypeCode = 0;
        this.userAddress = null;
        this.userPhone = null;
        this.userEmail = null;
        this.userActive = 0;
    }
    
    
}
