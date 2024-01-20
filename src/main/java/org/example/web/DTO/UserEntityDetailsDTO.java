package org.example.web.DTO;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.web.models.UserRole;

public class UserEntityDetailsDTO {
    private String username;
    private String firstName;
    private String lastName;

    private UserRole name;



    public UserEntityDetailsDTO(String username, String firstName, String lastName, UserRole name) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.name = name;

    }

    public UserEntityDetailsDTO() {

    }

    @NotEmpty(message = "Username cannot be null or empty")
    @Size(min = 2, message = "Username should be at least 2 characters long")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty(message = "First name cannot be null or empty")
    @Size(min = 2, message = "First name should be at least 2 characters long")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotEmpty(message = "Last name cannot be null or empty")
    @Size(min = 2, message = "Last name should be at least 2 characters long")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull(message = "Choose the userRole")
    public UserRole getUserRole() {
        return name;
    }

    public void setUserRole(UserRole name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserEntityDTO{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", name=" + name +
                '}';
    }
}

