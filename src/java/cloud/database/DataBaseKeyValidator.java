package cloud.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseKeyValidator {

    public static boolean validate(long id, String key) {
        boolean flag = false;
        try {
            ResultSet resultSet = DataBaseConnection.getDBConnection().createStatement().executeQuery("select * from contentkeys where contentId =" + id + " and value = '" + key + "'");
            if (resultSet.next()) {
                flag = true;
            }
        } catch (SQLException ex) {
        }
        return flag;
    }
}
