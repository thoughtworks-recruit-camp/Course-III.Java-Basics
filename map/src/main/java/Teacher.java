import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Teacher {
    private String name;
    private TreeMap<Integer, ArrayList<Student>> students = new TreeMap<>();

    public Teacher(String name) {
        this.name = name;
    }

    public Teacher(String name, Student[] students) {
        this.name = name;
        for (Student student : students) {
            this.addStudent(student);
        }
    }

    public Teacher(String name, Collection<Student> students) {
        this.name = name;
        for (Student student : students) {
            this.addStudent(student);
        }
    }

    public void addStudent(Student student) {
        if (!students.containsKey(student.getAge())) {
            students.put(student.getAge(), new ArrayList<>());
        }
        students.get(student.getAge()).add(student);
    }

    public void addStudents(Student[] students) {
        for (Student student : students) {
            this.addStudent(student);
        }
    }

    public void addStudents(Collection<Student> students) {
        for (Student student : students) {
            this.addStudent(student);
        }
    }

    private int getStudentsCount() {
        int count = 0;
        for (ArrayList<Student> studentGroup : students.values()) {
            count += studentGroup.size();
        }
        return count;
    }

    public String getAllStudents() {
        StringBuilder resultStr = new StringBuilder(String.format("## Teacher %s is managing %d students: \n",
                name, getStudentsCount()));

        for (ArrayList<Student> studentsGroup : students.values()) {
            for (Student student : studentsGroup) {
                resultStr.append(String.format("- Name: %s  Age: %d\n", student.getName(), student.getAge()));
            }
        }
        return resultStr.toString();
    }

    public String getStudentsGroupsByAge() {
        StringBuilder resultStr = new StringBuilder(
                String.format("## Teacher %s is managing students of %d age groups:\n",
                        name, students.keySet().size()));

        for (Map.Entry<Integer, ArrayList<Student>> entry : students.entrySet()) {
            resultStr.append(String.format("- Age Group %d: \n", entry.getKey()));
            for (Student student : entry.getValue()) {
                resultStr.append(String.format("  - %s\n", student.getName()));
            }
        }
        return resultStr.toString();
    }
}
