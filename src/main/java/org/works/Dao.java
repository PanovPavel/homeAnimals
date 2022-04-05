package org.works;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    List<T> getAll();
    T get(int id);
    void save(T t);
    void delete(T t);

}
