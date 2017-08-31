package cloud.model;

public class RandomStringGenerator {

    public static String generate() {
        char[] c = new char[10];
        int randomNumber = 0;
        for (int i = 0; i < 10; i++) {
            randomNumber = (int) (Math.random() * 92);
            c[i] = (char) (randomNumber + 32);
        }
        return new String(c);
    }

}
