import entity.MasterNumber;
import entity.Person;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonService {
    private Map<List<String>, Optional<PersonSet>> people;

    public PersonService() {
        this.people = new HashMap<>();
        people.put(Arrays.asList("1"), Optional.of(PersonSetDataProvider.providePersonSetWithNumber1()));
        people.put(Arrays.asList("1", "2"), Optional.of(PersonSetDataProvider.providePersonSetWithNumber1And2()));
    }

    public Stream<Person> getPersonByMasterNumbers(List<MasterNumber> numbers) {
        List<String> masterNumberStrings = numbers.stream().map(MasterNumber::getNumber).collect(Collectors.toList());
        return people.getOrDefault(masterNumberStrings, Optional.empty())
                .map(PersonSet::groupToPeople).orElse(Stream.empty());
    }
}
