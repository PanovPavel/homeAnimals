package org.works.dao;

import java.util.List;
import java.util.Optional;

/**
 * @author PanovPavel
 */
public interface Dao<T> {
    List<T> getAll();
    T get(int id);
    void saveOrUpdate(T t);
    void delete(int id);
}
