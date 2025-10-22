package Services;

import java.util.List;

public interface IDAO {

    <T> List<T> getAll(String nameQuery,Class<T> clazz);

    <T> void insert(T entity);

    <T> void update(T entity);

    <T> void delete(T entity);

    <T> T findById(Object id,Class<T> clazz);
}
