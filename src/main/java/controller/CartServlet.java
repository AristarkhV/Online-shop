package controller;

import factory.MailServiceFactory;
import factory.OrderServiceFactory;
import model.Code;
import model.Order;
import model.User;
import service.MailService;
import service.OrderService;
import util.RandomHelper;

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

    private static final MailService mailService = MailServiceFactory.getInstance();
    private static final OrderService orderService = OrderServiceFactory.getInstance();

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
        String email = request.getParameter("email");

        request.getRequestDispatcher("/orderPayment.jsp").forward(request, response);
        if (deliveryAddress.isEmpty() || email.isEmpty()) {
            request.setAttribute("error", "Empty fields :(");
            request.setAttribute("order", userFromSession.get().getUserCart().getUserProducts());
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
        } else {
            Order userOrder = new Order(userFromSession.get(),
                    deliveryAddress,
                    userFromSession.get().getUserCart().getUserProducts());
            orderService.addOrder(userOrder);
            userFromSession.get().setOrder(userOrder);
            String sendCnfirmCode = RandomHelper.generateCode();
            HttpSession session = request.getSession();
            Code code = new Code(sendCnfirmCode);
            mailService.sendConfirmCode(code, email);
            session.setAttribute("code", code.getCode());
            request.getRequestDispatcher("/orderPayment.jsp").forward(request, response);
        }
    }
}
