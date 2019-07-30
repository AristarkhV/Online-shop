package dao;

import model.Cart;
import model.Product;
import model.User;

import java.util.Optional;

public interface CartDao {

    void addCart(Cart cart);

    Optional<Cart> getCart(User value);

    void addProductToCart(Cart cart, Product product);

    void cleanCart(Cart cart);
}
