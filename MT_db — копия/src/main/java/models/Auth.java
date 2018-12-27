package models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 07.11.2018
 * Auth
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Auth {

    private Long id;
    private String cookieValue;
    private User user;
}