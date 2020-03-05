package entity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Person {
    private static final String formatTemplate = "Master Number: %s\nAddress:\n%s\nTels:\n%s\nEmails:\n%s\n";
    private String masterNumber;
    private Address address;
    private List<Telephone> telephones;
    private List<Email> emails;

    public Person() {
    }

    public Person(String masterNumber, List<Telephone> telephones, Address address, List<Email> emails) {
        this.masterNumber = masterNumber;
        this.address = address;
        this.telephones = telephones;
        this.emails = emails;
    }

    public Optional<SimpleAddress> getSimpleAddress() {
        Optional<Address> address = Optional.ofNullable(this.address);
        return address.map(value -> new SimpleAddress(value.getStreet(), value.getCity()));
    }

    public Address getAddress() {
        return address;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public List<Telephone> getTelephones() {
        return telephones;
    }

    @Override
    public String toString() {
        String telStrings = telephones.stream()
                .map(telephone -> String.format("\t%s %s", telephone.getCountryCode(), telephone.getTelephoneNumber()))
                .collect(Collectors.joining("\n"));
        String emailStrings = emails.stream()
                .map(email -> String.format("\t%s", email.getEmailAddress()))
                .collect(Collectors.joining("\n"));
        return String.format(formatTemplate, masterNumber, address, telStrings, emailStrings);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Person && ((Person) obj).masterNumber.equals(masterNumber));
    }
}
