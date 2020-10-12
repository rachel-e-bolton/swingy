package controllers.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteHelper {

    private static Connection conn = null;

    public static Connection getConn() throws ClassNotFoundException, SQLException {
        if(conn == null){
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:swingy.db");
        }
        return conn;
    }
}
