package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static final String user = "root";
    private static final String password = "Uu63116rGPo";
    private static final String url = "jdbc:mysql://localhost:3306/test";

    private static Connection connection = null;
    private static DB instance;

    private boolean free = true;

    private DB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DB getInstance() {
        if(instance == null) {
            instance = new DB();
        }
        return instance;
    }

    public synchronized  Connection getConnection() {
        if(free == false) {
            return null;
        }
        free = false;
        return connection;
    }

    public synchronized void freeConnection() { free = true; }

    public static synchronized  void closeConnection() {
        try {
            if( connection != null ) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
