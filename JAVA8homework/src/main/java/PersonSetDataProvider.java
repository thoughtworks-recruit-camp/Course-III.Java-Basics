import entity.Address;
import entity.Email;
import entity.MasterNumber;
import entity.Telephone;

import java.util.Arrays;
import java.util.List;

public class PersonSetDataProvider {

    public static PersonSet providePersonSetWithNumber1() {
        List<MasterNumber> masterNumbers =
                Arrays.asList(new MasterNumber("1"));

        List<Telephone> telephones =
                Arrays.asList(
                        new Telephone("1", "+1", "111"),
                        new Telephone("1", "+1", "1111"));

        List<Address> addresses =
                Arrays.asList(new Address("1", "Country A", "City a", "Street I"));

        List<Email> emails =
                Arrays.asList(new Email("1", "111@gmail.com"),
                        new Email("1", "1111@gmail.com"));

        return new PersonSet(masterNumbers, telephones, addresses, emails);
    }

    public static PersonSet providePersonSetWithNumber1And2() {
        List<MasterNumber> masterNumbers =
                Arrays.asList(new MasterNumber("1"),
                        new MasterNumber("2"));

        List<Address> addresses =
                Arrays.asList(
                        new Address("1", "Country A", "City a", "Street I"),
                        new Address("2", "Country B", "City b", "Street II"));

        List<Telephone> telephones =
                Arrays.asList(
                        new Telephone("1", "+1", "111"),
                        new Telephone("1", "+1", "1111"),
                        new Telephone("2", "+2", "222"),
                        new Telephone("2", "+2", "2222"));

        List<Email> emails =
                Arrays.asList(
                        new Email("1", "111@gmail.com"),
                        new Email("1", "1111@gmail.com"),
                        new Email("2", "222@gmail.com"),
                        new Email("2", "2222@gmail.com")
                );

        return new PersonSet(masterNumbers, telephones, addresses, emails);
    }
}
