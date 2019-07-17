package service.serviceImpl;

import dao.UserDao;
import db.Storage;
import factory.UserFactory;
import model.Order;
import model.Product;
import model.Role;
import model.User;
import service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static UserDao userDao = UserFactory.getInstance();

    @Override
    public void deleteUser(User deleteUser) {
        userDao.deleteUser(deleteUser);
    }

    @Override
    public void editUser(User editUser, String email, String password, Role role) {
        userDao.editUser(editUser, email, password, role);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void addProductToCart(Product product, User currentUser){
        userDao.addProductToCart(product, currentUser);
    }

    @Override
    public void addOrder(User currentUser, Order order) {
        userDao.addOrder(currentUser, order);
    }
}
