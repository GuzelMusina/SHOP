package services;

import models.Product;
import repository.ProductRepository;

import java.util.List;

/**
 * 26.12.2018
 * IndexServiceImpl
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
public class IndexServiceImpl implements IndexService {
    private ProductRepository productRepository;

    public IndexServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> search(String title) {
        return productRepository.findAllByTitleSearch(title);
    }
}
