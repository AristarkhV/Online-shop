package dao.impl;

import dao.OrderDao;
import db.Storage;
import model.Order;

import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    @Override
    public void addOrder(Order order) {
        Storage.orders.add(order);
    }
}
