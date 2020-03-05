package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PersonTest {

    @Test
    void should_return_address_without_country_and_region_when_get_address_given_address() {
        Person person = new Person("1234",
                null, new Address("1234", "country", "city", "street"), null);

        SimpleAddress expected = new SimpleAddress("street", "city");

        SimpleAddress actual = person.getSimpleAddress().orElse(new SimpleAddress());

        assertEquals(expected, actual);
    }

    @Test
    void should_return_empty_when_get_address_given_address_null() {
        assertFalse(new Person().getSimpleAddress().isPresent());
    }
}