package com.thoughtworks;

import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@NoArgsConstructor
public class StudentRepository {
    private static final String INSERT_INTO = "INSERT INTO student(id, name, gender, entrance_year, birthday, class)";
    private static final String VALUE_TEMPLATE = "(?,?,?,?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM student";
    private static final String UPDATE_BY_ID = "UPDATE student SET id=?, name=?, gender=?, entrance_year=?, birthday=?, class=? WHERE id =?";
    private static final String DELETE_BY_ID = "DELETE FROM student WHERE id =?";
    private Connection connection;


    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void clear() throws SQLException {
        connection.prepareStatement("TRUNCATE student").execute();
    }

    public void save(List<Student> students) throws SQLException {
        String valuesString = IntStream.range(0, students.size())
                .mapToObj(i -> VALUE_TEMPLATE)
                .collect(Collectors.joining(","));
        String sql = String.format("%s VALUES %s", INSERT_INTO, valuesString);
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            int offset = i * 6;
            setStudentValue(statement, student, offset);
        }
        statement.execute();
    }

    public void save(Student student) throws SQLException {
        String sql = String.format("%s VALUE %s", INSERT_INTO, VALUE_TEMPLATE);
        PreparedStatement statement = connection.prepareStatement(sql);
        setStudentValue(statement, student, 0);
        statement.execute();
    }

    public List<Student> queryAll() throws SQLException {
        return getStudentsFromQuery(connection.prepareStatement(SELECT_ALL));
    }

    public List<Student> queryByClassIdOrderByIdDesc(String classId) throws SQLException {
        String sql = String.format("%s WHERE class=? ORDER BY id DESC", SELECT_ALL);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, classId);
        return getStudentsFromQuery(statement);
    }

    public void update(String id, Student student) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_BY_ID);
        statement.setString(1, student.getId());
        statement.setString(2, student.getName());
        statement.setString(3, student.getGender());
        statement.setInt(4, student.getEntranceYear());
        statement.setDate(5, new Date(student.getBirthday().getTime()));
        statement.setString(6, student.getClassId());
        statement.setString(7, id);
        statement.execute();
    }

    public void delete(String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);
        statement.setString(1, id);
        statement.execute();
    }

    private List<Student> getStudentsFromQuery(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        List<Student> resultList = new ArrayList<>();
        while (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getString("id"));
            student.setName(resultSet.getString("name"));
            student.setGender(resultSet.getString("gender"));
            student.setEntranceYear(resultSet.getInt("entrance_year"));
            student.setBirthday(resultSet.getDate("birthday"));
            student.setClassId(resultSet.getString("class"));
            resultList.add(student);
        }
        return resultList;
    }

    private void setStudentValue(PreparedStatement statement, Student student, int offset) throws SQLException {
        statement.setString(offset + 1, student.getId());
        statement.setString(offset + 2, student.getName());
        statement.setString(offset + 3, student.getGender());
        statement.setInt(offset + 4, student.getEntranceYear());
        statement.setDate(offset + 5, new Date(student.getBirthday().getTime()));
        statement.setString(offset + 6, student.getClassId());
    }
}
