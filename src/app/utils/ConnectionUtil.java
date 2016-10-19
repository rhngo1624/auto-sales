package app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Opens and closes connection to database.
 */
public class ConnectionUtil {

    private static ConnectionUtil instance = null;

    private final String USERNAME = "SA";
    private final String PASSWORD = "";

    /**
     * Connection Path for database.
     */
    private final String CONN_STRING = "jdbc:sqlite:data/AutoSalesDB";

    private Connection conn = null;

    private ConnectionUtil() {
        //Empty Private Constructor
    }

    /**
     * Returns single instance of ConnectionUtil Class.
     *
     * @return ConnectionUtil instance
     */
    public static ConnectionUtil getInstance() {

        //checks in instance is null
        if (instance == null) {

            //creates new instance if null
            instance = new ConnectionUtil();

        }

        //returns instance
        return instance;

    }

    /**
     * Opens connection to database.
     *
     * @return true if connected, false otherwise.
     */
    public boolean openConnection() {

        try {

            conn = DriverManager.getConnection(CONN_STRING);
            return true;

        } catch (SQLException e) {

            System.err.println(e);
            return false;

        }

    }

    /**
     * Returns Connection Object if connection was successful.
     *
     * @return Connection
     */
    public Connection getConnection() {

        //checks if connection is null
        if (conn == null) {

            //checks if connection was established.
            if (openConnection()) {

                System.out.println("Connection opened");
                return conn;

            } else {

                return null;

            }

        }

        return conn;

    }

    /**
     * Terminates connection to database.
     */
    public void close() {

        System.out.println("Closing connection...");

        try {

            conn.close();
            conn = null;

        } catch (Exception e) {

        }

    }

}
