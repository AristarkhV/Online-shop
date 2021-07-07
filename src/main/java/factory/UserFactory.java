package factory;

import dao.UserDao;
import dao.impl.UserDaoImpl;

public class UserFactory {

    private static UserDao instance;

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    private UserFactory() {
    }
}
