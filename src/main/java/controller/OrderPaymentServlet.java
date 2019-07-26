package controller;

import factory.service.CodeServiceFatory;
import model.Code;
import model.Order;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        Optional<Code> code = codeService.getCode(email);
        String confirm = request.getParameter("confCode");
        if (code.isPresent() && code.get().getCode().equals(confirm)) {
            request.setAttribute("message", "Don't worry go to sleep :)");
        } else {
            request.setAttribute("message", "Invalid code, try again");
            request.setAttribute("email", email);
        }
        request.getRequestDispatcher("/orderPayment.jsp").forward(request, response);
    }
}
