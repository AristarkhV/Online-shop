package factory;

import dao.daoJDBC.CodeDao;
import dao.daoJDBC.impl.CodeDaoImpl;

public class CodeFactory {

    private static CodeDao instance;

    public static CodeDao getInstance() {
        if (instance == null) {
            instance = new CodeDaoImpl();
        }
        return instance;
    }

    private CodeFactory() {
    }
}
