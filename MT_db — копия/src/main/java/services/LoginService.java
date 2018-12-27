package services;

import forms.LoginForm;
import models.User;

import java.sql.SQLException;
import java.util.Optional;

/**
 * 11.11.2018
 * LoginService
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
public interface LoginService {
    Optional<String> login(LoginForm loginForm);
    boolean isExistByCookie(String cookieValue);
    User getUserByCookie(String cookie);
}
