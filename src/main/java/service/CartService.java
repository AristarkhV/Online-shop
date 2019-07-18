package service;

import model.Product;

import java.util.ArrayList;
import java.util.Optional;

public interface CartService {

    void addCartProduct(Product product);

    Optional<ArrayList<Product>> getCartProducts();

    int cartSize();
}
