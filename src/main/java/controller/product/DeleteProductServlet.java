package controller.product;

import dao.impl.ProductDaoImpl;
import factory.service.ProductServiceFactory;
import model.Product;
import org.apache.log4j.Logger;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/delete/product")
public class DeleteProductServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ProductDaoImpl.class);
    private ProductService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long productID = Long.parseLong(request.getParameter("productID"));
        Optional<Product> deleteProduct = productService.getProductById(productID);
        if (deleteProduct.isPresent()) {
            LOGGER.info("Try to delete  " + deleteProduct + " product ... \n");
            productService.deleteProduct(deleteProduct.get());
        } else {
            request.setAttribute("message", "Can't");
        }
        response.sendRedirect("/products");
    }
}
