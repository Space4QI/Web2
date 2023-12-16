package org.example.web.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@Entity
public class UserEntity extends TimeEntity {

    @OneToMany(mappedBy = "seller", targetEntity = Offer.class, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Offer> offers;
    private boolean isActive;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String imageURL;

    private String email;

    private int age;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "userRole_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserRole name;

    public UserEntity(String id, LocalDate created, LocalDate modified, List<Offer> offers, boolean isActive, String username, String password, String firstName, String lastName, String imageURL, String email, int age, UserRole name) {
        super(id, created, modified);
        this.offers = offers;
        this.isActive = isActive;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageURL = imageURL;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public UserEntity() {

    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public UserRole getUserRole() {
        return name;
    }

    public void setUserRole(UserRole name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "seller", cascade = CascadeType.MERGE)
    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "offers=" + offers +
                ", isActive=" + isActive +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", userRole=" + name +
                '}';
    }
}