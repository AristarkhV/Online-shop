package dao.impl;

import dao.RoleDao;
import db.Storage;
import model.Role;

import java.util.Optional;

public class RoleDaoImpl implements RoleDao {
    @Override
    public Optional<Role> getRoleByID(Long roleID) {
        return Storage.roles.stream().filter(role -> role.getRoleID().equals(roleID))
                .findFirst();
    }
}
