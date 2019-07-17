package service.serviceImpl;

import dao.OrderDao;
import factory.OrderFactory;
import model.Order;
import service.OrderService;

public class OrderServiceImpl implements OrderService {

    private static OrderDao orderDao = OrderFactory.getInstance();

    @Override
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }
}
