package application.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtil {
    public static Connection getConnection(){
        Connection c = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://localhost:1433;databaseName=CNPM;user=sa;password=zedopalter12;encrypt=true;trustServerCertificate=true;";
        try {
            Class.forName(driver);
            c = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    public static void closeConnection(Connection c){
        try {
            if(c!=null){
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}