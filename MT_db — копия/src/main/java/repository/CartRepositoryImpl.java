package repository;

import lombok.SneakyThrows;
import models.Cart;
import models.Product;
import models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;

import javax.sql.DataSource;
import java.util.List;

/**
 * 29.10.2018
 * CartRepositoryImpl
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
public class CartRepositoryImpl implements CartRepository {

    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_SELECT_CART_BY_OWNER_ID =
            "select * from cart where owner_id = ?;";

    //language=SQL
    private static final String SQL_INSERT_CART =
            "insert into cart(owner_id) values (?);";

    //language=SQL
    private static final String SQL_SELECT_CART_PRODUCTS =
            "select * from cart_product,product where cart_id = ? and product.id = product_id;";

    //language=SQL
    private static final String SQL_SELECT_COUNT_OF_ROWS =
            "SELECT count(*) FROM cart_product WHERE cart_id=? AND product_id=?;";

    //language=SQL
    private static final String SQL_SELECT_COUNT_OF_PRODUCTS =
            "SELECT count FROM cart_product WHERE cart_id=? AND product_id=?;";

    //language=SQL
    private static final String SQL_INSERT_INTO_CART_PRODUCT =
            "insert into cart_product (product_id, cart_id) values (?, ?);";

    //language=SQL
    private static final String SQL_UPDATE_COUNT_OF_PRODUCT =
            "UPDATE cart_product SET count = ? WHERE cart_id = ? and product_id = ?;";

    //language=SQL
    private static final String SQL_SELECT_PRODUCTS_FROM_CART =
            "SELECT id, name, cost, count FROM product p " +
                    "JOIN cart_product cp ON p.id = cp.product_id WHERE cp.cart_id = ?;";

    private RowMapper<Cart> cartRowMapper = (resultSet, i) -> Cart.builder()
            .id(resultSet.getLong("id"))
            .build();

    private RowMapper<Product> productRowMapper = (resultSet, i) -> Product.builder()
            .id(resultSet.getLong("product_id"))
            .name(resultSet.getString("name"))
            .cost(resultSet.getInt("cost"))
            .build();

    private RowMapper<Product> productWithCountRowMapper = (resultSet, i) -> Product.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .cost(resultSet.getInt("cost"))
            .count(resultSet.getInt("count"))
            .build();

    @SneakyThrows
    public CartRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Product> getProductList(Cart cart) {
        return jdbcTemplate.query(SQL_SELECT_PRODUCTS_FROM_CART, productWithCountRowMapper, cart.getId());

    }

    @Override
    public void addProduct(Product product, Cart cart) {
        Integer countOfRows = jdbcTemplate.queryForObject(SQL_SELECT_COUNT_OF_ROWS, Integer.TYPE, cart.getId(),
                product.getId());
        if (countOfRows == 0) {
            jdbcTemplate.update(SQL_INSERT_INTO_CART_PRODUCT, product.getId(), cart.getId());
        } else {
            Integer countOfProducts = jdbcTemplate.queryForObject(SQL_SELECT_COUNT_OF_PRODUCTS, Integer.TYPE, cart.getId(),
                    product.getId());
            countOfProducts++;
            jdbcTemplate.update(SQL_UPDATE_COUNT_OF_PRODUCT, countOfProducts, cart.getId(), product.getId());
        }
    }

    @Override
    public boolean isExistByUser(User cartOwner) {
        List<Cart> carts = jdbcTemplate.query(SQL_SELECT_CART_BY_OWNER_ID, cartRowMapper, cartOwner.getId());
        return carts.size() > 0;
    }

    @Override
    public void deleteProduct(Product product, Cart cart) {
        Integer countOfRows = jdbcTemplate.queryForObject(SQL_SELECT_COUNT_OF_ROWS, Integer.TYPE, cart.getId(),
                product.getId());
        if (countOfRows == 0) {
            System.out.println("Cart is block");
        } else {
            Integer countOfProducts = jdbcTemplate.queryForObject(SQL_SELECT_COUNT_OF_PRODUCTS, Integer.TYPE, cart.getId(),
                    product.getId());
            countOfProducts--;
            jdbcTemplate.update(SQL_UPDATE_COUNT_OF_PRODUCT, countOfProducts, cart.getId(), product.getId());
        }
    }

    @Override
    public Cart findByOwner(User cartOwner) {
        return jdbcTemplate.queryForObject(SQL_SELECT_CART_BY_OWNER_ID, cartRowMapper, cartOwner.getId());
    }

    @Override
    public void save(Cart model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT_CART, new String[]{"id"});
                    ps.setLong(1, model.getOwner().getId());
                    return ps;
                },
                keyHolder);

        model.setId(keyHolder.getKey().longValue());
    }


    @Override
    public void update(Cart model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Cart findOne(Long id) {
        return null;
    }

    @Override
    public List<Cart> findAll() {
        return null;
    }
}
