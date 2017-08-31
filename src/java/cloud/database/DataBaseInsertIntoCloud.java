package cloud.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseInsertIntoCloud {

    public static int storeCloudStuff(String title, String description, byte[] content, int userId, String uploadedDate, String fileName) throws SQLException {
        int n = 0;
        int rowNumber = 0;
        ResultSet rs = DataBaseConnection.getDBConnection().createStatement().executeQuery("select max(id) from cloud ");
        while (rs.next()) {
            rowNumber = (rs.getInt(1) + 1);
        }

        PreparedStatement statement = DataBaseConnection.getDBConnection().prepareStatement("insert into cloud (id,title,description,content,uploadedBy,uploadedOn,fileName) values (?,?,?,?,?,?,?)");
        statement.setInt(1, rowNumber);
        statement.setString(2, title);
        statement.setString(3, description);
        statement.setBytes(4, content);
        statement.setLong(5, userId);
        statement.setString(6, uploadedDate);
        statement.setString(7, fileName);
        n = statement.executeUpdate();
        return rowNumber;
    }

}
