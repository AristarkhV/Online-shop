package controller;

import factory.ProductServiceFactory;
import model.User;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/store")
public class StoreServlet extends HttpServlet {

    private ProductService productService = ProductServiceFactory.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<User> userFromSession = Optional.ofNullable((User) request.getSession().getAttribute("user"));
        int counter = userFromSession.get().getUserCart().getUserProducts().size();
        request.setAttribute("counter", counter);
        request.setAttribute("products", productService.getAll());
        request.getRequestDispatcher("/store.jsp").forward(request, response);
    }
}
