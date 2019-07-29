package service;

import model.Code;
import model.Order;
import model.Product;
import model.User;

import java.util.ArrayList;
import java.util.Optional;

public interface OrderService {

    void addOrder(Order value);

    Optional<Order> getUserOrder(User value);

    void createOrder(User user, String email, String deliveryAddress, ArrayList<Product> products);

    Code sendConfirmationCode(String email, Long orderID);
}
