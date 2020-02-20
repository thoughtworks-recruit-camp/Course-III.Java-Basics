package com.thoughtworks;

import java.util.Collection;

public interface Repository<E> {
    boolean save(String id, E entity);

    E get(String id);

    boolean delete(String id);

    boolean update(String id, E entity);

    Collection<E> list();
}
