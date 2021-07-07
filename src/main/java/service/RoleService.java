package service;

import model.Role;

import java.util.Optional;

public interface RoleService {

    Optional<Role> getRoleByName(String value);
}
