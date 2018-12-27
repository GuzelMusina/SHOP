package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Product;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import repository.*;
import services.LoginService;
import services.LoginServiceImpl;
import services.ShopService;
import services.ShopServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 26.12.2018
 * IndexServlet
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private AuthRepository authRepository;
    private CartRepository cartRepository;
    private ProductRepository productRepository;
    private ObjectMapper mapper = new ObjectMapper();
    private LoginService loginService;
    private ShopService shopService;

    @Override
    public void init() throws ServletException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("qwerty007");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/shop");
        UsersRepository usersRepository = new UsersRepositoryImpl(dataSource);
        authRepository = new AuthRepositoryImpl(dataSource);
        cartRepository = new CartRepositoryImpl(dataSource);
        productRepository = new ProductRepositoryImpl(dataSource);
        loginService = new LoginServiceImpl(authRepository, usersRepository);
        shopService = new ShopServiceImpl(loginService, cartRepository, productRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String query = req.getParameter("q");
        if (query == null) {
            req.getRequestDispatcher("ftl/index.ftl").forward(req, resp);
        } else if(query != null) {
            List<Product> result = shopService.search(query);
            String resultJson = mapper.writeValueAsString(result);
            resp.setStatus(200);
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.write(resultJson);
        }
    }
}
