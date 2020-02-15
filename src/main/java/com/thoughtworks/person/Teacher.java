package com.thoughtworks.person;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class Teacher {
    private final String name;
    private ArrayList<Student> students = new ArrayList<>();
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy年M月d日");

    public Teacher(String name) {
        this.name = name;
    }

    public Teacher(String name, Student[] students) {
        this.name = name;
        this.students.addAll(Arrays.asList(students));
    }

    public Teacher(String name, Collection<Student> students) {
        this.name = name;
        this.students.addAll(students);
    }

    private HashMap<String, ArrayList<Student>> getDuplicateStudents() {
        HashMap<String, ArrayList<Student>> idStudentsMap = new HashMap<>();
        for (Student student : this.students) {
            if (!idStudentsMap.containsKey(student.getId())) {
                idStudentsMap.put(student.getId(), new ArrayList<>());
            }
            idStudentsMap.get(student.getId()).add(student);
        }

        HashMap<String, ArrayList<Student>> duplicateMap = new HashMap<>();
        for (HashMap.Entry<String, ArrayList<Student>> idStudentsEntry : idStudentsMap.entrySet()) {
            if (idStudentsEntry.getValue().size() > 1) {
                duplicateMap.put(idStudentsEntry.getKey(), idStudentsEntry.getValue());
            }
        }
        return duplicateMap;
    }

    public void printDuplicateStudents() {
        for (HashMap.Entry<String, ArrayList<Student>> dupStudentsEntry : getDuplicateStudents().entrySet()) {
            String id = dupStudentsEntry.getKey();
            ArrayList<Student> duplicateStudents = dupStudentsEntry.getValue();

            System.out.printf("使用学号【%s】的学生存在重复（共%d名），为：\n", id, duplicateStudents.size());
            for (Student student : duplicateStudents) {
                System.out.print("\t");
                System.out.printf("姓名：%s  入学时间：%s\n", student.getName(), outputFormatter.format(student.getEntranceDate()));
            }
        }
    }

    @Override
    public String toString() {
        return String.format("我是%s老师，我管理%d名学生",
                this.name, this.students.size());
    }

    public String studentsIntros() {
        StringBuilder intros = new StringBuilder("有请学生们做自我介绍：\n");
        for (Student student : students) {
            intros.append(student.toString());
            intros.append("\n");
        }
        return intros.toString();
    }
}
