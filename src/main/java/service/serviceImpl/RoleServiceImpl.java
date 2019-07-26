package service.serviceImpl;

import dao.daoJDBC.RoleDao;
import factory.RoleFactory;
import model.Role;
import service.RoleService;

import java.util.Optional;

public class RoleServiceImpl implements RoleService {

    private static RoleDao roleDao = RoleFactory.getInstance();

    @Override
    public Optional<Role> getRoleByName(String value) {
        return roleDao.getRoleByName(value);
    }
}
