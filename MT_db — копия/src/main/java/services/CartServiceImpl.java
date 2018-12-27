package services;

import models.Cart;
import models.Product;
import models.User;
import repository.CartRepository;
import repository.ProductRepository;

import java.util.List;

/**
 * 26.12.2018
 * CartServiceImpl
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
public class CartServiceImpl implements CartService {
    private LoginService loginService;
    private CartRepository cartRepository;
    private ProductRepository productRepository;


    public CartServiceImpl(LoginService loginService, CartRepository cartRepository, ProductRepository productRepository) {
        this.loginService = loginService;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProductInCart(String cookie) {
        User cartOwner = loginService.getUserByCookie(cookie);
        Cart cart = cartRepository.findByOwner(cartOwner);
        return cartRepository.getProductList(cart);
    }

    @Override
    public void delete(Long productId, String cookie) {
        Product product = productRepository.findOne(productId);
        User cartOwner = loginService.getUserByCookie(cookie);
        Cart cart;
        if(cartRepository.isExistByUser(cartOwner)){
            cart = cartRepository.findByOwner(cartOwner);
            cartRepository.deleteProduct(product, cart);
        }else{
            System.out.println("Cart is block");
        }
    }
}
