package model;

import java.util.ArrayList;

public class Cart {

    private ArrayList<Product> userProducts;

    private Long userId;
    private int size;

    public Cart(Long userId) {
        this.userId = userId;
        this.userProducts = new ArrayList<>();
    }

    public Long getUserId() {
        return userId;
    }

    public ArrayList<Product> getUserProducts() {
        return userProducts;
    }
}
