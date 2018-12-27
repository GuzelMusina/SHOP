package repository;

import models.Auth;

import java.util.Optional;

/**
 * 07.11.2018
 * AuthRepository
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
public interface AuthRepository extends CrudRepository<Auth> {
    Optional<Auth> findByCookieValue(String cookieValue);
    Optional<Auth> findCurrent();
}
