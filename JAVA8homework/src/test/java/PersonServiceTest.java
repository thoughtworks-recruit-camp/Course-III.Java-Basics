import entity.MasterNumber;
import entity.Person;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonServiceTest {

    @Test
    void should_return_person_when_get_person_given_numbers_1() {
        PersonService personService = new PersonService();

        Stream<Person> person = personService.getPersonByMasterNumbers(Arrays.asList(new MasterNumber("1")));

        assertEquals(PersonDataProvider.providePersonWithNumber1(), person.findAny().get());
    }

    @Test
    void should_return_empty_when_get_person_given_numbers_3() {
        PersonService personService = new PersonService();

        Stream<Person> person = personService.getPersonByMasterNumbers(Arrays.asList(new MasterNumber("3")));

        assertEquals(0, person.count());
    }
}