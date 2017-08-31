package cloud.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseUser {

    public static int getUserId(String userName) throws SQLException {
        int userId = 0;
        ResultSet resultSet = DataBaseConnection.getDBConnection().createStatement().executeQuery("select user from user where userName = '" + userName + "'");
        if (resultSet.next()) {
            userId = resultSet.getInt(1);
        }
        return userId;
    }        
}
