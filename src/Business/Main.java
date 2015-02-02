/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;
import GUI.ConnectDatabase;
import GUI.Login;
import javax.swing.UnsupportedLookAndFeelException;
/**
 *
 * @author Administrator
 */
public class Main {
 public static void main(String[] args) throws UnsupportedLookAndFeelException{
//        ConnectDatabase run = new ConnectDatabase();
//
//        run.setVisible(true);
     new Login().setVisible(true);
       
    }
}
