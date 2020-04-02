package com.thoughtworks.legacyrepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

public class Student {

    private String id;
    private String name;
    private String gender;
    private int entranceYear;
    private Date birthday;
    private String classId;

    public Student() {
    }

    public Student(String id, String name, String gender, int entranceYear, String birthday, String classId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.entranceYear = entranceYear;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.birthday = format.parse(birthday);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.classId = classId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(int entranceYear) {
        this.entranceYear = entranceYear;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("name='" + name + "'")
                .add("gender='" + gender + "'")
                .add("entranceYear=" + entranceYear)
                .add("birthday=" + birthday)
                .add("classId='" + classId + "'")
                .toString();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}
