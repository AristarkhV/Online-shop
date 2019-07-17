package dao.impl;

import dao.UserDao;
import db.Storage;
import model.Role;
import model.User;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public void addUser(User user) {
        Storage.users.add(user);
        LOGGER.info(user + " is added to storage \n");
    }

    @Override
    public void deleteUser(User deleteUser) {
        Storage.users.remove(deleteUser);
        LOGGER.info(deleteUser + " is deleted from storage \n");
    }

    @Override
    public void editUser(User editUser, String email, String password, Role role) {
        Storage.users.stream().filter(user -> {
            if (user.getUserID().equals(editUser.getUserID())) {
                user.setPassword(password);
                user.setEmail(email);
                user.setRole(role);
                return true;
            }
            return false;
        }).findFirst();
        LOGGER.info(editUser + " is edited \n");
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Storage.users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findAny();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Storage.users.stream()
                .filter(user -> user.getUserID().equals(id))
                .findFirst();
    }
}
