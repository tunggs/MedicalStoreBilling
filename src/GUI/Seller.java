/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Seller.java
 *
 * Created on Nov 4, 2009, 4:23:49 PM
 */

package GUI;

import Business.UserManage;
import Data.msbsUser;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class Seller extends javax.swing.JFrame {

    /** Creates new form Seller */
    public Seller() throws UnsupportedLookAndFeelException {
        initComponents();
        mnSystem.setMnemonic('S');
        mnHelp.setMnemonic('H');
        miLogOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.CTRL_MASK));
        miExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
        miAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
        miHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
        lbNameLogin.setText(getUserLoginName());
        user = new msbsUser();
        Date todayD = new Date(System.currentTimeMillis());
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEE, MMMM dd, yyyy");
        String todayS = dayFormat.format(todayD.getTime());
        lblSystemTime.setText(todayS);
    }

    public String getUserLoginName() throws UnsupportedLookAndFeelException{

        return  Login.userLogged.getFullName();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        OrderPane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbNameLogin = new javax.swing.JLabel();
        btnChangePass = new javax.swing.JButton();
        lblSystemTime = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnSystem = new javax.swing.JMenu();
        miLogOut = new javax.swing.JMenuItem();
        miExit = new javax.swing.JMenuItem();
        mnHelp = new javax.swing.JMenu();
        miHelp = new javax.swing.JMenuItem();
        miAbout = new javax.swing.JMenuItem();

        jToolBar1.setRollover(true);

        jMenu2.setText("File");
        jMenuBar2.add(jMenu2);

        jMenu3.setText("Edit");
        jMenuBar2.add(jMenu3);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SELLER - MSBS");
        setBackground(new java.awt.Color(0, 153, 153));
        setLocationByPlatform(true);

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11));

        javax.swing.GroupLayout OrderPaneLayout = new javax.swing.GroupLayout(OrderPane);
        OrderPane.setLayout(OrderPaneLayout);
        OrderPaneLayout.setHorizontalGroup(
            OrderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 749, Short.MAX_VALUE)
        );
        OrderPaneLayout.setVerticalGroup(
            OrderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 385, Short.MAX_VALUE)
        );

        OrderPane = new GUI.OrderModule();

        jTabbedPane1.addTab("Order Management       ", new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/Drug-basket-48.png")), OrderPane); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/top.png"))); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setBackground(new java.awt.Color(0, 0, 153));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/welcome.png"))); // NOI18N

        lbNameLogin.setFont(new java.awt.Font("Monotype Corsiva", 1, 18));
        lbNameLogin.setForeground(new java.awt.Color(0, 102, 153));
        lbNameLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnChangePass.setText("Change Pass");
        btnChangePass.setToolTipText("Change your password here");
        btnChangePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePassActionPerformed(evt);
            }
        });

        lblSystemTime.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblSystemTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSystemTime.setText("DD/MM/YYYY");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnChangePass))
                    .addComponent(lbNameLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(22, 22, 22))
            .addComponent(lblSystemTime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNameLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChangePass)
                .addGap(11, 11, 11)
                .addComponent(lblSystemTime)
                .addContainerGap())
        );

        mnSystem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/systemicon.png"))); // NOI18N
        mnSystem.setMnemonic('S');
        mnSystem.setText("System");
        mnSystem.setToolTipText("System Tool");
        mnSystem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnSystem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnSystemActionPerformed(evt);
            }
        });

        miLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/logout.png"))); // NOI18N
        miLogOut.setText("Log Off");
        miLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miLogOutActionPerformed(evt);
            }
        });
        mnSystem.add(miLogOut);

        miExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/exit.png"))); // NOI18N
        miExit.setText("Exit");
        miExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExitActionPerformed(evt);
            }
        });
        mnSystem.add(miExit);

        jMenuBar1.add(mnSystem);

        mnHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/help.png"))); // NOI18N
        mnHelp.setText("Help");
        mnHelp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        miHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/information.png"))); // NOI18N
        miHelp.setText("Help Topics");
        miHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miHelpActionPerformed(evt);
            }
        });
        mnHelp.add(miHelp);

        miAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/about.png"))); // NOI18N
        miAbout.setText("About Us");
        miAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAboutActionPerformed(evt);
            }
        });
        mnHelp.add(miAbout);

        jMenuBar1.add(mnHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jPanel5, 0, 100, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnSystemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSystemActionPerformed

}//GEN-LAST:event_mnSystemActionPerformed

    private void miExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExitActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "Do you want to exit?");
        if(result == JOptionPane.YES_OPTION){
            System.exit(0);
        }
}//GEN-LAST:event_miExitActionPerformed

    private void miLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miLogOutActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Login login = null;
        try {
            login = new Login();
            lbNameLogin.setText(Login.userLogged.getNameLogin());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
        login.setVisible(true);
}//GEN-LAST:event_miLogOutActionPerformed

    private void btnChangePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePassActionPerformed
        // TODO add your handling code here:
        UserManage userMn = new UserManage();
        user = userMn.getUserByCode(userMn.getCodeByName(Login.userLogged.getNameLogin()));
        ChangePasswordForm chgPassForm = new ChangePasswordForm(this.frmParent,true,user);
        chgPassForm.setLocationRelativeTo(this);
        chgPassForm.setVisible(true);
}//GEN-LAST:event_btnChangePassActionPerformed

    private void miAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAboutActionPerformed
        // TODO add your handling code here:
        AboutUsForm ab = new AboutUsForm(this.frmParent,true);
        ab.setLocationRelativeTo(this);
        ab.setVisible(true);
    }//GEN-LAST:event_miAboutActionPerformed

    private void miHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miHelpActionPerformed
        // TODO add your handling code here:
        Runtime rt = Runtime.getRuntime();
        try {
            String filePath = "msbsUserGuide.chm";
            rt.exec("hh.exe "+filePath);

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,"Cannot load help file!","Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_miHelpActionPerformed

    /**
    * @param args the command line arguments
    */
    private JFrame frmParent;
    private msbsUser user;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel OrderPane;
    private javax.swing.JButton btnChangePass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbNameLogin;
    private javax.swing.JLabel lblSystemTime;
    private javax.swing.JMenuItem miAbout;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JMenuItem miHelp;
    private javax.swing.JMenuItem miLogOut;
    private javax.swing.JMenu mnHelp;
    private javax.swing.JMenu mnSystem;
    // End of variables declaration//GEN-END:variables

}
