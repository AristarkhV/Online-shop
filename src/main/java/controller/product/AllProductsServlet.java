package controller.product;

import factory.ProductServiceFactory;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/products")
public class AllProductsServlet extends HttpServlet {

    private ProductService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("products", productService.getAll());
        request.getRequestDispatcher("products.jsp").forward(request, response);
    }
}
