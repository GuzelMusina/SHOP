package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Cart;
import models.Product;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import repository.*;
import services.LoginService;
import services.LoginServiceImpl;
import services.ShopService;
import services.ShopServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * 17.10.2018
 * ShopServlet
 *
 * @author Guzel Musina (ITIS)
 * @version v1.0
 */
@WebServlet("/shop")
public class ShopServlet extends HttpServlet {

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
        String action = req.getParameter("list");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String query = req.getParameter("q");
        if (action == null && query == null) {
            req.getRequestDispatcher("ftl/shop.ftl").forward(req, resp);
        } else if (action != null) {
            List<Product> products = shopService.getProductInCart(getAuthCookie(req));
            PrintWriter writer = resp.getWriter();
            String json = mapper.writeValueAsString(products);
            writer.write(json);
            resp.setContentType("application/json");
            resp.setStatus(200);
        } else if (query != null) {
            List<Product> result = shopService.search(query);
            String resultJson = mapper.writeValueAsString(result);
            resp.setStatus(200);
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.write(resultJson);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long productId = Long.valueOf(req.getParameter("product_id"));
        String cookieValue = getAuthCookie(req);

        switch (req.getParameter("action")) {
            case "delete":
                shopService.delete(productId, cookieValue);
                break;
            case "buy":
                shopService.buy(productId, cookieValue);
                break;
        }

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        resp.setStatus(200);
        System.out.println("Out from servlet");
    }

    private String getAuthCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            cookies = new Cookie[0];
        }
        String cookieValue = "";

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("AuthFilter")) {
                cookieValue = cookie.getValue();
            }
        }
        return cookieValue;
    }
}
