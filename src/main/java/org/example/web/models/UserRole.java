package org.example.web.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class UserRole extends BaseEntity {
    @OneToMany(mappedBy = "userRole", targetEntity = UserEntity.class, cascade = {CascadeType.MERGE})
    private List<UserEntity> users;

    @Enumerated(EnumType.STRING)
    public RoleType roleType;

    public enum RoleType {
        USER(0),
        ADMIN(10);

        final int num;

        RoleType(int num) {
            this.num = num;
        }
    }

    public UserRole(String id, List<UserEntity> users, RoleType roleType) {
        super(id);
        this.users = users;
        this.roleType = roleType;
    }

    public UserRole() {

    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "users=" + users +
                ", roleType=" + roleType +
                '}';
    }
}