package cloud.model;

import java.io.File;

public class Test {

    public static void main(String argc[]) {
        File[] drives = File.listRoots();
        if (drives != null && drives.length > 0) {
            for (File aDrive : drives) {
                System.out.println(aDrive);                
            }
        }
    }
}
