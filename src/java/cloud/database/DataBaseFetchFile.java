package cloud.database;

import cloud.model.DownloadableFile;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseFetchFile {

    public static DownloadableFile getFileBlob(long blobId) {
        DownloadableFile f = null;
        try {
            ResultSet resultSet = DataBaseConnection.getDBConnection().createStatement().executeQuery("select content,fileName from cloud where id = " + blobId);
            while (resultSet.next()) {
                byte[] content = resultSet.getBytes(1);
                String fileName = resultSet.getString(2);
                f = new DownloadableFile(fileName, content);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseFetchFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        ;
        return f;
    }
}
