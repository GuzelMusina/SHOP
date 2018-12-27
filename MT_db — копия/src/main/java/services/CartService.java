package services;

import models.Product;

import java.util.List;

/**
 * 26.12.2018
 * CartService
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
public interface CartService {

    List<Product> getProductInCart(String cookie);

    void delete(Long productId, String cookie);
}
