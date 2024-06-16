package TurneraMedicaTP;
import java.util.List;

public interface Service<T> {
    void create(T entity) throws ServiceException;
    T get(String id) throws ServiceException;
    void update(T entity) throws ServiceException;
    void delete(String id) throws ServiceException;
    List<T> getAll() throws ServiceException;
}