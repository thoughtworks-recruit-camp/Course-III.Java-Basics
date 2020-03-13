import entity.Address;
import entity.Email;
import entity.MasterNumber;
import entity.Person;
import entity.Telephone;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
public class PersonSet {
    private List<MasterNumber> masterNumbers;
    private List<Telephone> telephones;
    private List<Address> addresses;
    private List<Email> emails;

    public Stream<Person> groupToPeople() {
        Map<String, List<Telephone>> telephonesMap = telephones.stream()
                .collect(Collectors.groupingBy(Telephone::getMasterNumber));
        Map<String, Address> addressesMap = addresses.stream()
                .collect(Collectors.toMap(Address::getMasterNumber, Function.identity(), (a1, a2) -> a1));
        Map<String, List<Email>> emailsMap = emails.stream()
                .collect(Collectors.groupingBy(Email::getMasterNumber));
        return masterNumbers.stream()
                .map(MasterNumber::getNumber)
                .map(masterNumber -> new Person(
                        masterNumber,
                        telephonesMap.getOrDefault(masterNumber, new LinkedList<>()),
                        addressesMap.getOrDefault(masterNumber, null),
                        emailsMap.getOrDefault(masterNumber, new LinkedList<>())));
    }

    public Stream<Person> groupToPeopleAlt() {
        return masterNumbers.stream()
                .map(MasterNumber::getNumber)
                .map(masterNumber -> new Person(
                        masterNumber,
                        telephones.stream()
                                .filter(telephone -> Objects.equals(telephone.getMasterNumber(), masterNumber))
                                .collect(Collectors.toList()),
                        addresses.stream()
                                .filter(address -> Objects.equals(address.getMasterNumber(), masterNumber))
                                .findAny().orElse(null),
                        emails.stream()
                                .filter(email -> Objects.equals(email.getMasterNumber(), masterNumber))
                                .collect(Collectors.toList())));
    }
}
