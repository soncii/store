package org.example.dao;

import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO<K, T> {

    public abstract List<T> findAll();

    public abstract Optional<T> findEntityById(K id);

    public abstract boolean delete(K id);

    public abstract T insert(T entity);

    public abstract T update(T entity);

}
