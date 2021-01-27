package cinema.service;

import java.util.List;

public interface GenericService<T> {
    T add(T value);

    List<T> getAll();
}
