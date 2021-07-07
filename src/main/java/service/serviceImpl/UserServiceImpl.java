package service.serviceImpl;

import dao.UserDao;
import factory.UserFactory;
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
    public void editUser(User value) {
        userDao.editUser(value);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void addUser(User value) {
        userDao.addUser(value);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userDao.getUserById(id);
    }
}
