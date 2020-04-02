package com.thoughtworks.repository;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public abstract class BasicRepository<E> implements AutoCloseable {
    protected final Class<E> entityClass;
    protected Connection connection;
    protected final SqlFormatter<E> sqlFormatter;
    protected final DataUtil<E> dataUtil;

    @Override
    public void close() {
        setConnection(null);
    }

    @SuppressWarnings("unchecked")
    public BasicRepository(){
        this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        sqlFormatter = new SqlFormatter<>(entityClass);
        dataUtil = new DataUtil<>(entityClass);
    }

    public final void setConnection(Connection connection) {
        this.connection = connection;
    }

    public final void saveAll(List<E> entities) throws SQLException{
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

    public final void deleteByPk(String pk) throws SQLException {
        String deleteSql = sqlFormatter.deleteByPKSql(pk);
        try (PreparedStatement statement = connection.prepareStatement(deleteSql)) {
            statement.executeUpdate();
        }
    }

    public final void updateByPK(String pk, E entity) throws SQLException{
        String updateSql = sqlFormatter.updateByPKSql(pk);
        try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
            dataUtil.setValues(statement, entity);
            statement.executeUpdate();
        }
    }

    public final List<E> queryAll() throws SQLException{
        String queryAllSql = sqlFormatter.queryAllSql();
        try (PreparedStatement statement = connection.prepareStatement(queryAllSql);
             ResultSet resultSet = statement.executeQuery()) {
            return dataUtil.makeEntities(resultSet);
        }
    }

    public final Optional<E> queryByPK(String pk) throws SQLException {
        String queryAllSql = sqlFormatter.queryByPKSql(pk);
        try (PreparedStatement statement = connection.prepareStatement(queryAllSql);
             ResultSet resultSet = statement.executeQuery()) {
            return dataUtil.makeEntities(resultSet).stream().findFirst();
        }
    }

    public final Optional<E> queryByPKExclusive(String pk) throws SQLException {
        String queryAllSql = sqlFormatter.queryByPKExclusiveSql(pk);
        try (PreparedStatement statement = connection.prepareStatement(queryAllSql);
             ResultSet resultSet = statement.executeQuery()) {
            return dataUtil.makeEntities(resultSet).stream().findFirst();
        }
    }
}
