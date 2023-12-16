package org.example.web.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.List;


public class UserRoleDTO {
    private String name;
    private List<UserEntityDTO> users;

    public UserRoleDTO(String name, List<UserEntityDTO> users) {
        this.name = name;
        this.users = users;
    }


    public UserRoleDTO() {
    }

    @NotNull(message = "Choose the roleType")
    public String getRoleType() {
        return name;
    }

    public void setRoleType(String roleType) {
        this.name = name;
    }

    public List<UserEntityDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntityDTO> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserRoleDTO{" +
                ", roleType='" + name + '\'' +
                '}';
    }
}
