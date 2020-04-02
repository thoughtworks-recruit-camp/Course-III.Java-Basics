package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

  public void save(List<Student> students) {
    students.forEach(this::save);
  }

  public void save(Student student) {
    // TODO:
  }

  public List<Student> query() {
    // TODO:
    return new ArrayList<>();
  }

  public List<Student> queryByClassId(String classId) {
    // TODO:
    return new ArrayList<>();
  }

  public void update(String id, Student student) {
    // TODO:
  }

  public void delete(String id) {
    // TODO:
  }
}
