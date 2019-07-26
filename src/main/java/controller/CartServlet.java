package controller;

import factory.service.CartServiceFactory;
import factory.service.MailServiceFactory;
import factory.service.OrderServiceFactory;
import model.Cart;
import model.Code;
import model.Order;
import model.User;
import service.CartService;
import service.MailService;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/store/cart")
public class CartServlet extends HttpServlet {

    private static final CartService cartService = CartServiceFactory.getInstance();
    private static final OrderService orderService = OrderServiceFactory.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Optional<User> user = Optional.ofNullable((User) request.getSession().getAttribute("user"));

        if (user.isPresent()) {
            if (cartService.getCart(user.get()).isPresent()) {
                request.setAttribute("check", 1);
                request.setAttribute("order", cartService.getCart(user.get()).get().getProducts());
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Empty cart :(");
                request.setAttribute("check", 0);
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Sign in, please");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Optional<User> user = Optional.ofNullable((User) request.getSession().getAttribute("user"));
        if (user.isPresent()) {
            Optional<Cart> cart = cartService.getCart(user.get());
            if (cart.isPresent() && !cart.get().getProducts().isEmpty()) {
                String deliveryAddress = request.getParameter("delivery");
                String email = request.getParameter("email");
                if (deliveryAddress.isEmpty() || email.isEmpty()) {
                    request.setAttribute("error", "Empty fields :(");
                    request.setAttribute("flag", 1);
                    request.setAttribute("order", cart.get().getProducts());
                    request.getRequestDispatcher("/cart.jsp").forward(request, response);
                } else {
                    orderService.createOrder(user.get(), email, deliveryAddress, cart.get().getProducts());
                    Optional<Order> order = orderService.getUserOrder(user.get());
                    if (order.isPresent()) {
                        orderService.sendConfirmationCode(email, order.get().getOrderID());
                        request.setAttribute("email", email);
                    }
                    request.getRequestDispatcher("/orderPayment.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Empty cart :(");
                request.setAttribute("flag", 0);
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
            }
        }
    }
}
