package controller;

import factory.service.CartServiceFactory;
import factory.service.CodeServiceFatory;
import model.Cart;
import model.Code;
import model.Order;
import model.User;
import service.CartService;
import service.CodeService;
import service.serviceImpl.CodeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/order/payment")
public class OrderPaymentServlet extends HttpServlet {

    private static final CodeService codeService = CodeServiceFatory.getInstance();
    private static final CartService cartService = CartServiceFactory.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Optional<User> user = Optional.ofNullable((User) request.getSession().getAttribute("user"));
        String email = request.getParameter("email");
        Optional<Code> code = codeService.getCode(email);
        String confirm = request.getParameter("confCode");
        if (code.isPresent() && code.get().getCode().equals(confirm)) {
            request.setAttribute("message", "Don't worry go to sleep :)");
            Cart cart = cartService.getCart(user.get()).get();
            cartService.cleanCart(cart);
        } else {
            request.setAttribute("message", "Invalid code, try again");
            request.setAttribute("email", email);
        }
        request.getRequestDispatcher("/orderPayment.jsp").forward(request, response);
    }
}
