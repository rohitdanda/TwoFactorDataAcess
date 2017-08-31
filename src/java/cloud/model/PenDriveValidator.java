package cloud.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;

public class PenDriveValidator {

    public static String HASH_STRING = "secured cloud";

    //public static String HASH_FILE_LOCATION = "D://hash";
    public static boolean validate() {
        boolean flag = false;
        String hashString = DigestUtils.sha384Hex(HASH_STRING);
        File[] drives = File.listRoots();
        if (drives != null && drives.length > 0) {
            for (File aDrive : drives) {
                //System.out.println(aDrive);                
                File f = new File(aDrive + "hash");
                if (f.exists()) {
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(f));
                        String line = br.readLine();
                        if (hashString.equals(line)) {
                            flag = true;
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(PenDriveValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(PenDriveValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        return flag;
    }

    public static void main(String argc[]) {
        System.out.println(validate());
    }
}
