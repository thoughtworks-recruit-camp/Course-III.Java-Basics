import model.Student;
import model.Teacher;

public class Application {

    public static void main(String[] args) {
        System.out.println("\n创建学生：");
        Student studentCindy = new Student("Cindy", "80");
        studentCindy.printSelfInfo();
        Student studentMary = new Student("Mary", "95");
        studentMary.printSelfInfo();

        System.out.println("\n创建教师：");
        Teacher teacherSolider = new Teacher("Solider", new Student[]{studentMary});
        teacherSolider.printSelfInfo();
        Teacher teacherBob = new Teacher("Bob", new Student[]{studentCindy});
        teacherBob.printSelfInfo();

        System.out.println("\n更改分数及打印教师及管理学生信息：");
        Teacher[] teachers = new Teacher[]{teacherSolider, teacherBob};
        System.out.println("\n更改前：");
        for (Teacher teacher : teachers) {
            teacher.printAllInfo();
        }
        for (Teacher teacher : teachers) {
            for (Student student : teacher.getStudents()) {
                Teacher.modifyStudentRating(student, "5");
            }
        }
        System.out.println("\n更改后：");
        for (Teacher teacher : teachers) {
            teacher.printAllInfo();
        }
    }
}
