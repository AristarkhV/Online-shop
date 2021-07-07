package controller;

import dao.impl.ProductDaoImpl;
import factory.service.CartServiceFactory;
import factory.service.ProductServiceFactory;
import model.Cart;
import model.Product;
import model.User;
import org.apache.log4j.Logger;
import service.CartService;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@WebServlet("/store/add/cart")
public class AddToCartServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ProductDaoImpl.class);
    private ProductService productService = ProductServiceFactory.getInstance();
    private static final CartService cartService = CartServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Optional<User> user = Optional.ofNullable((User) request.getSession().getAttribute("user"));
        if (user.isPresent() && user.get().getRole().getRole().equals("user")) {
            String id = request.getParameter("productID");
            Optional<Cart> cart = cartService.getCart(user.get());
            Optional<Product> product = productService.getProductById(Long.parseLong(id));
            if (product.isPresent()) {
                if (!cart.isPresent()){
                    cartService.addCart(new Cart(new ArrayList<Product>(), user.get()));
                    cart = cartService.getCart(user.get());
                }
                logger.info("Try to add " + product.get()
                        + "to "
                        + user.get() + " cart... \n");
                cartService.addProductToCart(cart.get(), product.get());
                response.sendRedirect("/store");
            } else {
                logger.info("Product not found  \n" + id);
            }
        } else {
            request.setAttribute("message", "Please, sign in as user");
            response.sendRedirect("/store");
            logger.info("User not found  \n");
        }
    }
}
