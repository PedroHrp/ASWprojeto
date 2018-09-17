package model.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro
 */

public class SQLiteBase {
    
    protected Connection conn;
    
    public Connection open(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:my database");
            return conn;
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return null;
    }
    
    public void close(){
        try {
            if(conn != null) 
                  conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
