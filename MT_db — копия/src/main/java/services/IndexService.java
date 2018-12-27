package services;

import models.Product;

import java.util.List;

/**
 * 26.12.2018
 * IndexService
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
public interface IndexService {
    List<Product> search(String title);
}
