package com.thoughtworks;

import com.thoughtworks.repository.DbUtil;

import java.sql.SQLException;
import java.util.Arrays;

public class AppPerson {
    public static void main(String[] args) throws SQLException {
        try (DbUtil dbUtil = new DbUtil("jdbc:mysql://localhost:3306/jdbc_demo?serverTimezone=UTC", "root", "root");  // FIXME
             PersonRepository repository = new PersonRepository()) {
            dbUtil.clear("person");
            repository.setConnection(dbUtil.getConnection());
            Person person1 = new Person("001", "PersonA", "男");
            Person person2 = new Person("002", "PersonB", "女");
            Person person3 = new Person("003", "PersonC", "男");
            repository.saveAll(Arrays.asList(person1, person2, person3));
            repository.queryAll().forEach(System.out::println);
        }
    }
}