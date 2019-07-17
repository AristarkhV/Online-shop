package service;

import model.Code;
import model.Product;
import model.User;

import java.util.ArrayList;
import java.util.Optional;

public interface CartService {

    void addCartProduct(Product product);

    Optional<ArrayList<Product>> getCartProducts();

    int cartSize();

    void createNewOrder(User user, String deliveryAddress, ArrayList<Product> products);

    Code sendConfirmationCode(String email);
}
