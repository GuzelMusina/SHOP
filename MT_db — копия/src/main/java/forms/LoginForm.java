package forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 11.11.2018
 * LoginForm
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {
    private String email;
    private String password;
}