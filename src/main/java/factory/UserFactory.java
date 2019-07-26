package factory;

import dao.daoJDBC.UserDao;
import dao.daoJDBC.impl.UserDaoImpl;

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
