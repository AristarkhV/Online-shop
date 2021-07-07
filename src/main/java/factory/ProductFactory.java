package factory;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;

public class ProductFactory {

    private static ProductDao instance;

    public static ProductDao getInstance() {
        if (instance == null) {
            instance = new ProductDaoImpl();
        }
        return instance;
    }

    private ProductFactory() {
    }
}
