package factory;

import dao.RoleDao;
import dao.impl.RoleDaoImpl;

public class RoleFactory {

    private static RoleDao instance;

    public static RoleDao getInstance() {
        if (instance == null) {
            instance = new RoleDaoImpl();
        }
        return instance;
    }

    private RoleFactory() {
    }
}
