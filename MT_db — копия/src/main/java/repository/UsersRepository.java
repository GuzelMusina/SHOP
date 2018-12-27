package repository;

import models.User;

/**
 * 11.11.2018
 * UsersRepository
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
public interface UsersRepository extends CrudRepository<User> {
    User findByEmail(String email);
}
