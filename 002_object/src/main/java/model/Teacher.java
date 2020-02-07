package model;

import java.util.ArrayList;

public class Teacher {
    private String name;
    private ArrayList<Student> students;


    public Teacher(String name, Student[] students) {
        this.name = name;
        this.students = new ArrayList<>();
        for (Student student : students) {
            this.addStudent(student);
        }
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void showAllStudents() {
        System.out.printf("%s老师管理学生信息：\n", this.name);
        for (Student student : this.students) {
            System.out.print('\t');
            student.printInfo();
        }
    }
}
