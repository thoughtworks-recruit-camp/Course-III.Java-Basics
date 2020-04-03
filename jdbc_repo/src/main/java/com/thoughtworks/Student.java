package com.thoughtworks;

import com.thoughtworks.repository.Column;
import com.thoughtworks.repository.PrimaryKey;
import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@ToString
@NoArgsConstructor
public class Student {
    @PrimaryKey
    private String id;
    private String name;
    private String gender;
    private int entranceYear;
    private Date birthday;
    @Column("class")
    private String classId;

    public void setId(Object id) {
        this.id = (String) id;
    }

    public void setName(Object name) {
        this.name = (String) name;
    }

    public void setGender(Object gender) {
        this.gender = (String) gender;
    }

    public void setEntranceYear(Object entranceYear) {
        this.entranceYear = ((Date) entranceYear).getYear() + 1900;
    }

    public void setBirthday(Object birthday) {
        this.birthday = (Date) birthday;
    }

    public void setClassId(Object classId) {
        this.classId = (String) classId;
    }

    public Student(String id, String name, String gender, int entranceYear, String birthdayStr, String classId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.entranceYear = entranceYear;
        this.birthday = parseBirthday(birthdayStr);
        this.classId = classId;
    }

    @SneakyThrows(ParseException.class)
    private Date parseBirthday(String birthday) {
        return new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
    }
}
