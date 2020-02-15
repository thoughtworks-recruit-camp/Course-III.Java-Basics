package person;

import java.text.MessageFormat;
import java.util.ArrayList;

public class Student {
    private final String name;
    private final Gender gender;

    public Student(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public String getInfo() {
        return MessageFormat.format("Name: {0}  Gender: {1}", this.name, this.gender);
    }

    public static Student[] filterByGender(ArrayList<Student> students, Gender gender) {
        ArrayList<Student> resultList = new ArrayList<>();
        for (Student student : students) {
            if (student.getGender() == gender) {
                resultList.add(student);
            }
        }
        return resultList.toArray(new Student[0]);
    }
}
