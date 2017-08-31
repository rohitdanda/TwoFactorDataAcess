package cloud.database;

import cloud.model.AsymmetricKeyManager;
import cloud.model.AsymmetricKeys;
import cloud.model.RandomStringGenerator;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataBaseRegisterUser {

    public static String registerUser(String userName, String password) throws SQLException {
        Connection connection = DataBaseConnection.getDBConnection();
        //String key = RandomStringGenerator.generate();
        AsymmetricKeys keys = AsymmetricKeyManager.generateKeys();
        int n = 0;
        Statement statement = connection.createStatement();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        String currentTime = sdf.format(cal.getTime());
        String query = "insert into user (userName,password,lastLoggedInOn,publicKey,privateKey) values ('" + userName + "','" + password + "','" + currentTime + "','" + keys.getPublicKey() + "','" + keys.getPrivateKey() + "')";
        System.out.println("query : " + query);
        n = statement.executeUpdate(query);

        return keys.getPrivateKey();
    }

}
