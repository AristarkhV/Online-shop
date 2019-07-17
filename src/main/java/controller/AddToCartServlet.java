package controller;

import dao.impl.ProductDaoImpl;
import factory.ProductServiceFactory;
import factory.UserServiceFactory;
import model.Product;
import model.User;
import org.apache.log4j.Logger;
import service.ProductService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/store/add/cart")
public class AddToCartServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ProductDaoImpl.class);
    private ProductService productService = ProductServiceFactory.getInstance();
    private UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Optional<User> userFromSession = Optional.ofNullable((User) request.getSession().getAttribute("user"));
        if (userFromSession.isPresent()) {
            String id = request.getParameter("productId");
            Optional<Product> addProduct = productService.getProductById(Long.parseLong(id));
            if (addProduct.isPresent()) {
                LOGGER.info("Try to add " + addProduct.get()
                        + "to "
                        + userFromSession.get() + " cart... \n");
                userService.addProductToCart(addProduct.get(), userFromSession.get());
                response.sendRedirect("/store");
            } else {
                LOGGER.info("Product not found  \n" + id);
            }
        } else {
            request.setAttribute("message", "Please, sign in");
            request.getRequestDispatcher("/store.jsp");
            LOGGER.info("User not found  \n");
        }
    }
}
