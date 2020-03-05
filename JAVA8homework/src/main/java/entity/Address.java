package entity;

public class Address {
    private static final String formatTemplate = "\tCountry: %s\n\tCity: %s\n\tStreet: %s";
    private String masterNumber;
    private String country;
    private String city;
    private String street;

    public Address(String masterNumber, String country, String city, String street) {
        this.masterNumber = masterNumber;
        this.country = country;
        this.city = city;
        this.street = street;
    }

    public String getMasterNumber() {
        return masterNumber;
    }

    public void setMasterNumber(String masterNumber) {
        this.masterNumber = masterNumber;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    @Override
    public String toString() {
        return String.format(formatTemplate, country, city, street);
    }
}


