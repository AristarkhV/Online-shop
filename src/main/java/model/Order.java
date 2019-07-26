package model;

import java.util.ArrayList;
import java.util.Objects;

public class Order {

    private Long orderID;
    private User user;
    private String email;
    private String deliveryAddress;
    private ArrayList<Product> orderProducts;

    public Order(User user, String email, String deliveryAddress, ArrayList<Product> orderProducts) {
        this.orderID = IdCreator.idCreator();
        this.email = email;
        this.user = user;
        this.orderProducts = orderProducts;
        this.deliveryAddress = deliveryAddress;
    }

    public Order(Long orderID, User user, String email, String deliveryAddress, ArrayList<Product> orderProducts) {
        this.orderID = orderID;
        this.email = email;
        this.user = user;
        this.orderProducts = orderProducts;
        this.deliveryAddress = deliveryAddress;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public ArrayList<Product> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(ArrayList<Product> orderProducts) {
        this.orderProducts = orderProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(orderID, order.orderID) &&
                Objects.equals(user, order.user) &&
                Objects.equals(email, order.email) &&
                Objects.equals(deliveryAddress, order.deliveryAddress) &&
                Objects.equals(orderProducts, order.orderProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, user, email, deliveryAddress, orderProducts);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", user=" + user +
                ", email='" + email + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", orderProducts=" + orderProducts +
                '}';
    }
}
