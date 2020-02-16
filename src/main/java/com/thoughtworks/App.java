package com.thoughtworks;

import com.thoughtworks.person.Student;
import com.thoughtworks.person.Teacher;

public class App {
    public static void main(String[] args) {
        Teacher teacherA = new Teacher("张龙", new Student[]{
                new Student("张三", "001", "2019.02.11"),
                new Student("李四", "002", "2019.02.10"),
                new Student("王五", "001", "2019.02.09"),
        });
        System.out.println(teacherA);
        teacherA.printStudentsIntros();

        teacherA.printDuplicateStudents();
    }
}
