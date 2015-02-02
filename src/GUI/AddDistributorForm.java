/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddDistributorForm.java
 *
 * Created on Nov 8, 2009, 4:22:50 PM
 */
package GUI;

import Business.SupplierManage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class AddDistributorForm extends javax.swing.JDialog {

    /** Creates new form AddDistributorForm */
    public AddDistributorForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        supMn = new SupplierManage();
    }

    public boolean checkProvider() {
        String txtEmailp = txtEmail.getText().trim();
        String txtPhonep = txtPhone.getText().trim();
        String txtNamep = txtFullName.getText().trim();
        String txtname = txtName.getText().trim();
        String Fax = txtFax.getText().trim();
        Pattern emailcheck = Pattern.compile(".+@.+\\.[a-z]+");
        Pattern phonecheck = Pattern.compile("[0-9]{9,11}");
        Pattern namecheck = Pattern.compile("^[a-zA-Z\\s]+");
        Pattern namecheck1 = Pattern.compile("^[a-zA-Z\\s]+");
        Pattern fax = Pattern.compile("[0-9]{9,11}");
        Matcher m = emailcheck.matcher(txtEmailp);
        Matcher p = phonecheck.matcher(txtPhonep);
        Matcher n = namecheck.matcher(txtNamep);
        Matcher n1 = namecheck1.matcher(txtname);
        Matcher faxC = fax.matcher(Fax);
        boolean email = m.matches();
        boolean phone = p.matches();
        boolean name = n.matches();
        boolean name1 = n1.matches();
        boolean faxCheck = faxC.matches();

        if (txtName.getText().trim().equals("") || txtName.getText().trim().equals(null)) {
            JOptionPane.showMessageDialog(this, "Name is not null !!!");
            txtName.requestFocus();
            return false;
        } else if (name1 == false) {
            JOptionPane.showMessageDialog(this, "Name is not valid !!!" + "\n" + "Name can't contained number!", "WARNING", JOptionPane.WARNING_MESSAGE);
            txtName.requestFocus();
            return false;

        }
        if (txtFullName.getText().trim().equals("") || txtFullName.getText().trim().equals(null)) {
            JOptionPane.showMessageDialog(this, "FullName is not null !!!");
            txtFullName.requestFocus();
            return false;
        } else if (name == false) {
            JOptionPane.showMessageDialog(this, "FullName is not valid !!!" + "\n" + "FullName can't contained number!", "WARNING", JOptionPane.WARNING_MESSAGE);
            txtFullName.requestFocus();
            return false;

        }
        if (txtPhone.getText().equals("") || txtPhone.getText().equals(null)) {
            JOptionPane.showMessageDialog(this, "Phone is not null !!!");
            txtPhone.requestFocus();
            return false;
        } else if (phone == false) {
            JOptionPane.showMessageDialog(this, "Phone is not valid !!!" + "\n" + "Phone can't contained text" + "\n" + "example : xxxxxxxxx or xxxxxxxxxxx", "WRANING", JOptionPane.WARNING_MESSAGE);
            txtPhone.requestFocus();
            return false;

        }
        if (txtAddress.getText().trim().equals("") || txtAddress.getText().trim().equals(null)) {
            txtAddress.requestFocus();
            JOptionPane.showMessageDialog(this, "Address is not null !!!");
            return false;
        }
        if (txtFax.getText().trim().equals("") || txtFax.getText().trim().equals(null)) {
        } else if (faxCheck == false) {
            JOptionPane.showMessageDialog(this, "Fax is not valid !!!" + "\n" + "Fax can't contained text" + "\n" + "example : xxxxxxxxx or xxxxxxxxxxx", "WRANING", JOptionPane.WARNING_MESSAGE);
            txtFax.requestFocus();
            return false;
        }

        if (txtEmail.getText().trim().equals("") || txtEmail.getText().trim().equals(null)) {
        } else if (email == false) {
            JOptionPane.showMessageDialog(this, "Email is not valid !!!" + "\n" + "Example : example@email.com", "WRANING", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }
        return true;
    }

    public void add() {
        String name = null;
        String fullName = null;
        String Address = null;
        String Phone = null;
        String Fax = null;
        String Email = null;
        String website = null;

        name = txtName.getText().trim();
        fullName = txtFullName.getText().trim();
        Address = txtAddress.getText().trim();
        Phone = txtPhone.getText().trim();
        Fax = txtFax.getText().trim();
        Email = txtEmail.getText().trim();
        website = txtWebsite.getText().trim();
        if (checkProvider()) {
            if (supMn.insertSupplier(name, fullName, Address, Phone, Fax, Email, website)) {
                JOptionPane.showMessageDialog(rootPane, "One Distributor has been added successful!");
                txtName.setText("");
                txtFax.setText("");
                txtFullName.setText("");
                txtAddress.setText("");
                txtEmail.setText("");
                txtPhone.setText("");

                this.dispose();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Check your Infomation", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtFullName = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtFax = new javax.swing.JTextField();
        txtWebsite = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        btAdd = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add new Distributor");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Distributor Add", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        jLabel1.setText("Name  ");

        jLabel2.setText("Full Name  ");

        jLabel3.setText("Email  ");

        jLabel4.setText("Phone  ");

        jLabel5.setText("Website  ");

        jLabel6.setText("Address  ");

        jLabel7.setText("Fax  ");

        txtAddress.setColumns(20);
        txtAddress.setRows(5);
        jScrollPane1.setViewportView(txtAddress);

        btAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icon/save (2).png"))); // NOI18N
        btAdd.setText("Add ");
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFax)
                            .addComponent(txtPhone)
                            .addComponent(txtFullName)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtWebsite, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(33, 33, 33)
                                .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(380, 380, 380)
                        .addComponent(btAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClose)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txtWebsite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(jLabel6)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClose))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        // TODO add your handling code here:
        add();
    }//GEN-LAST:event_btAddActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed
    /**
     * @param args the command line arguments
     */
    private SupplierManage supMn;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btnClose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFax;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtWebsite;
    // End of variables declaration//GEN-END:variables
}
