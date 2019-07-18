package controller;

import dao.impl.ProductDaoImpl;
import factory.service.CartServiceFactory;
import factory.service.ProductServiceFactory;
import factory.service.UserServiceFactory;
import model.Product;
import model.User;
import org.apache.log4j.Logger;
import service.CartService;
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
    private static final CartService cartService = CartServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Optional<User> currentUser = Optional.ofNullable((User)
                                         request.getSession().getAttribute("user"));
        if (currentUser.isPresent()) {
            String id = request.getParameter("productID");
            Optional<Product> additionalProduct = productService.getProductById(Long.parseLong(id));
            if (additionalProduct.isPresent()) {
                LOGGER.info("Try to add " + additionalProduct.get()
                        + "to "
                        + currentUser.get() + " cart... \n");
                cartService.addCartProduct(additionalProduct.get());
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
