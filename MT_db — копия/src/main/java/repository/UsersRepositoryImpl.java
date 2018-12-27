package repository;

import models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import javax.sql.DataSource;
/**
 * 11.11.2018
 * UsersRepositoryImpl
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
public class UsersRepositoryImpl implements UsersRepository {
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_SELECT_USER_BY_ID =
            "select * from users where id = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL_USERS =
            "select * from users";

    //language=SQL
    private static final String SQL_INSERT_USER =
            "insert into users(name, email, passwordhash) values (?,?,?)";

    //language=SQL
    private static final String SQL_SELECT_BY_EMAIL=
            "select * from users where email = ?";

    private RowMapper<User> userRowMapper = (resultSet, i) -> User.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .email(resultSet.getString("email"))
            .passwordHash(resultSet.getString("passwordHash"))
            .build();

    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public User findByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL,userRowMapper,email);

    }

    @Override
    public void save(User model) {
        jdbcTemplate.update(SQL_INSERT_USER, model.getName(), model.getEmail(),model.getPasswordHash());
    }

    @Override
    public void update(User model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User findOne(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID,
                userRowMapper, id);
    }

    @Override
    public List findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_USERS, userRowMapper);

    }
}
