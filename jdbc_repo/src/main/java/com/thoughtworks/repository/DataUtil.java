package com.thoughtworks.repository;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class DataUtil<E> {
    private final Class<E> entityClass;
    private final Method[] getters;
    private final Method[] setters;

    public DataUtil(Class<E> entityClass) {
        this.entityClass = entityClass;
        getters = getGetters();
        setters = getSetters();
    }

    @SneakyThrows({InvocationTargetException.class, IllegalAccessException.class})
    public void setValues(PreparedStatement statement, E entity) throws SQLException {
        for (int i = 0, len = getters.length; i < len; i++) {
            Object data = getters[i].invoke(entity);
            if (data.getClass() == Date.class) {
                statement.setDate(i + 1, new java.sql.Date(((Date) data).getTime()));
            } else {
                statement.setObject(i + 1, data);
            }
        }
    }

    public List<E> makeEntities(ResultSet resultSet) throws SQLException {
        List<E> resultList = new ArrayList<>();
        while (resultSet.next()) {
            resultList.add(makeEntity(resultSet));
        }
        return resultList;
    }

    @SneakyThrows({InvocationTargetException.class, IllegalAccessException.class})
    private E makeEntity(ResultSet resultSet) throws SQLException {
        E entity = getEmptyEntity();
        for (int i = 0, len = setters.length; i < len; i++) {
            setters[i].invoke(entity, resultSet.getObject(i + 1));
        }
        return entity;
    }

    @SneakyThrows({InvocationTargetException.class, IllegalAccessException.class, NoSuchMethodException.class, InstantiationException.class})
    private E getEmptyEntity() {
        return entityClass.getConstructor().newInstance();
    }

    @SneakyThrows({NoSuchMethodException.class})
    private Method[] getGetters() {
        Field[] fields = entityClass.getDeclaredFields();
        Method[] getters = new Method[fields.length];
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String prefix = getGetterPrefix(field);
            String getterName = getAccessorName(field.getName(), prefix);
            getters[i] = entityClass.getMethod(getterName);
        }
        return getters;
    }

    @SneakyThrows({NoSuchMethodException.class})
    private Method[] getSetters() {
        Field[] fields = entityClass.getDeclaredFields();
        Method[] setters = new Method[fields.length];
        for (int i = 0; i < fields.length; i++) {
            String setterName = getAccessorName(fields[i].getName(), "set");
            setters[i] = entityClass.getMethod(setterName, Object.class);
        }
        return setters;
    }

    private static String getGetterPrefix(Field field) {
        String prefix;
        if (field.getType().equals(boolean.class) || field.getType().equals(Boolean.class)) {
            prefix = "is";
        } else {
            prefix = "get";
        }
        return prefix;
    }

    private static String getAccessorName(String fieldName, String prefix) {
        return prefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }
}
