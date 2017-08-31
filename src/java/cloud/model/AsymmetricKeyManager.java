package cloud.model;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class AsymmetricKeyManager {

    public static AsymmetricKeys generateKeys() {
        AsymmetricKeys keys = null;
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
            keyGenerator.init(448);
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            String pubKey = new String(java.util.Base64.getEncoder().encode(keyPair.getPublic().getEncoded()));
            String priKey = new String(java.util.Base64.getEncoder().encode(keyPair.getPrivate().getEncoded()));
            keys = new AsymmetricKeys(priKey, pubKey);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AsymmetricKeyManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return keys;
    }

    public static String encryptTheDataWithPublicKey(String plainText, String pubKey) {
        String encText = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(pubKey.getBytes())));
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedbytes = cipher.doFinal(plainText.getBytes());
            encText = new String(Base64.getEncoder().encode(encryptedbytes));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(AsymmetricKeyManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encText;
    }

    public static String decryptTheDataWithPrivateKey(String encData, String priKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String plainText = null;

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        PrivateKey pk = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(priKey.getBytes())));
        cipher.init(Cipher.DECRYPT_MODE, pk);
        byte[] encryptedbytes = cipher.doFinal(Base64.getDecoder().decode(encData.getBytes()));
        plainText = new String(encryptedbytes);

        return plainText;
    }

    public static void main(String argc[]) {
        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALqTQFMz6PZ+9OW2Kks+TQJkoc0yT27OPuft6/fML7MeIOB259raNvfJm4y818fVNMRaxh5xiMZHb5dxySWpMSMCAwEAAQ==";
        String privateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAupNAUzPo9n705bYqSz5NAmShzTJPbs4+5+3r98wvsx4g4Hbn2to298mbjLzXx9U0xFrGHnGIxkdvl3HJJakxIwIDAQABAkEAonTfWNNlcWUAhxiEYTohQgkv3gWFpt0NZ1QC2KTBj/sZXT81cKoofurOa0nI9yKrlSlkY3egyxd4/44O3W7O4QIhAOrgrOrmn3q5TSKUGRxRRVmdl0bNjsP8B3Iaqk8sHthxAiEAy1qQHBRI1Z8m0C5MC6uydEf4UHj8soBczLJDK49PjNMCIBSQe1NNweTS2IgQg2pRS0sfWyFd2gQayuQYpZSs/j5hAiBHKvK0YQT8fi5/PTSwAO77cxScVTta3UGdAeYwIH+esQIgF4H4TAacQGwTLklRtAJoB9cI4l7lPrO1dZPCDcCNahY=";
        String plainText = "w!\\%e:>5>^";

        String encText = AsymmetricKeyManager.encryptTheDataWithPublicKey(plainText, publicKey);
        //String encText = "QvaQFGgQbIDvcBmuTRSg3xzn9gD+FSWxsPHZ2RVe3yhl/Abh5gY2dqnbh98HcPAxOocgmJlpKuHYF5zYsWfQRw==";
        System.out.println("encrypted text : " + encText);

//        String decText = AsymmetricKeyManager.decryptTheDataWithPrivateKey(encText, privateKey);
        //      System.out.println("decrypted text : " + decText);
    }
}
