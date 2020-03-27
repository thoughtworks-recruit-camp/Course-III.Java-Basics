package com.thoughtworks;

import com.thoughtworks.repository.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@Table("person")
public class Person {
    private String id;
    private String name;
    private String gender;
    private Date birthday;

    public Person(String id, String name, String gender, String birthdayStr) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = parseBirthday(birthdayStr);
    }

    public static Date parseBirthday(String birthday) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
        } catch (ParseException e) {
            return null;
        }
    }
}
