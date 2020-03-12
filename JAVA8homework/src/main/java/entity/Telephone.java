package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Telephone {
    private String masterNumber;
    private String countryCode;
    private String telephoneNumber;
}
