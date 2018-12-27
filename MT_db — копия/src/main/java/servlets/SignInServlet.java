package servlets;

import forms.LoginForm;
import forms.SignInForm;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import repository.AuthRepository;
import repository.AuthRepositoryImpl;
import repository.UsersRepository;
import repository.UsersRepositoryImpl;
import services.LoginServiceImpl;
import services.SignInService;
import services.SignInServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * 04.12.2018
 * SignInServlet
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
    // чтобы тебя увидеть
    private SignInService signInService;

    @Override
    public void init() throws ServletException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("qwerty007");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/shop");
        UsersRepository usersRepository = new UsersRepositoryImpl(dataSource);
        this.signInService = new SignInServiceImpl(usersRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("ftl/signin.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignInForm signInForm = SignInForm.builder()
                .name(req.getParameter("fullName"))
                .email(req.getParameter("name"))
                .password(req.getParameter("password"))
                .build();
        signInService.signIn(signInForm);
        resp.sendRedirect("/signin");
    }

}
