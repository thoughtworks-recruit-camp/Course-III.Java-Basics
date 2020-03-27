package com.thoughtworks.repository;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BasicRepository<E> {

    protected final Class<E> entityClass;
    protected Connection connection;
    protected final SqlFormatter<E> sqlFormatter;
    protected final DataUtil<E> dataUtil;

    public BasicRepository() {
        this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        sqlFormatter = new SqlFormatter<>(entityClass);
        dataUtil = new DataUtil<>(entityClass);
    }

    public final void setConnection(Connection connection) {
        this.connection = connection;
    }

    public final void clear() throws SQLException {
        String truncateSql = sqlFormatter.truncateSql();
        try (PreparedStatement statement = connection.prepareStatement(truncateSql)) {
            statement.execute();
        }
    }

    public final void saveAll(List<E> entities) throws SQLException {
        for (E entity : entities) {
            save(entity);
        }
    }

    public final void save(E entity) throws SQLException {
        String insertSql = sqlFormatter.insertSql();
        try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
            dataUtil.setValues(statement, entity);
            statement.executeUpdate();
        }
    }

    public final void delete(String id) throws SQLException {
        String deleteSql = sqlFormatter.deleteSql(id);
        try (PreparedStatement statement = connection.prepareStatement(deleteSql)) {
            statement.executeUpdate();
        }
    }

    public final void update(String id, E entity) throws SQLException {
        String updateSql = sqlFormatter.updateSql(id);
        try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
            dataUtil.setValues(statement, entity);
            statement.executeUpdate();
        }
    }

    public final List<E> queryAll() throws SQLException {
        String queryAllSql = sqlFormatter.queryAllSql();
        try (PreparedStatement statement = connection.prepareStatement(queryAllSql);
             ResultSet resultSet = statement.executeQuery()) {
            return dataUtil.makeEntities(resultSet);
        }
    }
}
