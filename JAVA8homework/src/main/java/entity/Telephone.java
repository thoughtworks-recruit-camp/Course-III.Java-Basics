package entity;

public class Telephone {
    private String masterNumber;
    private String countryCode;
    private String telephoneNumber;

    public Telephone(String masterNumber, String countryCode, String telephoneNumber) {
        this.masterNumber = masterNumber;
        this.countryCode = countryCode;
        this.telephoneNumber = telephoneNumber;
    }

    public String getMasterNumber() {
        return masterNumber;
    }

    public void setMasterNumber(String masterNumber) {
        this.masterNumber = masterNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }
}
