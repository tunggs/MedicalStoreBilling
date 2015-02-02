/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddUserForm.java
 *
 * Created on Nov 8, 2009, 3:44:47 PM
 */

package GUI;

import Business.UserManage;
import Data.msbsUser;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class AddUserForm extends javax.swing.JDialog {

    /** Creates new form AddUserForm */
    public AddUserForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        model =  new DefaultComboBoxModel(new String[] { "Select....", "Manager", "Seller", "Accountant" });
        cbxUserType.setModel(model);
        userMn = new UserManage();
    }
public boolean checkUser(){
        char[] pass = txtPassWord.getPassword();
        String pass1 = String.valueOf(pass);

        String txtEmailp = txtEmail.getText().trim();
        String txtNamep = txtFullName.getText().trim();
        String txtPhonep = txtPhone.getText().trim();

        Pattern emailcheck = Pattern.compile(".+@.+\\.[a-z]+");
        Pattern namecheck = Pattern.compile("^[a-zA-Z\\s]+");
        Pattern phonecheck = Pattern.compile("[0-9]{9,11}");

        Matcher m = emailcheck.matcher(txtEmailp);
        Matcher n = namecheck.matcher(txtNamep);
        Matcher p = phonecheck.matcher(txtPhonep);

        boolean email = m.matches();
        boolean name = n.matches();
        boolean phone = p.matches();

        if(txtUserLogin.getText().trim().equals("") || txtUserLogin.getText().trim().equals(null)){
            JOptionPane.showMessageDialog(this, "UserName is not null !!!");
            txtUserLogin.requestFocus();
            return false;
        } else if(pass1.equals(null) || pass1.equals("")){
            JOptionPane.showMessageDialog(this, "PassWord is not null !!!");
            txtUserLogin.requestFocus();
            return false;
        } else if(txtFullName.getText().trim().equals("") || txtFullName.getText().trim().equals(null)){
            JOptionPane.showMessageDialog(this, "FullName is not null !!!");
            txtFullName.requestFocus();
            return false;
        } else if(name == false){
            JOptionPane.showMessageDialog(this, "FullName is not valid !!!"+"\n"+" Name can't contained number!","WRANING",JOptionPane.WARNING_MESSAGE);
            txtFullName.requestFocus();
            return false;
        } else if(cbxUserType.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(this, "Must select is UserType !!!");
            cbxUserType.requestFocus();
            return false;
        } else if(txtPhone.getText().trim().equals("") || txtPhone.getText().trim().equals(null)){
            JOptionPane.showMessageDialog(this, "Phone is not null !!! ");
            txtPhone.requestFocus();
            return false;
        } else if(phone == false){
            JOptionPane.showMessageDialog(this, "Phone is not valid !!! "+"\n"+"Phone can't contained text!"+"\n"+"Example : xxxxxxxxx or xxxxxxxxxx","WRANING",JOptionPane.WARNING_MESSAGE);
            txtPhone.requestFocus();
            return false;
        } else if(txtEmail.getText().trim().equals("") || txtEmail.getText().trim().equals(null)){
            
        }else if(email == false){
            JOptionPane.showMessageDialog(this, "Email is not valid !!!"+"\n"+"Example : emxample@email.com","WRANING",JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return false;
        } else if(txtAddress.getText().trim().equals("") || txtAddress.getText().trim().equals(null)){
            JOptionPane.showMessageDialog(this, "Address is not null !!!");
            txtAddress.requestFocus();
            return false;
        }
        return true;
    }
    public void add(){
        String nameLogin = null;
        String password = null;
        String fullName = null;
        String userTypeName = null;
        int userTypeCode = 0 ;
        String userAddress = null;
        String userPhone = null;
        String userEmail = null;
        int userActive = 0;
        boolean flag = false;

        nameLogin = txtUserLogin.getText().trim();
        password = String.valueOf(txtPassWord.getPassword()).trim();
        fullName = txtFullName.getText().trim();
        if(model.getSelectedItem().toString().equals("Manager")){
            userTypeCode = 1;
        }else if(model.getSelectedItem().toString().equals("Seller")){
            userTypeCode = 2;
        }else if(model.getSelectedItem().toString().equals("Accountant")){
            userTypeCode = 3;
        }
        userAddress = txtAddress.getText().trim();
        userPhone = txtPhone.getText().trim();
        userEmail = txtEmail.getText().trim();
        if(rbtnActive_YES.isSelected()){
            userActive = 1;
        }else if(rbtnActive_YES.isSelected()){
            userActive  = 0;
        }
        Vector<msbsUser> allUser = new Vector<msbsUser>();
        allUser = userMn.getAllUser();
        for(int i=0;i<allUser.size();i++){
            msbsUser temp = new msbsUser();
            temp = (msbsUser)allUser.get(i);
            if(temp.getNameLogin().equals(nameLogin)){
                JOptionPane.showMessageDialog(this, "This username allready exist", "User name allready exist! ", JOptionPane.ERROR_MESSAGE);
                flag = false;
                break;
            }else{
                flag = true;
            }
        }
        if(checkUser()){
             if(flag == true){
                if(userMn.insertUser(nameLogin, password, fullName, userTypeCode, userAddress, userPhone, userEmail,userActive)){
                    JOptionPane.showMessageDialog(rootPane, "One user has been added successful!");
                    txtUserLogin.setText("");
                    txtPassWord.setText("");
                    txtFullName.setText("");
                    txtAddress.setText("");
                    txtEmail.setText("");
                    txtPhone.setText("");
                    model.setSelectedItem(0);
                    this.dispose();
                }else{
                    JOptionPane.showMessageDialog(this, "Cannot add new User to database", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
       
        
    }
      
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUserLogin = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPassWord = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbxUserType = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        rbtnActive_YES = new javax.swing.JRadioButton();
        rbtnActive_NO = new javax.swing.JRadioButton();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add new User for MSBS");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Users Add", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 153))); // NOI18N

        jLabel1.setText("Username");

        jLabel2.setText("Password ");

        jLabel3.setText("Full Name ");

        jLabel4.setText("User-Type ");

        jLabel5.setText("Phone       ");

        jLabel6.setText("Email         ");

        jLabel7.setText("Address  ");

        txtAddress.setColumns(20);
        txtAddress.setRows(5);
        jScrollPane1.setViewportView(txtAddress);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Add.png"))); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setText("Active");

        buttonGroup1.add(rbtnActive_YES);
        rbtnActive_YES.setSelected(true);
        rbtnActive_YES.setText("Yes");

        buttonGroup1.add(rbtnActive_NO);
        rbtnActive_NO.setText("No");

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/exit.png"))); // NOI18N
        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtPhone))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(txtUserLogin))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnClose))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxUserType, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassWord, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(25, 25, 25)
                                .addComponent(rbtnActive_YES)
                                .addGap(18, 18, 18)
                                .addComponent(rbtnActive_NO))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtUserLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtPassWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbxUserType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(rbtnActive_YES)
                            .addComponent(rbtnActive_NO))))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClose))
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         add();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    /**
    * @param args the command line arguments
    */
    
    private UserManage userMn;
    private DefaultComboBoxModel model;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbxUserType;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtnActive_NO;
    private javax.swing.JRadioButton rbtnActive_YES;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JPasswordField txtPassWord;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtUserLogin;
    // End of variables declaration//GEN-END:variables

}
