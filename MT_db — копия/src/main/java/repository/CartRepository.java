package repository;

import models.Cart;
import models.Product;
import models.User;

import java.sql.SQLException;
import java.util.List;


/**
 * 29.10.2018
 * CartRepository
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
public interface CartRepository extends CrudRepository<Cart> {
    List<Product> getProductList(Cart cart);

    void addProduct(Product product, Cart cart);

    boolean isExistByUser(User cartOwner);

    void deleteProduct(Product product, Cart cart);

    Cart findByOwner(User cartOwner);
}
