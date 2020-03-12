package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    private static final String FULL_ADDRESS_TEMPLATE = "\tCountry: %s\n\tCity: %s\n\tStreet: %s";
    private String masterNumber;
    private String country;
    private String city;
    private String street;

    public String getFullAddress() {
        return String.format(FULL_ADDRESS_TEMPLATE, country, city, street);
    }
}


