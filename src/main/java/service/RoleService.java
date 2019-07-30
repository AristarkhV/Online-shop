package service;

import model.Role;

import java.util.Optional;

public interface RoleService {

    void addRole(Role role);

    Optional<Role> getRoleByName(String value);
}
