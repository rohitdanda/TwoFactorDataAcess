package cloud.database;

import cloud.model.AsymmetricKeyManager;
import cloud.model.CloudRow;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseAvailableFilesInCloud {

    public static List<CloudRow> getAllFiles(int userId) {
        List<CloudRow> list = new ArrayList<CloudRow>();
        try {
            ResultSet resultSet = DataBaseConnection.getDBConnection().createStatement().executeQuery("select c.id,c.title,c.description,u1.userName, c.uploadedOn,c.fileName,u1.publicKey,ck.value,u2.userName from cloud c, user u1, user u2,contentkeys ck where u1.user =" + userId + " and ck.contentId = c.id and c.uploadedBy = u2.user");
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                //String uploadedBy = resultSet.getString(4);
                String uploadedOn = resultSet.getString(5);
                String fileName = resultSet.getString(6);
                String publicKeyOfUser = resultSet.getString(7);
                String conKey = resultSet.getString(8);
                String uploadedBy = resultSet.getString(9);
                String displayEncValue = AsymmetricKeyManager.encryptTheDataWithPublicKey(conKey, publicKeyOfUser);
                CloudRow row = new CloudRow(id, title, description, uploadedBy, uploadedOn, fileName, publicKeyOfUser, conKey, displayEncValue);
                list.add(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseAvailableFilesInCloud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
