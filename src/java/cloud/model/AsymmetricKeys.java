package cloud.model;

public class AsymmetricKeys {

    private String privateKey;
    private String publicKey;

    public AsymmetricKeys(String privateKey, String publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String toString() {
        return "public key : " + this.getPublicKey() + "\nprivate key : " + this.getPrivateKey();
    }

}
