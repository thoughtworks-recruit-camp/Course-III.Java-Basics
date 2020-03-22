package com.thoughtworks;

import com.thoughtworks.repository.Column;
import com.thoughtworks.repository.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@Table("student")
public class Student {
    private String id;
    private String name;
    private String gender;
    @Column("entrance_year")
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

    public static Date parseBirthday(String birthday) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
        } catch (ParseException e) {
            return null;
        }
    }
}
