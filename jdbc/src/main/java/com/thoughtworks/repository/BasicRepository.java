package com.thoughtworks.repository;

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

    public BasicRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
        sqlFormatter = new SqlFormatter<>(entityClass);
        dataUtil = new DataUtil<>(entityClass);
    }

    public final void setConnection(Connection connection) {
        this.connection = connection;
    }

    public final void clear() throws SQLException {
        String truncateSql = sqlFormatter.truncateSql();
        PreparedStatement statement = connection.prepareStatement(truncateSql);
        statement.execute();
    }

    public final void saveAll(List<E> entities) throws SQLException {
        for (E entity : entities) {
            save(entity);
        }
    }

    public final void save(E entity) throws SQLException {
        String insertSql = sqlFormatter.insertSql();
        PreparedStatement statement = connection.prepareStatement(insertSql);
        dataUtil.setValues(statement, entity);
        statement.execute();
    }

    public final void delete(String id) throws SQLException {
        String deleteSql = sqlFormatter.deleteSql(id);
        PreparedStatement statement = connection.prepareStatement(deleteSql);
        statement.execute();
    }

    public final void update(String id, E entity) throws SQLException {
        String updateSql = sqlFormatter.updateSql(id);
        PreparedStatement statement = connection.prepareStatement(updateSql);
        dataUtil.setValues(statement, entity);
        statement.execute();
    }

    public final List<E> queryAll() throws SQLException {
        String queryAllSql = sqlFormatter.queryAllSql();
        PreparedStatement statement = connection.prepareStatement(queryAllSql);
        ResultSet resultSet = statement.executeQuery();
        return dataUtil.makeEntities(resultSet);
    }
}
