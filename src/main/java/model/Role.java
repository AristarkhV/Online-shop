package model;

import util.IdCreator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "role")
public class Role {

    private Long roleID;
    private String role;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
        this.roleID = IdCreator.idCreator();
    }

    public Role(Long roleID, String role) {
        this.role = role;
        this.roleID = roleID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getRoleID() {
        return roleID;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role='" + role + '\'' +
                ", roleID=" + roleID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role1 = (Role) o;
        return Objects.equals(role, role1.role) &&
                Objects.equals(roleID, role1.roleID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, roleID);
    }

    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
