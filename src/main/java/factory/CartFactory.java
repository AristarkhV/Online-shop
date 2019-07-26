package factory;

import dao.daoJDBC.CartDao;
import dao.daoJDBC.impl.CartDaoImpl;

public class CartFactory {

    private static CartDao instance;

    public static CartDao getInstance() {
        if (instance == null) {
            instance = new CartDaoImpl();
        }
        return instance;
    }

    private CartFactory() {
    }
}
