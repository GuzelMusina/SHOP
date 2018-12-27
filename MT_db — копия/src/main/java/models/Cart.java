package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.util.ArrayList;
import java.util.List;

/**
 * 17.10.2018
 * Cart
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Long id;
    private User owner;
    private List<Product> products = new ArrayList<>();

    public void add(Product product){
        products.add(product);
    }
    public void delete(Product product){
        products.remove(product);
    }
}
