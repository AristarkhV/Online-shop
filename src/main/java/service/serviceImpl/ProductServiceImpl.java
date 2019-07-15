package service.serviceImpl;

import dao.ProductDao;
import factory.ProductFactory;
import model.Product;
import service.ProductService;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    public static ProductDao productDao = ProductFactory.getInstance();

    public ProductServiceImpl() {
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override
    public void deleteProduct(Product deleteProduct) {
        productDao.deleteProduct(deleteProduct);
    }

    @Override
    public void editProduct(Product editProduct, String name, Double price, String description) {
        productDao.editProduct(editProduct, name, price, description);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productDao.getProductById(id);
    }
}
