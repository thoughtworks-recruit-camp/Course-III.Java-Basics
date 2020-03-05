package entity;

public class Email {
    private String masterNumber;
    private String emailAddress;

    public Email(String masterNumber, String emailAddress) {
        this.masterNumber = masterNumber;
        this.emailAddress = emailAddress;
    }

    public String getMasterNumber() {
        return masterNumber;
    }

    public void setMasterNumber(String masterNumber) {
        this.masterNumber = masterNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
