package controller;

import model.Order;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/store/cart")
public class CartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<User> userFromSession = Optional.ofNullable((User)
                request.getSession().getAttribute("user"));
        if (userFromSession.isPresent()) {
            request.setAttribute("order", userFromSession.get().getUserCart().getUserProducts());
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Sign in, please");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Optional<User> userFromSession = Optional.ofNullable((User)
                request.getSession().getAttribute("user"));

        String deliveryAddress = request.getParameter("delivery");
        if (deliveryAddress.isEmpty()) {
            request.setAttribute("error", "Empty fields :(");
            request.setAttribute("order", userFromSession.get().getUserCart().getUserProducts());
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
        } else {
            Order userOrder = new Order(userFromSession.get().getUserID());
            userOrder.setDeliveryAddress(deliveryAddress);
            userFromSession.get().setOrder(userOrder);
            request.getRequestDispatcher("/orderPayment.jsp").forward(request, response);
        }
    }
}
