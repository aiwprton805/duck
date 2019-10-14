package com.magistr.duck.dao;

import java.util.Optional;

public interface CrudDao<ID, T> extends Dao<T> {

    void create(T entity);

    Optional<T> read(ID id);

    void update(T entity);

    void delete(ID id);
}
