package repository;

import models.Product;

import java.util.List;

/**
 * 11.11.2018
 * ProductRepository
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
public interface ProductRepository extends CrudRepository<Product>{
    List<Product> findAllByTitleSearch(String title);
}