package model;

import java.util.Objects;

public class Order {
    private Long userID;
    private Long orderID;
    private String deliveryAddress;

    public Order(Long userID) {
        this.userID = userID;
        this.orderID = IdCreator.idCreator();
    }

    public Long getOrderID() {
        return orderID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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
                "userID=" + userID +
                ", orderID=" + orderID +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return userID.equals(order.userID) &&
                orderID.equals(order.orderID) &&
                deliveryAddress.equals(order.deliveryAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, orderID, deliveryAddress);
    }
}
