import entity.MasterNumber;
import entity.Person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class App {
    private static final PersonService personService = new PersonService();

    public static void main(String[] args) {
        printPersonsInfo(Arrays.asList(new MasterNumber("1"), new MasterNumber("2")));
        printPersonsInfo(Arrays.asList(new MasterNumber("3")));
    }

    private static void printPersonsInfo(List<MasterNumber> masterNumbers) {
        System.out.printf("\nGetting person info of master numbers %s\n\n", masterNumbers);
        Stream<Person> personStream = personService.getPersonByMasterNumbers(masterNumbers);
        long count = personStream.peek(System.out::println).filter(x -> true).count();  // filter() is a countermeasure for Stream optimization in JDK9+
        if (count == 0) {
            System.out.println("NO person info found!");
        }
    }
}
