package forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 04.12.2018
 * SignInForm
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInForm {
    private String name;
    private String email;
    private String password;
}
