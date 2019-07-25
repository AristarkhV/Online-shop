package dao.daoJDBC;

import model.Role;

import java.util.Optional;

public interface RoleDao {

    Optional<Role> getRoleByName(String value);
}
