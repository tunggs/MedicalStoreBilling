/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Access;

/**
 *
 * @author Administrator
 */
import java.io.FileInputStream;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;
import java.sql.CallableStatement;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectDB {

    public static final int MODE_PREPARED 	= 0;
    public static final int MODE_CALLABLE 	= 1;
    public static final int MODE_NORMAL 	= 2;

    private PreparedStatement pre = null;
    private Connection conn = null;
    private Statement stat = null;
    private ResultSet rs = null;


    private int mode = MODE_NORMAL;


    public ConnectDB() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * DBFacade Constructor, used to create a driver-customized
     * DBFacade object.
     * @param driver - the specific driver
     *
     */
    public ConnectDB(String Driver) throws Exception {
        Class.forName(Driver);
    }

    
    public boolean open() throws Exception {
        boolean temp = false;
        
        Properties p = new Properties();
        try {
            FileInputStream fin = new FileInputStream("connection.cfg");
            p.load(fin);
        } catch(Exception e) {
            e.printStackTrace();
        }

        String server = p.getProperty("ServerID");
        String port = p.getProperty("Port");
        String databaseName = p.getProperty("Database");
        String username = p.getProperty("Username");
        String password = p.getProperty("Password");

        String sql = "jdbc:sqlserver://" + server + ":" + port +
                ";databaseName=" + databaseName +
                ";User=" + username +
                ";Password=" + password;

         

        try {
            conn = DriverManager.getConnection(sql);
            return true;
        } catch (SQLException ex) {
            System.err.println("Connect Database Fail !");
            ex.printStackTrace();
            return false;
        }
        
    }

    public void close() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (pre != null) {
            pre.close();
        }
        if (conn != null) {
            conn = null;
        }
    }
    public  ResultSet executeQuery(String spName) throws SQLException{
        if(conn != null)
        {
            CallableStatement cs = conn.prepareCall("{call " + spName + "}");
            return cs.executeQuery();
        }
        return null;
    }
    public  ResultSet executeQuery(String spName, Vector paramList) throws SQLException{
        if(conn != null){
            String strQ = "{call " + spName + "(";
            int t =0;
            for(Object obj : paramList){
                if(t != 0)
                    strQ += ",";
                if(obj instanceof Integer){
                    Integer i = (Integer)obj;
                    strQ += i.toString();
                }else if(obj instanceof String){
                    String s = (String)obj;
                    strQ += "'" + s + "'";
                }
                t++;
            }
            strQ += ")}";

            CallableStatement cst = conn.prepareCall(strQ);
            return cst.executeQuery();
        }
        return null;
    }
    public  int executeUpdate(String spName, Vector paramList) throws SQLException{
        if(conn != null)
        {
            String strQ = "{call " + spName + "(";

            int t = 0;
            for(Object obj : paramList)
            {
                if(t != 0)
                    strQ += ",";
                if(obj instanceof Integer)
                {
                    Integer i = (Integer)obj;
                    strQ += i.toString();
                }
                else if(obj instanceof Double)
                {
                    Double f = (Double)obj;
                    strQ += f.toString();
                }
                else if(obj instanceof String)
                {
                    String s = (String)obj;
                    strQ += "'" + s + "'";
                }
                else if(obj instanceof Date)
                {
                    String s = String.valueOf(obj);
                    strQ += "'" + s + "'";
                }
                t++;
            }
            strQ += ")}";

            CallableStatement cst = conn.prepareCall(strQ);
            return cst.executeUpdate();
        }
        return -1;
    }

    public void setMode(int mod) {
        this.mode = mod;
    }   
}
