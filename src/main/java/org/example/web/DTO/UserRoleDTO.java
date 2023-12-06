package org.example.web.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.List;


public class UserRoleDTO {
    private String roleType;
    private List<UserEntityDTO> users;

    public UserRoleDTO(String roleType, List<UserEntityDTO> users) {
        this.roleType = roleType;
        this.users = users;
    }


    public UserRoleDTO() {
    }

    @NotNull(message = "Choose the roleType")
    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
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
                ", roleType='" + roleType + '\'' +
                '}';
    }
}
