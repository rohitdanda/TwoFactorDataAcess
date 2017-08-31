package cloud.database;

public interface DataBaseConfiguration {
public static final String JDBC_DRIVER_NAME = "com.mysql.jdbc.Driver";
    public static final String SERVER_LOCATION = "jdbc:mysql://localhost";    
    public static final int PORT_NUMBER = 3306;
    public static final String SCHEMA = "cloud";
    public static final String user = "root";
    public static final String password = "mysql";  
}
