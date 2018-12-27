package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import repository.AuthRepository;
import repository.AuthRepositoryImpl;
import repository.UsersRepository;
import repository.UsersRepositoryImpl;
import services.LoginService;
import services.LoginServiceImpl;

/**
 * 07.11.2018
 * AuthFilter
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */

@WebFilter("/shop")
public class AuthFilter implements Filter {

    private LoginService service;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("qwerty007");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/shop");
        UsersRepository usersRepository = new UsersRepositoryImpl(dataSource);
        AuthRepository authRepository = new AuthRepositoryImpl(dataSource);
        this.service = new LoginServiceImpl(authRepository, usersRepository);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Cookie cookies[] = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("AuthFilter")) {
                    if (service.isExistByCookie(cookie.getValue())) {
                        chain.doFilter(request, response);
                        return;
                    }
                }
            }
            response.sendRedirect("/login");
            return;
        }
        response.sendRedirect("/login");
        return;
    }

    @Override
    public void destroy() {

    }
}
