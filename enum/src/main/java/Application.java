import person.Gender;
import person.Student;

public class Application {
    public static void main(String[] args) {
        Student[] students = new Student[]{
                new Student("Linda", Gender.Female),
                new Student("Bob", Gender.Male),
                new Student("Solider", Gender.Male),
                new Student("Cindy", Gender.Female)};
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
