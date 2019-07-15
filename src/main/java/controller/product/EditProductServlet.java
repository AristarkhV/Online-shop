package controller.product;

import dao.impl.ProductDaoImpl;
import factory.ProductServiceFactory;
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

@WebServlet("edit/product")
public class EditProductServlet extends HttpServlet {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(ProductDaoImpl.class);
    private ProductService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("productId");
        if (id != null) {
            Optional<Product> editProduct = productService.getProductById(currentID(id).get());
            request.setAttribute("productName", editProduct.get().getName());
            request.setAttribute("productPrice", editProduct.get().getPrice());
            request.setAttribute("productDescription", editProduct.get().getDescription());
            request.setAttribute("productId", editProduct.get().getProductId());
            request.getRequestDispatcher("/addProduct.jsp").forward(request, response);
        } else {
            LOGGER.info("Product is not found");
            response.sendRedirect("/addProduct.jsp");
        }
    }

    private Optional<Long> currentID(String id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.of(Long.parseLong(id));
    }
}
