package com.thoughtworks.repository;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class SqlFormatter<E> {
    private final String tableName;
    private final List<String> columnNames;
    private final String pKName;  // Currently only allow for String PK

    SqlFormatter(Class<E> entityClass) {
        tableName = Optional.ofNullable(entityClass.getAnnotation(Table.class))
                .map(Table::value)
                .orElse(toSqlIdentifier(entityClass.getSimpleName()));
        Function<Field, String> getColumnName = (Field field) ->
                Optional.ofNullable(field.getAnnotation(Column.class))
                        .map(Column::value)
                        .orElse(toSqlIdentifier(field.getName()));
        columnNames = Arrays.stream(entityClass.getDeclaredFields())
                .map(getColumnName)
                .collect(Collectors.toList());
        pKName = Arrays.stream(entityClass.getDeclaredFields())
                .filter(field -> field.getAnnotation(PrimaryKey.class) != null)
                .map(getColumnName)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Primary key not defined"));
    }

    public String insertSql() {
        return String.format("INSERT INTO %s(%s) VALUE (%s)",
                tableName,
                getJoinedColumns(),
                getPlaceholders());
    }

    public String deleteByPKSql(String pk) {
        return String.format("DELETE FROM %s WHERE %s = '%s'",
                tableName,
                pKName,
                pk);
    }

    public String updateByPKSql(String pk) {
        return String.format("UPDATE %s SET %s WHERE %s = '%s'",
                tableName,
                getUpdateColumns(),
                pKName,
                pk
        );
    }

    public String queryAllSql() {
        return String.format("SELECT %s FROM %s",
                getJoinedColumns(),
                tableName);
    }

    public String queryByPKSql(String pk) {
        return String.format("SELECT %s FROM %s WHERE %s = '%s'",
                getJoinedColumns(),
                tableName,
                pKName,
                pk);
    }

    public String queryByPKExclusiveSql(String pk) {
        return String.format("SELECT %s FROM %s WHERE %s = '%s' FOR UPDATE",
                getJoinedColumns(),
                tableName,
                pKName,
                pk);
    }

    private static String toSqlIdentifier(String javaIdentifier) {
        String result = javaIdentifier
                .chars()
                .mapToObj(c -> c > 44 && c < 91  // A-Z?
                        ? "_" + (char) (c + 32)  // -> _a-_z
                        : "" + (char) c
                ).collect(Collectors.joining());
        if (result.startsWith("_")) {
            return result.substring(1); // Clip first "_"
        }
        return result;
    }

    private String getJoinedColumns() {
        return String.join(", ", columnNames);
    }

    public String getPlaceholders() {
        return String.join(", ", Collections.nCopies(columnNames.size(), "?"));
    }

    private String getUpdateColumns() {
        return columnNames.stream()
                .map(col -> col + "=?")
                .collect(Collectors.joining(", "));
    }
}
