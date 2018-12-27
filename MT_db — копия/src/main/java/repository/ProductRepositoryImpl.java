package repository;

import models.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
/**
 * 11.11.2018
 * ProductRepositoryImpl
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
public class ProductRepositoryImpl implements ProductRepository {
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_SELECT_PRODUCT_BY_ID =
            "select * from product where id = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL_PRODUCTS =
            "select * from product";

    //language=SQL
    private static final String SQL_INSERT_PRODUCT =
            "insert into product(name,cost) values (?,?)";

    //language=SQL
    private static final String SQL_UPDATE_PRODUCT =
            "update product set(name,cost) = (?,?) where id = ?";
    //language=SQL
    private static final String SQL_DELETE_PRODUCT =
            "DELETE * FROM product where id=?";
    //language=SQL
    private static final String SQL_SEARCH=
            "SELECT * from product WHERE product.name ilike ?";

    private RowMapper<Product> productRowMapper = (resultSet, i) -> Product.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .cost(resultSet.getInt("cost"))
            .build();

    public ProductRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Product model) {
        jdbcTemplate.update(SQL_INSERT_PRODUCT, productRowMapper, model.getName(), model.getCost());

    }

    @Override
    public void update(Product model) {

    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_PRODUCT,productRowMapper, id);
    }

    @Override
    public Product findOne(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_PRODUCT_BY_ID, productRowMapper, id);

    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_PRODUCTS, productRowMapper);

    }

    @Override
    public List<Product> findAllByTitleSearch(String title) {
        return jdbcTemplate.query(SQL_SEARCH, productRowMapper, "%" + title + "%");

    }
}
