package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private static final String FULL_INFO_TEMPLATE = "Master Number: %s\nAddress:\n%s\nTels:\n%s\nEmails:\n%s\n";

    private String masterNumber;
    private List<Telephone> telephones;
    private Address address;
    private List<Email> emails;

    public Optional<SimpleAddress> getSimpleAddress() {
        Optional<Address> address = Optional.ofNullable(this.address);
        return address.map(value -> new SimpleAddress(value.getStreet(), value.getCity()));
    }

    public String getFullInfo() {
        String telStrings = telephones.stream()
                .map(telephone -> String.format("\t%s", telephone.getFullNumber()))
                .collect(Collectors.joining("\n"));
        String emailStrings = emails.stream()
                .map(email -> String.format("\t%s", email.getEmailAddress()))
                .collect(Collectors.joining("\n"));
        return String.format(FULL_INFO_TEMPLATE, masterNumber, address.getFullAddress(), telStrings, emailStrings);
    }
}
