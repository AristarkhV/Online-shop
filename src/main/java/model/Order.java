package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private User user;
    private Long orderID;
    private String deliveryAddress;
    private List<Product> orderProducts = new ArrayList<>();

    public Order(User user, String deliveryAddress, ArrayList orderProducts) {
        this.orderID = IdCreator.idCreator();
        this.user = user;
        this.orderProducts = orderProducts;
        this.deliveryAddress = deliveryAddress;
    }

    public Long getOrderID() {
        return orderID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", orderID=" + orderID +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(user, order.user) &&
                Objects.equals(orderID, order.orderID) &&
                Objects.equals(deliveryAddress, order.deliveryAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, orderID, deliveryAddress);
    }
}
