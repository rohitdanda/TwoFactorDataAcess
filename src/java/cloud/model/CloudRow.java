package cloud.model;

public class CloudRow {

    private Long id;
    private String title;
    private String description;
    private String uploadedBy;
    private String uploadedOn;
    private String fileName;
    private String publicKeyOfUser;
    private String encKey;
    private String displayEncValue;

    public CloudRow(Long id, String title, String description, String uploadedBy, String uploadedOn, String fileName, String publicKeyOfUser, String encKey, String displayEncValue) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.uploadedBy = uploadedBy;
        this.uploadedOn = uploadedOn;
        this.fileName = fileName;
        this.publicKeyOfUser = publicKeyOfUser;
        this.encKey = encKey;
        this.displayEncValue = displayEncValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public String getUploadedOn() {
        return uploadedOn;
    }

    public void setUploadedOn(String uploadedOn) {
        this.uploadedOn = uploadedOn;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getEncKey() {
        return encKey;
    }

    public void setEncKey(String encKey) {
        this.encKey = encKey;
    }

    public String getPublicKeyOfUser() {
        return publicKeyOfUser;
    }

    public void setPublicKeyOfUser(String publicKeyOfUser) {
        this.publicKeyOfUser = publicKeyOfUser;
    }

    public String getDisplayEncValue() {
        return displayEncValue;
    }

    public void setDisplayEncValue(String displayEncValue) {
        this.displayEncValue = displayEncValue;
    }

}
