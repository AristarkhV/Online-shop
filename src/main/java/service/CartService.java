package service;

import model.Cart;
import model.Product;
import model.User;

import java.util.ArrayList;
import java.util.Optional;

public interface CartService {

    void addCart(Cart cart);

    Optional<Cart> getCart(User value);

    void addProductToCart(Cart cart, Product product);

    int cartSize(User value);
}
