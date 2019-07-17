package service;

import model.Order;
import model.Product;
import model.Role;
import model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();

    void addUser(User user);

    void deleteUser(User deleteUser);

    void editUser(User editUser, String email, String password, Role role);

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    void addProductToCart(Product product, User currentUser);

    void addOrder(User currentUser, Order order);
}
