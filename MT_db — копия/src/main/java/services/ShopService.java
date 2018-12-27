package services;

import models.Cart;
import models.Product;

import javax.servlet.http.Cookie;
import java.sql.SQLException;
import java.util.List;

/**
 * 11.11.2018
 * ShopService
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
public interface ShopService {
    List<Product> getProductInCart(String cookie);

    void buy(Long productId, String cookie);

    void delete(Long productId, String cookie);

    List<Product> search(String title);

}
