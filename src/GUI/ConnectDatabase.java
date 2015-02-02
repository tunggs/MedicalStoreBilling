/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConnectDatabase.java
 *
 * Created on Nov 20, 2009, 11:55:49 PM
 */
package GUI;

import Access.ConnectDB;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Administrator
 */
public class ConnectDatabase extends javax.swing.JFrame {

    /** Creates new form ConnectDatabase */
    public ConnectDatabase() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        this.setVisible(false);
        initComponents();
        loadConfig();


    }

    public void createConfig() {
        //if (checkForm()) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("connection.cfg"));

            severName = txtServername.getText();
            port = txtPort.getText();
            databasename = txtDatabasename.getText();
            usename = txtUsername.getText();
            password = String.valueOf(txtPassword.getPassword());


            writer.write("Username=");
            writer.write(usename);
            writer.newLine();
            writer.write("Port=");
            writer.write(port);
            writer.newLine();
            writer.write("ServerID=");
            writer.write(severName);
            writer.newLine();
            writer.write("Password=");
            writer.write(password);
            writer.newLine();
            writer.write("Database=");
            writer.write(databasename);
            writer.newLine();
            writer.write("Instance=SQLEXPRESS");


            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Cannot create the database connection file." + "\n" + " Please try again! ");
        }
    //  }
    }

    public void loadConfig() {
        flag = true;

        Properties p = new Properties();
        try {
            FileInputStream fin = new FileInputStream("connection.cfg");
            p.load(fin);
            this.dispose();
        } catch (Exception e) {
            flag = false;
        }
        if (flag == true) {
            String server = p.getProperty("ServerID");
            String port = p.getProperty("Port");
            String databaseName = p.getProperty("Database");
            String username = p.getProperty("Username");
            String password = p.getProperty("Password");

            txtDatabasename.setText(databaseName);
            txtPassword.setText(password);
            txtUsername.setText(username);
            txtServername.setText(server);
            txtPort.setText(port);
            this.dispose();
        }
        if (test() == false) {
            JOptionPane.showMessageDialog(this, "Cannot connect to Database !" + "\n" + "Please Enter your configuration for Connection to Database! ", "ERROR CONNECT TO DATABASE", JOptionPane.ERROR_MESSAGE);
            
        //this.setVisible(true);
        
        }




    }

    private boolean checkForm() {
        severName = txtServername.getText();
        port = txtPort.getText();
        databasename = txtDatabasename.getText();
        usename = txtUsername.getText();
        password = String.valueOf(txtPassword.getPassword());

        if (severName.equals("") || severName.equals(null)) {
            JOptionPane.showConfirmDialog(this, "Please enter Server Name!", "Message", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
            txtServername.requestFocus();
            return false;
        }
        if (port.equals("") || port.equals(null)) {
            JOptionPane.showConfirmDialog(this, "Please enter Port!", "Message", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
            txtPort.requestFocus();
            return false;
        }
        if (databasename.equals("") || databasename.equals(null)) {
            JOptionPane.showConfirmDialog(this, "Please enter Database Name!", "Message", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
            txtDatabasename.requestFocus();
            return false;
        }
        if (usename.equals("") || usename.equals(null)) {
            JOptionPane.showConfirmDialog(this, "Please enter UserName!", "Message", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
            txtUsername.requestFocus();
            return false;
        }
        if (password.equals("") || password.equals(null)) {
            JOptionPane.showConfirmDialog(this, "Please enter Password!", "Message", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
            txtPassword.requestFocus();
            return false;
        }
        return true;
    }

    public boolean test() {
        try {
            ConnectDB conn = new ConnectDB();
            return conn.open();
        } catch (Exception ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Cannot connect to Database , Try again!  ", "ERROR CONNECT TO DATABASE", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    public void connect() {
        createConfig();
        if (test() == false) {
            JOptionPane.showMessageDialog(this, "Cannot connect to Database, Try again! ", "ERROR CONNECT TO DATABASE", JOptionPane.ERROR_MESSAGE);

        } else {
            try {
                JOptionPane.showMessageDialog(this, "Connect Succesfull! Now you can login to MSBS! ", "SUCCESFULL", JOptionPane.INFORMATION_MESSAGE);
                new Login().setVisible(true);
                this.dispose();
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        txtServername = new javax.swing.JTextField();
        txtPort = new javax.swing.JTextField();
        txtDatabasename = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        btnConnect = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CONNECT TO MSBS DATABASE");
        setAlwaysOnTop(true);
        setLocationByPlatform(true);

        jLabel1.setText("Server name:");

        txtServername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtServernameActionPerformed(evt);
            }
        });

        txtPort.setText("1433");

        txtDatabasename.setText("MSBSDB");
        txtDatabasename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDatabasenameActionPerformed(evt);
            }
        });

        jLabel3.setText("Database name:");

        jLabel2.setText("Port:");

        jLabel4.setText("User name:");

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });

        jLabel5.setText("Password:");

        btnConnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/login.png"))); // NOI18N
        btnConnect.setMnemonic('N');
        btnConnect.setText("Connect");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Refresh.ico-32x32.png"))); // NOI18N
        btnReset.setMnemonic('R');
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/exit.png"))); // NOI18N
        btnCancel.setMnemonic('X');
        btnCancel.setText("Exit");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Datasource Connect.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Monotype Corsiva", 1, 20));
        jLabel7.setForeground(new java.awt.Color(0, 51, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Connect to Medical Store Billing System");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnConnect)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReset)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtServername, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                        .addComponent(txtPassword)
                                        .addComponent(txtDatabasename, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                        .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtServername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDatabasename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnConnect)
                            .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                            .addComponent(btnCancel)))
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtServernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtServernameActionPerformed
}//GEN-LAST:event_txtServernameActionPerformed

    private void txtDatabasenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDatabasenameActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtDatabasenameActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        this.btnConnectActionPerformed(evt);
}//GEN-LAST:event_txtPasswordActionPerformed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if (evt.getKeyCode() == 27) {
            System.exit(0);
        }
}//GEN-LAST:event_txtPasswordKeyPressed

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed

        connect();

    }//GEN-LAST:event_btnConnectActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        System.exit(0);
}//GEN-LAST:event_btnCancelActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        txtDatabasename.setText("");
        txtPassword.setText("");
        txtPort.setText("");
        txtServername.setText("");
        txtUsername.setText("");

    }//GEN-LAST:event_btnResetActionPerformed
    /**
     * @param args the command line arguments
     */
    private boolean flag;
    private String severName;
    private String port;
    private String databasename;
    private String usename;
    private String password;
    private JFrame frmParent;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtDatabasename;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPort;
    private javax.swing.JTextField txtServername;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
