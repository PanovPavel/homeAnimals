package org.works.service;

import java.util.List;

public interface Service<T> {
    List<T> getAll();
    T get(int id);
    void saveOrUpdate(T t);
    void delete(int id);
}
