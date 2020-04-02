package com.thoughtworks;

import com.thoughtworks.repository.Column;
import com.thoughtworks.repository.PrimaryKey;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
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
