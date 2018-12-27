package repository;

import java.sql.SQLException;
import java.util.List;

/**
 * 17.10.2018
 * CrudRepository
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
public interface CrudRepository<T> {
    void save(T model);
    void update(T model);
    void delete(Long id);
    T findOne(Long id);
    List<T> findAll();
}
