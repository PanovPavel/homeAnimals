package org.works.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    List<T> getAll();
    T get(int id);
    void saveOrUpdate(T t);
    void delete(int id);

}
