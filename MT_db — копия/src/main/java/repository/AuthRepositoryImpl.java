package repository;

import models.Auth;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
/**
 * 07.11.2018
 * AuthRepositoryImpl
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
public class AuthRepositoryImpl implements AuthRepository {

    //language=SQL
    private static final String SQL_INSERT =
            "insert into auth(user_id, cookie_value) values (?, ?)";

    //language=SQL
    private static final String SQL_SELECT_BY_COOKIE_VALUE =
            "select * from auth where cookie_value = ?";

    private JdbcTemplate template;

    public AuthRepositoryImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private RowMapper<Auth> authRowMapper = (rs, rowNum) -> Auth.builder()
            .id(rs.getLong("user_id"))
            .cookieValue(rs.getString("cookie_value"))
            .build();

    @Override
    public void save(Auth model) {
        template.update(SQL_INSERT, model.getUser().getId(), model.getCookieValue());

    }

    @Override
    public void update(Auth model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Auth findOne(Long id) {
        return null;
    }

    @Override
    public List<Auth> findAll() {
        return null;
    }

    @Override
    public Optional<Auth> findByCookieValue(String cookieValue) {
        try {
            return Optional.of(template.queryForObject(SQL_SELECT_BY_COOKIE_VALUE, authRowMapper, cookieValue));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Auth> findCurrent() {
        return Optional.empty();
    }
}
