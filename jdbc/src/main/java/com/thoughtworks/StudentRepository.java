package com.thoughtworks;

import com.thoughtworks.repository.BasicRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentRepository extends BasicRepository<Student> {
    public StudentRepository() {
        super(Student.class);
    }

    public List<Student> queryByClassIdOrderByIdDesc(String classId) throws SQLException {
        String sql = String.format("%s WHERE class=%s ORDER BY id DESC", sqlFormatter.queryAllSql(), classId);
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        return dataUtil.makeEntities(resultSet);
    }
}
