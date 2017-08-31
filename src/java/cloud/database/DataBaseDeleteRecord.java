package cloud.database;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseDeleteRecord {

    public static void delete(long recordId) {
        String sql1 = "delete from contentkeys where contentId = " + recordId;
        int num1 = 0;
        try {
            num1 = DataBaseConnection.getDBConnection().createStatement().executeUpdate(sql1);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseDeleteRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql2 = "delete from cloud where id = " + recordId;
        if (num1 == 1 || num1 == 0) {
            int num2 = 0;
            try {
                num2 = DataBaseConnection.getDBConnection().createStatement().executeUpdate(sql2);
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseDeleteRecord.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
