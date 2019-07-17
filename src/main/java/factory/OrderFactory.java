package factory;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;

public class OrderFactory {
    private static OrderDao instance;

    public static synchronized OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDaoImpl();
        }
        return instance;
    }

    private OrderFactory() {
    }
}
