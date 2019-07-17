package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private Long userID;
    private List<Order> orderList;
    private Cart userCart;
    private Role role;
    private String email;
    private String password;

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.userID = IdCreator.idCreator();
        this.userCart = new Cart(userID);
        this.orderList = new ArrayList<>();
    }

    public Cart getUserCart() {
        return userCart;
    }

    public void setUserCart(Cart userCart) {
        this.userCart = userCart;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(userID, user.userID) &&
                Objects.equals(orderList, user.orderList) &&
                Objects.equals(userCart, user.userCart) &&
                Objects.equals(role, user.role) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, orderList, userCart, role, email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", orderList=" + orderList +
                ", userCart=" + userCart +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
