package com.thoughtworks.repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class SqlFormatter<E> {
    private final String tableName;
    private final List<String> columnNames;

    SqlFormatter(Class<E> entityClass) {
        this.tableName = entityClass.getAnnotation(Table.class).value();
        this.columnNames = Arrays.stream(entityClass.getDeclaredFields())
                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                        .map(Column::value)
                        .orElse(field.getName()))
                .collect(Collectors.toList());
    }

    public String truncateSql() {
        return String.format("TRUNCATE %s", tableName);
    }

    public String insertSql() {
        return String.format("INSERT INTO %s(%s) VALUE (%s)",
                tableName,
                String.join(", ", columnNames),
                String.join(", ", Collections.nCopies(columnNames.size(), "?")));
    }

    public String deleteSql(String id) {
        return String.format("DELETE FROM %s WHERE id = %s",
                tableName, id);
    }

    public String updateSql(String id) {
        return String.format("UPDATE %s SET %s WHERE id =%s",
                tableName,
                columnNames.stream().map(col -> col + "=?").collect(Collectors.joining(", ")),
                id);
    }

    public String queryAllSql() {
        return String.format("SELECT %s FROM %s",
                String.join(", ", columnNames),
                tableName);
    }
}
