package com.thoughtworks;

import com.thoughtworks.repository.BasicRepository;
import lombok.NoArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@NoArgsConstructor
public class StudentRepositoryNew extends BasicRepository<Student> {
    public List<Student> queryByClassIdOrderByIdDesc(String classId) throws SQLException {
        String sql = String.format("%s WHERE class='%s' ORDER BY id DESC", sqlFormatter.queryAllSql(), classId);
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            return dataUtil.makeEntities(resultSet);
        }
    }

    public void delete(String id) throws SQLException {
        deleteByPk(id);
    }

    public void update(String id, Student student) throws SQLException {
        updateByPK(id, student);
    }
}
