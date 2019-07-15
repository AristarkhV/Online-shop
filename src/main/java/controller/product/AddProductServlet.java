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

@WebServlet(value = "/addProduct")
public class AddProductServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ProductDaoImpl.class);
    private ProductService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("productId");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Double price = 0.0;
        if (!(request.getParameter("price").equals(""))) {
            price = Double.parseDouble(request.getParameter("price"));
        }
        if (name.equals("") || description.equals("") || request.getParameter("price").equals("")) {
            request.setAttribute("error", "Empty fields :(");
            request.getRequestDispatcher("/addProduct.jsp").forward(request, response);
        } else {
            if (!id.equals("") && id != null) {
                Optional<Product> editProduct = productService.getProductById(currentID(id).get());
                if (editProduct.isPresent()) {
                    LOGGER.info("Try to edit  " + editProduct.get() + "... \n");
                    productService.editProduct(editProduct.get(), name, price, description);
                } else {
                    LOGGER.info("Product not found  \n" + id);
                }
            } else {
                Product product = new Product(name, description, price);
                LOGGER.info("Try to add  " + product + "... \n");
                productService.addProduct(product);
            }
            request.setAttribute("done", "Done :)");
            request.getRequestDispatcher("/addProduct.jsp").forward(request, response);
        }
        request.setAttribute("products", productService.getAll());
        request.getRequestDispatcher("/products.jsp").forward(request, response);
        request.getRequestDispatcher("/addProduct.jsp").forward(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/addProduct.jsp");
    }

    private Optional<Long> currentID(String id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.of(Long.parseLong(id));
    }
}
