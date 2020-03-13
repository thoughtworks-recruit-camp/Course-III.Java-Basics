import entity.Person;

import java.util.stream.Stream;

public class PersonSetPerfDemo {
    public static void main(String[] args) {
        PersonSet demoSet = PersonSetDataProvider.provideRandomPersonData(10000); // changeable
        long timeBegin, timeEnd, timePassed;
        timeBegin = System.currentTimeMillis();
        Stream<Person> personStream = demoSet.groupToPeopleAlt();  // changeable
        long masterIdSum = personStream
                .map(Person::getMasterNumber)
                .map(Integer::valueOf).mapToInt(Integer::intValue)
                .sum();
        timeEnd = System.currentTimeMillis();
        timePassed = timeEnd - timeBegin;
        System.out.printf("count: %d, in %dms\n", masterIdSum, timePassed);
    }
}
