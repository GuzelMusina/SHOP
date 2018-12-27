package services;

import forms.SignInForm;
import models.Auth;
import models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repository.UsersRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * 04.12.2018
 * SignInServiceImpl
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
public class SignInServiceImpl implements SignInService {
    private UsersRepository usersRepository;
    private BCryptPasswordEncoder encoder;

    public SignInServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.encoder = new BCryptPasswordEncoder();
    }


    @Override
    public void signIn(SignInForm signInForm) {
        User newUser = User.builder()
                .name(signInForm.getName())
                .email(signInForm.getEmail())
                .passwordHash(encoder.encode(signInForm.getPassword()))
                .build();
        usersRepository.save(newUser);
    }
}
