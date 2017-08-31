package cloud.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.http.HttpSession;

public class DataBaseLoginUser {

    public static boolean userAuthentication(HttpSession session, String userName, String password) throws SQLException {
        boolean flag = false;
        ResultSet resultSet = DataBaseConnection.getDBConnection().createStatement().executeQuery("select publicKey from user where userName = '" + userName + "' and password = '" + password + "'");
        if (resultSet.next()) {
            String pKey = resultSet.getString(1);
            session.setAttribute("userKey", pKey);
            flag = true;
        }
        return flag;
    }

    public static boolean updateUserLoginTime(String userName) throws SQLException {

        Connection connection = DataBaseConnection.getDBConnection();
        int n = 0;
        Statement statement = connection.createStatement();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        String currentTime = sdf.format(cal.getTime());
        n = statement.executeUpdate("update user set lastLoggedInOn = '" + currentTime + "' where userName = '" + userName + "'");
        return (n == 1);
    }
}
