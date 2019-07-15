package service;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAll();

    void addProduct(Product product);

    void deleteProduct(Product deleteProduct);

    void editProduct(Product editProduct, String name, Double price, String description);

    Optional<Product> getProductById(Long id);
}
