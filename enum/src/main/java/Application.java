import person.Gender;
import person.Student;

import java.util.ArrayList;
import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>(Arrays.asList(
                new Student("Linda", Gender.Female),
                new Student("Bob", Gender.Male),
                new Student("Solider", Gender.Male),
                new Student("Cindy", Gender.Female)
        ));
        System.out.println("\nCreated students:");
        for (Student student : students) {
            System.out.printf("\t %s\n", student.getInfo());
        }

        System.out.println("\nShow female students:");
        for (Student student : Student.filterByGender(students, Gender.Female)) {
            System.out.printf("\t %s\n", student.getInfo());
        }
    }
}
