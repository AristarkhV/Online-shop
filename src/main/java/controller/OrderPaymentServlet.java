package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order/payment")
public class OrderPaymentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
}
