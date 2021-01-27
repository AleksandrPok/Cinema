package cinema.dao;

import java.util.List;

public interface GenericDao<T> {
    T add(T value);

    List<T> getAll();
}
