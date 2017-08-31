package cloud.database;


import cloud.database.DataBaseConnection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DataBaseKeyStore {

    public static void storeKey(long cloudId, String randomStr) {
        try {
            // insert into contentkeys (contentId,value) values()
            String query = "insert into contentkeys (contentId,value) values("+cloudId+",'"+randomStr+"')";
            DataBaseConnection.getDBConnection().createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseKeyStore.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
