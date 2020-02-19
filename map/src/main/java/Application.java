public class Application {

    public static void main(String[] args) {
        Teacher teacherBob = new Teacher("Bob", new Student[]{
                new Student("Linda", 18),
                new Student("Cindy", 19),
                new Student("Mary", 19)});
        teacherBob.addStudents(new Student[]{
                new Student("Ben", 19),
                new Student("Solider", 20)
        });

        System.out.println(teacherBob.getAllStudents());
        System.out.println(teacherBob.getStudentsGroupsByAge());
    }
}
