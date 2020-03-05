package entity;

public class SimpleAddress {
    private String street;
    private String city;

    public SimpleAddress() {
    }

    public SimpleAddress(String street, String city) {
        this.street = street;
        this.city = city;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof SimpleAddress
                && ((SimpleAddress) obj).city.equals(city)
                && ((SimpleAddress) obj).street.equals(street));
    }
}
