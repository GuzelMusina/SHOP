package services;

import forms.SignInForm;
import models.User;

import java.util.Optional;

/**
 * 04.12.2018
 * SignInService
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
public interface SignInService {
    void signIn(SignInForm signInForm);
}
