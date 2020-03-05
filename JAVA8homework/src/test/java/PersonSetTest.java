import entity.Address;
import entity.Email;
import entity.Person;
import entity.Telephone;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonSetTest {

    private static final String NO_MATCH_MASTER_NUMBER = "0000999999";

    @Test
    void should_return_single_person_when_group_to_person_given_person_master_data() {
        PersonSet personSet = PersonSetDataProvider.providePersonSetWithNumber1();

        List<Person> people = personSet.groupToPeople().collect(Collectors.toList());

        Person expected = PersonDataProvider.providePersonWithNumber1();
        assertEquals(1, people.size());
        assertEquals(expected, people.get(0));
    }

    @Test
    void should_return_multiple_persons_when_group_to_person_given_multiple_person_master_data() {
        PersonSet multipleDataPersonSet = PersonSetDataProvider.providePersonSetWithNumber1And2();

        List<Person> people = multipleDataPersonSet.groupToPeople().collect(Collectors.toList());

        assertEquals(2, people.size());
        Person firstPerson = PersonDataProvider.providePersonWithNumber1();
        Person secondPerson = PersonDataProvider.providePersonWithNumber2();
        assertTrue(people.contains(firstPerson));
        assertTrue(people.contains(secondPerson));
    }

    @Test
    void should_return_empty_stream_when_group_to_person_given_no_person_master_data() {
        PersonSet personSet = PersonSetDataProvider.providePersonSetWithNumber1();
        personSet.setMasterNumbers(new ArrayList<>());

        Stream<Person> oSqlPersonStream = personSet.groupToPeople();

        assertEquals(0, oSqlPersonStream.count());
    }

    @Test
    void should_return_empty_address_when_group_to_person_given_no_address_matches_person_number() {
        PersonSet personSet = PersonSetDataProvider.providePersonSetWithNumber1();
        List<Address> addresses = personSet.getAddresses();
        addresses.forEach(address -> address.setMasterNumber(NO_MATCH_MASTER_NUMBER));
        personSet.setAddresses(addresses);

        List<Person> people = personSet.groupToPeople().collect(Collectors.toList());

        assertTrue(people.stream().allMatch(person -> Objects.isNull(person.getAddress())));
    }

    @Test
    void should_return_empty_email_when_group_to_person_given_no_email_matches_person_number() {
        PersonSet personSet = PersonSetDataProvider.providePersonSetWithNumber1();
        List<Email> emails = personSet.getEmails();
        emails.forEach(email -> email.setMasterNumber(NO_MATCH_MASTER_NUMBER));
        personSet.setEmails(emails);

        List<Person> people = personSet.groupToPeople().collect(Collectors.toList());

        assertTrue(people.stream().allMatch(person -> person.getEmails().isEmpty()));
    }


    @Test
    void should_return_empty_telephone_when_group_to_o_sql_person_given_no_telephone_matches_person_number() {
        PersonSet personSet = PersonSetDataProvider.providePersonSetWithNumber1();
        List<Telephone> telephones = personSet.getTelephones();
        telephones.forEach(telephone -> telephone.setMasterNumber(NO_MATCH_MASTER_NUMBER));
        personSet.setTelephones(telephones);

        List<Person> people = personSet.groupToPeople().collect(Collectors.toList());

        assertTrue(people.stream().allMatch(person -> person.getTelephones().isEmpty()));
    }


    @Test
    void should_return_empty_stream_when_group_to_o_sql_person_given_no_general_data() {
        PersonSet personSet = PersonSetDataProvider.providePersonSetWithNumber1();
        personSet.setMasterNumbers(new ArrayList<>());

        List<Person> people = personSet.groupToPeople().collect(Collectors.toList());

        assertTrue(people.isEmpty());
    }

}