package controller;

import factory.service.CartServiceFactory;
import factory.service.ProductServiceFactory;
import service.CartService;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/store")
public class StoreServlet extends HttpServlet {

    private static final CartService cartService = CartServiceFactory.getInstance();

    private ProductService productService = ProductServiceFactory.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int cardsCounter = cartService.cartSize();
        request.setAttribute("cardsCounter", cardsCounter);
        request.setAttribute("products", productService.getAll());
        request.getRequestDispatcher("/store.jsp").forward(request, response);
    }
}
