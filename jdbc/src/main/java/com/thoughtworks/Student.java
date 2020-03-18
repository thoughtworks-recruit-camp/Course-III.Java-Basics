package com.thoughtworks;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class Student {

  private String id;
  private String name;
  private String gender;
  private int entranceYear;
  private Date birthday;
  private String classId;

  public Student(String id, String name, String gender, int entranceYear, String birthday,
                 String classId) {
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
}
