package service;

import model.Code;
import model.Order;
import model.Product;
import model.User;

import java.util.ArrayList;

public interface OrderService {

    void addOrder(Order order);

    void createNewOrder(User user, String deliveryAddress, ArrayList<Product> products);

    Code sendConfirmationCode(String email);
}
