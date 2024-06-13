package TurneraMedicaTP;

import java.util.List;

public interface DAO<T> {
    void create(T entity) throws Exception;
    T get(String id) throws Exception;
    void update(T entity) throws Exception;
    void delete(String id) throws Exception;
    List<T> getAll() throws Exception;
}
