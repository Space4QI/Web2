package org.example.web.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class UserRole extends BaseEntity {
    @OneToMany(mappedBy = "name", targetEntity = UserEntity.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<UserEntity> users;

    private String name;

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

    public UserRole(String id, List<UserEntity> users, String name, RoleType roleType) {
        super(id);
        this.users = users;
        this.name = name;
        this.roleType = roleType;
    }

    public UserRole() {

    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role", cascade = CascadeType.REMOVE)
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "users=" + users +
                ", roleType=" + roleType +
                '}';
    }
}