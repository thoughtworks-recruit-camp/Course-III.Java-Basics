package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Teacher {
    private final String name;
    private ArrayList<Student> students;

    public Teacher(String name) {
        this.name = name;
    }

    public Teacher(String name, Student[] students) {
        this.name = name;
        this.students = new ArrayList<>(Arrays.asList(students));
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public Student[] getStudents() {
        return students.toArray(new Student[0]);
    }

    public static void modifyStudentRating(Student student, String offsetStr) {
        student.modifyRating(offsetStr);
    }

    public void printSelfInfo() {
        System.out.printf("教师姓名：%s\t管理学生: [", this.name);
        if (this.students.size() > 0) {
            for (Student student : this.students) {
                System.out.printf(" %s ", student.getName());
            }
        } else {
            System.out.print("无");
        }
        System.out.print("]\n");
    }

    public void printStudentsInfo() {
        for (Student student : this.students) {
            System.out.print('\t');
            student.printSelfInfo();
        }
    }

    public void printAllInfo() {
        this.printSelfInfo();
        System.out.println("管理学生信息：");
        this.printStudentsInfo();
    }
}
