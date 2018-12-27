package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.soap.SAAJResult;

/**
 * 17.10.2018
 * Product
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private Long id;
    private String name;
    private Integer cost;
    private Integer count;
}
