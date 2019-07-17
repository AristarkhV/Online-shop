package controller;

import factory.MailServiceFactory;
import model.Code;
import model.User;
import service.MailService;
import util.RandomHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/order/payment")
public class OrderPaymentServlet extends HttpServlet {

    private static final MailService mailService = MailServiceFactory.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = (String) request.getSession().getAttribute("code");
        String confirm = request.getParameter("confCode");
        if (code.equals(confirm)) {
            request.setAttribute("message", "Don't worry :)");
        } else {
            request.setAttribute("message", "Invalid code, try again");
        }
        request.getRequestDispatcher("/orderPayment.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sendCnfirmCode = RandomHelper.generateCode();
        HttpSession session = request.getSession();
        User userFromSession = (User) session.getAttribute("user");
        Code code = new Code(sendCnfirmCode);
        new Thread(() -> mailService.sendConfirmCode(code)).start();
        session.setAttribute("code", code.getCode());
        response.sendRedirect("/orderPayment.jsp");
    }
}
