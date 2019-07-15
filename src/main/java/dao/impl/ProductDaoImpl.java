package dao.impl;

import dao.ProductDao;
import db.Storage;
import model.Product;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {

    private static final Logger LOGGER = Logger.getLogger(ProductDaoImpl.class);

    @Override
    public List<Product> getAll() {
        return Storage.products;
    }

    @Override
    public void addProduct(Product product) {
        Storage.products.add(product);
        LOGGER.info(product + " is added to storage \n");
    }

    @Override
    public void deleteProduct(Product product) {
        Storage.products.remove(product);
        LOGGER.info(product + " is added to storage \n");
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return Storage.products.stream()
                .filter(product -> product.getProductId().equals(id))
                .findFirst();
    }


    @Override
    public void editProduct(Product editProduct, String name, Double price, String description) {
        Storage.products.stream().filter(product -> {
            if (product.getProductId().equals(editProduct.getProductId())) {
                product.setName(name);
                product.setPrice(price);
                product.setDescription(description);
                return true;
            }
            return false;
        }).findFirst();
        LOGGER.info(editProduct + " is updated \n");
    }
}
