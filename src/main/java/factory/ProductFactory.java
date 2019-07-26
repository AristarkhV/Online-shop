package factory;

import dao.daoJDBC.ProductDao;
import dao.daoJDBC.impl.ProductDaoImpl;

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
