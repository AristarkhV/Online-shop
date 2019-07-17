package dao;

import model.Role;

import java.util.Optional;

public interface RoleDao {

    Optional<Role> getRoleByID(Long roleID);
}
