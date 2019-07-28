package service.serviceImpl;

import dao.ProductDao;
import factory.ProductFactory;
import model.Product;
import service.ProductService;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private static ProductDao productDao = ProductFactory.getInstance();

    public ProductServiceImpl() {
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public void addProduct(Product value) {
        productDao.addProduct(value);
    }

    @Override
    public void deleteProduct(Product value) {
        productDao.deleteProduct(value);
    }

    @Override
    public void editProduct(Product value) {
        productDao.editProduct(value);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productDao.getProductById(id);
    }
}
