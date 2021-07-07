package factory;

import dao.CodeDao;
import dao.impl.CodeDaoImpl;

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
