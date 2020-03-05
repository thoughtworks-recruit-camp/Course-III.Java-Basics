import entity.Address;
import entity.Email;
import entity.Person;
import entity.Telephone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PersonDataProvider {

    public static Person providePersonWithNumber1() {

        Address address =
                new Address("1", "Country A", "City a", "Street I");

        List<Telephone> telephones =
                new ArrayList<>(Arrays.asList(
                        new Telephone("1", "+1", "111"),
                        new Telephone("1", "+1", "1111")));

        List<Email> emails =
                new ArrayList<>(Arrays.asList(
                        new Email("1", "111@gmail.com"),
                        new Email("1", "1111@gmail.com")));

        return new Person("1", telephones, address, emails);
    }


    public static Person providePersonWithNumber2() {

        Address address =
                new Address("2", "Country B", "City b", "Street II");

        List<Telephone> telephones =
                Arrays.asList(
                        new Telephone("2", "+2", "222"),
                        new Telephone("2", "+2", "2222"));

        List<Email> emails =
                Arrays.asList(
                        new Email("2", "222@gmail.com"),
                        new Email("2", "2222@gmail.com")
                );

        return new Person("2", telephones, address, emails);
    }
}
