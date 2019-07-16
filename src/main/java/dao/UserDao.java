package dao;

import model.Role;
import model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> getAll();

    void addUser(User user);

    void deleteUser(User deleteUser);

    void editUser(User editUser, String email, String password, Role role);

    Optional<User> getUserByEmail(String email);

    public Optional<User> getUserById(Long id);
}
