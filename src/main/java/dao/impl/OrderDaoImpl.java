package dao.impl;

import dao.OrderDao;
import db.Storage;
import model.Order;

public class OrderDaoImpl implements OrderDao {
    @Override
    public void addOrder(Order order) {
        Storage.orders.add(order);
    }
}
