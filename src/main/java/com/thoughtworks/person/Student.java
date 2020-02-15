package com.thoughtworks.person;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Student {

    private final String name;
    private final String id;
    private final LocalDate entranceDate;

    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy年M月d日");


    public Student(String name, String id, String entranceDateStr) {
        this.name = name;
        this.id = id;
        this.entranceDate = LocalDate.parse(entranceDateStr, inputFormatter);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public LocalDate getEntranceDate() {
        return entranceDate;
    }

    private int getPeriod() {
        return Period.between(entranceDate, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return String.format("我叫%s，我的学号是%s，%s入学，学龄%d年",
                this.name, this.id, outputFormatter.format(entranceDate), getPeriod());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Student otherStudent = (Student) obj;
        return this.id.equals(otherStudent.id);
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(this.id);
    }
}
