package service;

import model.Code;
import model.Order;
import model.Product;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    void addOrder(Order value);

    Optional<Order> getUserOrder(User value);

    void createOrder(User user, String email, String deliveryAddress, List<Product> products);

    Code sendConfirmationCode(String email, Order order);
}
