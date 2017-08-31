package cloud.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseConnection {

    private static Connection connection = null;

    private static Connection dbConnection() {
        try {
            Class.forName(DataBaseConfiguration.JDBC_DRIVER_NAME);
            connection = DriverManager.getConnection(DataBaseConfiguration.SERVER_LOCATION + ":" + DataBaseConfiguration.PORT_NUMBER + "/" + DataBaseConfiguration.SCHEMA, DataBaseConfiguration.user, DataBaseConfiguration.password);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public static Connection getDBConnection() {
        if (connection == null) {
            connection = dbConnection();
        }
        return connection;
    }
}
