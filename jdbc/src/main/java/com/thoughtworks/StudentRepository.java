package com.thoughtworks;

import com.thoughtworks.repository.BasicRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentRepository extends BasicRepository<Student> {
    public List<Student> queryByClassIdOrderByIdDesc(String classId) throws SQLException {
        String sql = String.format("%s WHERE class=? ORDER BY id DESC", sqlFormatter.queryAllSql());
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, classId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return dataUtil.makeEntities(resultSet);
            }
        }
    }
}
