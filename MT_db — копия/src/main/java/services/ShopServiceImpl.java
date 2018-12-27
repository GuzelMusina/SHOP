package services;

import models.Cart;
import models.Product;
import models.User;
import repository.CartRepository;
import repository.ProductRepository;

import javax.servlet.http.Cookie;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 11.11.2018
 * ShopServiceImpl
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
public class ShopServiceImpl implements ShopService {

    private LoginService loginService;
    private CartRepository cartRepository;
    private ProductRepository productRepository;


    public ShopServiceImpl(LoginService loginService, CartRepository cartRepository, ProductRepository productRepository) {
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
    public void buy(Long productId, String cookie) {
        Product product = productRepository.findOne(productId);
        User cartOwner = loginService.getUserByCookie(cookie);
        Cart cart;
        if (!cartRepository.isExistByUser(cartOwner)) {
            cart = Cart.builder()
                    .owner(cartOwner)
                    .build();
            cartRepository.save(cart);
        } else {
            cart = cartRepository.findByOwner(cartOwner);
        }
        cartRepository.addProduct(product, cart);
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

    @Override
    public List<Product> search(String title) {
        return productRepository.findAllByTitleSearch(title);
    }


}
