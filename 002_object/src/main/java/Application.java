import model.Student;
import model.Teacher;

public class Application {
    public static void main(String[] args) {
        Student studentA = new Student("李莉", 20, Student.Gender.Female);
        studentA.printInfo();
        studentA.study();
        studentA.relax();

        Student studentB = new Student("王其", 21, Student.Gender.Male);
        Teacher teacherA = new Teacher("张龙", new Student[]{studentA, studentB});
        Student studentC = new Student("赵毅", 22, Student.Gender.Male);
        teacherA.addStudent(studentC);
        teacherA.showAllStudents();
    }
}