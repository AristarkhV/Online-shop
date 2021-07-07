package model;

import java.util.ArrayList;
import java.util.Objects;

public class Cart {

    private Long cartID;
    private User user;
    private ArrayList<Product> products;

    public Cart(Long codeID, ArrayList<Product> products, User user) {
        this.cartID = codeID;
        this.user = user;
        this.products = products;
    }

    public Cart(ArrayList<Product> products, User user) {
        this.cartID = IdCreator.idCreator();
        this.user = user;
        this.products = products;
    }

    public Long getCartID() {
        return cartID;
    }

    public void setCartID(Long cartID) {
        this.cartID = cartID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return Objects.equals(cartID, cart.cartID) &&
                Objects.equals(user, cart.user) &&
                Objects.equals(products, cart.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartID, user, products);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartID=" + cartID +
                ", user=" + user +
                ", products=" + products +
                '}';
    }
}
