package org.example.web.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserEntityDTO {
    private String username;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private String imageUrl;

    private String password;

    private String userRole;

    public UserEntityDTO(String username, String firstName, String lastName, boolean isActive, String imageUrl, String password, String userRole) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.imageUrl = imageUrl;
        this.password = password;
        this.userRole = userRole;
    }

    public UserEntityDTO() {

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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @NotEmpty(message = "Choose the image")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty(message = "Choose the userRole")
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserEntityDTO{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isActive=" + isActive +
                ", imageUrl='" + imageUrl + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
